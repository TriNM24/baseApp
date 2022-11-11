package android.com.baseapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<BD : ViewDataBinding, VM : ViewModel> : Fragment() {
    lateinit var viewModel: VM
    var binding: BD? = null

    abstract val resourceLayoutId: Int
    abstract fun onInitView(root: View?)
    protected abstract fun subscribeUi(viewModel: VM)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(resourceLayoutId, container, false)
        binding = DataBindingUtil.bind(root)
        onInitView(root)
        return root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        subscribeUi(viewModel)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[clazz]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    /////////////////////
    // support fun
    /////////////////////////
    //Get the actual type of generic T
    @Suppress("UNCHECKED_CAST")
    private val clazz: Class<VM> = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
}