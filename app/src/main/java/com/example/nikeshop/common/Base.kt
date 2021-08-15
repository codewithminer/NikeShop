package com.example.nikeshop.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nikeshop.R
import java.lang.IllegalStateException

abstract class NikeFragment: Fragment(), NikeView {
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout

    override val viewContext: Context?
        get() = context
}

abstract class NikeActivity: AppCompatActivity(), NikeView {

    override val rootView: CoordinatorLayout?
        get() {
            val viewGroup = window.decorView.findViewById(android.R.id.content) as ViewGroup
            if (viewGroup !is CoordinatorLayout){
                viewGroup.children.forEach {
                    if (it is CoordinatorLayout)
                        return it
                }
                throw IllegalStateException("RootView must be instance of ConstrainLayout")
            }
            else
                return viewGroup
        }

    override val viewContext: Context?
        get() = this

}

interface NikeView{
    val rootView:CoordinatorLayout?
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