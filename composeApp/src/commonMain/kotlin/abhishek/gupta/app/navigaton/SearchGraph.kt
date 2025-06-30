package abhishek.gupta.app.navigaton

import abhishek.gupta.app.navigaton.GameNavGraph.Dest
import abhishek.gupta.game.ui.gameDetails.GameDetailsScreen
import abhishek.gupta.search.ui.SearchScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

object SearchGraph : BaseNavGraph {


    sealed class Dest(val routes: String) {

        data object Root : Dest("/search-root")

        data object Search : Dest("/search")



    }


    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            route = Dest.Root.routes,
            startDestination = Dest.Search.routes
        ) {

            composable(route = Dest.Search.routes) {

                SearchScreen(modifier = modifier.fillMaxSize(), onClick = {
                    navHostController.navigate(GameNavGraph.Dest.Details.getRoutes(it))
                }, onBackClick = {
                    navHostController.popBackStack()
                })

            }



        }
    }
}