package flickster.com.flickster.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import flickster.com.flickster.App;
import flickster.com.flickster.database.MovieDatabase;

public class AddMovieViewModel extends ViewModel{
    private LiveData<Movie> movieLiveData;
    public AddMovieViewModel(MovieDatabase movieDatabase, int movieId) {
        movieLiveData = App.get().getDB().movieDao().findById(movieId );
    }

    public LiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }
}
