package com.bed.hogwarts.presentation.components.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bed.hogwarts.BuildConfig
import com.bed.hogwarts.presentation.components.image.ImageComponent

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CameraComponent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val file = createImageFile(context)
    val uri = FileProvider.getUriForFile(
        context,
        "${BuildConfig.APPLICATION_ID}.provider",
        file
    )
    
    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { capturedImageUri = uri }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) cameraLauncher.launch(uri)
        else Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                handlerCamera(uri, context, cameraLauncher, permissionLauncher)
            }) {
                Text(text = "Capture Image From Camera")
            }
        }

        if (capturedImageUri.path?.isNotEmpty() == true) ImageComponent(uri = capturedImageUri)
    }
}

private fun createImageFile(context: Context) =
    File.createTempFile(
        "JPG_${SimpleDateFormat("yyyyMMdd", Locale.US).format(Date())}_",
        ".jpg",
        context.externalCacheDir
    )

private fun handlerCamera(
    uri: Uri,
    context: Context,
    cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>,
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>
) {
    val permission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

    if (permission == PackageManager.PERMISSION_GRANTED) cameraLauncher.launch(uri)
    else permissionLauncher.launch(Manifest.permission.CAMERA)
}
