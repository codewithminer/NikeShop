package com.example.nikeshop.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.example.nikeshop.R
import kotlinx.android.synthetic.main.view_toolbar.view.*

class NikeToolBar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var onBackButtonClickListener: View.OnClickListener? = null
        set(value) {
            field = value
            back_btn.setOnClickListener(onBackButtonClickListener)
        }

    init {
        inflate(context, R.layout.view_toolbar, this)
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.NikeToolBar)
            val title = a.getString(R.styleable.NikeToolBar_nt_toolbar)
            if (title != null && !title.isEmpty()) {
                toolbarTitleTv.text = title
            }
            a.recycle()
        }
    }
}