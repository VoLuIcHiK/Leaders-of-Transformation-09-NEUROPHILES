package ru.mrmarvel.hellofigma.screens

import android.Manifest
import android.os.Build
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.relay.compose.BoxScopeInstanceImpl.align
import com.tencent.yolov8ncnn.RoomStatistic
import com.tencent.yolov8ncnn.Yolov8Ncnn
import ru.mrmarvel.hellofigma.camerabutton.CameraButton
import ru.mrmarvel.hellofigma.changeflatbutton.ChangeFlatButton
import ru.mrmarvel.hellofigma.changeroombutton.ChangeRoomButton
import ru.mrmarvel.hellofigma.data.CameraScreenViewModel
import ru.mrmarvel.hellofigma.endroombutton.EndRoomButton
import ru.mrmarvel.hellofigma.flatinputfield.FlatInputField
import ru.mrmarvel.hellofigma.flatlabel.FlatLabel
import ru.mrmarvel.hellofigma.flatlock.FlatLock
import ru.mrmarvel.hellofigma.flatlock.IsLocked
import ru.mrmarvel.hellofigma.flatprogress.FlatProgress
import ru.mrmarvel.hellofigma.roomprogressbutton.RoomProgressButton
import ru.mrmarvel.hellofigma.util.findActivity

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    viewModel: CameraScreenViewModel = hiltViewModel(),
    navigateToObserveResultScreen: () -> Unit = {}
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

    val currentFlatNumber = remember {viewModel.currentFlatNumber}
    val isFlatLocked = remember {viewModel.isFlatLocked}
    val isFlatChangeWindowShown = remember { mutableStateOf(false) }
    // val camerasCount = Camera.getNumberOfCameras()
    // for (i in 0 until camerasCount) {
    //     var camera: Camera? = Camera.open(i)
    //     val parameters: Camera.Parameters = camera!!.parameters
    //     parameters.set("orientation", "portrait");     camera.setDisplayOrientation(90);
    //     camera.parameters = parameters
    //     camera.release()
    //     camera = null
    // }

    // we will show camera preview once permission is granted
