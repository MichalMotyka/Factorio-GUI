package org.example.service;

import okhttp3.*;
import org.example.model.UserLogin;
import org.example.repository.UserRepository;

public class LoginService {

    PropertiesReader propertiesReader = PropertiesReader.getInstance();
    UserRepository userRepository = UserRepository.getInstance();
    public boolean login(String login, String password){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ErrorInterceptor())
                .build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), new UserLogin(login,password).toString());

        Request request = new Request.Builder()
                .url(propertiesReader.getValue("api.factorio.user.auth"))
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                userRepository.setToken(response.header("Authorization"));
                return true;
            } else {
                System.out.println("Wystąpił błąd: " + response.code());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
