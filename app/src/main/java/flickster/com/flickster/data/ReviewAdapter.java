package flickster.com.flickster.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import flickster.com.flickster.R;
import flickster.com.flickster.model.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{
    List<Review> reviews;
    Context context;

    public ReviewAdapter(Context context, List<Review> reviews) {
        this.reviews = reviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.reviews_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        holder.authorName.setText(reviews.get(position).getAuthor());
        holder.reviewContent.setText(reviews.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{
        TextView authorName;
        TextView reviewContent;
        public ReviewViewHolder(View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.author_name);
            reviewContent = itemView.findViewById(R.id.review_content);
        }
    }
}
