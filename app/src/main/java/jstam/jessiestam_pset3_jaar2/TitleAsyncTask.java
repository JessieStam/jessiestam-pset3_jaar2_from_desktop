package jstam.jessiestam_pset3_jaar2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jessie on 22/09/2016.
 */

public class TitleAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    SecondActivity secondActivity;
    ArrayList<String> data_list;

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

        //String title = params[0];
        // Log.d("findproblem4", params[0]);
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

                this.secondActivity.setData(data_list);
            }
    }
}
