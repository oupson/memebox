package fr.oupson.memebox.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag")
data class Tag(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tag_id")
    var tagId: Long = 0L,

    @ColumnInfo(name = "tag_name")
    var tagName: String
)
