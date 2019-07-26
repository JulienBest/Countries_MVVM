package tubs.musictinder.country_mvvm_own.di;

import dagger.Component;
import tubs.musictinder.country_mvvm_own.model.CountriesService;

@Component(modules = ApiModule.class)
public interface APIComponent {
    void inject(CountriesService service);
}
