package uz.devmi.rortyexperience.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun <DATA_TYPE> LazyListScope.LazyDisplayStateHandler(
    modifier: Modifier = Modifier,
    state: DisplayState<DATA_TYPE>,
    minHeight: Dp = 200.dp,
    enterAnimation: EnterTransition = fadeIn(),
    exitAnimation: ExitTransition = fadeOut(),
    loadingContent: @Composable () -> Unit = { CircularProgressIndicator() },
    dataContent: (items: DATA_TYPE) -> Unit
) {
    val isSuccess = !state.isLoading && state.error == null && state.data != null

    if (isSuccess) {
        state.data?.let { items ->
            dataContent(items)
        }
    } else {
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = minHeight)
                    .animateItem()
            ) {
                AnimatedVisibility(
                    visible = state.isLoading,
                    enter = enterAnimation,
                    exit = exitAnimation,
                ) { loadingContent() }

                AnimatedVisibility(
                    visible = state.error != null,
                    enter = enterAnimation,
                    exit = exitAnimation
                ) {
                    state.error
                }
            }
        }
    }

}