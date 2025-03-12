package uz.devmi.rortyexperience.feature.character.list.parts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.devmi.rortyexperience.core.RectangleShimmerComponent

@Composable
fun CharacterListShimmer(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(10) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                RectangleShimmerComponent(
                    Modifier
                        .size(56.dp)
                )
                Spacer(Modifier.width(12.dp))
                RectangleShimmerComponent(
                    Modifier
                        .height(24.dp)
                        .fillMaxWidth()
                )
            }
        }

    }
}