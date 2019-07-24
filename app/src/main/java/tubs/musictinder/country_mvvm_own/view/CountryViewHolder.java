package tubs.musictinder.country_mvvm_own.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tubs.musictinder.country_mvvm_own.R;
import tubs.musictinder.country_mvvm_own.model.Country;

public class CountryViewHolder extends RecyclerView.ViewHolder {

    TextView countryName;

    public CountryViewHolder(@NonNull View itemView) {
        super(itemView);
        countryName = itemView.findViewById(R.id.name);
    }

    public void bind(Country country) {
        countryName.setText(country.getCountryName());
    }
}
