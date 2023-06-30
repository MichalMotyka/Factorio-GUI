package org.example.service;

import com.google.gson.Gson;
import okhttp3.*;
import org.apache.http.client.utils.URIBuilder;
import org.example.model.Document;
import org.example.model.DocumentType;
import org.example.model.Status;
import org.example.repository.UserRepository;

import javax.swing.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class OrderService {

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new ErrorInterceptor())
            .build();
    PropertiesReader propertiesReader = PropertiesReader.getInstance();
    UserRepository userRepository = UserRepository.getInstance();

    public Document getExtendedDocument(String number){
        URI uri;

        try {
            uri = new URIBuilder(propertiesReader.getValue("api.factorio.document.extend.get")).addParameter("uid",number).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Request request = new Request.Builder()
                .url(uri.toString())
                .get()
                .header("Authorization", userRepository.getToken())
                .header("Refresh", userRepository.getToken())
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return gson.fromJson(Objects.requireNonNull(response.body()).string(), Document.class);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Wystąpił błąd: " + response.code());
                System.out.println("Wystąpił błąd: " + response.code());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Document[] getDocuments(String search, Status status, DocumentType documentType,String number) {
        URIBuilder uri;
        URI url;
        try {
            uri = new URIBuilder(propertiesReader.getValue("api.factorio.document.get"));
            if (search != null && !search.equals("")) {
                uri.addParameter("search", search);
            }
            if (status != null) {
                uri.addParameter("status", status.name());
            }
            if (documentType != null) {
                uri.addParameter("type", documentType.name());
            }
            if (number != null){
                uri.addParameter("id",number);
            }
            url = uri.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Request request = new Request.Builder()
                .url(url.toString())
                .get()
                .header("Authorization", userRepository.getToken())
                .header("Refresh", userRepository.getToken())
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return gson.fromJson(Objects.requireNonNull(response.body()).string(), Document[].class);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Wystąpił błąd: " + response.code());
                System.out.println("Wystąpił błąd: " + response.code());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Document createDocument(Document document) {
        Request request = new Request.Builder()
                .url(propertiesReader.getValue("api.factorio.document.create"))
                .post(RequestBody.create(MediaType.parse("application/json"), gson.toJson(document)))
                .header("Authorization", userRepository.getToken())
                .header("Refresh", userRepository.getToken())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return gson.fromJson(Objects.requireNonNull(response.body()).string(), Document.class);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Nie udało się utworzyć dokumentu. Wystąpił błąd: " + response.code());
                System.out.println("Wystąpił błąd: " + response.code());
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nie udało się nawiązać połączenia z serwerem");
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateStatus(String id) {
        String uri;
        try {
            uri = new URIBuilder(propertiesReader.getValue("api.factorio.document.status"))
                    .addParameter("id", id)
                    .build().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Request request = new Request.Builder()
                .url(uri)
                .patch(RequestBody.create(MediaType.parse("application/json"),""))
                .header("Authorization", userRepository.getToken())
                .header("Refresh", userRepository.getToken())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                JOptionPane.showMessageDialog(new JFrame(), "Pomyślnie zmieniono status dokumentu");
                return true;
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Nie udało się zmienić statusu dokumentu. Wystąpił błąd: " + response.code()+". Sprawdź czy jest wystaczająca ilośc towaru w magazynie");
                System.out.println("Wystąpił błąd: " + response.code());
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nie udało się nawiązać połączenia z serwerem");
            e.printStackTrace();
            return false;
        }
    }
}
