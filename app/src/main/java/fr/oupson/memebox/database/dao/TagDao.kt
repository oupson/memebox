package fr.oupson.memebox.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.oupson.memebox.database.model.Tag

@Dao
interface TagDao {
    @Query("SELECT * FROM tag")
    suspend fun getAll() : List<Tag>

    @Insert
    fun insertAll(vararg tags: Tag)

    @Delete
    fun delete(tag: Tag)
}