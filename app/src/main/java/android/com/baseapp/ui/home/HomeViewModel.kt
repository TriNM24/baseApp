package android.com.baseapp.ui.home

import android.com.baseapp.data.api.respone.Resource
import android.com.baseapp.data.repository.HomeRepository
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val mQuoteData: MutableLiveData<Resource<*>> = MutableLiveData()

    val text2: ObservableField<String> = ObservableField<String>()

    init {
        text2.set("This is home Fragment using databinding")
        Timber.d("test create from Hilt")
    }

    fun getApi() {
        Timber.d("cal api")
        mQuoteData.value = Resource.loading(null)
        viewModelScope.launch {
            mQuoteData.postValue(repository.getQuotes())
        }
    }

}