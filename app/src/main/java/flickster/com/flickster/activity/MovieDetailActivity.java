package flickster.com.flickster.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import flickster.com.flickster.R;
import flickster.com.flickster.model.Movie;
import flickster.com.flickster.util.Constant;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
