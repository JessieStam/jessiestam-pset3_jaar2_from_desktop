package jstam.jessiestam_pset3_jaar2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
 * Created by Jessie on 22/09/2016.
 */

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    //krijgt data mee (title, poster)

    //ArrayList<ArrayList> titles_posters;
    private ArrayList<String> titles;
    private ArrayList<String> posters;

    String currentColor = "White";

    public FilmAdapter(ArrayList<String> titles, ArrayList<String> posters) {

        this.titles = titles;
        this.posters = posters;
    }

    View.OnClickListener listener = new View.OnClickListener() {

        private String unfinished = "White";
        private String finished = "Gray";

        @Override
        public void onClick(View view) {

        // if item is selected, change color to gray
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

        viewHolder.myTextView.setText(titles.get(position));

        Bitmap poster = BitmapFactory.decodeFile(posters.get(position));
        viewHolder.myImageView.setImageBitmap(poster);

        Picasso.with(main).load(posters.get(position)).resize(100, 148).into(viewHolder.myImageView);

        viewHolder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}
