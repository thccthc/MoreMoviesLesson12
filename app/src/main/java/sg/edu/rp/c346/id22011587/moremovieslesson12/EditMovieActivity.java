package sg.edu.rp.c346.id22011587.moremovieslesson12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditMovieActivity extends AppCompatActivity {

    TextView tvMovieID, tvMovieTitle, tVGenre, tVYear, tVRating;
    EditText etMovieID, etMovieTitle, etGenre, etYear;
    Button buttonUpdate, buttonDelete;
    Spinner spn;
    Movie data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        tvMovieID = findViewById(tvMovieID);
        tvMovieTitle = findViewById(tvMovieTitle);
        tVGenre = findViewById(R.id.tVGenre);
        tVYear = findViewById(tVYear);
        tVRating = findViewById(tVRating);
        etMovieID = findViewById(etMovieID);
        etMovieTitle = findViewById(etMovieTitle);
        etGenre = findViewById(etGenre);
        etYear = findViewById(etYear);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        spn = findViewById(R.id.spn);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(EditMovieActivity.this);
                int id = Integer.parseInt(etMovieID.getText().toString());
                String title = etMovieTitle.getText().toString();
                String genre = etGenre.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                String rating = spn.getSelectedItem().toString();

                Movie updatedMovie = new Movie(id, title, genre, year, rating);
                db.updateMovie(updatedMovie);
                db.close();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditMovieActivity.this);
                int id = Integer.parseInt(etMovieID.getText().toString());
                dbh.deleteMovie(id);
                dbh.close();
            }
        });
    }
}