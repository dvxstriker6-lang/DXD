package com.example.model

/**
 * Gaming Community Hub model
 */
data class GameHub(
    val id: String = "",
    val name: String = "",
    val category: String = "Tactical FPS",
    val memberCount: String = "124K",
    val onlineCount: String = "8.2K",
    val activeLobbies: Int = 42,
    val description: String = "",
    val tag: String = "#VALORANT",
    val isJoined: Boolean = false
)

/**
 * Esports & Community Tournament model
 */
data class Tournament(
    val id: String = "",
    val title: String = "",
    val game: String = "",
    val prizePool: String = "$10,000",
    val registeredTeams: Int = 28,
    val maxTeams: Int = 32,
    val startTime: String = "Starts in 2h 45m",
    val entryFee: String = "Free / 500 DXD Coins",
    val status: String = "REGISTRATION OPEN", // LIVE, UPCOMING, COMPLETED
    val format: String = "5v5 Single Elimination",
    val isRegistered: Boolean = false
)

/**
 * Competitive Clan / Gaming Organization
 */
data class Clan(
    val id: String = "",
    val name: String = "",
    val tag: String = "",
    val membersCount: Int = 48,
    val maxMembers: Int = 50,
    val rank: Int = 1,
    val winStreak: Int = 14,
    val totalWins: Int = 1420,
    val leaderTag: String = "Vortex_X"
)

/**
 * Leaderboard player entry
 */
data class LeaderboardEntry(
    val rank: Int = 1,
    val gamerTag: String = "",
    val clanTag: String = "DXD",
    val mmr: Int = 3850,
    val winRate: Float = 78.5f,
    val tier: String = "Grandmaster",
    val mainGame: String = "Valorant"
)
