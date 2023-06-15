package org.example.service;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static PropertiesReader propertiesReader;
    private Properties properties;
    InputStream input = null;

    private PropertiesReader(){
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try{
            String filePath = "src/main/resources/application.properties"; // Zmień na właściwą ścieżkę do pliku properties
            input = new FileInputStream(filePath);
            properties.load(input);
        }catch (IOException e){
            JOptionPane.showMessageDialog(new JFrame(),"Nie udało się znaleźć pliku konfiguracyjnego, skontatkuj się z administratorem");
        }
    }

    public String getValue(String key){
        if (key.contains("api.factorio") && !key.equals("api.factorio.url")){
            return  properties.getProperty("api.factorio.url")+properties.getProperty(key);
        }
      return properties.getProperty(key);
    }


    public static PropertiesReader getInstance() {
        if (propertiesReader == null) {
            synchronized (PropertiesReader.class) {
                if (propertiesReader == null) {
                    propertiesReader = new PropertiesReader();
                }
            }
        }
        return propertiesReader;
    }



}
