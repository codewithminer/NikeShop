package com.example.nikeshop.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nikeshop.R

abstract class NikeFragment: Fragment(), NikeView {
    override val rootView: ConstraintLayout?
        get() = view as ConstraintLayout

    override val viewContext: Context?
        get() = context
}

abstract class NikeActivity: AppCompatActivity(), NikeView {

    override val rootView: ConstraintLayout?
        get() = window.decorView.rootView as ConstraintLayout

    override val viewContext: Context?
        get() = this

}

interface NikeView{
    val rootView:ConstraintLayout?
    val viewContext:Context?
    fun setProgressIndicator(mustShow: Boolean){
        rootView?.let {
            viewContext?.let { context ->
                var loadingView = it.findViewById<View>(R.id.loadingView)
                if(loadingView == null && mustShow){
                    loadingView = LayoutInflater.from(context).inflate(R.layout.view_loading, it, false)
                    it.addView(loadingView)
                }

                loadingView?.visibility = if (mustShow) View.VISIBLE else View.GONE
            }
        }
    }
}

abstract class NikeViewModel: ViewModel(){
    val progressBarLiveData = MutableLiveData<Boolean>()
}