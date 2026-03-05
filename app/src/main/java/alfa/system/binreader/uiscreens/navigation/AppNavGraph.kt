package alfa.system.binreader.uiscreens.navigation

import alfa.system.binreader.uiscreens.history.HistoryScreen
import alfa.system.binreader.uiscreens.search.BinSearchScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


object Routes {
    const val SEARCH = "search"
    const val HISTORY = "history"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SEARCH
    ) {
        composable(route = Routes.SEARCH) {
            BinSearchScreen(onOpenHistory = { navController.navigate(Routes.HISTORY) })
        }
        composable(route = Routes.HISTORY) {
            HistoryScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}