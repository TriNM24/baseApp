package android.com.baseapp.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val text2: ObservableField<String> = ObservableField<String>()
    init {
        text2.set("This is home Fragment using databinding")
    }
}