package android.com.baseapp.ui.home

import android.com.baseapp.R
import android.com.baseapp.adapter.MainMenuAdapter
import android.com.baseapp.data.api.baseRespone.ApiResult
import android.com.baseapp.databinding.FragmentHomeBinding
import android.com.baseapp.ui.base.BaseFragment
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private lateinit var adapter: MainMenuAdapter

    override val resourceLayoutId = R.layout.fragment_home

    override fun onInitView(root: View?) {
        adapter = MainMenuAdapter {

            when (it) {
                "Expendable header list" -> {
                    findNavController().navigate(R.id.action_nav_home_to_expendable_navigation, bundleOf("title" to "Dynamic title"))
                }
                "Google map" -> {
                    findNavController().navigate(R.id.action_nav_home_to_mapFragment, bundleOf("title" to "Dynamic title map"))
                }
                "Draw text in image" -> {
                    findNavController().navigate(R.id.action_nav_home_to_drawTextFragment, bundleOf("title" to "Dynamic title draw text"))
                }
                else -> {
                    Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.listMenu.adapter = adapter
    }

    override fun subscribeUi(viewModel: HomeViewModel) {
        binding.viewModel = viewModel

        viewModel.mQuoteData.observe(this) { data ->
            when (data) {
                is ApiResult.Success -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                    val datas = listOf("Expendable header list", "Google map", "Draw text in image")
                    adapter.setData(datas)
                    adapter.notifyDataSetChanged()
                    mLoadingDialog.dismiss()
                }

                is ApiResult.Error -> {
                    Toast.makeText(requireContext(), "Error: ${data.message}", Toast.LENGTH_LONG)
                        .show()
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