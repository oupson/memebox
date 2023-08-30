package fr.oupson.memebox.ui

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.oupson.memebox.database.repository.ImageRepository
import fr.oupson.memebox.database.repository.TagRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imageRepository: ImageRepository, private val tagRepository: TagRepository
) : ViewModel() {
    fun loadA() {
        viewModelScope.launch {
            imageRepository.loadAsync()
        }
    }

    fun cachePickedImage(context: Context, uri: Uri, callback: (Long) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            context.contentResolver.openInputStream(uri)?.use { input ->
                val tempId = System.currentTimeMillis()
                val tempFile = context.cacheDir.resolve(tempId.toString())
                tempFile.deleteOnExit()
                tempFile.outputStream().use { output ->
                    input.copyTo(output)
                }

                withContext(Dispatchers.Main) {
                    callback.invoke(tempId)
                }
            }
        }
    }

    fun getTempImageUri(context: Context, tempImageId: Long): Uri =
        context.cacheDir.resolve(tempImageId.toString()).toUri()

}
