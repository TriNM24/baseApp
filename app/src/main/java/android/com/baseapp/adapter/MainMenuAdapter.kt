package android.com.baseapp.adapter

import android.com.baseapp.R
import android.com.baseapp.adapter.base.AnyAdapter
import android.com.baseapp.adapter.base.AnyViewHolder
import android.com.baseapp.adapter.viewholder.MainMenuViewHolder
import android.com.baseapp.databinding.ItemMainMenuBinding
import android.view.View

class MainMenuAdapter(private val clickHandler: ((data: String) -> Unit)? = null): AnyAdapter<String>() {
    override fun getLayoutId(): Int {
        return R.layout.item_main_menu
    }

    override fun onCreateViewHolder(view: View): AnyViewHolder<String> {
        return MainMenuViewHolder(ItemMainMenuBinding.bind(view))
    }

    override fun onBoundViewHolder(holder: AnyViewHolder<String>, data: String) {
        when(holder){
            is MainMenuViewHolder -> holder.mBinding.btnItem.setOnClickListener{
                clickHandler?.invoke(data)
            }
        }
    }
}