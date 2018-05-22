package flickster.com.flickster.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import flickster.com.flickster.R;
import flickster.com.flickster.data.MovieAdapter;
import flickster.com.flickster.model.Movie;
import flickster.com.flickster.util.Constant;
import flickster.com.flickster.util.MovieAsyncTask;

public class MainActivity extends BaseActivity {

    List<Movie> movieList = new ArrayList<>();
    MovieAdapter movieAdapter;

    @BindView(R.id.movie_list_view)
    RecyclerView recyclerView;

    @BindView(R.id.mv_progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        ButterKnife.bind(this);
        executeAsyncTask(Constant.MOVIE_POPULAR);
    }

    public void executeAsyncTask(String sort_order) {
        progressBar.setVisibility(View.VISIBLE);
        new MovieAsyncTask(this, sort_order).execute();
    }
    public void updateUI(List<Movie> movieList){
        progressBar.setVisibility(View.GONE);
        this.movieList = movieList;
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
