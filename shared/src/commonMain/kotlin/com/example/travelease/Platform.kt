package com.example.travelease

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform