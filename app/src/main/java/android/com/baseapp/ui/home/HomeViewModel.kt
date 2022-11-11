package android.com.baseapp.ui.home

import android.com.baseapp.utils.LogTag
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val log: LogTag) : ViewModel() {
    val text2: ObservableField<String> = ObservableField<String>()
    init {
        text2.set("This is home Fragment using databinding")
        log.d("test create from Hilt")
    }
}