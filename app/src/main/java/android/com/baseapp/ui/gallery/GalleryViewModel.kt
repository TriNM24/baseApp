package android.com.baseapp.ui.gallery

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {
    val text2: ObservableField<String> = ObservableField<String>()
    init {
        text2.set("This is gallery Fragment using databinding")
    }
}