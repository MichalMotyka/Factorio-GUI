package org.example.model;

public class UserLogin {
    public UserLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String login;
    private String password;

    @Override
    public String toString() {
        return "{" +
                "\"login\" : \"" + login + "\""+
                ", \"password\":\"" + password + "\"" +
                '}';
    }
}
