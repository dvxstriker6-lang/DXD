package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.NeonPrimaryButton
import com.example.ui.theme.DxdBrightRed
import com.example.ui.theme.DxdDarkObsidian
import com.example.ui.theme.DxdNeonRed
import com.example.ui.theme.DxdPureBlack
import com.example.ui.theme.DxdRedGlowBorder
import com.example.ui.theme.DxdTextMuted
import com.example.ui.theme.DxdTextPrimary
import com.example.ui.theme.DxdTextSecondary
import com.example.ui.theme.OrbitronFontFamily
import com.example.ui.theme.RajdhaniFontFamily
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateNext: () -> Unit
) {
    val scale = remember { Animatable(0.7f) }
    val progress = remember { Animatable(0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "pulseGlow")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(700, easing = FastOutSlowInEasing)
        )
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(1600, easing = LinearEasing)
        )
        delay(400)
        onNavigateNext()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DxdPureBlack)
            .testTag("splash_screen"),
        contentAlignment = Alignment.Center
    ) {
        // Background Cyber Ambient Glow
        Box(
            modifier = Modifier
                .size(320.dp)
                .scale(scale.value)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            DxdNeonRed.copy(alpha = glowAlpha * 0.45f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            // Glowing DXD Logo Frame
            Box(
                modifier = Modifier
                    .size(170.dp)
                    .scale(scale.value)
                    .clip(RoundedCornerShape(28.dp))
                    .background(DxdDarkObsidian)
                    .border(
                        width = 2.dp,
                        color = DxdNeonRed.copy(alpha = glowAlpha),
                        shape = RoundedCornerShape(28.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_dxd_logo),
                    contentDescription = "DXD Logo",
                    modifier = Modifier.size(150.dp)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // App Title
            Text(
                text = "DXD",
                fontFamily = OrbitronFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 38.sp,
                letterSpacing = 4.sp,
                color = DxdTextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = null,
                    tint = DxdBrightRed,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "SOCIAL GAMING NEXUS",
                    fontFamily = RajdhaniFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    letterSpacing = 2.sp,
                    color = DxdBrightRed
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "ESPORTS • SQUADS • TOURNAMENTS",
                fontFamily = RajdhaniFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                letterSpacing = 1.sp,
                color = DxdTextMuted
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Cyber Progress Bar
            Column(
                modifier = Modifier.width(220.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = { progress.value },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    color = DxdNeonRed,
                    trackColor = Color(0xFF221116)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = if (progress.value < 0.95f) "CONNECTING TO SERVERS..." else "READY TO ENGAGE",
                    fontFamily = OrbitronFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    letterSpacing = 1.sp,
                    color = DxdTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tap to Skip / Quick Launch button
            NeonPrimaryButton(
                text = "Enter Arena",
                onClick = onNavigateNext,
                modifier = Modifier
                    .width(180.dp)
                    .height(44.dp),
                testTag = "splash_enter_button"
            )
        }
    }
}
