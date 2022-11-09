package android.com.baseapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<BD : ViewDataBinding>: AppCompatActivity() {
    abstract val resourceLayoutId : Int
    var binding: BD? = null

    abstract fun onInitView(root: View?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, resourceLayoutId)
        setContentView(binding?.root)
        onInitView(binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}