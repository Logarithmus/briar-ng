package dev.logarithmus.p2pdroid.wifi.server.dialogs

import android.R
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import android.view.Window

class LoaderDialog : Dialog {
    private var mContext: Context
    private val cancellable = false
    private var animationView: AnimationView? = null

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

    constructor(context: Context) : super(context, R.style.full_screen_dialog) {
        mContext = context
        setDialogView()
    }

    fun setDialogView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setContentView(R.layout.view_loader)
        setCancelable(cancellable)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window!!.setBackgroundDrawableResource(R.color.transparent)
        window!!.attributes.windowAnimations = R.style.dialog_animation
        animationView = findViewById(R.id.gif)
    }

    fun setDrawable(drawableId: Int) {
        animationView!!.visibility = View.VISIBLE
        animationView!!.setMovieResource(drawableId)
    }
}