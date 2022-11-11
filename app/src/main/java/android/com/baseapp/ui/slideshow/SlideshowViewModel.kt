package android.com.baseapp.ui.slideshow

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor() : ViewModel() {

    val text2: ObservableField<String> = ObservableField<String>()
    init {
        text2.set("This is slideshow Fragment using databinding")
    }
}