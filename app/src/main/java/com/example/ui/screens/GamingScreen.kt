package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Clan
import com.example.model.GameHub
import com.example.model.LeaderboardEntry
import com.example.model.Tournament
import com.example.ui.components.CyberBadge
import com.example.ui.components.GamerAvatar
import com.example.ui.components.NeonGamingCard
import com.example.ui.components.NeonOutlinedButton
import com.example.ui.components.NeonPrimaryButton
import com.example.ui.components.NeonSectionHeader
import com.example.ui.components.StatCounterBox
import com.example.ui.theme.DxdBrightRed
import com.example.ui.theme.DxdDarkObsidian
import com.example.ui.theme.DxdDarkRedContainer
import com.example.ui.theme.DxdNeonCyan
import com.example.ui.theme.DxdNeonGold
import com.example.ui.theme.DxdNeonGreen
import com.example.ui.theme.DxdNeonRed
import com.example.ui.theme.DxdPureBlack
import com.example.ui.theme.DxdRedGlowBorder
import com.example.ui.theme.DxdSurfaceVariant
import com.example.ui.theme.DxdTextMuted
import com.example.ui.theme.DxdTextPrimary
import com.example.ui.theme.DxdTextSecondary
import com.example.ui.theme.OrbitronFontFamily
import com.example.ui.theme.RajdhaniFontFamily

@Composable
fun GamingScreen(
    currentSubTab: String,
    onSubTabSelected: (String) -> Unit,
    gameHubs: List<GameHub>,
    tournaments: List<Tournament>,
    clans: List<Clan>,
    leaderboard: List<LeaderboardEntry>,
    onToggleJoinHub: (String) -> Unit,
    onRegisterTournament: (String) -> Unit
) {
    val subTabs = listOf(
        "HUBS" to "Game Hubs",
        "TOURNAMENTS" to "Tourneys",
        "CLANS" to "Clans",
        "LEADERBOARD" to "Ranking"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DxdPureBlack)
            .testTag("gaming_community_screen")
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            // Header Bar
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DxdDarkObsidian)
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = "GAMING COMMUNITY",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Competitive Arenas, Scrims & Global Rankings",
                        fontFamily = RajdhaniFontFamily,
                        fontSize = 13.sp,
                        color = DxdTextMuted
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Sub Tabs Row
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(subTabs) { (key, label) ->
                            val isSelected = currentSubTab == key
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (isSelected) DxdNeonRed else DxdSurfaceVariant)
                                    .border(
                                        width = 1.dp,
                                        color = if (isSelected) DxdBrightRed else Color(0xFF2B2E40),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .clickable { onSubTabSelected(key) }
                                    .padding(horizontal = 14.dp, vertical = 8.dp)
                                    .testTag("gaming_tab_$key")
                            ) {
                                Text(
                                    text = label.uppercase(),
                                    fontFamily = OrbitronFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    letterSpacing = 0.5.sp,
                                    color = if (isSelected) Color.White else DxdTextSecondary
                                )
                            }
                        }
                    }
                }
            }

            // Sub Tab Content
            when (currentSubTab) {
                "HUBS" -> {
                    item {
                        NeonSectionHeader(
                            title = "Active Competitive Hubs",
                            subtitle = "Join official title communities to find squads",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }

                    items(gameHubs, key = { it.id }) { hub ->
                        GameHubCard(
                            hub = hub,
                            onToggleJoin = { onToggleJoinHub(hub.id) }
                        )
                    }
                }

                "TOURNAMENTS" -> {
                    item {
                        NeonSectionHeader(
                            title = "Esports & Cups",
                            subtitle = "Official cash prize tournaments & scrim leagues",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }

                    items(tournaments, key = { it.id }) { tourney ->
                        TournamentCard(
                            tournament = tourney,
                            onRegister = { onRegisterTournament(tourney.id) }
                        )
                    }
                }

                "CLANS" -> {
                    item {
                        NeonSectionHeader(
                            title = "Elite Organizations",
                            subtitle = "Top tier competitive rosters & clan rankings",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }

                    items(clans, key = { it.id }) { clan ->
                        ClanCard(clan = clan)
                    }
                }

                "LEADERBOARD" -> {
                    item {
                        LeaderboardHeader()
                    }

                    items(leaderboard, key = { it.rank }) { entry ->
                        LeaderboardRowItem(entry = entry)
                    }
                }
            }
        }
    }
}

