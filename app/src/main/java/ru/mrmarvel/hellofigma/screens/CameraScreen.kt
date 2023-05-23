package ru.mrmarvel.hellofigma.screens

import android.Manifest
import android.content.pm.ActivityInfo
import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Build
import android.widget.Toast
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.relay.compose.BoxScopeInstanceImpl.align
import ru.mrmarvel.hellofigma.camerabutton.CameraButton
import ru.mrmarvel.hellofigma.changeflatbutton.ChangeFlatButton
import ru.mrmarvel.hellofigma.data.CameraScreenViewModel
import ru.mrmarvel.hellofigma.util.LockScreenOrientation

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    viewModel: CameraScreenViewModel = hiltViewModel()
) {
    Text("Тут должна была быть камера, но автор поленился")
    val context = LocalContext.current
    val permissions = mutableListOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    permissions += if (Build.VERSION.SDK_INT <= 28){
        listOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        )
    }else {
        listOf(Manifest.permission.CAMERA)
    }

    val permissionState = rememberMultiplePermissionsState(
        permissions = permissions)

    if (!permissionState.allPermissionsGranted){
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }
    if (!permissionState.allPermissionsGranted){
        permissionState.revokedPermissions.forEach {
            Toast.makeText(context, "Нужно разрешение ${it.permission}!", Toast.LENGTH_LONG).show()
        }
    }




    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current
    val screeHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    var previewView: PreviewView

    // we will show camera preview once permission is granted
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    Surface(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        if (permissionState.allPermissionsGranted){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
                contentAlignment = Alignment.CenterStart
            ) {
                if (remember {viewModel.isStarted}.value) {
                    AndroidView(
                        factory = {
                            previewView = PreviewView(it)
                            viewModel.showCameraPreview(previewView, lifecycleOwner)
                            previewView
                        },
                        modifier = Modifier
                                // FORCE FILL
                            .fillMaxHeight()
                            .width(screenWidth * 0.97f)
                    )
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
            contentAlignment = Alignment.CenterEnd,
        ) {
            IconButton(onClick = {
                Toast.makeText(
                    context,
                    "Pressed",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.isStarted.value = !viewModel.isStarted.value
            }) {
                CameraButton()
            }
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
            contentAlignment = Alignment.TopEnd,
        ) {
            ChangeFlatButton(Modifier.wrapContentSize())
        }

    }
    return

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column() {
            
        }
        Box(
            modifier = Modifier
                .height(screeHeight * 0.05f)
                .background(color = Color.Black),
            contentAlignment = Alignment.Center
        ){
            IconButton(onClick = {
                if (permissionState.allPermissionsGranted){
                    viewModel.captureAndSave(context)
                }
                else{
                    Toast.makeText(
                        context,
                        "Please accept permission in app settings",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }) {

                CameraButton()

            }
        }

    }
}

@Preview
@Composable
fun CameraButtonPreview() {
    CameraButton()
}

@Preview
@Composable
fun ChangeFlatButtonPreview() {
    ChangeFlatButton()
}