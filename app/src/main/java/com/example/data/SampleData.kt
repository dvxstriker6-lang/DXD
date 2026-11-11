package com.example.data

import com.example.model.ChatMessage
import com.example.model.Clan
import com.example.model.Conversation
import com.example.model.GameHub
import com.example.model.GamerProfile
import com.example.model.LeaderboardEntry
import com.example.model.NotificationItem
import com.example.model.NotificationType
import com.example.model.Post
import com.example.model.Tournament
import com.example.model.VoiceRoom

object SampleData {

    val currentUser = GamerProfile(
        id = "user_001",
        gamerTag = "VORTEX_X",
        clanTag = "DXD",
        email = "vortex@dxdgaming.gg",
        bio = "DXD Founding Vanguard. Global Valorant Radiant #42. Scrims & Tournament inquiries in DM.",
        avatarUrl = "",
        bannerUrl = "",
        level = 48,
        rankTitle = "Radiant #42",
        dxdCoins = 3850,
        wins = 428,
        losses = 134,
        kdRatio = 3.65f,
        winRate = 76.2f,
        badges = listOf(
            "Tournament MVP",
            "Radiant Ace",
            "DXD Night Cup 2026 Champion",
            "100+ Win Streak",
            "Sniper God"
        ),
        favoriteGames = listOf("Valorant", "Apex Legends", "Call of Duty: Warzone", "CS2"),
        isOnline = true,
        statusText = "Ready for DXD Night Cup Semifinals 🏆"
    )

    data class LiveStreamStory(
        val id: String,
        val gamerTag: String,
        val clanTag: String,
        val game: String,
        val viewers: String,
        val isLive: Boolean = true
    )

    val liveStories = listOf(
        LiveStreamStory("s1", "NeonSpecter", "DXD", "Valorant", "4.8K"),
        LiveStreamStory("s2", "ValkyriePro", "DXD", "Apex", "12.4K"),
        LiveStreamStory("s3", "GhostRider", "RONIN", "Warzone", "3.1K"),
        LiveStreamStory("s4", "ShadowBlade", "DXD", "CS2", "8.9K"),
        LiveStreamStory("s5", "CyberNova", "PHANTOM", "LoL", "5.6K")
    )

    val initialPosts = listOf(
        Post(
            id = "post_1",
            authorId = "user_002",
            authorTag = "NeonSpecter",
            authorClan = "DXD",
            authorLevel = 52,
            isAuthorPro = true,
            game = "Valorant",
            content = "Just dropped a 1v5 clutch on Haven A-Site to secure match point against Team Ronin! Never count the DXD squad out 🔥 Who wants to squad up for tonight's scrims?",
            highlightBadge = "ACE 1v5 CLUTCH",
            matchStat = "38 Kills • 4.2 K/D • MVP",
            likesCount = 1420,
            commentsCount = 184,
            sharesCount = 68,
            isLiked = false,
            timestamp = "12m ago"
        ),
        Post(
            id = "post_2",
            authorId = "user_003",
            authorTag = "ValkyriePro",
            authorClan = "DXD",
            authorLevel = 45,
            isAuthorPro = true,
            game = "Apex Legends",
            content = "Predator lobby wiped! 18 squad kills with the new Wingman meta. Running rank grind until 2 AM. Drop your gamer tags below to get invited to squad voice!",
            highlightBadge = "CHAMPION SQUAD",
            matchStat = "5,240 Dmg • 14 Kills • Win",
            likesCount = 985,
            commentsCount = 92,
            sharesCount = 34,
            isLiked = true,
            timestamp = "45m ago"
        ),
        Post(
            id = "post_3",
            authorId = "user_004",
            authorTag = "CipherZero",
            authorClan = "RONIN",
            authorLevel = 38,
            isAuthorPro = false,
            game = "Warzone",
            content = "Final circle in Urzikstan, solo versus trio with nothing but a DMR and throwing knives. GG to the squad for holding rotation angles!",
            highlightBadge = "VICTORY ROYALE",
            matchStat = "22 Kills • Gulag Escaped",
            likesCount = 642,
            commentsCount = 57,
            sharesCount = 19,
            isLiked = false,
            timestamp = "2h ago"
        ),
        Post(
            id = "post_4",
            authorId = "user_005",
            authorTag = "ApexTitan",
            authorClan = "DXD",
            authorLevel = 60,
            isAuthorPro = true,
            game = "CS2",
            content = "DXD Premier division roster has officially locked in. See everyone on the server for the championship bracket tomorrow. Full focus mode engaged ⚡",
            highlightBadge = "TOURNAMENT ANNOUNCEMENT",
            matchStat = "Premier Rating 24,500+",
            likesCount = 2100,
            commentsCount = 312,
            sharesCount = 145,
            isLiked = true,
            timestamp = "4h ago"
        )
    )

