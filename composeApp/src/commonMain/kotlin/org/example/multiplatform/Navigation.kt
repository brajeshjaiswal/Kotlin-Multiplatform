package org.example.multiplatform

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.multiplatform.models.MealResponse
import org.example.multiplatform.ui.GetMealDetailScreen
import org.example.multiplatform.ui.GetMealsCategories


object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val local = LocalNavigator.currentOrThrow
        TopAppBar("Home", Icons.Default.Menu) {
            GetMealsCategories { mealResponse ->
                local.push(DetailsScreen(mealResponse))
                // Navigator(HomeScreen)
            }
        }
    }
}

class DetailsScreen(val mealResponse: MealResponse) : Screen {
    @Composable
    override fun Content() {
        TopAppBar("Details", Icons.AutoMirrored.Filled.ArrowBack) {
            GetMealDetailScreen(mealResponse)
        }
    }
}

@Composable
fun TopAppBar(title: String, imageVector: ImageVector, navPush: @Composable () -> Unit) {
    val local = LocalNavigator.currentOrThrow
    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                contentColor = Color.Black,
                backgroundColor = Color.White,
                title = {
                    Text(
                        title,
                        maxLines = 1,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { local.pop() }) {
                        Icon(
                            imageVector = imageVector,
                            tint = Color.Black,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colors.surface,
        ) {
            navPush()
        }
    }
}