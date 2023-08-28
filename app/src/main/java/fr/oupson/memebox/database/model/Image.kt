package fr.oupson.memebox.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class Image(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "image_id")
    var imageId: Long = 0L,

    @ColumnInfo(name = "image_title")
    var imageTitle: String
)
