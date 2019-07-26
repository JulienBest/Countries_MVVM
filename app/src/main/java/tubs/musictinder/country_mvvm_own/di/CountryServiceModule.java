package tubs.musictinder.country_mvvm_own.di;

import dagger.Module;
import dagger.Provides;
import tubs.musictinder.country_mvvm_own.model.CountriesService;

@Module
public class CountryServiceModule {

    @Provides
    public CountriesService provideCountryService() {
        return new CountriesService();
    }
}
