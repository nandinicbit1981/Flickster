package flickster.com.flickster.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import flickster.com.flickster.R;
import flickster.com.flickster.model.Trailer;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    List<Trailer> trailers;
    Context context;
    public TrailerAdapter(Context context, List<Trailer> trailers) {
        this.trailers = trailers;
        this.context = context;

    }

    @NonNull
    @Override
    public TrailerAdapter.TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trailer_item, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.TrailerViewHolder holder, int position) {
        Picasso.with(context).load("https://img.youtube.com/vi/"+ trailers.get(position).getKey()+"/0.jpg").into(holder.trailerThumbnail);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
      ImageView trailerThumbnail;
        public TrailerViewHolder(View itemView) {

            super(itemView);
            trailerThumbnail = itemView.findViewById(R.id.trailer_thumbnail);
        }
    }
}
