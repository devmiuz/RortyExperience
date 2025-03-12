package uz.devmi.rortyexperience.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.devmi.rortyexperience.R

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    error: AppError?,
    onRetry: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(vertical = 20.dp, horizontal = 32.dp)
    ) {
        Icon(
            Icons.Default.Clear,
            "Failure icon",
            Modifier.size(128.dp),
            Color.Red
        )

        Text(
            text = when (error) {
                AppError.NetworkError -> stringResource(R.string.network_unavailable)
                AppError.NotFound -> stringResource(R.string.data_not_found)
                is AppError.Unknown -> stringResource(
                    R.string.unknown_error_occurred,
                    error.message
                )

                null -> stringResource(R.string.error_occurred)
            },
            style = MaterialTheme.typography.titleMedium
        )

        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.retry))
        }
    }
}