package com.example.model

/**
 * Gamer profile model representing a player in the DXD ecosystem.
 * Formatted with default arguments for clean Firestore serialization.
 */
data class GamerProfile(
    val id: String = "",
    val gamerTag: String = "",
    val clanTag: String = "DXD",
    val email: String = "",
    val bio: String = "Competitive FPS & MOBA Player | DXD Elite Squad Leader",
    val avatarUrl: String = "",
    val bannerUrl: String = "",
    val level: Int = 42,
    val rankTitle: String = "Immortal III",
    val dxdCoins: Int = 2450,
    val wins: Int = 348,
    val losses: Int = 112,
    val kdRatio: Float = 3.42f,
    val winRate: Float = 75.6f,
    val badges: List<String> = listOf("Tournament MVP", "Apex Predator", "Night Cup Champion", "Clutch God"),
    val favoriteGames: List<String> = listOf("Valorant", "Apex Legends", "Call of Duty: Warzone", "CS2"),
    val isOnline: Boolean = true,
    val statusText: String = "Streaming DXD Scrims @ 8 PM"
)
