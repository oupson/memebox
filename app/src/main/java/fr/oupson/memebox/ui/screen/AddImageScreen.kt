package fr.oupson.memebox.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fr.oupson.memebox.R
import fr.oupson.memebox.ui.ImageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddImageScreen(tempImageId: Long, imageViewModel: ImageViewModel, onAddedImage: (Long) -> Unit) {
    var title by rememberSaveable { mutableStateOf("") }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(stringResource(R.string.add_an_image))
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {

        }) {
            // TODO: Content Description
            Icon(Icons.Default.Save, null)
        }
    }) { paddingValue ->
        Surface(modifier = Modifier.fillMaxSize().padding(paddingValue), color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                val imageUri = imageViewModel.getTempImageUri(LocalContext.current, tempImageId)
                // TODO: Content Description
                AsyncImage(
                    imageUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().aspectRatio(1f).clip(MaterialTheme.shapes.large)
                )

                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = title, label = {
                    Text(stringResource(R.string.title))
                }, onValueChange = {
                    title = it
                }, modifier = Modifier.fillMaxWidth().fillMaxWidth())
            }
        }
    }
}