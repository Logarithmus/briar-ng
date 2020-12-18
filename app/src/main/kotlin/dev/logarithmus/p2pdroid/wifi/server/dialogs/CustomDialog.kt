package dev.logarithmus.p2pdroid.wifi.server.dialogs

import android.R
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class CustomDialog : Dialog, View.OnClickListener {
    private var mContext: Context
    private var isCancellable = false
    private var textView: TextView? = null
    private var img: ImageView? = null
    private var btnPositiveBtn: Button? = null
    private var eventListener: DialogEventListener? = null

    constructor(context: Context, theme: Int) : super(context, theme) {
        mContext = context
        setDialogView()
    }

    protected constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener) {
        mContext = context
        setDialogView()
    }

    constructor(context: Context): super(context, R.style.full_screen_dialog) {
        mContext = context
        setDialogView()
    }

    constructor(context: Context, eventListener: DialogEventListener?) : super(
        context,
        R.style.full_screen_dialog
    ) {
        mContext = context
        this.eventListener = eventListener
        setDialogView()
    }

    fun setDialogView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setContentView(R.layout.view_dialog)
        setCancelable(isCancellable)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window!!.setBackgroundDrawableResource(R.color.transparent)
        window!!.attributes.windowAnimations = R.style.dialog_animation
        textView = findViewById(R.id.dialog_txt)
        img = findViewById(R.id.dialog_img)
        btnPositiveBtn = findViewById(R.id.btn_ok)
        btnPositiveBtn.setOnClickListener(this)
    }

    fun setImg(imageResource: Int, show: Boolean) {
        showImg(show)
        if (imageResource != 0) img!!.setImageResource(imageResource)
    }

    fun showImg(show: Boolean) {
        img?.visibility = if (show) { View.VISIBLE } else { View.GONE }
    }

    val dialogText: TextView?
        get() = textView

    fun setText(message: String, show: Boolean) {
        showTitle(show)
        val spannableString: SpannableString = getSpannedText(mContext, 1.8f, message)
        textView?.text = spannableString
    }

    fun showTitle(show: Boolean) {
        textView?.visibility = if (show) { View.VISIBLE } else { View.GONE }
    }

    override fun onClick(v: View) {
        if (v === btnPositiveBtn) {
            dismiss()
            if (eventListener != null) eventListener!!.onPositiveButtonClicked(v)
        }
    }

    companion object {
        fun getSpannedText(context: Context, textSize: Float, text: String): SpannableString {
            val spannableString = SpannableString(text)
            if (text.indexOf("\n") != -1) {
                spannableString.setSpan(
                    ForegroundColorSpan(context.resources.getColor(R.color.activity_login_edittext_txt)),
                    text.indexOf("\n"), text.length, 0
                )
                spannableString.setSpan(RelativeSizeSpan(textSize), text.indexOf("\n"), text.length, 0)
            }
            return spannableString
        }
    }
}