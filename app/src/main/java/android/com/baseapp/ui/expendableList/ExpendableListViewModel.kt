package android.com.baseapp.ui.expendableList

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExpendableListViewModel @Inject constructor() : ViewModel() {

    init {
        Timber.i("This is ExpendableListViewModel Fragment using databinding")
    }
}