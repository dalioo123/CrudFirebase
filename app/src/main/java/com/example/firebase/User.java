package com.example.firebase;

public class User {

    private String id;
    private String produit;

    public User() {
    }

    public User(String id, String produit) {
        this.id = id;
        this.produit = produit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }
}
