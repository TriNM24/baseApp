package android.com.baseapp.ui.splash

import android.com.baseapp.utils.SingleLiveEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    val resultChecking: SingleLiveEvent<ResultSplash> = SingleLiveEvent()

    init {
        Timber.d("Splash activity")
    }

    fun processChecking(){
        viewModelScope.launch {
            delay(1000)
            resultChecking.postValue(ResultSplash.MAIN)
        }
    }
}

enum class ResultSplash{
    LOGIN,
    MAIN
}