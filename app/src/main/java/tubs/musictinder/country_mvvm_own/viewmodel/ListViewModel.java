package tubs.musictinder.country_mvvm_own.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import tubs.musictinder.country_mvvm_own.model.CountriesService;
import tubs.musictinder.country_mvvm_own.model.Country;

public class ListViewModel extends ViewModel implements tubs.musictinder.country_mvvm_own.viewmodel.ViewModel {
    private MutableLiveData<List<Country>> countries = new MutableLiveData<>(); //all the countrie objects
    private MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>(); //If there was an error loading the data
    private MutableLiveData<Boolean> loading = new MutableLiveData<>(); //Whether the view model is loading the data

    private CountriesService countriesService = new CountriesService();
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void refresh() {
        fetchCountries();
    }

    private void fetchCountries() {

        loading.setValue(true);
        disposable.add(countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())  //does the backend call on the backend
                .observeOn(AndroidSchedulers.mainThread())  //observes changes on the main thread
                .subscribeWith(new DisposableSingleObserver<List<Country>>() {  //What happens when the information is received
                    @Override
                    public void onSuccess(List<Country> value) {
                        countries.setValue(value);
                        countryLoadError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        countryLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
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
