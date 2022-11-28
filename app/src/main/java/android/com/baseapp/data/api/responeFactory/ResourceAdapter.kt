package android.com.baseapp.data.api.responeFactory

import android.com.baseapp.data.api.respone.Resource
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResourceAdapter(private val type: Type): CallAdapter<Type, Call<Resource<Type>>> {
    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<Resource<Type>> = ResourceCall(call)
}