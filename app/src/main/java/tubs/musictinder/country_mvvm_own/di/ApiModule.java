package tubs.musictinder.country_mvvm_own.di;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tubs.musictinder.country_mvvm_own.model.CountriesAPI;

@Module
class ApiModule {

    private final String BASE_URL = "https://raw.githubusercontent.com";

    @Provides
    CountriesAPI provideCountryApi() {
        Retrofit.Builder builder = new Retrofit.Builder();
        return builder
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //Makes the Country Objects observable
                .build()
                .create(CountriesAPI.class);
    }
}
