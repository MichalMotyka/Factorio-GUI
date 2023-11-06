package org.example.model;

public class Order {
    private long id;
    private String name;
    private String surname;
    private String company;
    private String pesel;
    private String nip;
    private String adress;
    private String city;
    private String postCode;
    private String mail;
    private String phone;
    private String description;
    private Deliver deliver;
    private boolean COD;
    private boolean isFacture;

    public Order(long id, String name, String surname, String company, String pesel, String nip, String adress, String city, String postCode, String mail, String phone, String description, Deliver deliver, boolean COD, boolean isFacture) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.company = company;
        this.pesel = pesel;
        this.nip = nip;
        this.adress = adress;
        this.city = city;
        this.postCode = postCode;
        this.mail = mail;
        this.phone = phone;
        this.description = description;
        this.deliver = deliver;
        this.COD = COD;
        this.isFacture = isFacture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Deliver getDeliver() {
        return deliver;
    }

    public void setDeliver(Deliver deliver) {
        this.deliver = deliver;
    }

    public boolean isCOD() {
        return COD;
    }

    public void setCOD(boolean COD) {
        this.COD = COD;
    }

    public boolean isFacture() {
        return isFacture;
    }

    public void setFacture(boolean facture) {
        isFacture = facture;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", company='" + company + '\'' +
                ", pesel='" + pesel + '\'' +
                ", nip='" + nip + '\'' +
                ", adress='" + adress + '\'' +
                ", city='" + city + '\'' +
                ", postCode='" + postCode + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", deliver=" + deliver +
                ", COD=" + COD +
                ", isFacture=" + isFacture +
                '}';
    }
}
