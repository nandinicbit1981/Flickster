package flickster.com.flickster.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import flickster.com.flickster.R;
import flickster.com.flickster.data.TrailerAdapter;
import flickster.com.flickster.model.Movie;
import flickster.com.flickster.model.Trailer;
import flickster.com.flickster.util.Constant;
import flickster.com.flickster.util.TrailerAsyncTask;

public class MovieDetailActivity extends BaseActivity {

    String LOG = MovieDetailActivity.class.getCanonicalName();
    @BindView(R.id.md_title)
    TextView tv_title;

    @BindView(R.id.movie_image_thumbnail)
    ImageView image_view_thumbnail;

    @BindView(R.id.tv_release_year)
    TextView tv_release_year;

    @BindView(R.id.tv_movie_ratings)
    TextView tv_movie_ratings;

    @BindView(R.id.tv_plot_synopsis)
    TextView tv_plot_synopsis;

    @BindView(R.id.trailer_rv)
    RecyclerView trailerRV;

    TrailerAdapter trailerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Movie movie = (Movie) getIntent().getExtras().get(Constant.MOVIE_INFO);
        tv_title.setText(movie.getTitle());
        Picasso.with(this)
                .load(Constant.IMAGE_URL + movie.getPosterPath())
                .into(image_view_thumbnail);
        String releaseDate = movie.getReleaseDate();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        trailerRV.setLayoutManager(linearLayoutManager);

        try {
            Date date = new SimpleDateFormat(Constant.DATE_FORMAT).parse(releaseDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            tv_release_year.setText(String.valueOf(year));
            DecimalFormat dtime = new DecimalFormat("#.##");
            Double rating = Double.valueOf(dtime.format(movie.getVoteAverage()));

            tv_movie_ratings.setText(rating.toString() + "/10");
            tv_plot_synopsis.setText(movie.getOverview());
        } catch (Exception e) {
            Log.e(LOG, e.getMessage());
        }

        // adding trailer information.
        new TrailerAsyncTask(MovieDetailActivity.this, movie.getId().toString()).execute();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void updateTrailers(List<Trailer> trailers) {
        trailerAdapter = new TrailerAdapter(this, trailers);

        trailerRV.setAdapter(trailerAdapter);
        this.trailerAdapter.notifyDataSetChanged();
    }
}
