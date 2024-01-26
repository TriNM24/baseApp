package android.com.baseapp.ui.gallery

import android.com.baseapp.R
import android.com.baseapp.databinding.FragmentGalleryBinding
import android.com.baseapp.ui.base.BaseFragment
import android.view.View
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragmentGalleryBinding, GalleryViewModel>() {
    override val resourceLayoutId = R.layout.fragment_gallery
    override fun onInitView(root: View?) {

    }

    override fun subscribeUi(viewModel: GalleryViewModel) {
        binding?.viewModel = viewModel
    }
}