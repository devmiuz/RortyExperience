package uz.devmi.rortyexperience.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun <DATA_TYPE> DisplayStateHandler(
    modifier: Modifier = Modifier,
    state: DisplayState<DATA_TYPE>,
    enterAnimation: EnterTransition = fadeIn(),
    exitAnimation: ExitTransition = fadeOut(),
    loadingContent: @Composable () -> Unit = { CircularProgressIndicator() },
    dataContent: @Composable AnimatedVisibilityScope.(DATA_TYPE) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val isSuccess = !state.isLoading && state.error == null && state.data != null

        AnimatedVisibility(
            visible = state.isLoading,
            enter = enterAnimation,
            exit = exitAnimation,
        ) {
            loadingContent()
        }

        AnimatedVisibility(
            visible = state.error != null,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            state.error
        }

        AnimatedVisibility(
            visible = isSuccess,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            state.data?.let {
                dataContent(it)
            }
        }
    }

}