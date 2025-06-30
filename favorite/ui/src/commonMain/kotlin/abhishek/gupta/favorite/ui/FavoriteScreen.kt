package abhishek.gupta.favorite.ui


import abhishek.gupta.common.domain.model.Game
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete

import androidx.compose.runtime.Composable

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage


import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier.padding(
        top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    ), onBackClick: () -> Unit, onDetails: (Int) -> Unit, isDeleteShown: Boolean = false
) {

    val viewModel = koinViewModel<FavoriteViewModel>()
    val games = viewModel.game.collectAsStateWithLifecycle()


    Scaffold(
        modifier = modifier.fillMaxSize()
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
        topBar = {
            TopAppBar(
                title = { Text("Favorite") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            modifier = Modifier.padding(18.dp),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }


                },


                )

        }) { innerpadding ->
        FavoriteScreenContent(
            modifier = modifier.fillMaxSize()


                .background(MaterialTheme.colors.background),
            games = games.value,


            onDelete = {
                viewModel.delete(it)
            },
            isDeleteShown = isDeleteShown,
            onDetails = onDetails
        )
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteScreenContent(
    modifier: Modifier = Modifier,
    games: List<Game>,

    onDetails: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    isDeleteShown: Boolean
) {

    val listState = rememberLazyListState()
    val scrollOffset = remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    val offsetY by animateDpAsState(targetValue = (-scrollOffset.value / 3).dp.coerceAtLeast((-56).dp))
    val AppBarHeight = 56.dp // Standard TopAppBar height

    val isLight = MaterialTheme.colors.isLight
    val backgroundColor = if (isLight) Color.White else Color.Black
    val iconTint = if (isLight) Color.Black else Color.White


    if (games.isEmpty()) {
        Box(
            Modifier.fillMaxSize()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())

                .background(MaterialTheme.colors.background),


            ) {


            Text(
                text = "Nothing Found",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )

        }
    } else {


        Box(
            modifier = modifier.fillMaxSize()

        ) {

            LazyColumn(

                state = listState,

                modifier = Modifier.fillMaxSize()
            ) {


                items(games) {
                    Card(
                        modifier = Modifier.padding(12.dp).fillMaxWidth().height(350.dp)
                            .clickable { onDetails(it.id) }, shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = it.imageUrl,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Box(
                                modifier = Modifier.padding(12.dp)
                                    .background(Color.LightGray, RoundedCornerShape(12.dp))
                                    .fillMaxWidth().align(Alignment.BottomCenter)
                            ) {
                                Text(
                                    it.name,
                                    style = MaterialTheme.typography.body1,
                                    modifier = Modifier.padding(8.dp),
                                    color = MaterialTheme.colors.background,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            if (isDeleteShown) {
                                IconButton(
                                    onClick = { onDelete(it.id) },
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .background(color = iconTint, shape = CircleShape)
                                        .align(Alignment.TopEnd)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        modifier = Modifier.padding(4.dp),
                                        tint = backgroundColor
                                    )
                                }
                            }

                        }


                    }
                }
            }


        }


    }

}






