    package com.svalero.musicrightsapp.database;

    import androidx.room.Dao;
    import androidx.room.Delete;
    import androidx.room.Insert;
    import androidx.room.OnConflictStrategy;
    import androidx.room.Query;
    import androidx.room.Update;

    import com.svalero.musicrightsapp.domain.Concert;

    import java.util.List;

    @Dao
    public interface ConcertDao {

        @Query("SELECT * FROM concert")
        List<Concert> getAll();

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        long insert(Concert concert);

        @Query("DELETE FROM concert WHERE id = :id")
        void delete(long id);

        @Update
        void update(Concert concert);

        @Query("DELETE FROM concert WHERE showTitle = :id")
        void deleteAll(long id);
    }