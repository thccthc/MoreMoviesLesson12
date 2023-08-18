package sg.edu.rp.c346.id22011587.moremovieslesson12;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowMovies extends AppCompatActivity {

    Button buttonShowAllPG13Movies;
    ListView listView;
    ArrayList<Movie> movieList;
    MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        // Initialize the ListView and set the custom adapter
        buttonShowAllPG13Movies = findViewById(R.id.buttonShowAllPG13Movies);
        listView = findViewById(R.id.lv);

        DBHelper dbHelper = new DBHelper(this);
        movieList = dbHelper.getAllMovies();
        dbHelper.close();

        adapter = new MovieListAdapter(this, R.layout.row, movieList);
        listView.setAdapter(adapter);

        // Set a click listener for list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie selectedMovie = movieList.get(position);

                Intent intent = new Intent(ShowMovies.this, ShowMovies.class);
                intent.putExtra("title", selectedMovie.getTitle());
                intent.putExtra("genre", selectedMovie.getGenre());
                intent.putExtra("year", selectedMovie.getYear());
                intent.putExtra("rating", selectedMovie.getRating());

                startActivity(intent);
            }
        });
    }
}