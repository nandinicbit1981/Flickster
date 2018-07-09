package flickster.com.flickster.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import flickster.com.flickster.model.Movie;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<Movie> getAll();
//
//    @Query("SELECT * FROM movie WHERE favorite is 1")
//    List<Movie> findByFav(String name);

    @Insert
    void insertAll(List<Movie> movies);

    @Insert
    void insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);
}