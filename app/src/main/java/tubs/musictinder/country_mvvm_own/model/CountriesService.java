package tubs.musictinder.country_mvvm_own.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {
    private final String BASE_URL = "https://raw.githubusercontent.com";
    private CountriesAPI countriesAPI;

    public CountriesService() {
        Retrofit.Builder builder = new Retrofit.Builder();
        countriesAPI = builder
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //Makes the Country Objects observable
                .build()
                .create(CountriesAPI.class);
    }

    public Single<List<Country>> getCountries() {
        return countriesAPI.getCountries();
    }
}
