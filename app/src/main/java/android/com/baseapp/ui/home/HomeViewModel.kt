package android.com.baseapp.ui.home

import android.com.baseapp.data.api.baseRespone.ApiResult
import android.com.baseapp.data.api.dataResponse.QuoteList
import android.com.baseapp.data.repository.ApiRepository
import android.com.baseapp.utils.SingleLiveEvent
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    val mQuoteData: SingleLiveEvent<ApiResult<QuoteList?>> = SingleLiveEvent()

    val text2: ObservableField<String> = ObservableField<String>()

    init {
        text2.set("This is home Fragment using databinding")
        Timber.d("test create from Hilt")
    }

    fun getApi() {
        Timber.d("cal api")
        mQuoteData.value = ApiResult.Loading()
        viewModelScope.launch {
            val response = repository.getQuotes()
            if(response.isSuccessful){
                mQuoteData.postValue(ApiResult.Success(response.body()))
            }else{
                response.errorBody()?.let {
                    mQuoteData.postValue(ApiResult.Error(response.code().toString(),null))
                }

            }
        }
    }

}