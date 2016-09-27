package jstam.jessiestam_pset3_jaar2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Watch List - MainActivity
 * Jessie Stam
 * 10560599
 *
 * This application allows the user to look for movies and add them to a list of movies that they
 * still want to watch. This Activity parses the user for a title and them moves on to Second
 * Activity to display film information. When information is returned from Second Activity it is
 * added to the RecyclerView with films that the user still wants to see.
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView moviesList;
    EditText title_input;
    String title_string;
    String title;
    String poster;

    ArrayList<String> titles;
    ArrayList<String> posters;

    LinearLayoutManager manager;
    FilmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create RecyclerView item and EditText item
        moviesList = (RecyclerView) findViewById(R.id.movies_list);
        title_input = (EditText) findViewById(R.id.user_search_input);

        // if savedInstanceState is empty, create new list objects from titles and posters
        if (savedInstanceState == null) {
            // create new titles and posters list
            titles = new ArrayList<>();
            posters = new ArrayList<>();
        }
        // if savedInstanceState is not empty, restore lists
        else {
            // restore titles and posters lists
            titles = savedInstanceState.getStringArrayList("titles");
            posters = savedInstanceState.getStringArrayList("posters");
        }

        // get extras from SecondActivity
        Bundle extras = getIntent().getExtras();

        // if extras exist, update title and poster string and titles and posters lists
        if (extras != null) {
            title = extras.getString("title");
            poster = extras.getString("poster");
            titles = extras.getStringArrayList("title_list");
            posters = extras.getStringArrayList("poster_list");

        }

        // use a linear layout manager on RecyclerView
        manager = new LinearLayoutManager(this);
        moviesList.setLayoutManager(manager);

        // create new FilmAdapter object and set to RecyclerView
        adapter = new FilmAdapter(titles, posters);

        moviesList.setAdapter(adapter);

        // update RecyclerView
        editRecyclerView();
    }

    /**
     * Gets title string from the user and moves to Second Activity to search for that film
     */
    public void searchFilm(View viewSearchFilm) {

        // get title string from user input
        title_string = title_input.getText().toString();

        // move to second Activity and add extras
        Intent searchTitle = new Intent(this, SecondActivity.class);
        searchTitle.putExtra("title", title_string);
        searchTitle.putExtra("title_list", titles);
        searchTitle.putExtra("poster_list", posters);

        startActivity(searchTitle);

        // clear the EditText
        title_input.getText().clear();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save list objects
        outState.putStringArrayList("titles", titles);
        outState.putStringArrayList("posters", posters);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // restore list objects
        titles = savedInstanceState.getStringArrayList("titles");
        posters = savedInstanceState.getStringArrayList("posters");
    }

    /**
     * Adds a new film to the RecyclerView
     */
    public void editRecyclerView() {

        // check is titles and posters lists exist and add title and poster
        if (titles != null && posters != null && title != null && poster != null) {
            titles.add(title);
            posters.add(poster);
        }

        // update RecyclerView
        adapter.notifyDataSetChanged();
    }
}