    val gameHubs = listOf(
        GameHub(
            id = "hub_val",
            name = "Valorant Elite",
            category = "Tactical Shooter",
            memberCount = "184K",
            onlineCount = "14.2K",
            activeLobbies = 86,
            description = "High-tier competitive scrims, lineup sharing, and ranked 5-stack recruitment.",
            tag = "#VALORANT",
            isJoined = true
        ),
        GameHub(
            id = "hub_apex",
            name = "Apex Champions Hub",
            category = "Battle Royale",
            memberCount = "126K",
            onlineCount = "9.8K",
            activeLobbies = 54,
            description = "Predator & Master rank grinding, tourney announcements, and meta discussions.",
            tag = "#APEX",
            isJoined = true
        ),
        GameHub(
            id = "hub_wz",
            name = "Warzone Ops Center",
            category = "Battle Royale",
            memberCount = "98K",
            onlineCount = "6.5K",
            activeLobbies = 39,
            description = "Resurgence speedruns, weapon loadouts, and nightly custom lobbies.",
            tag = "#WARZONE",
            isJoined = false
        ),
        GameHub(
            id = "hub_cs2",
            name = "CS2 Tactical Syndicate",
            category = "Tactical Shooter",
            memberCount = "142K",
            onlineCount = "11.1K",
            activeLobbies = 68,
            description = "Premier 20k+ matchmaking, team recruitment, smoke lineups, and DXD Cups.",
            tag = "#CS2",
            isJoined = false
        ),
        GameHub(
            id = "hub_lol",
            name = "LoL Summoner Rift",
            category = "MOBA",
            memberCount = "210K",
            onlineCount = "19.4K",
            activeLobbies = 112,
            description = "Clash team builder, patch breakdowns, and 1v1 Mid lane tournaments.",
            tag = "#LEAGUE",
            isJoined = false
        )
    )

    val tournaments = listOf(
        Tournament(
            id = "tourney_1",
            title = "DXD Night Cup: Invitational",
            game = "Valorant",
            prizePool = "$10,000 USD",
            registeredTeams = 28,
            maxTeams = 32,
            startTime = "Today @ 8:00 PM EST",
            entryFee = "500 DXD Coins",
            status = "REGISTRATION CLOSING",
            format = "5v5 Double Elimination",
            isRegistered = true
        ),
        Tournament(
            id = "tourney_2",
            title = "Apex Predator Showdown",
            game = "Apex Legends",
            prizePool = "$5,000 USD",
            registeredTeams = 18,
            maxTeams = 20,
            startTime = "Tomorrow @ 6:00 PM EST",
            entryFee = "Free Entry",
            status = "REGISTRATION OPEN",
            format = "Custom Lobby • 6 Matches",
            isRegistered = false
        ),
        Tournament(
            id = "tourney_3",
            title = "Warzone Trios Deathmatch",
            game = "Call of Duty: Warzone",
            prizePool = "$2,500 USD",
            registeredTeams = 12,
            maxTeams = 24,
            startTime = "Saturday @ 9:00 PM EST",
            entryFee = "250 DXD Coins",
            status = "REGISTRATION OPEN",
            format = "Trios Kill Race",
            isRegistered = false
        ),
        Tournament(
            id = "tourney_4",
            title = "CS2 1v1 Aim King Challenge",
            game = "CS2",
            prizePool = "$1,000 USD + Skins",
            registeredTeams = 64,
            maxTeams = 64,
            startTime = "Sunday @ 4:00 PM EST",
            entryFee = "100 DXD Coins",
            status = "BRACKET LOCKED",
            format = "1v1 Single Elimination",
            isRegistered = false
        )
    )

    val clans = listOf(
        Clan(
            id = "clan_1",
            name = "DXD Syndicate",
            tag = "DXD",
            membersCount = 48,
            maxMembers = 50,
            rank = 1,
            winStreak = 18,
            totalWins = 1890,
            leaderTag = "VORTEX_X"
        ),
        Clan(
            id = "clan_2",
            name = "Ronin Vanguard",
            tag = "RONIN",
            membersCount = 46,
            maxMembers = 50,
            rank = 2,
            winStreak = 12,
            totalWins = 1650,
            leaderTag = "ShadowBlade"
        ),
        Clan(
            id = "clan_3",
            name = "Phantom Esports",
            tag = "PHANTOM",
            membersCount = 50,
            maxMembers = 50,
            rank = 3,
            winStreak = 9,
            totalWins = 1420,
            leaderTag = "CyberNova"
        )
    )

    val leaderboard = listOf(
        LeaderboardEntry(1, "VORTEX_X", "DXD", 4120, 78.4f, "Grandmaster", "Valorant"),
        LeaderboardEntry(2, "NeonSpecter", "DXD", 3980, 76.1f, "Grandmaster", "Valorant"),
        LeaderboardEntry(3, "ValkyriePro", "DXD", 3890, 74.8f, "Predator", "Apex"),
        LeaderboardEntry(4, "KageStorm", "RONIN", 3740, 71.9f, "Immortal", "Valorant"),
        LeaderboardEntry(5, "BlazeHunter", "PHANTOM", 3650, 69.5f, "Master", "Warzone"),
        LeaderboardEntry(6, "PhantomGhost", "PHANTOM", 3590, 68.2f, "Global Elite", "CS2")
    )

