package android.com.baseapp.ui.home

import android.com.baseapp.R
import android.com.baseapp.databinding.FragmentHomeBinding
import android.com.baseapp.ui.base.BaseFragment
import android.com.baseapp.utils.LogTag
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val resourceLayoutId: Int
        get() = R.layout.fragment_home

    override fun onInitView(root: View?) {

    }

    override fun subscribeUi(viewModel: HomeViewModel) {
        binding?.viewModel = viewModel
    }
}