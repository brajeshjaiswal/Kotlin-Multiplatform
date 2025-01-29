package org.example.multiplatform.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.example.multiplatform.models.MealResponse
import org.example.multiplatform.viewmodel.HomeViewModel
import org.koin.compose.getKoin

@Composable
fun GetMealsCategories(onClick: (MealResponse) -> Unit) {
    val mainViewModel: HomeViewModel = getKoin().get()
    val meals = mainViewModel.meals.collectAsState()

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals.value?.categories.orEmpty()) { meal ->
            ShowMealsCategoryItems(meal = meal) {
                onClick(meal)
            }
        }
    }
}

@Composable
fun ShowMealsCategoryItems(meal: MealResponse, onClick: (MealResponse) -> Unit) {
    var expandable by remember { mutableStateOf(false) }
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable {
                onClick(meal)
            }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            AsyncImage(
                model = meal.imageUrl.orEmpty(),
                contentDescription = null,
                modifier = Modifier
                    .size(82.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )

            Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(
                    text = meal.name.orEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h4
                )

                Text(
                    text = meal.description.orEmpty(),
                    modifier = Modifier.padding(5.dp),
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    maxLines = if (expandable) 10 else 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Icon(
                imageVector = if (expandable) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown,
                contentDescription = "",
                modifier = Modifier
                    .size(35.dp)
                    .padding(1.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { expandable = !expandable }
            )

        }
    }
}