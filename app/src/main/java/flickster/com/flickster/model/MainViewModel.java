package flickster.com.flickster.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import flickster.com.flickster.App;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Movie>> movies;

    public MainViewModel(@NonNull Application application) {
        super(application);
        movies = App.get().getDB().movieDao().getAll();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }
}
