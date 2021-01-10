package com.example.tmdbclient.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.models.artists.Artist

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(artists: List<Artist>)

    @Query("DELETE FROM tb_popular_artist")
    suspend fun deleteAllArtists()

    @Query("SELECT * FROM tb_popular_artist")
    suspend fun getArtists(): List<Artist>
}