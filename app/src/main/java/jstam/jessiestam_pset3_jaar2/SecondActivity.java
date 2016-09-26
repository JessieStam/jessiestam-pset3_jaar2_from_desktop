package jstam.jessiestam_pset3_jaar2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jessie on 22/09/2016.
 */

public class SecondActivity extends MainActivity{

    String title;
    String film_title;
    String film_year;
    String film_director;
    String film_actors;
    String film_summary;
    String film_poster;

    TextView print_title;
    TextView print_year;
    TextView print_director;
    TextView print_actors;
    TextView print_summary;
    ImageView print_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // get extras from MainActivity
        Bundle extras = getIntent().getExtras();
        title = extras.getString("title");

        print_title = (TextView) findViewById(R.id.my_watchlist_title);
        print_year = (TextView) findViewById(R.id.year_string);
        print_director = (TextView) findViewById(R.id.director_string);
        print_actors = (TextView) findViewById(R.id.actors_string);
        print_summary = (TextView) findViewById(R.id.summary_string);

        print_poster = (ImageView) findViewById(R.id.poster_imageview);

        TitleAsyncTask asyncTask = new TitleAsyncTask(this);

        asyncTask.execute(title);

    }

    public void setData(ArrayList<String> data_list) {

        View button = findViewById(R.id.add_remove_button);

        if (data_list.size() == 0) {
            button.setVisibility(View.INVISIBLE);
        }
        else {
            button.setVisibility(View.VISIBLE);

            // get title and print to screen
            film_title = data_list.get(0);
            print_title.setText(film_title);

            film_year = data_list.get(1);
            print_year.setText(film_year);

            // get director and print to screen
            film_director = data_list.get(2);
            print_director.setText(film_director);

            // get actors and print to screen
            film_actors = data_list.get(3);
            print_actors.setText(film_actors);

            // get summary and print to screen
            film_summary = data_list.get(4);
            print_summary.setText(film_summary);

            //create poster
            film_poster = data_list.get(5);

            // print_poster.setImageBitmap(HttpRequestHelper.getPoster(film_poster));

            //Bitmap poster = BitmapFactory.decodeFile(film_poster);

            //picasso.with(film_poster).load(poster).into(print_poster);

            Picasso.with(this).load(film_poster).into(print_poster);

            //print_poster.setImageBitmap(poster);
        }
    }

    public void addRemove(View view) {

        // save image and title to mainactivity and add to recyclerview there
        // move to second Activity
        Intent addRemove = new Intent(this, MainActivity.class);

        // move extras to SecondActivity
        addRemove.putExtra("title", film_title);
        addRemove.putExtra("poster", film_poster);

        startActivity(addRemove);

        finish();
    }
}
