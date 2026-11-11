package com.example.model

/**
 * Social gaming feed post model.
 */
data class Post(
    val id: String = "",
    val authorId: String = "",
    val authorTag: String = "",
    val authorClan: String = "DXD",
    val authorLevel: Int = 1,
    val isAuthorPro: Boolean = false,
    val game: String = "",
    val content: String = "",
    val highlightBadge: String? = null,
    val matchStat: String? = null,
    val likesCount: Int = 0,
    val commentsCount: Int = 0,
    val sharesCount: Int = 0,
    val isLiked: Boolean = false,
    val timestamp: String = "Just now"
)
