package fr.oupson.memebox.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.oupson.memebox.database.model.Image

@Dao
interface ImageDao {
    @Query("SELECT * FROM image")
    suspend fun getAll(): List<Image>

    @Query("SELECT * FROM image WHERE image_title LIKE :title")
    suspend fun findAllByTitle(title: String): List<Image>

    @Insert
    fun insertAll(vararg images: Image)

    @Delete
    fun delete(image: Image)
}