@Composable
fun GameHubCard(
    hub: GameHub,
    onToggleJoin: () -> Unit
) {
    NeonGamingCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        borderColor = if (hub.isJoined) DxdNeonRed else DxdRedGlowBorder
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(DxdDarkRedContainer)
                            .border(1.dp, DxdNeonRed, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SportsEsports,
                            contentDescription = null,
                            tint = DxdBrightRed,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = hub.name,
                            fontFamily = OrbitronFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.White
                        )
                        Text(
                            text = "${hub.category} • ${hub.tag}",
                            fontFamily = RajdhaniFontFamily,
                            fontSize = 12.sp,
                            color = DxdBrightRed
                        )
                    }
                }

                CyberBadge(
                    text = "${hub.activeLobbies} Lobbies",
                    color = DxdNeonGreen
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = hub.description,
                fontFamily = RajdhaniFontFamily,
                fontSize = 14.sp,
                color = DxdTextSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column {
                        Text(text = "MEMBERS", fontFamily = OrbitronFontFamily, fontSize = 9.sp, color = DxdTextMuted)
                        Text(text = hub.memberCount, fontFamily = OrbitronFontFamily, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.White)
                    }
                    Column {
                        Text(text = "ONLINE", fontFamily = OrbitronFontFamily, fontSize = 9.sp, color = DxdTextMuted)
                        Text(text = hub.onlineCount, fontFamily = OrbitronFontFamily, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = DxdNeonGreen)
                    }
                }

                if (hub.isJoined) {
                    NeonOutlinedButton(
                        text = "Joined",
                        onClick = onToggleJoin,
                        icon = Icons.Default.Check,
                        modifier = Modifier.height(40.dp)
                    )
                } else {
                    NeonPrimaryButton(
                        text = "Join Hub",
                        onClick = onToggleJoin,
                        modifier = Modifier.height(40.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TournamentCard(
    tournament: Tournament,
    onRegister: () -> Unit
) {
    NeonGamingCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        borderColor = if (tournament.isRegistered) DxdNeonGold else DxdNeonRed,
        glowIntensity = 0.6f
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CyberBadge(
                    text = tournament.game.uppercase(),
                    color = DxdBrightRed
                )
                Text(
                    text = tournament.status,
                    fontFamily = OrbitronFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = if (tournament.isRegistered) DxdNeonGold else DxdNeonGreen
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = tournament.title,
                fontFamily = OrbitronFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${tournament.format} • Entry: ${tournament.entryFee}",
                fontFamily = RajdhaniFontFamily,
                fontSize = 13.sp,
                color = DxdTextSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Stat row: Prize pool, slots, start time
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatCounterBox(
                    title = "Prize Pool",
                    value = tournament.prizePool,
                    valueColor = DxdNeonGold,
                    modifier = Modifier.weight(1f)
                )

                StatCounterBox(
                    title = "Teams",
                    value = "${tournament.registeredTeams}/${tournament.maxTeams}",
                    modifier = Modifier.weight(1f)
                )

                StatCounterBox(
                    title = "Schedule",
                    value = "Today",
                    valueColor = DxdBrightRed,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            NeonPrimaryButton(
                text = if (tournament.isRegistered) "Squad Registered ✓" else "Register Squad",
                onClick = onRegister,
                modifier = Modifier.fillMaxWidth(),
                icon = Icons.Default.EmojiEvents,
                testTag = "tournament_register_button_${tournament.id}"
            )
        }
    }
}

@Composable
fun ClanCard(clan: Clan) {
    NeonGamingCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(DxdDarkRedContainer)
                        .border(1.5.dp, DxdNeonRed, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "#${clan.rank}",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = DxdBrightRed
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "[${clan.tag}] ${clan.name}",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Leader: ${clan.leaderTag} • ${clan.membersCount}/${clan.maxMembers} Members",
                        fontFamily = RajdhaniFontFamily,
                        fontSize = 12.sp,
                        color = DxdTextSecondary
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Whatshot,
                        contentDescription = "Streak",
                        tint = DxdBrightRed,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${clan.winStreak}W STREAK",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = DxdBrightRed
                    )
                }
                Text(
                    text = "${clan.totalWins} Total Wins",
                    fontFamily = RajdhaniFontFamily,
                    fontSize = 11.sp,
                    color = DxdTextMuted
                )
            }
        }
    }
}

@Composable
fun LeaderboardHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        NeonSectionHeader(
            title = "Global MMR Leaderboard",
            subtitle = "Season 12 Radiant & Predator Tier Rankings"
        )
    }
}

@Composable
fun LeaderboardRowItem(entry: LeaderboardEntry) {
    val rankBadgeColor = when (entry.rank) {
        1 -> DxdNeonGold
        2 -> Color(0xFFC0C0C0)
        3 -> Color(0xFFCD7F32)
        else -> DxdTextSecondary
    }

    NeonGamingCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        borderColor = if (entry.rank <= 3) rankBadgeColor.copy(alpha = 0.5f) else DxdRedGlowBorder.copy(alpha = 0.2f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(if (entry.rank <= 3) rankBadgeColor.copy(alpha = 0.2f) else DxdSurfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${entry.rank}",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = rankBadgeColor
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "[${entry.clanTag}] ${entry.gamerTag}",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.White
                    )
                    Text(
                        text = "${entry.tier} • ${entry.mainGame}",
                        fontFamily = RajdhaniFontFamily,
                        fontSize = 11.sp,
                        color = DxdTextMuted
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${entry.mmr} MMR",
                    fontFamily = OrbitronFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = DxdBrightRed
                )
                Text(
                    text = "${entry.winRate}% Win Rate",
                    fontFamily = RajdhaniFontFamily,
                    fontSize = 11.sp,
                    color = DxdNeonGreen
                )
            }
        }
    }
}
