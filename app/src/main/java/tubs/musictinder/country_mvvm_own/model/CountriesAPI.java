package tubs.musictinder.country_mvvm_own.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountriesAPI {
    @GET("DevTides/countries/master/countriesV2.json")
    Single<List<Country>> getCountries();
}
