package uz.devmi.rortyexperience.core

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    error: AppError?,
    onRetry: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        ErrorContent(
            error = error,
            onRetry = onRetry,
            modifier = modifier
                .clip(MaterialTheme.shapes.medium)
                .background(Color.White)
        )
    }
}