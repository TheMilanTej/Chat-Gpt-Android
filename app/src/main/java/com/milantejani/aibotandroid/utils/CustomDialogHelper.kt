package com.milantejani.aibotandroid.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.annotation.LayoutRes

open class CustomDialogHelper(private val mContext: Context) {

    private var dialog: Dialog? = null

    fun create(@LayoutRes layoutId: Int, onCancelListener: (() -> Unit)? = null): View {
        // Inflate the custom layout
        val inflater = LayoutInflater.from(mContext)
        val dialogView = inflater.inflate(layoutId, null) as View

        // Create the custom dialog
        dialog = Dialog(mContext)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(dialogView)

        // Set cancel listener if provided
        onCancelListener?.let {
            dialog?.setOnDismissListener { _ ->
                onCancelListener.invoke()
            }
        }

        return dialogView
    }

    fun cancelOnTouch(b: Boolean) {
        dialog?.let {
            it.setCancelable(b)
            it.setCanceledOnTouchOutside(b)
        }
    }

    fun show() {
        dialog?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    fun dismiss() {
        dialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    fun isShowing(): Boolean? {
        return dialog?.isShowing
    }
}
