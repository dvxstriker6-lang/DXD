package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.GamerProfile
import com.example.ui.components.CyberBadge
import com.example.ui.components.GamerAvatar
import com.example.ui.components.NeonGamingCard
import com.example.ui.components.NeonOutlinedButton
import com.example.ui.components.NeonPrimaryButton
import com.example.ui.components.NeonSectionHeader
import com.example.ui.components.NeonTextField
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    currentUser: GamerProfile,
    onLogout: () -> Unit
) {
    var userBio by remember { mutableStateOf(currentUser.bio) }
    var showEditBioDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DxdPureBlack)
            .testTag("user_profile_screen")
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            // Gamer Profile Header Banner
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF4A0A16), Color(0xFF1B070B), DxdDarkObsidian)
                            )
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick = { /* Settings */ },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.5f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            // Avatar & Identity Card Overlap
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Avatar shifted up
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .background(DxdDarkObsidian)
                            .border(3.dp, DxdNeonRed, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        GamerAvatar(
                            tag = currentUser.gamerTag,
                            size = 88.dp,
                            isPro = true
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "[${currentUser.clanTag}] ${currentUser.gamerTag}",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CyberBadge(text = "LEVEL ${currentUser.level}", color = DxdNeonGold)
                        CyberBadge(text = currentUser.rankTitle, color = DxdBrightRed)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Bio Card
                    NeonGamingCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        onClick = { showEditBioDialog = true }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = userBio,
                                fontFamily = RajdhaniFontFamily,
                                fontSize = 14.sp,
                                color = DxdTextSecondary,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Bio",
                                tint = DxdBrightRed,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            // Performance Metrics
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    NeonSectionHeader(
                        title = "Combat Analytics",
                        subtitle = "Verified season match metrics"
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        StatCounterBox(
                            title = "Victories",
                            value = "${currentUser.wins}",
                            valueColor = DxdNeonGold,
                            modifier = Modifier.weight(1f)
                        )
                        StatCounterBox(
                            title = "Win Rate",
                            value = "${currentUser.winRate}%",
                            valueColor = DxdNeonGreen,
                            modifier = Modifier.weight(1f)
                        )
                        StatCounterBox(
                            title = "K/D Ratio",
                            value = "${currentUser.kdRatio}",
                            valueColor = DxdBrightRed,
                            modifier = Modifier.weight(1f)
                        )
                        StatCounterBox(
                            title = "DXD Coins",
                            value = "${currentUser.dxdCoins}",
                            valueColor = DxdNeonCyan,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Trophies and Badges Showcase
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    NeonSectionHeader(
                        title = "Medals & Honors",
                        subtitle = "Unlocked tournament trophies & accolades"
                    )

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        currentUser.badges.forEach { badge ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(DxdDarkRedContainer)
                                    .border(1.dp, DxdNeonRed.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.EmojiEvents,
                                        contentDescription = null,
                                        tint = DxdNeonGold,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = badge.uppercase(),
                                        fontFamily = OrbitronFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Competitive Games
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    NeonSectionHeader(
                        title = "Active Titles",
                        subtitle = "Rosters & Competitive Matchmaking"
                    )

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        currentUser.favoriteGames.forEach { game ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(DxdSurfaceVariant)
                                    .border(1.dp, Color(0xFF2C2F40), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.SportsEsports,
                                        contentDescription = null,
                                        tint = DxdBrightRed,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = game,
                                        fontFamily = RajdhaniFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Log Out button
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    NeonOutlinedButton(
                        text = "Sign Out Of Arena",
                        onClick = onLogout,
                        modifier = Modifier.fillMaxWidth(),
                        icon = Icons.Default.ExitToApp,
                        testTag = "profile_logout_button"
                    )
                }
            }
        }

        // Edit Bio Dialog
        if (showEditBioDialog) {
            AlertDialog(
                onDismissRequest = { showEditBioDialog = false },
                containerColor = DxdDarkObsidian,
                title = {
                    Text(
                        text = "EDIT CLAN BIO",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                text = {
                    NeonTextField(
                        value = userBio,
                        onValueChange = { userBio = it },
                        label = "Gamer Bio",
                        testTag = "edit_bio_input"
                    )
                },
                confirmButton = {
                    Button(
                        onClick = { showEditBioDialog = false },
                        colors = ButtonDefaults.buttonColors(containerColor = DxdNeonRed),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "SAVE BIO",
                            fontFamily = OrbitronFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showEditBioDialog = false }) {
                        Text(
                            text = "CANCEL",
                            fontFamily = OrbitronFontFamily,
                            color = DxdTextSecondary
                        )
                    }
                }
            )
        }
    }
}
