package android.com.baseapp.ui.base

import android.app.Dialog
import android.com.baseapp.utils.DialogUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<BD : ViewDataBinding, VM : ViewModel> : Fragment() {
    lateinit var viewModel: VM
    private var _binding: BD? = null
    val binding get() = _binding!!
    lateinit var mLoadingDialog: Dialog

    @get:LayoutRes
    abstract val resourceLayoutId: Int

    open val owner: ViewModelStoreOwner = this
        //get() = this

    abstract fun onInitView(root: View?)
    protected abstract fun subscribeUi(viewModel: VM)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, resourceLayoutId, container, false)
        onInitView(binding.root)
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoadingDialog = DialogUtils.getLoading(requireContext())
        initViewModel()
        subscribeUi(viewModel)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(owner)[clazz]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /////////////////////
    // support fun
    /////////////////////////
    //Get the actual type of generic T
    @Suppress("UNCHECKED_CAST")
    private val clazz: Class<VM> =
        (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
}