package android.com.baseapp.ui.expendableList

import android.annotation.SuppressLint
import android.com.baseapp.R
import android.com.baseapp.databinding.ListGroupBinding
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil


class CustomizedExpandableListAdapter(
    val context: Context,
    val expandableTitleList: List<String>,
    val expandableDetailList: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {


    override fun getGroupCount(): Int {
        return expandableTitleList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return expandableDetailList[this.expandableTitleList[groupPosition]]?.size?:0
    }

    override fun getGroup(groupPosition: Int): Any {
        return expandableTitleList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.expandableDetailList[this.expandableTitleList[groupPosition]]?.get(childPosition)?:""
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    @SuppressLint("InflateParams")
    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var mConvertView = convertView
        val listTitle = getGroup(groupPosition) as String
        if (mConvertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            mConvertView = ListGroupBinding.inflate(layoutInflater).root
        }

        val binding = DataBindingUtil.getBinding<ListGroupBinding>(mConvertView)
        binding?.title = listTitle
        binding?.groupArrow?.isActivated = isExpanded
        return mConvertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val expandedListText = getChild(groupPosition, childPosition) as String
        var mConvertView = convertView
        if (mConvertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            mConvertView = layoutInflater.inflate(R.layout.list_item, null)
        }
        val expandedListTextView = mConvertView?.findViewById(R.id.expandedListItem) as TextView
        expandedListTextView.text = expandedListText
        return mConvertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }


}