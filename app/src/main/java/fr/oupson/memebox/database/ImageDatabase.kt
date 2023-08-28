package fr.oupson.memebox.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.oupson.memebox.database.dao.ImageDao
import fr.oupson.memebox.database.dao.TagDao
import fr.oupson.memebox.database.model.Image
import fr.oupson.memebox.database.model.ImageTagsCrossRef
import fr.oupson.memebox.database.model.Tag

@Database(entities = [Image::class, Tag::class, ImageTagsCrossRef::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
    abstract fun tagDao(): TagDao

    companion object {
        /*The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
        It means that changes made by one thread to INSTANCE are visible to all other threads immediately.*/
        @Volatile
        private var INSTANCE: ImageDatabase? = null

        fun getInstance(context: Context): ImageDatabase {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE


                Log.e("TAG", "GetInstance")

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, ImageDatabase::class.java, "image_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
