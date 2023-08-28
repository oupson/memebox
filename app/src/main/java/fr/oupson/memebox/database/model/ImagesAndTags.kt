package fr.oupson.memebox.database.model

import androidx.room.*

@Entity(primaryKeys = ["image_id", "tag_id"])
data class ImageTagsCrossRef(
    @ColumnInfo(name = "image_id") var imageId: Long,

    @ColumnInfo(name = "tag_id") var tagId: Long
)


data class ImageWithTags(
    @Embedded val image: Image,
    @Relation(
        parentColumn = "image_id",
        entityColumn = "tag_id",
        associateBy = Junction(ImageTagsCrossRef::class)
    )
    val tags: List<Tag>
)

data class TagWithImages(
    @Embedded val tag: Tag,
    @Relation(
        parentColumn = "songId",
        entityColumn = "playlistId",
        associateBy = Junction(ImageTagsCrossRef::class)
    )
    val images: List<Image>
)
