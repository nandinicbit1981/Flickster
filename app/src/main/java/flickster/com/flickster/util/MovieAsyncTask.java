package flickster.com.flickster.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import flickster.com.flickster.BuildConfig;
import flickster.com.flickster.activity.MainActivity;
import flickster.com.flickster.model.Movie;
import flickster.com.flickster.model.MovieResponse;
import flickster.com.flickster.network.APIClient;
import flickster.com.flickster.network.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nandpa on 5/19/18.
 */

public class MovieAsyncTask extends AsyncTask<String, Integer, Void> {

    final static String LOG = MovieAsyncTask.class.getCanonicalName();
    String sort_order;
    List<Movie> movies;
    Context context;
    Call<MovieResponse> movieResponse;


    public MovieAsyncTask(Context context, String moviePopular) {
        this.sort_order = moviePopular;
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... option) {
        try {
            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            if(this.sort_order.equals(Constant.MOVIE_POPULAR)) {
                movieResponse = apiInterface.getPopularMovieList(BuildConfig.My_Movie_DB_API_Key);
            } else {
                movieResponse = apiInterface.getTopRatedMovies(BuildConfig.My_Movie_DB_API_Key);
            }
            movieResponse.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> movieResponse, Response<MovieResponse> response) {
                    if(response.body() != null) {
                        movies = response.body().getResults();
                        ((MainActivity) context).updateUI(movies);
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> responseCall, Throwable t) {
                    Log.e(LOG, responseCall.toString());
                }

            });
        } catch(Exception e) {
            Log.e(LOG, e.getMessage());
        }
        return null;
    }
}
