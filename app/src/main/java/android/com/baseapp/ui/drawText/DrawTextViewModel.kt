package android.com.baseapp.ui.drawText

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DrawTextViewModel @Inject constructor() : ViewModel() {

    init {
        Timber.i("This is DrawTextViewModel Fragment using databinding")
    }
}