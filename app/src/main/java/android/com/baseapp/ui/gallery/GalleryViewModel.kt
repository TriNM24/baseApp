package android.com.baseapp.ui.gallery

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor() : ViewModel() {
    val text2: ObservableField<String> = ObservableField<String>()
    init {
        text2.set("This is gallery Fragment using databinding")
    }
}