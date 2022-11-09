package android.com.baseapp.ui.slideshow

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    val text2: ObservableField<String> = ObservableField<String>()
    init {
        text2.set("This is slideshow Fragment using databinding")
    }
}