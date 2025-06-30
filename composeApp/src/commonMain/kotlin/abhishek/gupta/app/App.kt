package abhishek.gupta.app

import abhishek.gupta.app.navigaton.FavoriteGraph
import abhishek.gupta.app.navigaton.GameNavGraph
import abhishek.gupta.app.navigaton.SearchGraph
import abhishek.gupta.favorite.ui.FavoriteScreen

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {

    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = GameNavGraph.Dest.Root.routes) {
        listOf(
            GameNavGraph,
            SearchGraph,
            FavoriteGraph
        ).forEach {
            it.build(
                modifier = Modifier.fillMaxSize(),
                navHostController = navHostController,
                navGraphBuilder = this,
            )
        }
    }


}
