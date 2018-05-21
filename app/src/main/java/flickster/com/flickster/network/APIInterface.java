package flickster.com.flickster.network;


import flickster.com.flickster.model.Movie;
import flickster.com.flickster.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nandpa on 5/21/18.
 */

public interface APIInterface {

    @GET("popular")
    Call<MovieResponse> getPopularMovieList(@Query("api_key") String api_key);

    @GET("top_rated")
    Call<Movie> getTopRatedMovies();
}
