package com.example.covid19;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

public class Sudan {
    @SerializedName("Country")
    private String Country;
    @SerializedName("Confirmed")
    private Integer Confirmed;
    @SerializedName("Deaths")
    private Integer Deaths;
    @SerializedName("Recovered")
    private Integer Recovered;
    @SerializedName("Active")
    private Integer Active;

    public Sudan(String country, Integer confirmed, Integer deaths, Integer recovered, Integer active) {
        Country = country;
        Confirmed = confirmed;
        Deaths = deaths;
        Recovered = recovered;
        Active = active;
    }

    public String getCountry() {
        return Country;
    }

    public Integer getConfirmed() {
        return Confirmed;
    }

    public Integer getDeaths() {
        return Deaths;
    }

    public Integer getRecovered() {
        return Recovered;
    }

    public Integer getActive() {
        return Active;
    }
}
