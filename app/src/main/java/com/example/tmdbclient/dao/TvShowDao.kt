package com.example.tmdbclient.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.models.tv_shows.TvShow

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(tvShows: List<TvShow>)

    @Query("DELETE FROM tb_popular_tv_show")
    suspend fun deleteAllTvShows()

    @Query("SELECT * FROM tb_popular_tv_show")
    suspend fun getTvShows(): List<TvShow>
}