package android.com.baseapp.ui.slideshow

import android.com.baseapp.R
import android.com.baseapp.databinding.FragmentSlideshowBinding
import android.com.baseapp.ui.base.BaseFragment
import android.view.View
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SlideshowFragment : BaseFragment<FragmentSlideshowBinding, SlideshowViewModel>() {

    override val resourceLayoutId = R.layout.fragment_slideshow

    override fun onInitView(root: View?) {

    }

    override fun subscribeUi(viewModel: SlideshowViewModel) {
        binding.viewModel = viewModel
    }
}