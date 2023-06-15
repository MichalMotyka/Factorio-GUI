package org.example.service;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImageData {
    private String name;
    private String id;
    private File image;

    public ImageData(String name, String id, File image) {
        this.name = name;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
