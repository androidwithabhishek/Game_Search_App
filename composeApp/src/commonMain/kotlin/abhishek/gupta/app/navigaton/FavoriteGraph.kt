package abhishek.gupta.app.navigaton

import abhishek.gupta.favorite.ui.FavoriteScreen
import abhishek.gupta.search.ui.SearchScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

object FavoriteGraph : BaseNavGraph {


    sealed class Desk(val routes: String) {
        data object Root : Desk("/favorite_root")
        data object Favorite : Desk("/favorite")

    }

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(route = Desk.Root.routes, startDestination = Desk.Favorite.routes) {


            composable(Desk.Favorite.routes) {
                FavoriteScreen(
                    modifier = Modifier.fillMaxSize(),
                    onBackClick = {navHostController.popBackStack()},
                    onDetails = { navHostController.navigate(GameNavGraph.Dest.Details.getRoutes(it))},
                    isDeleteShown = true
                )
            }

        }


    }


}