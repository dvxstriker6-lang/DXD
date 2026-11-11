package com.example

import com.example.viewmodel.DxdViewModel
import com.example.viewmodel.MainTab
import com.example.viewmodel.Screen
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DxdViewModelTest {

    private lateinit var viewModel: DxdViewModel

    @Before
    fun setup() {
        viewModel = DxdViewModel()
    }

    @Test
    fun initialState_isSplashScreen() {
        assertEquals(Screen.Splash, viewModel.currentScreen.value)
        assertEquals(MainTab.HOME, viewModel.currentTab.value)
    }

    @Test
    fun login_updatesUserAndNavigatesToMain() {
        viewModel.login("TEST_GAMER")
        assertTrue(viewModel.isLoggedIn.value)
        assertEquals("TEST_GAMER", viewModel.currentUser.value.gamerTag)
        assertEquals(Screen.Main, viewModel.currentScreen.value)
    }

    @Test
    fun toggleLikePost_incrementsAndDecrementsLikes() {
        val firstPost = viewModel.posts.value.first()
        val initialLikes = firstPost.likesCount
        val initialLiked = firstPost.isLiked

        viewModel.toggleLikePost(firstPost.id)
        val updatedPost = viewModel.posts.value.first { it.id == firstPost.id }
        assertEquals(!initialLiked, updatedPost.isLiked)
        assertEquals(if (initialLiked) initialLikes - 1 else initialLikes + 1, updatedPost.likesCount)
    }

    @Test
    fun createPost_prependsNewPostToFeed() {
        val initialCount = viewModel.posts.value.size
        viewModel.createPost("Clutched 1v3 in overtime!", "Valorant", "ACE CLUTCH")
        val updatedPosts = viewModel.posts.value

        assertEquals(initialCount + 1, updatedPosts.size)
        assertEquals("Clutched 1v3 in overtime!", updatedPosts.first().content)
        assertEquals("ACE CLUTCH", updatedPosts.first().highlightBadge)
    }

    @Test
    fun sendMessage_addsMessageToConversation() {
        val conv = viewModel.conversations.value.first()
        val initialMsgCount = conv.messages.size

        viewModel.openConversation(conv)
        viewModel.sendMessage(conv.id, "Let's lock in Haven!")

        val updatedConv = viewModel.conversations.value.first { it.id == conv.id }
        assertEquals(initialMsgCount + 1, updatedConv.messages.size)
        assertEquals("You: Let's lock in Haven!", updatedConv.lastMessage)
    }

    @Test
    fun registerForTournament_togglesRegistrationState() {
        val tourney = viewModel.tournaments.value.first()
        val initialRegistered = tourney.isRegistered
        val initialTeams = tourney.registeredTeams

        viewModel.registerForTournament(tourney.id)
        val updatedTourney = viewModel.tournaments.value.first { it.id == tourney.id }

        assertEquals(!initialRegistered, updatedTourney.isRegistered)
        assertEquals(if (initialRegistered) initialTeams - 1 else initialTeams + 1, updatedTourney.registeredTeams)
    }
}
