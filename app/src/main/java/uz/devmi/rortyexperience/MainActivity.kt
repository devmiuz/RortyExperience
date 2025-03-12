package uz.devmi.rortyexperience

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.devmi.rortyexperience.feature.character.list.CharacterListScreen
import uz.devmi.rortyexperience.ui.theme.RortyExperienceTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.networkState.collectLatest { status ->
                    Toast.makeText(this@MainActivity, "Network Status: $status", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        setContent {
            RortyExperienceTheme {
                CharacterListScreen()
            }
        }
    }
}