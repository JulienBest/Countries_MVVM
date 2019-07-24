package tubs.musictinder.country_mvvm_own.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.LinkedList;
import java.util.List;
import tubs.musictinder.country_mvvm_own.model.Country;

public class ListViewModel extends ViewModel {
    MutableLiveData<List<Country>> countries = new MutableLiveData<>(); //all the countrie objects
    MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>(); //If there was an error loading the data
    MutableLiveData<Boolean> loading = new MutableLiveData<>(); //Whether the view model is loading the data

    public void refresh() {
        fetchCountries();
    }

    private void fetchCountries() {
        List<Country> mockData = new LinkedList<>();
        mockData.add(new Country("Germany"));
        mockData.add(new Country("England"));
        mockData.add(new Country("South Africa"));
        mockData.add(new Country("Syria"));
        mockData.add(new Country("France"));
        mockData.add(new Country("Denmark"));
        mockData.add(new Country("Sweden"));
        mockData.add(new Country("Italy"));

        System.out.println("FETCHING");

        countryLoadError.setValue(false);
        loading.setValue(false);
        countries.setValue(mockData);
    }

    public MutableLiveData<List<Country>> getCountries() {
        return countries;
    }

    public MutableLiveData<Boolean> getCountryLoadError() {
        return countryLoadError;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }
}
