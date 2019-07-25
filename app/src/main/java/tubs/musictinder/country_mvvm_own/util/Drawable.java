package tubs.musictinder.country_mvvm_own.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import tubs.musictinder.country_mvvm_own.R;

public class Drawable {

    public CircularProgressDrawable getProgressDrawable(Context context) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(20f);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }

    public void loadImage(ImageView imageView, String url, CircularProgressDrawable progressDrawable, Context context) {
        RequestOptions options = new RequestOptions().placeholder(progressDrawable).error(R.mipmap.ic_launcher_round);
        Glide.with(context).setDefaultRequestOptions(options).load(url).into(imageView);
    }
}
