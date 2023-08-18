package sg.edu.rp.c346.id22011587.moremovieslesson12;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieListAdapter extends ArrayAdapter<Movie> {

    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public MovieListAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent_context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvGenre = rowView.findViewById(R.id.tvGenre);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        ImageView imageViewRating = rowView.findViewById(R.id.imageViewRating);

        Movie currentItem = movieList.get(position);
        tvTitle.setText(currentItem.getTitle());
        tvGenre.setText("+" + currentItem.getGenre());
        tvYear.setText(currentItem.getYear() + "");

        if (currentItem.getRating().equalsIgnoreCase("g")) {
            imageViewRating.setImageResource(R.drawable.rating_g);
        } else if (currentItem.getRating().equalsIgnoreCase("m18")) {
            imageViewRating.setImageResource(R.drawable.rating_m18);
        } else if (currentItem.getRating().equalsIgnoreCase("nc16")) {
            imageViewRating.setImageResource(R.drawable.rating_nc16);
        } else if (currentItem.getRating().equalsIgnoreCase("pg")) {
            imageViewRating.setImageResource(R.drawable.rating_pg);
        } else if (currentItem.getRating().equalsIgnoreCase("pg13")) {
            imageViewRating.setImageResource(R.drawable.rating_pg13);
        } else {
            imageViewRating.setImageResource(R.drawable.rating_r21);
        }
        return rowView;
    }
}
