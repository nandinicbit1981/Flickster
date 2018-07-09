package flickster.com.flickster.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import flickster.com.flickster.model.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
