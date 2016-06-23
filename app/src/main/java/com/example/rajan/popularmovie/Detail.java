package com.example.rajan.popularmovie;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {
    TextView title, rating, overview, release, trailer_text, review_text, favourite_text;
    ImageView image, backdrop, favourite, trailer_icon, review_icon, favourite_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        overview = (TextView) findViewById(R.id.details_overview);
        title = (TextView) findViewById(R.id.details_movie_name);
        rating = (TextView) findViewById(R.id.details_rating);
        image = (ImageView) findViewById(R.id.details_image);
        backdrop = (ImageView) findViewById(R.id.details_backdrop);
        release = (TextView) findViewById(R.id.details_release_date);
        trailer_icon = (ImageView) findViewById(R.id.icon_youtube);
        trailer_text = (TextView) findViewById(R.id.details_movie_trailer);
        review_icon = (ImageView) findViewById(R.id.icon_review);
        review_text = (TextView) findViewById(R.id.details_movie_review);
        favourite_icon = (ImageView)findViewById(R.id.icon_favourite);
        favourite_text = (TextView)findViewById(R.id.details_movie_favourite);

        String Title = getIntent().getStringExtra("Title");
        String Overview = getIntent().getStringExtra("Overview");
        String Image = getIntent().getStringExtra("Image");
        String Backdrop = getIntent().getStringExtra("Backdrop");
        String Vote_average = getIntent().getStringExtra("Vote_average");
        String Release_date = getIntent().getStringExtra("Release_date");
        favourite_text.setText("Favourite movie");
        favourite_icon.setImageDrawable(null);

        title.setText(Title);
        overview.setText(Overview);
        Picasso.with(getApplicationContext()).load(Image).into(image);
        Picasso.with(getApplicationContext()).load(Backdrop).into(backdrop);
        String temp_rating = "Rating :\n"+Vote_average;
        rating.setText(temp_rating);
        String temp_release = "Release Date : \n"+Release_date;
        release.setText(temp_release);
    }
}
