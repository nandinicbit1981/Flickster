package flickster.com.flickster.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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
import flickster.com.flickster.model.MainViewModel;
import flickster.com.flickster.model.Movie;
import flickster.com.flickster.util.Constant;
import flickster.com.flickster.util.MovieAsyncTask;

import static flickster.com.flickster.util.Constant.MOVIE_POPULAR;

public class MainActivity extends BaseActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String SCROLL_POSITION = "Scroll position";
    List<Movie> movieList = new ArrayList<>();
    MovieAdapter movieAdapter;

    @BindView(R.id.movie_list_view)
    RecyclerView recyclerView;

    @BindView(R.id.mv_progress_bar)
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    int scrollPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        if(savedInstanceState != null && savedInstanceState.containsKey(SCROLL_POSITION)) {
            scrollPosition = savedInstanceState.getInt(SCROLL_POSITION);
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        getData();
    }

    public void executeAsyncTask(String sort_order) {
        progressBar.setVisibility(View.VISIBLE);
        new MovieAsyncTask(this, sort_order).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    public void getData() {
        //executeAsyncTask(Constant.MOVIE_POPULAR);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        String sort_option = sharedPreferences.getString(getResources().getString(R.string.pref_key), MOVIE_POPULAR);

        if(sort_option.equals(Constant.MOVIE_FAVORITE)) {
            setUpViewModel();
        } else {
            executeAsyncTask(sort_option);
        }
    }

    public void updateUI(final List<Movie> movieListUpdated){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                progressBar.setVisibility(View.GONE);
                movieList = movieListUpdated;
                movieAdapter = new MovieAdapter(MainActivity.this, movieList);
                recyclerView.setAdapter(movieAdapter);
                recyclerView.scrollToPosition(scrollPosition);

                movieAdapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public void setUpViewModel(){
        MainViewModel mainViewModel =  ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                updateUI(movies);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        int position = ((GridLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        outState.putInt(SCROLL_POSITION, position);
        super.onSaveInstanceState(outState);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            // launch settings activity
            startActivity(new Intent(MainActivity.this, PreferenceActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getResources().getString(R.string.pref_key))){
                executeAsyncTask(sharedPreferences.getString(key,""));
        }
    }
}
