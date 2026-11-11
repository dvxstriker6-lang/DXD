package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Verified
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.SampleData
import com.example.model.GamerProfile
import com.example.model.Post
import com.example.ui.components.CyberBadge
import com.example.ui.components.GamerAvatar
import com.example.ui.components.LivePulsingBadge
import com.example.ui.components.NeonGamingCard
import com.example.ui.components.NeonPrimaryButton
import com.example.ui.components.NeonTextField
import com.example.ui.theme.DxdBrightRed
import com.example.ui.theme.DxdDarkObsidian
import com.example.ui.theme.DxdDarkRedContainer
import com.example.ui.theme.DxdNeonGold
import com.example.ui.theme.DxdNeonRed
import com.example.ui.theme.DxdPureBlack
import com.example.ui.theme.DxdRedGlowBorder
import com.example.ui.theme.DxdSurfaceCard
import com.example.ui.theme.DxdSurfaceVariant
import com.example.ui.theme.DxdTextMuted
import com.example.ui.theme.DxdTextPrimary
import com.example.ui.theme.DxdTextSecondary
import com.example.ui.theme.OrbitronFontFamily
import com.example.ui.theme.RajdhaniFontFamily

@Composable
fun HomeScreen(
    currentUser: GamerProfile,
    posts: List<Post>,
    onLikePost: (String) -> Unit,
    onCreatePost: (String, String, String?) -> Unit,
    onNavigateToTournament: () -> Unit,
    onNavigateToNotifications: () -> Unit
) {
    var showCreatePostDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DxdPureBlack)
            .testTag("home_feed_screen")
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            // Header Top Bar
            item {
                HomeTopBar(
                    currentUser = currentUser,
                    onNotificationClick = onNavigateToNotifications
                )
            }

            // Live Streamers Story Bar
            item {
                LiveStoriesSection()
            }

            // Featured Tournament Banner Card
            item {
                FeaturedTournamentCard(
                    onRegisterClick = onNavigateToTournament
                )
            }

            // Quick Create Post Trigger
            item {
                QuickPostBar(
                    currentUser = currentUser,
                    onClick = { showCreatePostDialog = true }
                )
            }

            // Feed Posts Header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "COMMUNITY TRANSMISSIONS",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 1.sp,
                        color = DxdTextPrimary
                    )
                    Text(
                        text = "FILTER: ALL HUBS",
                        fontFamily = RajdhaniFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = DxdBrightRed
                    )
                }
            }

            // Feed Posts Items
            items(posts, key = { it.id }) { post ->
                PostItemCard(
                    post = post,
                    onLike = { onLikePost(post.id) }
                )
            }
        }

        // Create Post Dialog
        if (showCreatePostDialog) {
            CreatePostDialog(
                onDismiss = { showCreatePostDialog = false },
                onPostSubmitted = { content, game, badge ->
                    onCreatePost(content, game, badge)
                    showCreatePostDialog = false
                }
            )
        }
    }
}

@Composable
fun HomeTopBar(
    currentUser: GamerProfile,
    onNotificationClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DxdDarkObsidian)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // App Logo & Brand
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, DxdNeonRed, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_dxd_logo),
                    contentDescription = "DXD Logo",
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = "DXD",
                    fontFamily = OrbitronFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    letterSpacing = 2.sp,
                    color = Color.White
                )
                Text(
                    text = "LVL ${currentUser.level} • ${currentUser.rankTitle}",
                    fontFamily = RajdhaniFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = DxdBrightRed
                )
            }
        }

        // Coins balance & Notification bell
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(DxdDarkRedContainer)
                    .border(1.dp, DxdNeonGold.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.MonetizationOn,
                        contentDescription = "Coins",
                        tint = DxdNeonGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${currentUser.dxdCoins}",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }

            IconButton(
                onClick = onNotificationClick,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(DxdSurfaceVariant)
                    .testTag("home_notif_button")
            ) {
                Icon(
                    imageVector = Icons.Default.NotificationsNone,
                    contentDescription = "Notifications",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun LiveStoriesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "CLAN BROADCASTS",
                fontFamily = OrbitronFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 0.5.sp,
                color = DxdTextSecondary
            )
            LivePulsingBadge()
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(SampleData.liveStories, key = { it.id }) { story ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(72.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(62.dp)
                            .clip(CircleShape)
                            .background(DxdDarkObsidian)
                            .border(
                                width = 2.dp,
                                color = DxdNeonRed,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        GamerAvatar(
                            tag = story.gamerTag,
                            size = 54.dp,
                            isOnline = false
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .clip(RoundedCornerShape(3.dp))
                                .background(DxdNeonRed)
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = story.viewers,
                                fontFamily = OrbitronFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 8.sp,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = story.gamerTag,
                        fontFamily = RajdhaniFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = DxdTextPrimary,
                        maxLines = 1
                    )
                    Text(
                        text = story.game,
                        fontFamily = RajdhaniFontFamily,
                        fontSize = 10.sp,
                        color = DxdTextMuted,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun FeaturedTournamentCard(
    onRegisterClick: () -> Unit
) {
    NeonGamingCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(18.dp),
        borderColor = DxdNeonRed,
        glowIntensity = 0.7f
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.banner_tournament),
                    contentDescription = "Tournament Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Dark gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, DxdSurfaceCard)
                            )
                        )
                )

                // Top badges
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CyberBadge(text = "OFFICIAL INVITATIONAL", color = DxdBrightRed)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.Black.copy(alpha = 0.7f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "PRIZE: $10,000 USD",
                            fontFamily = OrbitronFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = DxdNeonGold
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "DXD NIGHT CUP: VALORANT",
                    fontFamily = OrbitronFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    letterSpacing = 1.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "5v5 Double Elimination • 28/32 Teams Registered • Starts in 2h 30m",
                    fontFamily = RajdhaniFontFamily,
                    fontSize = 13.sp,
                    color = DxdTextSecondary
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    NeonPrimaryButton(
                        text = "Deploy Squad",
                        onClick = onRegisterClick,
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.EmojiEvents,
                        testTag = "tournament_banner_deploy_button"
                    )
                }
            }
        }
    }
}

