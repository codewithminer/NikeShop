package com.example.nikeshop.data

data class Checkout(
    val purchase_success: Boolean,
    val payable_price: Int,
    val payment_status: String
)
