package com.domain;

public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Address(String street, String city, String state, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public boolean validate() {return false;}

    //Getters and Setters
    //Street
    public String getStreet() {return street; }
    public void setStreet(String street) {this.street = street; }

    //City
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    //State
    public String getState() {return state;}
    public void setState(String state) {this.state = state; }

    //Postal Code
    public String getPostalCode() {return postalCode;}
    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}

    //Country
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
}
