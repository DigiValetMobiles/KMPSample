package com.jetbrains.kmpapp.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.graphql.ContinentsQuery
import com.jetbrains.kmpapp.utils.States
import org.koin.androidx.compose.koinViewModel

@Composable
fun CountriesListScreen(
    navigateToDetails: (continentCode: String) -> Unit,
) {
    val viewModel: CountriesViewModel = koinViewModel()
    val objects by viewModel.continents.collectAsState()

    viewModel.getContinents("")

    AnimatedContent(objects) {
        when (it) {
            is States.Success -> {
                if (it.data.isEmpty()) EmptyScreenContent(Modifier.fillMaxSize())
                else ObjectGrid(
                    objects = (objects as States.Success<List<ContinentsQuery.Continent?>>).data,
                onObjectClick = navigateToDetails,
                )
            }

            else -> EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ObjectGrid(
    objects: List<ContinentsQuery.Continent?>,
    onObjectClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
//        columns = GridCells.Adaptive(180.dp),
        columns = GridCells.Fixed(1),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(objects, key = { it?.code.orEmpty() }) { obj ->
            ObjectFrame(obj = obj, onObjectClick)
        }
    }
}

@Composable
private fun ObjectFrame(
    obj: ContinentsQuery.Continent?,
    onObjectClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(8.dp)
        .clickable { obj?.code?.let { onObjectClick(it) } }) {
        Text(
            obj?.code.orEmpty(),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
        )
        Text(obj?.name.orEmpty(), style = MaterialTheme.typography.body2)
        Text("Countries: ${obj?.countries.orEmpty().size}", style = MaterialTheme.typography.caption)
    }
}
