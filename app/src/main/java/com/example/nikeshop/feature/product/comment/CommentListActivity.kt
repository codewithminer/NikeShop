package com.example.nikeshop.feature.product.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.common.EXTRA_KEY_ID
import com.example.nikeshop.data.Comment
import kotlinx.android.synthetic.main.activity_comment_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CommentListActivity : AppCompatActivity() {

    val commentListViewModel: CommentListViewModel by viewModel { parametersOf(intent.extras!!.getInt(
        EXTRA_KEY_ID)) }

    val commentAdapter = CommentAdapter(true)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_list)

        commentListViewModel.commentsLiveData.observe(this){
            commentRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            commentAdapter.comments = it as ArrayList<Comment>
            commentRv.adapter = commentAdapter
        }
    }
}