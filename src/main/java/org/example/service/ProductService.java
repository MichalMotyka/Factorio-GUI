package org.example.service;

import com.google.gson.Gson;
import okhttp3.*;
import org.example.model.Product;
import org.example.model.ProductRow;
import org.example.repository.UserRepository;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class ProductService {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new ErrorInterceptor())
            .build();
    UserRepository userRepository = UserRepository.getInstance();
    PropertiesReader propertiesReader = PropertiesReader.getInstance();
    public ProductRow[] getAllProducts(){
        Request request = new Request.Builder()
                .url(propertiesReader.getValue("api.factorio.product.getall"))
                .get()
                .header("Authorization",userRepository.getToken())
                .header("Refresh",userRepository.getToken())
                .build();
        return getProductRows(request);
    }
    public Product getProductById(long id){
        String uri = null;
        try {
            uri = new URIBuilder(propertiesReader.getValue("api.factorio.product.get")).addParameter("id", String.valueOf(id)).build().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Request request = new Request.Builder()
                .url(uri)
                .get()
                .header("Authorization",userRepository.getToken())
                .header("Refresh",userRepository.getToken())
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return gson.fromJson(Objects.requireNonNull(response.body()).string(), Product.class);
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


    public boolean createProduct(Product product){
        Request request = new Request.Builder()
                .url(propertiesReader.getValue("api.factorio.product.create"))
                .post(RequestBody.create(MediaType.parse("application/json"),gson.toJson(product)))
                .header("Authorization",userRepository.getToken())
                .header("Refresh",userRepository.getToken())
                .build();
        return validateResponse(request);
    }

    public boolean addImage(String path){
        if (path == null){
            JOptionPane.showMessageDialog(null,"Produkt musi mieć zdefiniowane przynajmniej jedno zjęcie");
        }
        File fileToSend = new File(path);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileToSend.getName(),
                        RequestBody.create(MediaType.parse("image/png"), fileToSend))
                .build();

        // Tworzenie żądania HTTP POST
        Request request = new Request.Builder()
                .url(propertiesReader.getValue("api.factorio.dropbox.add"))
                .post(requestBody)
                .build();
        // Wykonanie żądania
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("Plik został wysłany pomyślnie!");
            } else {
                JOptionPane.showMessageDialog(null,"Wystąpił błąd podczas wysyłania pliku.");
                return false;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Wystąpił błąd podczas wykonywania żądania: " + e.getMessage());
            return false;
        }

        return true;
    }

    public void addImages(List<ImageData> paths) {
        for (ImageData value : paths){
            if (!addImage(value.getImage().getAbsolutePath())) break;
        }
    }

    public boolean editProduct(Product product) {
        Request request = new Request.Builder()
                .url(propertiesReader.getValue("api.factorio.product.edit"))
                .put(RequestBody.create(MediaType.parse("application/json"),gson.toJson(product)))
                .header("Authorization",userRepository.getToken())
                .header("Refresh",userRepository.getToken())
                .build();
        return validateResponse(request);
    }

    private boolean validateResponse(Request request) {
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Nie udało się utworzyć produktu. Wystąpił błąd: " + response.code());
                System.out.println("Wystąpił błąd: " + response.code());
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nie udało się nawiązać połączenia z serwerem");
            e.printStackTrace();
            return false;
        }
    }

    public ProductRow[] searchProduct(String text, Boolean acitve) {
        String uri;
        try {
            if (acitve == null){
                uri = new URIBuilder(propertiesReader.getValue("api.factorio.product.get"))
                        .addParameter("search", String.valueOf(text))
                        .build().toString();
            }else {
                uri = new URIBuilder(propertiesReader.getValue("api.factorio.product.get"))
                        .addParameter("search", String.valueOf(text))
                        .addParameter("active",String.valueOf(acitve))
                        .build().toString();
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Request request = new Request.Builder()
                .url(uri)
                .get()
                .header("Authorization",userRepository.getToken())
                .header("Refresh",userRepository.getToken())
                .build();
        return getProductRows(request);
    }

    private ProductRow[] getProductRows(Request request) {
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return gson.fromJson(response.body().string(), ProductRow[].class);
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
}
