package com.bed.hogwarts.presentation.screens.picker

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.bed.hogwarts.presentation.components.ImageComponent

@Composable
fun PickerScreen(modifier: Modifier = Modifier) {
    var uris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    val pickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(maxItems = 5)
    ) { uris = it }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = modifier.height(24.dp))
            LazyRow {
                items(uris) { uri ->
                    ImageComponent(uri = uri)
                }
            }
            Spacer(modifier = modifier.height(24.dp))
            Button(onClick = {
                handlerPicker(pickerLauncher)
            }) {
                Text(text = "Pick multiple photo")
            }
        }
    }
}

private fun handlerPicker(
    pickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, List<Uri>>
) {
    pickerLauncher.launch(
        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
    )
}
