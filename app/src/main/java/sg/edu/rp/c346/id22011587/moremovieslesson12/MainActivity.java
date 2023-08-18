package sg.edu.rp.c346.id22011587.moremovieslesson12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textViewMovieTitle, textViewGenre, textViewYear, textViewRating;
    EditText editTextMovieTitle, editTextGenre, editTextYear;
    Spinner spinner;
    Button buttonInsert, buttonShowList;
    ListView lvMovies;
    ArrayList<Movie> movieList;

    @Override
    protected void onResume() {
        super.onResume();

        buttonShowList.performClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMovieTitle = findViewById(R.id.tvMovieTitle);
        textViewGenre = findViewById(R.id.tVGenre);
        textViewYear = findViewById(R.id.tVYear);
        textViewRating = findViewById(R.id.tVRating);
        editTextMovieTitle = findViewById(R.id.etMovieTitle);
        editTextGenre = findViewById(R.id.editTextSingers);
        editTextYear = findViewById(R.id.etYear);
        spinner = findViewById(R.id.spinner);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonShowList = findViewById(R.id.buttonShowList);
        lvMovies = findViewById(R.id.lvMovies);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.rating_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Prepare the intent
                Intent intentDetail = new Intent(MainActivity.this, ShowMovies.class);

                //Insert data into the intent
                Movie currentMovie = movieList.get(position);
                intentDetail.putExtra("title", currentMovie.getTitle());
                intentDetail.putExtra("genre", currentMovie.getGenre());
                intentDetail.putExtra("year", currentMovie.getYear());
                intentDetail.putExtra("rating", currentMovie.getRating());

                //Start the new activity
                startActivity(intentDetail);
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user inputs
                String title = editTextMovieTitle.getText().toString();
                String genre = editTextGenre.getText().toString();
                int year = Integer.parseInt(editTextYear.getText().toString());
                String rating = spinner.getSelectedItem().toString();

                DBHelper dbHelper = new DBHelper(MainActivity.this);
                long result = dbHelper.insertMovie(title, genre, year, rating);
                dbHelper.close();

                if (result != -1) {
                    // Insert successful, show a toast and clear input fields
                    Toast.makeText(MainActivity.this, "Movie inserted successfully", Toast.LENGTH_SHORT).show();
                    editTextMovieTitle.setText("");
                    editTextGenre.setText("");
                    editTextYear.setText("");
                } else {
                    // Insert failed, show an error toast
                    Toast.makeText(MainActivity.this, "Failed to insert movie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                movieList = dbHelper.getAllMovies();
                dbHelper.close();

                if (movieList != null && !movieList.isEmpty()) {
                    MovieListAdapter movieAdapter = new MovieListAdapter(MainActivity.this, movieList);
                    lvMovies.setAdapter(movieAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "No movies found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] ratingOptions = getResources().getStringArray(R.array.rating_options);

                if (position >= 0 && position < ratingOptions.length) {
                    String selectedOption = ratingOptions[position];
                    String message = "Spinner Item, " + selectedOption + " Selected";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}