package tubs.musictinder.country_mvvm_own;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import tubs.musictinder.country_mvvm_own.model.CountriesService;
import tubs.musictinder.country_mvvm_own.model.Country;
import tubs.musictinder.country_mvvm_own.viewmodel.ListViewModel;

public class ListViewModelTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    public CountriesService countriesService;

    @InjectMocks
    public ListViewModel listViewModel;

    private Single<List<Country>> testSingle = null;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setupRXSchedulers() {
        {
            Scheduler immediate = new Scheduler() {
                @Override
                public Disposable scheduleDirect(Runnable run, long delay, TimeUnit unit) {
                    return super.scheduleDirect(run, 0, unit);
                }

                @Override
                public Worker createWorker() {
                    return new ExecutorScheduler.ExecutorWorker(Runnable::run);
                }
            };
            RxJavaPlugins.setInitIoSchedulerHandler(schedulerCallable -> immediate);
            RxJavaPlugins.setComputationSchedulerHandler(scheduler -> immediate);
            RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
            RxJavaPlugins.setInitSingleSchedulerHandler(schedulerCallable -> immediate);
            RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);
        }
    }

    @Test
    public void getCountriesSuccess() {
        Country country = new Country("Germany", "Berlin", "www.google.de");
        ArrayList<Country> countryList = new ArrayList<>();
        countryList.add(country);

        testSingle = Single.just(countryList);
        Mockito.when(countriesService.getCountries()).thenReturn(testSingle);
        listViewModel.refresh();

        Assert.assertEquals(1, Objects.requireNonNull(listViewModel.getCountries().getValue()).size());
        Assert.assertEquals(false, listViewModel.getCountryLoadError().getValue());
        Assert.assertEquals(false, listViewModel.getLoading().getValue());
    }

    @Test
    public void getCountriesFailure() {
        testSingle = Single.error(new Throwable());

        Mockito.when(countriesService.getCountries()).thenReturn(testSingle);
        listViewModel.refresh();
        Assert.assertEquals(true, listViewModel.getCountryLoadError().getValue());
        Assert.assertEquals(false, listViewModel.getLoading().getValue());
    }
}
