package tubs.musictinder.country_mvvm_own.di;

import dagger.Component;
import tubs.musictinder.country_mvvm_own.viewmodel.ListViewModel;

@Component (modules = CountryServiceModule.class)
public interface CountryServiceComponent {
    void inject(ListViewModel listViewModel);
}
