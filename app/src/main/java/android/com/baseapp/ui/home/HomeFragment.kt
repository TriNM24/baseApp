package android.com.baseapp.ui.home

import android.com.baseapp.R
import android.com.baseapp.adapter.MainMenuAdapter
import android.com.baseapp.data.api.respone.ApiResult
import android.com.baseapp.databinding.FragmentHomeBinding
import android.com.baseapp.ui.base.BaseFragment
import android.view.View
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private lateinit var adapter: MainMenuAdapter

    override val resourceLayoutId: Int
        get() = R.layout.fragment_home

    override fun onInitView(root: View?) {
        adapter = MainMenuAdapter {
            Toast.makeText(requireActivity(),it,Toast.LENGTH_LONG).show()
        }
        binding?.listMenu?.adapter = adapter
    }

    override fun subscribeUi(viewModel: HomeViewModel) {
        binding?.viewModel = viewModel

        viewModel.mQuoteData.observe(this){ data ->
            when(data){
                is ApiResult.Success -> {
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_LONG).show()
                    val datas = listOf("1","2","3")
                    adapter.setData(datas)
                    adapter.notifyDataSetChanged()
                    mLoadingDialog.dismiss()
                }
                is ApiResult.Error -> {
                    Toast.makeText(requireContext(),"Error: ${data.message}",Toast.LENGTH_LONG).show()
                    mLoadingDialog.dismiss()
                }
                is ApiResult.Loading -> {
                    mLoadingDialog.show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getApi()
    }
}