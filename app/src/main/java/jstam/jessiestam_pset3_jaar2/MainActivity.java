package jstam.jessiestam_pset3_jaar2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    ArrayList<String> titles;
    ArrayList<String> posters;

    LinearLayoutManager manager;
    FilmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesList = (RecyclerView) findViewById(R.id.movies_list);

        titles = new ArrayList<>();
        posters = new ArrayList<>();

        if (savedInstanceState == null) {

            title_input = (EditText) findViewById(R.id.user_search_input);

        }
        else {

            Bundle extras = getIntent().getExtras();
            title = extras.getString("title");
            poster = extras.getString("poster");

            titles = savedInstanceState.getStringArrayList("titles");
            posters = savedInstanceState.getStringArrayList("posters");

            // add title and poster from second activity to list
//            if (poster != null && title != null) {
//                posters.add(poster);
//                titles.add(title);
//            }
        }

        // use a linear layout manager
        manager = new LinearLayoutManager(this);
        moviesList.setLayoutManager(manager);

        // specify an adapter (see also next example)
        adapter = new FilmAdapter(titles, posters);
        moviesList.setAdapter(adapter);

        editRecyclerView();
    }

    public void searchFilm(View viewSearchFilm) {

        title_string = title_input.getText().toString();

        // move to second Activity
        Intent searchTitle = new Intent(this, SecondActivity.class);

        // move extras to SecondActivity
        searchTitle.putExtra("title", title_string);

        startActivity(searchTitle);

        // clear the EditText
        title_input.getText().clear();
    }

    public void editRecyclerView() {

        if (poster != null && title != null) {
            titles.add(title);
            posters.add(poster);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save lists object
        outState.putStringArrayList("titles", titles);
        outState.putStringArrayList("posters", posters);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // restore story object
        //story = (Story) savedInstanceState.getSerializable("story");
        titles = savedInstanceState.getStringArrayList("titles");
        posters = savedInstanceState.getStringArrayList("posters");
    }
}
