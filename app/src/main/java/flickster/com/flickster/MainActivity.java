package flickster.com.flickster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import flickster.com.flickster.data.MovieAdapter;
import flickster.com.flickster.model.Movie;
import flickster.com.flickster.util.Constant;

public class MainActivity extends AppCompatActivity {

    List<Movie> movieList = new ArrayList<>();
    MovieAdapter movieAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MovieAsyncTask(this, Constant.MOVIE_POPULAR).execute();

    }

    public void updateUI(List<Movie> movieList){
        this.movieList = movieList;
        recyclerView = (RecyclerView) findViewById(R.id.movie_list_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        this.movieAdapter = new MovieAdapter(this, this.movieList);
        recyclerView.setAdapter(this.movieAdapter);
        this.movieAdapter.notifyDataSetChanged();
    }
}
