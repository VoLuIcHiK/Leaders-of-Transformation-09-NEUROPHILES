package ru.mrmarvel.hellofigma.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import ru.mrmarvel.hellofigma.R
import ru.mrmarvel.hellofigma.backbutton.BackButton
import ru.mrmarvel.hellofigma.blue1linebutton.Blue1lineButton
import ru.mrmarvel.hellofigma.camoletappbar.CamoletAppBar
import ru.mrmarvel.hellofigma.data.SharedViewModel
import ru.mrmarvel.hellofigma.videoframe.VideoFrame

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ObserveStartScreen(
    sharedViewModel: SharedViewModel,
    navigateToCameraScreen: () -> Unit = {},
    navigateBack: () -> Unit = {}
) {
    val context = LocalContext.current
    val permissions = mutableListOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val permissionState = rememberMultiplePermissionsState(
        permissions = permissions)

    if (!permissionState.allPermissionsGranted){
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }
    if (!permissionState.allPermissionsGranted){
        permissionState.revokedPermissions.forEach {
            // Toast.makeText(context, "Нужно разрешение ${it.permission}!", Toast.LENGTH_LONG).show()
        }
    }
    Scaffold(
        topBar = {
            CamoletAppBar(Modifier.fillMaxWidth(),
                onBurgerClick = {
                    // Toast.makeText(context, "Открыть меню!", Toast.LENGTH_SHORT).show()
                },
                onProfileClick = {
                    // Toast.makeText(context, "Открыть профиль!", Toast.LENGTH_SHORT).show()
                },
                appBarText = "НАЧАЛО ОБХОДА"
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp, top = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Blue1lineButton(Modifier,
                    buttonText = "Начать",
                    onItemClicked = {
                        // Toast.makeText(context, "Создать видео!", Toast.LENGTH_SHORT).show()
                        navigateToCameraScreen()
                    }
                )
            }
        }
    ) { scaffoldPadding ->
        getLocation(LocalContext.current
        ) { location -> sharedViewModel.currentLocation.value = location
            // Toast.makeText(context, "GPS:$location", Toast.LENGTH_SHORT).show()
        }
        Surface(
            Modifier.padding(scaffoldPadding),
        ) {
            ObserveStartMain()
        }
    }
}

@Composable
fun ObserveStartMain(sharedViewModel: SharedViewModel = SharedViewModel()) {
    Surface(Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            BackButton(
                Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp))
            VideoFrame()
            val elementModifier = Modifier.padding(vertical = 3.dp)
            Column(Modifier.padding(top = 24.dp)) {
                val projectName = remember {mutableStateOf("Жульен")}
                GeoInput(label = "ЖК", buttonValue = sharedViewModel.selectedProjectName, modifier=elementModifier)
                GeoInput(label = "Дом", buttonValue = sharedViewModel.selectedBuildingName, modifier=elementModifier)
                GeoInput(label = "Секция", buttonValue = sharedViewModel.selectedSectionNumber, modifier=elementModifier)
                GeoInput(label = "Этаж", buttonValue = sharedViewModel.selectedFloorNumber, modifier=elementModifier)
            }
        }
    }
}

@Composable
fun GeoInput(label: String, buttonValue: MutableState<String> = mutableStateOf(""),
modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.black))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(label, Modifier.weight(weight = 0.5f))
            TextField(value = buttonValue.value, onValueChange = {buttonValue.value = it},
                Modifier.weight(0.5f))
        }
    }
}

@Preview
@Composable
fun GeoInputPreview() {
    val x = remember {mutableStateOf("Жульен")}
    GeoInput(label = "ЖК", x)
}


private fun getLocation(context: Context, locationListener: LocationListener) {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        Log.d("MYDEBUG", "Нет разрешений!")
        return
    }
    locationManager.requestLocationUpdates(
        LocationManager.GPS_PROVIDER,
        5000,
        5f,
        locationListener
    )

}