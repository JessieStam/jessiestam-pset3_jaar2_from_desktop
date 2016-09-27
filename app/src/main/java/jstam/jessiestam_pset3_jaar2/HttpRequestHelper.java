package jstam.jessiestam_pset3_jaar2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Watch List - HttpRequestHelper
 * Jessie Stam
 * 10560599
 *
 * This activity creates a URL using the film title chosen by the user and starts and internet
 * connection to fetch the film data. Returns a result String that is parsed in TitleAsyncTask.
 */

public class HttpRequestHelper {

    // strings for URL
    private static final String url1 = "http://www.omdbapi.com/?t=";
    private static final String url2 = "&plot=short&r=json";

    /**
     * Downloads information from the server and puts it into a string object.
     */
    protected static synchronized String downloadFromServer(String... params) {

        // create result String and film title String
        String result = "";
        String film = params[0];

        // replace spaces with + sign to make URL work
        String film_title = film.replaceAll(" ", "+");

        // complete URL string and turn into URL
        String complete_URL_string = url1 + film_title + url2;
        URL complete_URL = null;

        try {
            complete_URL = new URL(complete_URL_string);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // make internet connection
        HttpURLConnection connection;
        if (complete_URL != null) {
            try {
                connection = (HttpURLConnection) complete_URL.openConnection();

                // open connection, set request method
                connection.setRequestMethod("GET");

                // get response code
                Integer responce_code = connection.getResponseCode();

                // if 200-300, read input stream
                if (200 <= responce_code && responce_code <= 299) {
                    BufferedReader bufferedReader = new BufferedReader
                            (new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result = result + line;
                    }
                }
                // when error occurs, read error stream
                else {
                    BufferedReader bufferedReader = new BufferedReader
                            (new InputStreamReader(connection.getErrorStream()));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // return result string
        return result;
    }
}
