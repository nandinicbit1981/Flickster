package flickster.com.flickster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
        executeAsyncTask(Constant.MOVIE_POPULAR);
    }

    public void executeAsyncTask(String sort_order) {
        new MovieAsyncTask(this, sort_order).execute();
    }
    public void updateUI(List<Movie> movieList){
        this.movieList = movieList;
        recyclerView = (RecyclerView) findViewById(R.id.movie_list_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        this.movieAdapter = new MovieAdapter(this, this.movieList);
        recyclerView.setAdapter(this.movieAdapter);
        this.movieAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_popular :
                executeAsyncTask(Constant.MOVIE_POPULAR);
                break;
            case R.id.menu_top_rated:

                executeAsyncTask(Constant.MOVIE_TOP_RATED);
                break;
            default:
                break;
        }

        return true;
    }
}
