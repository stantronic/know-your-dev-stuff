package space.stanton.know.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import space.stanton.know.android.theme.Palette

@Composable
fun AppScreen(content: @Composable () -> Unit = {}) {
    Surface(color = Palette.MidnightBlue) {
        Scaffold(
            Modifier
                .safeContentPadding()
                .background(Palette.MidnightBlue)
        ) { paddingValues ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                content()
            }
        }
    }
}


