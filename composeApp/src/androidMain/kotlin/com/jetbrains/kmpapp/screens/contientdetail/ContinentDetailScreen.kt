package com.jetbrains.kmpapp.screens.contientdetail

import androidx.compose.animation.AnimatedContent
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
import com.jetbrains.kmpapp.component.EmptyScreenContent
import com.jetbrains.kmpapp.graphql.ContinentDetailsQuery
import com.jetbrains.kmpapp.utils.States
import org.koin.androidx.compose.koinViewModel

/**
 * Screen to show detail of a continent i.e countries and all
 *
 * Note:- Currently not showing this screen
 * */
@Composable
fun ContinentDetailScreen(continentCode: String, navigateBack: () -> Unit) {
    val viewModel: ContinentDetailViewModel = koinViewModel()

    val objects by viewModel.countries.collectAsState()

    viewModel.getCountries(continentCode)

    AnimatedContent(objects) {
        when (it) {
            is States.Success -> {
                if (it.data?.countries.isNullOrEmpty()) EmptyScreenContent(Modifier.fillMaxSize())
                else ObjectGrid(
                    continent = (objects as States.Success<ContinentDetailsQuery.Continent?>).data,
                )
            }

            else -> EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

/**
 * Grid to display to countries
 * */
@Composable
private fun ObjectGrid(
    continent: ContinentDetailsQuery.Continent?,
    modifier: Modifier = Modifier,
) {

    Column(modifier.padding(8.dp)) {
        Text(
            continent?.name.orEmpty(),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(continent?.countries.orEmpty(), key = { it?.name.orEmpty() }) { obj ->
                ObjectFrame(country = obj)
            }
        }
    }
}

/**
 * Countries grid item
 * */
@Composable
private fun ObjectFrame(
    country: ContinentDetailsQuery.Country?,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(8.dp)) {
        Text(
            country?.name.orEmpty(),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
        )
        Text(country?.code.orEmpty(), style = MaterialTheme.typography.body2)
        Text(
            "Phone: ${country?.phone}", style = MaterialTheme.typography.caption
        )
    }
}
