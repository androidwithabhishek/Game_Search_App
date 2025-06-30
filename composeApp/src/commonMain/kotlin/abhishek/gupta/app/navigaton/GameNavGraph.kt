package abhishek.gupta.app.navigaton

import abhishek.gupta.game.ui.game.GameScreen
import abhishek.gupta.game.ui.gameDetails.GameDetailsScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

object GameNavGraph : BaseNavGraph {


    sealed class Dest(val routes: String) {
        data object Root : Dest("/game-root")


        data object Game : Dest("/game")


        data object Details : Dest("/game_details/{id}") {
            fun getRoutes(id: Int) = "/game_details/${id}"
        }

    }

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(

            route = Dest.Root.routes,
            startDestination = Dest.Game.routes,
        ) {
            composable(Dest.Game.routes) {
                GameScreen(
                    modifier = modifier.fillMaxSize(),
                    onSearchClick = { navHostController.navigate(SearchGraph.Dest.Search.routes) },
                    onGameClick = { navHostController.navigate(Dest.Details.getRoutes(it)) },
                    onFavoriteClick = { navHostController.navigate(FavoriteGraph.Desk.Favorite.routes) },

                )
            }

            composable(Dest.Details.routes) {

                val id = it.arguments?.getString("id")
                GameDetailsScreen(
                    modifier = Modifier.fillMaxSize(),
                    id = id.toString(),
                    onBackClick = {
                        navHostController.popBackStack()
                    }, onSearchClick = { navHostController.navigate(SearchGraph.Dest.Search.routes) }
                )

            }
        }

    }
}