package tubs.musictinder.country_mvvm_own.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import tubs.musictinder.country_mvvm_own.R;
import tubs.musictinder.country_mvvm_own.model.Country;
import tubs.musictinder.country_mvvm_own.util.Drawable;

class CountryViewHolder extends RecyclerView.ViewHolder {

    private TextView countryName;
    private TextView capital;
    private ImageView imageView;
    private Drawable drawable = new Drawable();
    private CircularProgressDrawable progressDrawable = drawable.getProgressDrawable(itemView.getContext());

    CountryViewHolder(@NonNull View itemView) {
        super(itemView);
        countryName = itemView.findViewById(R.id.name);
        capital = itemView.findViewById(R.id.capital);
        imageView = itemView.findViewById(R.id.imageView);
    }

    void bind(Country country) {
        countryName.setText(country.getCountryName());
        capital.setText(country.getCapital());
        drawable.loadImage(imageView, country.getFlagUrl(), progressDrawable, itemView.getContext());
    }
}
