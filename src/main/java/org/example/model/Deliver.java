package org.example.model;

public enum Deliver {
    PACZKOMAT("Paczkomat"),
    KURIER("Kurier");

    public final String value;
    Deliver(String value){
        this.value = value;
    }
}
