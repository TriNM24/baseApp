package android.com.baseapp.ui.gallery

import android.com.baseapp.R
import android.com.baseapp.databinding.FragmentGalleryBinding
import android.com.baseapp.ui.base.BaseFragment
import android.view.View

class GalleryFragment : BaseFragment<FragmentGalleryBinding, GalleryViewModel>() {

    override val resourceLayoutId: Int
        get() = R.layout.fragment_gallery

    override fun onInitView(root: View?) {

    }

    override fun subscribeUi(viewModel: GalleryViewModel) {
        binding?.viewModel = viewModel
    }
}