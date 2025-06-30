package abhishek.gupta.game.ui.gameDetails


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage

import org.koin.compose.viewmodel.koinViewModel


@Composable
fun GameDetailsScreen(
    modifier: Modifier = Modifier,
    id: String,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,

    ) {

    val isLight = MaterialTheme.colors.isLight
    val backgroundColor = if (isLight) Color.White else Color.Black
    val iconTint = if (isLight) Color.Black else Color.White

    val viewModel = koinViewModel<GameDetailsViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()






    LaunchedEffect(id) {
        viewModel.getGameDetails(id = id.toInt())
    }

    Scaffold(
        modifier = modifier.fillMaxSize()
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
        topBar = {
            TopAppBar(
                title = { uiState.value.data?.let { Text(it.name) } },
                navigationIcon = {
                    IconButton(onClick = onBackClick) { // <-- replace with your actual back handler
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {

                        uiState.value.data?.let { data ->
                            viewModel.save(
                                id = data.id,
                                image = data.backgroundImage,
                                name = data.name
                            )

                        }
                    }

                    ) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = {

                        uiState.value.data?.let { data ->
                            viewModel.delete(id = data.id)
                        }

                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }

            )

        },

        ) { innerpading ->


        GameDetailsScreenContent(
            modifier = modifier.fillMaxSize().padding(innerpading), uiState = uiState.value,

            )
    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameDetailsScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameDetailsScreen.UiState,


    ) {


    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (uiState.error.isNotBlank()) {
        val clipboardManager: ClipboardManager = LocalClipboardManager.current

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            SelectionContainer {
                Text(uiState.error, modifier = Modifier.clickable {
                    clipboardManager.setText(
                        AnnotatedString(uiState.error)
                    )
                })
            }
        }
    }


    uiState.data?.let { data ->
        Box(modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                item {
                    AsyncImage(
                        model = data.backgroundImage, contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(350.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                            .fillMaxWidth(),
                        text = data.name,
                        style = MaterialTheme.typography.h4
                    )
                }


                item {
                    Text(
                        text = data.description,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            .fillMaxWidth()
                    )
                }

                item {
                    Column(modifier = Modifier.fillMaxWidth()) {

                        Text(
                            text = "Platforms",
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                        )

                        LazyRow(modifier = Modifier.fillMaxWidth()) {
                            items(data.platforms) {
                                Card(
                                    modifier = Modifier.padding(12.dp).wrapContentSize(),
                                    shape = RoundedCornerShape(12.dp),

                                    ) {
                                    Column(
                                        modifier = Modifier.width(150.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        AsyncImage(
                                            model = it.image,
                                            contentDescription = null,
                                            modifier = Modifier.padding(top = 8.dp).background(
                                                color = Color.Transparent, shape = CircleShape
                                            ).clip(CircleShape).size(120.dp),
                                            contentScale = ContentScale.Crop
                                        )
                                        Text(
                                            modifier = Modifier.padding(vertical = 8.dp),
                                            text = it.name,
                                            style = MaterialTheme.typography.caption.copy(
                                                fontWeight = FontWeight.SemiBold, // or FontWeight.Bold
                                                fontSize = 16.sp // increase size from default (~14sp)
                                            )
                                        )

                                    }

                                }
                            }
                        }

                    }
                }



                item {
                    Text(
                        text = "Stores",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                            .padding(bottom = 12.dp)
                    )
                }

                items(data.stores) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 8.dp)
                            .fillMaxWidth()
                    ) {

                        AsyncImage(
                            model = it.image,
                            contentDescription = null,
                            modifier = Modifier.size(120.dp).background(
                                color = Color.Transparent, shape = RoundedCornerShape(12.dp)
                            ).clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.width(8.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = it.name, style = MaterialTheme.typography.h6,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = it.domain, style = MaterialTheme.typography.body2,
                                textDecoration = TextDecoration.Underline
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Gamecount: " + it.gameCount,
                                style = MaterialTheme.typography.caption
                            )

                        }

                    }
                }


                item {
                    Text(
                        text = "Tags", style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                    )
                }

                item {
                    FlowRow(
                        modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
                    ) {

                        data.tags.forEach {
                            Row(
                                modifier = Modifier.padding(top = 8.dp, end = 12.dp).background(
                                    color = MaterialTheme.colors.background,
                                    shape = RoundedCornerShape(200.dp)
                                ).border(
                                    width = .5.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(200.dp)
                                ).clip(RoundedCornerShape(200.dp)),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = it.image,
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                        .padding(5.dp)
                                        .background(color = Color.Transparent, shape = CircleShape)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = it.name, style = MaterialTheme.typography.caption,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            }
                        }

                    }
                }


                item {
                    Text(
                        text = "Developers",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(horizontal = 12.dp)
                            .padding(top = 24.dp, bottom = 12.dp)
                    )
                }

                items(data.developers) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 8.dp)
                            .fillMaxWidth(), verticalAlignment = Alignment.Top
                    ) {

                        AsyncImage(
                            model = it.image,
                            contentDescription = null,
                            modifier = Modifier.size(120.dp).background(
                                color = Color.Transparent, shape = RoundedCornerShape(12.dp)
                            ).clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.width(8.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = it.name,
                                style = MaterialTheme.typography.h4,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Gamecount: " + it.gameCount,
                                style = MaterialTheme.typography.h6
                            )
                        }
                    }

                }


            }


        }


    }
}