@Composable
fun QuickPostBar(
    currentUser: GamerProfile,
    onClick: () -> Unit
) {
    NeonGamingCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(14.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GamerAvatar(tag = currentUser.gamerTag, size = 40.dp)
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(DxdSurfaceVariant)
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Broadcast your clutch or recruit a squad...",
                    fontFamily = RajdhaniFontFamily,
                    fontSize = 13.sp,
                    color = DxdTextMuted
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(DxdDarkRedContainer)
                    .border(1.dp, DxdNeonRed, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create Post",
                    tint = DxdBrightRed,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun PostItemCard(
    post: Post,
    onLike: () -> Unit
) {
    val flameColor by animateColorAsState(
        targetValue = if (post.isLiked) DxdBrightRed else DxdTextMuted,
        label = "flameColor"
    )

    NeonGamingCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        glowIntensity = if (post.isLiked) 0.6f else 0.3f
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Post Author Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    GamerAvatar(
                        tag = post.authorTag,
                        size = 44.dp,
                        isPro = post.isAuthorPro
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "[${post.authorClan}] ${post.authorTag}",
                                fontFamily = OrbitronFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                            if (post.isAuthorPro) {
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.Verified,
                                    contentDescription = "Verified Pro",
                                    tint = DxdBrightRed,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = post.game,
                                fontFamily = RajdhaniFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = DxdBrightRed
                            )
                            Text(
                                text = " • ${post.timestamp}",
                                fontFamily = RajdhaniFontFamily,
                                fontSize = 12.sp,
                                color = DxdTextMuted
                            )
                        }
                    }
                }

                if (post.highlightBadge != null) {
                    CyberBadge(text = post.highlightBadge, color = DxdBrightRed)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Post Content Text
            Text(
                text = post.content,
                fontFamily = RajdhaniFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                lineHeight = 21.sp,
                color = DxdTextPrimary
            )

            // Optional Match Stats Badge Card
            if (post.matchStat != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(DxdDarkRedContainer.copy(alpha = 0.5f))
                        .border(0.8.dp, DxdRedGlowBorder.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "MATCH METRICS: ${post.matchStat}",
                            fontFamily = OrbitronFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            letterSpacing = 0.5.sp,
                            color = DxdNeonGold
                        )
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = DxdNeonGold,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Post Interaction Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Like / Flame button
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(onClick = onLike)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .testTag("post_like_button_${post.id}")
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = "Flame / Like",
                        tint = flameColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${post.likesCount}",
                        fontFamily = OrbitronFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = flameColor
                    )
                }

                // Comments
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ChatBubbleOutline,
                        contentDescription = "Comments",
                        tint = DxdTextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${post.commentsCount}",
                        fontFamily = OrbitronFontFamily,
                        fontSize = 12.sp,
                        color = DxdTextSecondary
                    )
                }

                // Share
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = DxdTextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${post.sharesCount}",
                        fontFamily = OrbitronFontFamily,
                        fontSize = 12.sp,
                        color = DxdTextSecondary
                    )
                }

                // Bookmark
                IconButton(onClick = { /* Saved */ }, modifier = Modifier.size(28.dp)) {
                    Icon(
                        imageVector = Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = DxdTextMuted,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CreatePostDialog(
    onDismiss: () -> Unit,
    onPostSubmitted: (String, String, String?) -> Unit
) {
    var contentText by remember { mutableStateOf("") }
    var selectedGame by remember { mutableStateOf("Valorant") }
    var selectedBadge by remember { mutableStateOf("CLUTCH PLAY") }

    val badges = listOf("CLUTCH PLAY", "LOOKING FOR SQUAD", "ACE 1v5", "WARZONE WIN")

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = DxdDarkObsidian,
        title = {
            Text(
                text = "TRANSMIT TO CLAN FEED",
                fontFamily = OrbitronFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                letterSpacing = 1.sp,
                color = Color.White
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                NeonTextField(
                    value = contentText,
                    onValueChange = { contentText = it },
                    label = "Transmission message...",
                    modifier = Modifier.height(120.dp),
                    testTag = "create_post_input"
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "SELECT HIGHLIGHT TAG",
                    fontFamily = OrbitronFontFamily,
                    fontSize = 11.sp,
                    color = DxdBrightRed
                )

                Spacer(modifier = Modifier.height(6.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(badges) { badge ->
                        val isSelected = selectedBadge == badge
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) DxdNeonRed else DxdSurfaceVariant)
                                .clickable { selectedBadge = badge }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = badge,
                                fontFamily = OrbitronFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = if (isSelected) Color.White else DxdTextSecondary
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (contentText.isNotBlank()) {
                        onPostSubmitted(contentText, selectedGame, selectedBadge)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = DxdNeonRed),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "TRANSMIT",
                    fontFamily = OrbitronFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "CANCEL",
                    fontFamily = OrbitronFontFamily,
                    color = DxdTextSecondary
                )
            }
        }
    )
}
