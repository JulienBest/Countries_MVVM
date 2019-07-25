package tubs.musictinder.country_mvvm_own.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import tubs.musictinder.country_mvvm_own.R;
import tubs.musictinder.country_mvvm_own.model.Country;
import tubs.musictinder.country_mvvm_own.viewmodel.ListViewModel;
import tubs.musictinder.country_mvvm_own.viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity {

    private ViewModel listViewModel;
    private CountryListAdapter countryListAdapter;
    private RecyclerView recyclerView;

    private TextView errorText;
    private ProgressBar loadingSpinner;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        listViewModel.refresh();

        countryListAdapter = new CountryListAdapter(new ArrayList<>()); //passes an empty list

        recyclerView = findViewById(R.id.countriesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(countryListAdapter);

        errorText = findViewById(R.id.list_error);
        loadingSpinner = findViewById(R.id.loading_view);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        observeViewModel();

        swipeRefreshLayout.setOnRefreshListener(() -> listViewModel.refresh());
    }

    private void observeViewModel() {
        listViewModel.getCountries().observe(this, countries -> {
            if (countries != null) countryListAdapter.updateCountries(countries);
        });

        listViewModel.getCountryLoadError().observe(this, error -> errorText.setVisibility(error ? View.VISIBLE : View.GONE));

        listViewModel.getLoading().observe(this, loading -> {
            loadingSpinner.setVisibility(loading ? View.VISIBLE : View.GONE);
            if (loading) {
                errorText.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }
            swipeRefreshLayout.setRefreshing(loading);
        });
    }
}
