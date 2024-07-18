package com.paragon.kmpapp.screens.cachinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paragon.kmpapp.utils.FetchType
import org.koin.androidx.compose.koinViewModel

/**
 * Screen to show available caching options
 * */
@Composable
fun CachingListScreen(navigateToContinents: (cachingType: String) -> Unit) {

    val viewModel: CachingListViewModel = koinViewModel()
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextComponent(
            "Fetch from network via KTOR",
            modifier = Modifier.clickable(
                indication = null, interactionSource = interactionSource
            ) {
                navigateToContinents(FetchType.KTOR.name)
            },
        )

        TextComponent(
            "Fetch from network via graphql",
            modifier = Modifier.clickable(
                indication = null, interactionSource = interactionSource
            ) {
                navigateToContinents(FetchType.GraphQl.name)
            },
        )

        TextComponent(
            "Fetch from graphql cache",
            modifier = Modifier.clickable(
                indication = null, interactionSource = interactionSource
            ) {
                navigateToContinents(FetchType.GraphQlCaching.name)
            },
        )

        TextComponent(
            "Fetch from room",
            modifier = Modifier.clickable(
                indication = null, interactionSource = interactionSource
            ) {
                navigateToContinents(FetchType.RoomCaching.name)
            },
        )

        TextComponent(
            "Fetch from dataStorePreferences",
            modifier = Modifier.clickable(
                indication = null, interactionSource = interactionSource
            ) {
                navigateToContinents(FetchType.DataStorePreferencesCaching.name)
            },
        )

        TextComponent("Clear All Cache", modifier = Modifier.clickable(
            indication = null, interactionSource = interactionSource
        ) {
            viewModel.clearCache()
        })
    }
}


/**
 * Reuse this component to show text
 * */
@Composable
fun TextComponent(
    textValue: String = "",
    modifier: Modifier,
) {
    Text(
        text = textValue,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.secondary,
        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
        modifier = modifier.fillMaxWidth().padding(16.dp).background(
            color = MaterialTheme.colors.onSecondary, shape = CircleShape
        ).padding(16.dp)
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CachingListPreview() {
    CachingListScreen { }
}