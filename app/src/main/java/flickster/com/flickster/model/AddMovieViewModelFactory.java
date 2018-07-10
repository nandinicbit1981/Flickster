package flickster.com.flickster.model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import flickster.com.flickster.database.MovieDatabase;

public class AddMovieViewModelFactory  extends ViewModelProvider.NewInstanceFactory{

    private final MovieDatabase movieDatabase;
    private final int movieId;

    public AddMovieViewModelFactory(MovieDatabase movieDatabase, int movieId) {
        this.movieDatabase = movieDatabase;
        this.movieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddMovieViewModel(movieDatabase, movieId);
    }
}
