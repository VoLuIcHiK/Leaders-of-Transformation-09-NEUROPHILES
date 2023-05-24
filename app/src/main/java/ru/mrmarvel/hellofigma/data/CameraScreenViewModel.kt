package ru.mrmarvel.hellofigma.data

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.mrmarvel.hellofigma.domain.repository.CustomCameraRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CameraScreenViewModel @Inject constructor(
    private val repo: CustomCameraRepo
):ViewModel() {

    public val isStarted = mutableStateOf(false)
    val isRoomSelected = mutableStateOf(true)
    val currentFlatNumber = mutableStateOf("128")
    val isFlatLocked = mutableStateOf(false)

    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ){
        viewModelScope.launch {
            repo.showCameraPreview(
                previewView,
                lifecycleOwner
            )
        }
    }

    fun captureAndSave(context: Context){
        viewModelScope.launch {
            repo.captureAndSaveImage(context)
        }
    }


}