//    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    Surface(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        // TODO: Посмотреть, нужно ли вызывать несколько раз. Спросить у Сереги как работает Surface
        var yolov8Ncnn = Yolov8Ncnn();
        yolov8Ncnn.loadModel(
            context.findActivity().assets,
            0,
            0
        );
        if (permissionState.allPermissionsGranted){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
                contentAlignment = Alignment.CenterStart
            ) {
                AnimatedVisibility(visible = remember {viewModel.isStarted}.value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Box(Modifier
                        // FORCE FILL
                        .fillMaxWidth()
                        .width(screenWidth * 0.97f)) {
                        AndroidView(
                            factory = {
                                // previewView = PreviewView(it)
                                // viewModel.showCameraPreview(previewView, lifecycleOwner)
                                // previewView
                                var yolov8Ncnn: Yolov8Ncnn? = null
                                SurfaceView(context).apply {
                                    holder.addCallback(object : SurfaceHolder.Callback {
                                        override fun surfaceCreated(p0: SurfaceHolder) {
                                            if (yolov8Ncnn == null) {
                                                yolov8Ncnn = Yolov8Ncnn()
                                                yolov8Ncnn?.loadModel(
                                                    context.findActivity().assets,
                                                    0,
                                                    0
                                                )
                                            }

                                        }

                                        override fun surfaceChanged(
                                            p0: SurfaceHolder,
                                            p1: Int,
                                            p2: Int,
                                            p3: Int
                                        ) {
                                            yolov8Ncnn?.openCamera(1)
                                            yolov8Ncnn?.setOutputWindow(p0.surface)
                                        }

                                        override fun surfaceDestroyed(p0: SurfaceHolder) {
                                            Log.d("MYDEBUG", "Camera surface destroyed!")
                                            yolov8Ncnn?.closeCamera()
                                            yolov8Ncnn = null
                                        }

                                    })
                                }
                            },
                            modifier = Modifier
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            IconButton(onClick = {
                Toast.makeText(
                    context,
                    "Pressed",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.isStarted.value = !viewModel.isStarted.value
                if (!viewModel.isStarted.value) {
                    // TODO: Сделать нормальное получение
                    var a = yolov8Ncnn.data
                    Log.d("data", a.toString())
                    var roomStatistic = RoomStatistic()

                    // Записываем среднюю уверенность
                    // TODO: Добавить логику парного соответствия
                    for ((key, value) in a) {
                        // TODO: Сделать выбор комнаты
                        if (key in roomStatistic.kitchen.keys && value.size > 20)
                            roomStatistic.kitchen[key] = value.sum() / value.size;
                    }
                    Log.d("data", roomStatistic.kitchen.toString())
                    Log.d("data", viewModel.roomRealData.toString())
                    navigateToObserveResultScreen()
                }
            }) {
                CameraButton()
            }
        }
        AnimatedVisibility(visible = !isFlatChangeWindowShown.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.BottomStart,
            ) {
                ChangeFlatButton(Modifier.wrapContentSize(), onItemClick = {
                    isFlatChangeWindowShown.value = true
                })
            }
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                FlatLabel(Modifier, "Квартира ${currentFlatNumber.value}")
                val lockClick = {
                    isFlatLocked.value = !isFlatLocked.value
                }
                Crossfade(targetState = isFlatLocked.value) {
                    when (it) {
                        false -> FlatLock(
                            Modifier.padding(start = 8.dp),
                            onItemClick = lockClick, isLocked = IsLocked.NotLocked
                        )

                        true -> FlatLock(
                            Modifier.padding(start = 8.dp),
                            onItemClick = lockClick, isLocked = IsLocked.Locked
                        )
                    }
                }
                FlatProgress(Modifier.padding(start=8.dp),"0%")
            }
        }
        val isRoomSelected = remember {viewModel.isRoomSelected}
        AnimatedVisibility(visible = isRoomSelected.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                EndRoomButton(onItemClick = {
                    isRoomSelected.value = false
                })
            }
        }
        val roomsNames = listOf("Санузел", "Коридор", "Жилая", "Кухня")
        AnimatedVisibility(
            visible = !remember { viewModel.isRoomSelected }.value,
            enter = expandVertically(expandFrom = Alignment.Top),
            exit = shrinkVertically(shrinkTowards = Alignment.Top),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 32.dp, horizontal = 2.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    items(roomsNames.size) { i ->
                        RoomProgressButton(roomName = roomsNames[i], progressText = "${(i+1) * 25}%", onItemClick = {
                            viewModel.isRoomSelected.value = true
                        })
                    }
                }
            }
        }
        AnimatedVisibility(visible = isFlatChangeWindowShown.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                val textIn = remember { mutableStateOf("") }
                FlatInputField(
                    fieldItem = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            BasicTextField(
                                value = textIn.value,
                                onValueChange = {textIn.value = it},
                                singleLine = true,
                                textStyle = TextStyle(textAlign = TextAlign.Center),
                            )
                        }
                    },
                    onOkClick = {
                        val newVal = textIn.value.toIntOrNull() ?: return@FlatInputField
                        currentFlatNumber.value = newVal.toString()
                        isFlatLocked.value = true
                        isFlatChangeWindowShown.value = false
                    })

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

@Preview
@Composable
fun FlatLabelPreview() {
    FlatLabel(Modifier, "Квартира 128")
}

@Preview
@Composable
fun FlatLockPreview() {
    FlatLock()
}

@Preview
@Composable
fun FlatProgressPreview() {
    FlatProgress(Modifier, "0%")
}
@Preview
@Composable
fun RoomProgressButtonPreview() {
    RoomProgressButton(Modifier, "Туалет", "0%")
}

@Preview
@Composable
fun ChangeRoomButtonPreview() {
    ChangeRoomButton()
}

@Preview
@Composable
fun FlatInputFieldPreview() {
    FlatInputField(
        fieldItem = {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                BasicTextField(
                    value = "1",
                    onValueChange = {},
                    singleLine = true,
                    modifier = Modifier.align(Alignment.Center),
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                )
            }
        })
}
