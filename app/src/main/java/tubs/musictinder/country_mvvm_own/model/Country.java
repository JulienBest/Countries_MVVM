package tubs.musictinder.country_mvvm_own.model;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("name")
    private String countryName;
    @SerializedName("capital")
    private String capital;
    @SerializedName("flagPNG")
    private String flagUrl;

    public Country(String countryName, String capital, String flagUrl) {
        this.countryName = countryName;
        this.capital = capital;
        this.flagUrl = flagUrl;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlagUrl() {
        return flagUrl;
    }
}
