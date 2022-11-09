package android.com.baseapp.ui.slideshow

import android.com.baseapp.R
import android.com.baseapp.databinding.FragmentSlideshowBinding
import android.com.baseapp.ui.base.BaseFragment
import android.view.View

class SlideshowFragment : BaseFragment<FragmentSlideshowBinding, SlideshowViewModel>() {

    override val resourceLayoutId: Int
        get() = R.layout.fragment_slideshow

    override fun onInitView(root: View?) {

    }

    override fun subscribeUi(viewModel: SlideshowViewModel) {
        binding?.viewModel = viewModel
    }
}