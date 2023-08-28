package fr.oupson.memebox.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.oupson.memebox.database.repository.ImageRepository
import fr.oupson.memebox.database.repository.TagRepository
import kotlinx.coroutines.launch
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
}
