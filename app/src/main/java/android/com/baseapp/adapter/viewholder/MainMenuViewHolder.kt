package android.com.baseapp.adapter.viewholder

import android.com.baseapp.adapter.base.AnyViewHolder
import android.com.baseapp.databinding.ItemMainMenuBinding

class MainMenuViewHolder(val mBinding: ItemMainMenuBinding) : AnyViewHolder<String>(mBinding.root) {
    override fun bind(data: String) {
        mBinding.btnItem.text = data
    }
}