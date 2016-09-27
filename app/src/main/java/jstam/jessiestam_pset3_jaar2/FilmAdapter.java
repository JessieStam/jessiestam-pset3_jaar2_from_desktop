package jstam.jessiestam_pset3_jaar2;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Watch List - FilmAdapter
 * Jessie Stam
 * 10560599
 *
 * This adapter is used on a RecyclerView to update it. It also allows the user to select an item in
 * the RecyclerView and chance its color for when film has been watched, but this feature is not yet
 * saved when the activity is killed and restored.
 */

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    private ArrayList<String> titles;
    private ArrayList<String> posters;

    /**
     * This function constructs the FilmAdapter
     */
    public FilmAdapter(ArrayList<String> titles, ArrayList<String> posters) {

        this.titles = titles;
        this.posters = posters;
    }

    // set onClickListener to RecyclerView
    View.OnClickListener listener = new View.OnClickListener() {

        String currentColor = "White";
        private String unfinished = "White";
        private String finished = "Gray";

        // when item is clicked, change color
        @Override
        public void onClick(View view) {
        // if item is selected, change color to turquoise
            if (currentColor.equals(unfinished) || currentColor == null) {
                view.setBackgroundColor(Color.parseColor("#d4f7f4"));
                currentColor = finished;
            }
            // if item is not selected, change color back to white
            else if (currentColor.equals(finished)) {
                view.setBackgroundColor(Color.WHITE);
                currentColor = unfinished;
            }
        }
    };

    /**
     * Construct ViewHolder to display title and poster in
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView myTextView;
        ImageView myImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.titles_row);
            myImageView = (ImageView) itemView.findViewById(R.id.poster_row);
        }
    }

    @Override
    public FilmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        MainActivity main = new MainActivity();

        // add film title and poster to RecyclerView and resize poster
        viewHolder.myTextView.setText(titles.get(position));
        Picasso.with(main).load(posters.get(position)).resize(100, 148).into(viewHolder.myImageView);

        // set listener to allow for selecting item and changing color
        viewHolder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}
