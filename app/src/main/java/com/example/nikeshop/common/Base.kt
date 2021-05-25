package com.example.nikeshop

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class NikeFragment: Fragment(), NikeView {
    override fun setProgressIndicator(mustShow: Boolean) {
        TODO("Not yet implemented")
    }
}

abstract class NikeActivity: AppCompatActivity(), NikeView{
    override fun setProgressIndicator(mustShow: Boolean) {
        TODO("Not yet implemented")
    }
}

interface NikeView{
    fun setProgressIndicator(mustShow: Boolean)
}

abstract class NikeViewModel: ViewModel()