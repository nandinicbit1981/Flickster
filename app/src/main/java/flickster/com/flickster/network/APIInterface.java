package flickster.com.flickster.network;


import flickster.com.flickster.model.MovieResponse;
import flickster.com.flickster.model.ReviewResponse;
import flickster.com.flickster.model.TrailerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by nandpa on 5/21/18.
 */

public interface APIInterface {

    @GET("popular")
    Call<MovieResponse> getPopularMovieList(@Query("api_key") String api_key);

    @GET("top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String api_key);

    @GET("{id}/videos")
    Call<TrailerResponse> getTrailers(@Path("id") String id, @Query("api_key") String api_key);

    @GET("{id}/reviews")
    Call<ReviewResponse> getReviews(@Path("id") String id, @Query("api_key") String api_key);
}
