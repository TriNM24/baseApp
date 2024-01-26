package android.com.baseapp.ui.expendableList

import android.com.baseapp.R
import android.com.baseapp.databinding.FragmentListBinding
import android.com.baseapp.ui.base.BaseFragment
import android.view.View
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpendableListFragment : BaseFragment<FragmentListBinding, ExpendableListViewModel>() {

    override val resourceLayoutId = R.layout.fragment_list
    override fun onInitView(root: View?) {

    }

    override fun subscribeUi(viewModel: ExpendableListViewModel) {
        binding?.viewModel = viewModel
    }
}