    val voiceRooms = listOf(
        VoiceRoom("vr1", "DXD Squad Alpha [Valorant]", "Valorant", 4, 5, 14, false),
        VoiceRoom("vr2", "Night Cup Strategy & Warmup", "Valorant", 3, 5, 18, true),
        VoiceRoom("vr3", "Apex Predator Ranked Trio", "Apex Legends", 2, 3, 22, false)
    )

    val conversations = listOf(
        Conversation(
            id = "c_clan",
            name = "DXD Main Squad",
            gamerTag = "Squad Chat (48 members)",
            clanTag = "DXD",
            lastMessage = "ValkyriePro: Scrims starting in 15 mins! Hop in Voice Alpha",
            lastMessageTime = "2m ago",
            unreadCount = 3,
            isGroup = true,
            isOnline = true,
            currentActivity = "4 Members in Voice Alpha",
            messages = listOf(
                ChatMessage("m1", "user_002", "NeonSpecter", "Are we locking in Breeze and Haven for tonight?", "7:30 PM", false),
                ChatMessage("m2", "user_001", "VORTEX_X", "Yes, Haven first. We run Double Initiator.", "7:32 PM", true),
                ChatMessage("m3", "user_003", "ValkyriePro", "Scrims starting in 15 mins! Hop in Voice Alpha", "7:45 PM", false)
            )
        ),
        Conversation(
            id = "c_neon",
            name = "NeonSpecter",
            gamerTag = "NeonSpecter#001",
            clanTag = "DXD",
            lastMessage = "Let's run a warm-up deathmatch right now",
            lastMessageTime = "8m ago",
            unreadCount = 1,
            isGroup = false,
            isOnline = true,
            currentActivity = "In Match: Valorant Ranked",
            messages = listOf(
                ChatMessage("m10", "user_002", "NeonSpecter", "Yo Vortex, you seen the bracket for tonight?", "6:15 PM", false),
                ChatMessage("m11", "user_001", "VORTEX_X", "Yeah, we play against Team Ronin in round 2.", "6:20 PM", true),
                ChatMessage("m12", "user_002", "NeonSpecter", "Let's run a warm-up deathmatch right now", "6:22 PM", false, isGameInvite = true, inviteGame = "Valorant", inviteLobbyCode = "DXD-LOBBY-9942")
            )
        ),
        Conversation(
            id = "c_valk",
            name = "ValkyriePro",
            gamerTag = "ValkyriePro#777",
            clanTag = "DXD",
            lastMessage = "Gave your clutch clip 5 flames 🔥",
            lastMessageTime = "1h ago",
            unreadCount = 0,
            isGroup = false,
            isOnline = true,
            currentActivity = "In Apex Legends Lobby",
            messages = listOf(
                ChatMessage("m20", "user_003", "ValkyriePro", "That 1v5 on Haven was insane!", "5:00 PM", false),
                ChatMessage("m21", "user_003", "ValkyriePro", "Gave your clutch clip 5 flames 🔥", "5:01 PM", false)
            )
        ),
        Conversation(
            id = "c_ghost",
            name = "GhostRider",
            gamerTag = "GhostRider#404",
            clanTag = "RONIN",
            lastMessage = "Good luck tonight in the Night Cup semifinal!",
            lastMessageTime = "3h ago",
            unreadCount = 0,
            isGroup = false,
            isOnline = false,
            currentActivity = "Offline",
            messages = listOf(
                ChatMessage("m30", "user_004", "GhostRider", "Good luck tonight in the Night Cup semifinal!", "3:10 PM", false)
            )
        )
    )

    val notifications = listOf(
        NotificationItem(
            id = "notif_1",
            type = NotificationType.TOURNAMENT,
            title = "Tournament Alert: DXD Night Cup",
            description = "Round 1 kicks off in 30 minutes! Make sure all 5 players are in the custom lobby.",
            timeAgo = "10m ago",
            isRead = false,
            actionLabel = "Enter Tournament Lobby"
        ),
        NotificationItem(
            id = "notif_2",
            type = NotificationType.CLAN_INVITE,
            title = "Squad Invitation",
            description = "[DXD] Squad Alpha requested you to join their Valorant Competitive 5-Stack.",
            timeAgo = "25m ago",
            isRead = false,
            actionLabel = "Accept Squad",
            hasActionButtons = true
        ),
        NotificationItem(
            id = "notif_3",
            type = NotificationType.REWARD,
            title = "Daily Gamer Bonus Claimed!",
            description = "You received +250 DXD Coins and +50 Clan XP for your 7-day login streak.",
            timeAgo = "1h ago",
            isRead = true,
            actionLabel = "View Wallet"
        ),
        NotificationItem(
            id = "notif_4",
            type = NotificationType.MENTION,
            title = "NeonSpecter mentioned you",
            description = "\"Just dropped a 1v5 clutch to secure match point... Never count DXD out!\"",
            timeAgo = "2h ago",
            isRead = true,
            actionLabel = "View Post"
        ),
        NotificationItem(
            id = "notif_5",
            type = NotificationType.MATCH_READY,
            title = "Ranked Match Found",
            description = "Server: US East (14ms). All 10 players connected.",
            timeAgo = "4h ago",
            isRead = true
        )
    )
}
