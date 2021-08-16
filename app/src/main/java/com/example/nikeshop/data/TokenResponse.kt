package com.example.nikeshop.data

data class TokenResponse(
    val token_type: String,
    val expires_in: Int,
    val access_token: String,
    val refresh_token: String
)