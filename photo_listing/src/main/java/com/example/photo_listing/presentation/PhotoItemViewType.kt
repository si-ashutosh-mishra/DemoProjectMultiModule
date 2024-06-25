package com.example.photo_listing.presentation

enum class PhotoItemViewType(val id: Int) {
    CAROUSEL(1),
    MATCHPHOTOS(2),
    TRAINING(3),
    BEHINDSCENES(4),
    BANNER(5),
    UNKNOWN(-1)
}