package jstam.jessiestam_pset3_jaar2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Watch List - SecondActivity
 * Jessie Stam
 * 10560599
 *
 * This activity displays film information for film chosen by user and gives film title and poster
 * back to Main Activity so they can be added to the Watch List.
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

    ArrayList<String> title_list;
    ArrayList<String> poster_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // get extras from MainActivity
        Bundle extras = getIntent().getExtras();
        title = extras.getString("title");
        title_list = extras.getStringArrayList("title_list");
        poster_list = extras.getStringArrayList("poster_list");

        print_title = (TextView) findViewById(R.id.my_watchlist_title);
        print_year = (TextView) findViewById(R.id.year_string);
        print_director = (TextView) findViewById(R.id.director_string);
        print_actors = (TextView) findViewById(R.id.actors_string);
        print_summary = (TextView) findViewById(R.id.summary_string);
        print_poster = (ImageView) findViewById(R.id.poster_imageview);

        // create new TitleAsyncTask object and execute
        TitleAsyncTask asyncTask = new TitleAsyncTask(this);
        asyncTask.execute(title);
    }

    /**
     * Is started from TitleAsyncTask when data is fetched successfully. Takes title, year,
     * director, actors, summary and poster strings from datalist and prints them to the screen.
     */
    public void setData(ArrayList<String> data_list) {

        View button = findViewById(R.id.add_remove_button);

        // if datalist is empty, remove button that adds data to the Main Activity Watch List
        if (data_list.size() == 0) {
            button.setVisibility(View.INVISIBLE);
        }
        else {
            button.setVisibility(View.VISIBLE);

            // get title and print to screen
            film_title = data_list.get(0);
            print_title.setText(film_title);

            // get year and print to screen
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

            // get poster and print to screen using picasso jarfile
            film_poster = data_list.get(5);
            Picasso.with(this).load(film_poster).into(print_poster);
        }
    }

    /**
     * When button is clicked, start Main Activity and add title and poster to be added to Watch
     * List, along with the list of films already added to the Watch List
     */
    public void addRemove(View view) {

        // move to second Activity and add extras
        Intent addRemove = new Intent(this, MainActivity.class);

        // move extras to SecondActivity
        addRemove.putExtra("title", film_title);
        addRemove.putExtra("poster", film_poster);

        addRemove.putExtra("title_list", title_list);
        addRemove.putExtra("poster_list", poster_list);

        startActivity(addRemove);

        // finish this activity
        finish();
    }
}
