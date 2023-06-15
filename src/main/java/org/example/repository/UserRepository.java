package org.example.repository;

public class UserRepository {
    private static UserRepository userRepository;
    private String refteshToken;
    private String token;
    private String name;



    private UserRepository(){

    }
    public static UserRepository getInstance(){
        if (userRepository == null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getRefteshToken() {
        return refteshToken;
    }

    public void setRefteshToken(String refteshToken) {
        this.refteshToken = refteshToken;
    }
}
