package flickster.com.flickster.util;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.List;

import flickster.com.flickster.BuildConfig;
import flickster.com.flickster.activity.MovieDetailActivity;
import flickster.com.flickster.model.Review;
import flickster.com.flickster.model.ReviewResponse;
import flickster.com.flickster.network.APIClient;
import flickster.com.flickster.network.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class ReviewAsyncTask extends AsyncTask<String, Integer, List<Review>> {
    final static String LOG = TrailerAsyncTask.class.getCanonicalName();
    List<Review> reviews;
    Context context;
    String id;

    public ReviewAsyncTask(Context context, String id) {
        this.context = context;
        this.id = id;
    }

    @Override
    protected List<Review> doInBackground(String... strings) {

        try {
            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            final Call<ReviewResponse> reviewResponse = apiInterface.getReviews(id, BuildConfig.My_Movie_DB_API_Key);

            reviewResponse.enqueue(new Callback<ReviewResponse>() {

                @Override
                public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                    reviews = response.body().getResults();
                    ((MovieDetailActivity) context).updateReviews(reviews);
                }

                @Override
                public void onFailure(Call<ReviewResponse> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            Log.e(LOG, e.getMessage());
        }

        return reviews;
    }

}

