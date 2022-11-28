package android.com.baseapp.utils

import android.app.Dialog
import android.com.baseapp.R
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

object Utils {
    fun getLoading(context: Context, message: String? = null): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.WrapContentDialog)
        val view = LayoutInflater.from(context).let { it.inflate(R.layout.dialog_loading, null) }
        message?.let {
            view.findViewById<TextView>(R.id.loading_msg)?.text = it
        }
        builder.setView(view)
        val dialog = builder.create()
        dialog.setCancelable(false)
        // This should be called once in your Fragment's onViewCreated() or in Activity onCreate() method to avoid dialog duplicates.
        // This should be called once in your Fragment's onViewCreated() or in Activity onCreate() method to avoid dialog duplicates.
        return dialog
    }
}