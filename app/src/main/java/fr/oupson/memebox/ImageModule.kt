package fr.oupson.memebox

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.oupson.memebox.database.ImageDatabase
import fr.oupson.memebox.database.repository.ImageRepository
import fr.oupson.memebox.database.repository.TagRepository

@Module
@InstallIn(SingletonComponent::class)
object ImageModule {
    @Provides
    fun provideImageRepository(
        @ApplicationContext context: Context
    ): ImageRepository {
        return ImageRepository(ImageDatabase.getInstance(context).imageDao())
    }

    @Provides
    fun provideTagRepository(
        @ApplicationContext context: Context
    ): TagRepository {
        return TagRepository(ImageDatabase.getInstance(context).tagDao())
    }
}
