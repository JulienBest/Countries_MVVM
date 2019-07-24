package tubs.musictinder.country_mvvm_own.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tubs.musictinder.country_mvvm_own.R;
import tubs.musictinder.country_mvvm_own.model.Country;

public class CountryListAdapter extends RecyclerView.Adapter<CountryViewHolder> {
    private ArrayList<Country> countryList;

    public CountryListAdapter(ArrayList<Country> countryList) {
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countryList.get(position));
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void updateCountries(List<Country> newCountries) {
        countryList.clear();
        countryList.addAll(newCountries);
        notifyDataSetChanged();
    }
}
