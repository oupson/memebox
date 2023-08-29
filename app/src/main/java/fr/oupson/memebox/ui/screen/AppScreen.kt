package fr.oupson.memebox.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import fr.oupson.memebox.Greeting
import fr.oupson.memebox.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(stringResource(R.string.app_name))
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {

        }) {
            // TODO: Content Description
            Icon(Icons.Default.Add, null)
        }
    }) {
        Surface(modifier = Modifier.fillMaxSize().padding(it), color = MaterialTheme.colorScheme.background) {
            Greeting("Android")
        }
    }
}