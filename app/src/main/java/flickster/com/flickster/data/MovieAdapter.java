package flickster.com.flickster.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import flickster.com.flickster.R;
import flickster.com.flickster.model.Movie;

/**
 * Created by nandpa on 5/21/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    List<Movie> movieList = new ArrayList<>();
    Context context;

    public  MovieAdapter(Context context, List<Movie> movies) {
        this.movieList = movies;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w780//" + movieList.get(position).getPosterPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_image);
        }
    }
}
