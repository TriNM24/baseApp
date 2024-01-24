package android.com.baseapp.utils

import android.app.Dialog
import android.com.baseapp.R
import android.com.baseapp.databinding.DialogLoadingBinding
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog


object Utils {
    fun getLoading(context: Context, message: String? = null): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.WrapContentDialog)
        //val view = LayoutInflater.from(context).let { it.inflate(R.layout.dialog_loading, null) }
        val binding = DialogLoadingBinding.inflate(LayoutInflater.from(context))
        message?.let {
            binding.loadingMsg.text = it
        }
        builder.setView(binding.root)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // This should be called once in your Fragment's onViewCreated() or in Activity onCreate() method to avoid dialog duplicates.
        return dialog
    }
}