package jstam.jessiestam_pset3_jaar2;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView moviesList;
    // adapter object
    // layoutmanager object
    EditText title_input;
    String title_string;
    String title;
    String poster;


    ArrayList<ArrayList> titles_posters;
    ArrayList<String> titles;
    ArrayList<String> posters;

//    String[] titles;
//    String[] posters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            title_input = (EditText) findViewById(R.id.user_search_input);
            title_string = title_input.getText().toString();

            moviesList = (RecyclerView) findViewById(R.id.movies_list);

        }
        else {

            Bundle extras = getIntent().getExtras();
            title = extras.getString("title");
            poster = extras.getString("poster");

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            FilmAdapter adapter = new FilmAdapter(this, titles_posters);

            moviesList.setLayoutManager(layoutManager);
            moviesList.setAdapter(adapter);

            // add title and poster from second activity to list
            posters.add(poster);
            titles.add(title);

            titles_posters = null;
            titles_posters.add(titles);
            titles_posters.add(posters);
        }
    }

    public void searchFilm(View viewSearchFilm) {

        // move to second Activity
        Intent searchTitle = new Intent(this, SecondActivity.class);

        // move extras to SecondActivity
        searchTitle.putExtra("title", title_string);

        // clear the EditText
        title_input.getText().clear();

        startActivity(searchTitle);
    }

    // maak nog een ding om die items aan te klikken en van kleur te veranderen ohnee dit gebeurt
    // in de adapter maar je moet die dan wel aanroepen

    public void addToRecyclerView(View listview) {



    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save story object
        //outState.putSerializable("story", story);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // restore story object
        //story = (Story) savedInstanceState.getSerializable("story");
    }
}
