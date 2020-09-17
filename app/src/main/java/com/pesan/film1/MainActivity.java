package com.pesan.film1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private String []ttmovie;
    private String [] yearmovie;
    private String [] descriptionMovie;
    private TypedArray postermovie;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie>movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        movieAdapter = new MovieAdapter (this);

        ListView lvMOvie = findViewById (R.id.lv_movie);
        lvMOvie.setAdapter (movieAdapter);

        prepare();
        addItem();

        lvMOvie.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent (MainActivity.this,DetailMovieActivity.class);
                intent.putExtra (DetailMovieActivity.KEY_DETAIL_DATA,movies.get (position));
                startActivity (intent);
            }
        });


    }
    private  void prepare(){
        ttmovie = getResources ().getStringArray (R.array.isi_movie);
        yearmovie = getResources ().getStringArray (R.array.tahun_movie);
        descriptionMovie = getResources ().getStringArray (R.array.description_movie);
        postermovie = getResources ().obtainTypedArray (R.array.poster_movie);
    }
    private void addItem(){
        movies = new ArrayList<> ();
        for (int i = 0; i < ttmovie.length; i++){
            Movie movie = new Movie ();
            movie.setPoster (postermovie.getResourceId (i,-1));
            movie.setTitle (ttmovie[i]);
            movie.setYear (ttmovie[i]);
            movie.setDescription (descriptionMovie[i]);
            movies.add (movie);

        }
        movieAdapter.setMovies (movies);
    }
}
