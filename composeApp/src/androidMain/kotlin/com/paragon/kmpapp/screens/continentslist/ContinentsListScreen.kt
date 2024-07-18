package com.paragon.kmpapp.screens.continentslist

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
import com.paragon.kmpapp.component.EmptyScreenContent
import com.paragon.kmpapp.data.Continent
import com.paragon.kmpapp.utils.FetchType
import com.paragon.kmpapp.utils.States
import org.koin.androidx.compose.koinViewModel


/**
 * Screen to show a list of continents
 * */
@Composable
fun ContinentsListScreen(
    navigateToDetails: (continentCode: String) -> Unit, fetchType: String = FetchType.GraphQl.value
) {
    val viewModel: ContinentsListViewModel = koinViewModel()
    viewModel.getContinents(fetchType = fetchType)
    val objects by viewModel.continents.collectAsState()

    AnimatedContent(objects) {
        when (it) {
            is States.Success -> {
                if (it.data.isEmpty()) EmptyScreenContent(Modifier.fillMaxSize())
                else ObjectGrid(
                    objects = (objects as States.Success<List<Continent?>>).data,
                    onObjectClick = navigateToDetails,
                )
            }

            else -> EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

/**
 * Grid to display continents
 * */
@Composable
private fun ObjectGrid(
    objects: List<Continent?>,
    onObjectClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(objects, key = { it?.code.orEmpty() }) { obj ->
            ObjectFrame(obj = obj, onObjectClick)
        }
    }
}

/**
 * Continents Grid Item
* */
@Composable
private fun ObjectFrame(
    obj: Continent?,
    onObjectClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(8.dp).clickable { obj?.code?.let { onObjectClick(it) } }) {
        Text(
            obj?.code.orEmpty(),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
        )
        Text(obj?.name.orEmpty(), style = MaterialTheme.typography.body2)
        Text(
            "Countries: ${obj?.countries.orEmpty().size}", style = MaterialTheme.typography.caption
        )
    }
}
