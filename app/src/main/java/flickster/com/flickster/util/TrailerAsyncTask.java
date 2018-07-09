package flickster.com.flickster.util;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.List;

import flickster.com.flickster.BuildConfig;
import flickster.com.flickster.activity.MovieDetailActivity;
import flickster.com.flickster.model.Trailer;
import flickster.com.flickster.model.TrailerResponse;
import flickster.com.flickster.network.APIClient;
import flickster.com.flickster.network.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class TrailerAsyncTask extends AsyncTask<String, Integer, List<Trailer>> {
    final static String LOG = TrailerAsyncTask.class.getCanonicalName();
    List<Trailer> trailers;
    Context context;
    String id;

    public TrailerAsyncTask(Context context, String id) {
        this.context = context;
        this.id = id;
    }

    @Override
    protected List<Trailer> doInBackground(String... strings) {

        try {
            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            final Call<TrailerResponse> trailerResponses = apiInterface.getTrailers(id, BuildConfig.My_Movie_DB_API_Key);

            trailerResponses.enqueue(new Callback<TrailerResponse>() {
                @Override
                public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                    trailers = response.body().getResults();
                    ((MovieDetailActivity)context).updateTrailers(trailers);
                }

                @Override
                public void onFailure(Call<TrailerResponse> call, Throwable t) {
                    Log.e(LOG,"failed");
                }
            });
        } catch (Exception e) {
            Log.e(LOG, e.getMessage());
        }

        return trailers;
    }

}
