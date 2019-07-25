package tubs.musictinder.country_mvvm_own.viewmodel;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import tubs.musictinder.country_mvvm_own.model.Country;

public interface ViewModel {
    void refresh();

    MutableLiveData<List<Country>> getCountries();

    MutableLiveData<Boolean> getCountryLoadError();

    MutableLiveData<Boolean> getLoading();
}
