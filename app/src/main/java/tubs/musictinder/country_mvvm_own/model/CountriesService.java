package tubs.musictinder.country_mvvm_own.model;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import tubs.musictinder.country_mvvm_own.di.DaggerAPIComponent;

public class CountriesService {

    @Inject
    CountriesAPI countriesAPI;

    public CountriesService() {
        DaggerAPIComponent.create().inject(this);
    }

    public Single<List<Country>> getCountries() {
        return countriesAPI.getCountries();
    }
}
