package abhishek.gupta.game.ui.game

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

import org.koin.compose.viewmodel.koinViewModel


@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    onSearchClick:()->Unit,
    onGameClick:(Int)-> Unit

) {

    val viewModel = koinViewModel<GameViewModel>()


    val uiState = viewModel.uiState.collectAsStateWithLifecycle()




    GameScreenContent(
        modifier = modifier.fillMaxSize().padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()), uiState = uiState.value,
        onFavoriteClick = onFavoriteClick,
        onSearchClick = onSearchClick,
        onClick = onGameClick,
        viewModel = viewModel
    )

}

@Composable
fun GameScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameScreen.UiState,
    onFavoriteClick: () -> Unit,
    onSearchClick: () -> Unit,
    onClick: (Int) -> Unit,
    viewModel: GameViewModel
) {
    val listState = rememberLazyListState()
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisibleItem != null && lastVisibleItem.index >= totalItems - 5
        }
    }
    val scrollOffset = remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    val offsetY by animateDpAsState(targetValue = (-scrollOffset.value / 3).dp.coerceAtLeast((-56).dp))
    val AppBarHeight = 56.dp // Standard TopAppBar height

    if (shouldLoadMore && !uiState.isLoading && uiState.error.isBlank()) {
        LaunchedEffect(Unit) {
            // trigger next page load

            viewModel.getGames()
        }
    }
    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colors.background)) {

        LazyColumn(

            state = listState,
            contentPadding = PaddingValues(top = AppBarHeight),
            modifier = Modifier.fillMaxSize()
        ) {
            if (uiState.isLoading) {
                item {
                    Box(Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }

            if (uiState.error.isNotBlank()) {
                item {
                    Box(Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                        Text(uiState.error)
                    }
                }
            }

            uiState.data?.let { data ->


                items(data) {
                    Card(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                            .height(350.dp)
                            .clickable { onClick(it.id) },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = it.imageUrl,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Box(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .background(Color.LightGray, RoundedCornerShape(12.dp))
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
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
                        }
                    }
                }
            }

            if (uiState.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }




        TopAppBar(
            title = { Text("ABHISHEK GUPTA APP") },
            actions = {
                IconButton(onClick = onSearchClick) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
                IconButton(onClick = onFavoriteClick) {
                    Icon(Icons.Default.Favorite, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = offsetY)
                .background(MaterialTheme.colors.surface)
        )
    }
}



