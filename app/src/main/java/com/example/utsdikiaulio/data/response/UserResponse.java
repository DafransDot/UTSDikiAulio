package com.example.utsdikiaulio.data.response;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("login")
    private String username;

    private String name;

    @SerializedName("avatar_url")
    private String foto;

    private String bio;

    private String location;

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getfoto() {
        return foto;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }
}
