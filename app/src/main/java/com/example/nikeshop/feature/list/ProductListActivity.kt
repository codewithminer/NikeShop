package com.example.nikeshop.feature.list

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nikeshop.R
import com.example.nikeshop.common.EXTRA_KEY_DATA
import com.example.nikeshop.common.NikeActivity
import com.example.nikeshop.data.Product
import com.example.nikeshop.feature.main.ProductListAdapter
import com.example.nikeshop.feature.main.VIEW_TYPE_LARGE
import com.example.nikeshop.feature.main.VIEW_TYPE_SMALL
import com.example.nikeshop.feature.product.ProductDetailActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.item_prodcuct.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductListActivity : NikeActivity(), ProductListAdapter.ProductOnClickListener {

    val productListViewModel: ProductListViewModel by viewModel { parametersOf(intent.extras!!.getInt(
        EXTRA_KEY_DATA)) }
    val productListAdapter:ProductListAdapter by inject {parametersOf(VIEW_TYPE_SMALL)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val gridLayoutManager = GridLayoutManager(this, 2)
        productRv.layoutManager = gridLayoutManager
        productRv.adapter = productListAdapter
        productListAdapter.productOnClickListener = this

        viewTypeChangerBtn.setOnClickListener {
            if (productListAdapter.viewType == VIEW_TYPE_SMALL){
                viewTypeChangerBtn.setImageResource(R.drawable.ic_view_type_large)
                productListAdapter.viewType = VIEW_TYPE_LARGE
                gridLayoutManager.spanCount = 1
                productListAdapter.notifyDataSetChanged()
            }
            else{
                productListAdapter.viewType = VIEW_TYPE_SMALL
                viewTypeChangerBtn.setImageResource(R.drawable.ic_grid)
                gridLayoutManager.spanCount = 2
                productListAdapter.notifyDataSetChanged()
            }
        }

        productListViewModel.selectedSortTitleLiveData.observe(this){
            selectedSortTitleTv.text = getString(it)
        }

        productListViewModel.progressBarLiveData.observe(this){
            setProgressIndicator(it)
        }

        toolbarView.onBackButtonClickListener = View.OnClickListener {
            finish()
        }


        sortBtn.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(this)
                .setSingleChoiceItems(R.array.sort, productListViewModel.sort,
                ){ dialog, selectedSortIndex ->
                    dialog.dismiss()
                    productListViewModel.onSelectedSortChangedByUser(selectedSortIndex)
                }.setTitle(R.string.sort)

            dialog.show()
        }

        productListViewModel.productsLiveData.observe(this){
            productListAdapter.products = it as ArrayList<Product>
        }
    }

    override fun onProductClick(product: Product) {
        startActivity(Intent(this, ProductDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA, product)
        })
    }
}