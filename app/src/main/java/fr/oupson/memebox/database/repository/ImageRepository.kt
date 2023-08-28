package fr.oupson.memebox.database.repository

import fr.oupson.memebox.database.dao.ImageDao

class ImageRepository(private val imageDao: ImageDao) {
    suspend fun loadAsync() {
        imageDao.getAll()
    }
}