package jstam.jessiestam_pset3_jaar2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Watch List - TitleAsyncTask
 * Jessie Stam
 * 10560599
 *
 * Gets result String from HttpRequestHelper and parses it as JSONObject to extract film title,
 * year, director, actors, summary and poster. Extracted data is added to datalist and moved on to
 * Second Activity's setData function that starts when datalist is completed.
 */

public class TitleAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    SecondActivity secondActivity;
    ArrayList<String> data_list;

    /**
     * Constructs TitleAsyncTask
     */
    public TitleAsyncTask(SecondActivity activity) {
        secondActivity = activity;
        context = this.secondActivity.getApplicationContext();
    }

    // let user know data is being downloaded
    @Override
    protected void onPreExecute() {
        // inform user
        Toast.makeText(context, "Getting data from server", Toast.LENGTH_SHORT).show();
    }

    // download the data
    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    // read the data and put in list
    @Override
    protected void onPostExecute(String readFilmInfo) {
            super.onPostExecute(readFilmInfo);

            // if nothing was found, inform user
            if (readFilmInfo.length() == 0) {
                Toast.makeText(context, "No data was found", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Data was found", Toast.LENGTH_SHORT).show();

                // create new datalist
                data_list = new ArrayList<>();

                try {
                    // create new JSONObject
                    JSONObject response_object = new JSONObject(readFilmInfo);
                    String title_object = response_object.getString("Title");
                    String year_object = response_object.getString("Year");
                    String director_object = response_object.getString("Director");
                    String actors_object = response_object.getString("Actors");
                    String summary_object = response_object.getString("Plot");
                    String poster_object = response_object.getString("Poster");

                    // add to list as String
                    data_list.add(title_object);
                    data_list.add(year_object);
                    data_list.add(director_object);
                    data_list.add(actors_object);
                    data_list.add(summary_object);
                    data_list.add(poster_object);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                // if film is not found, let user know
                if (data_list.size() == 0) {
                    Toast.makeText(context, "but your film was not...", Toast.LENGTH_SHORT).show();
                }

                // start Second Activity's setData function to display the data
                this.secondActivity.setData(data_list);
            }
    }
}
