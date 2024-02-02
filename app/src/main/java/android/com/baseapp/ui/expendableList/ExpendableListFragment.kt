package android.com.baseapp.ui.expendableList

import android.com.baseapp.R
import android.com.baseapp.adapter.CustomizedExpandableListAdapter
import android.com.baseapp.databinding.FragmentListBinding
import android.com.baseapp.ui.base.BaseFragment
import android.com.baseapp.adapter.itemObject.ExpandableListDataItems.getData
import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExpendableListFragment : BaseFragment<FragmentListBinding, ExpendableListViewModel>() {

    override val resourceLayoutId = R.layout.fragment_list

    var expandableListAdapter: ExpandableListAdapter? = null
    lateinit var expandableTitleList: List<String>
    lateinit var expandableDetailList: HashMap<String, List<String>>

    override fun onInitView(root: View?) {
        expandableDetailList = getData()
        expandableTitleList = ArrayList(expandableDetailList.keys)
        expandableListAdapter = CustomizedExpandableListAdapter(
            requireContext(), expandableTitleList,
            expandableDetailList
        )

        binding.listMenu.setAdapter(expandableListAdapter)

        binding.listMenu.setOnGroupExpandListener { groupPosition ->
            Toast.makeText(
                requireContext(),
                "${expandableTitleList[groupPosition]} List Expanded.",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.listMenu.setOnGroupCollapseListener { groupPosition ->
            Toast.makeText(
                requireContext(),
                "${expandableTitleList[groupPosition]} List Collapsed.",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.listMenu.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            Toast.makeText(
                requireContext(), expandableTitleList[groupPosition]
                        + " -> "
                        + expandableDetailList[expandableTitleList[groupPosition]]?.get(
                    childPosition
                ), Toast.LENGTH_SHORT
            ).show()
            false
        }
        //expand group one for default
        binding.listMenu.expandGroup(0)
    }

    override fun subscribeUi(viewModel: ExpendableListViewModel) {
        binding.viewModel = viewModel
    }
}