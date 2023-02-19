package com.example.traineemoveapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.traineemoveapp.MainActivity.Companion.DETAIL_FILM
import com.example.traineemoveapp.MainActivity.Companion.FILM_ID
import com.example.traineemoveapp.MainActivity.Companion.LIST_FILMS
import com.example.traineemoveapp.compose.filmDetails.FilmDetailsFragment
import com.example.traineemoveapp.compose.filmList.FilmListFragment
import com.example.traineemoveapp.repository.FilmRepository
import com.example.traineemoveapp.viewModel.DetailFilmViewModel
import com.example.traineemoveapp.viewModel.MainActivityViewModel

sealed class NavRoute(val route: String) {
    object FilmListRoute : NavRoute(LIST_FILMS)
    object DetailsRoute : NavRoute("$DETAIL_FILM/{$FILM_ID}") {
        fun createRoute(filmId: Int) = "$DETAIL_FILM/$filmId"
    }
}

@Composable fun FilmAppScreen(viewModel:MainActivityViewModel, filmRepositoryImpl: FilmRepository) {
    val navController = rememberNavController()
    NavHost(
            navController = navController,
            startDestination = NavRoute.FilmListRoute.route,
    ) {
        composable(route = NavRoute.FilmListRoute.route) {
            FilmListFragment(onClickToDetailScreen = { filmId ->
                navController.navigate(NavRoute.DetailsRoute.createRoute(filmId))
            }, viewModel = viewModel)
        }
        composable(route = NavRoute.DetailsRoute.route, arguments = listOf(navArgument(FILM_ID) {
            type = NavType.IntType
        })) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getInt(FILM_ID)
            Log.v("test_log","filmId = " + filmId.toString() )
            requireNotNull(filmId) { "gamesId parameter wasn't found. Please make sure it's set!" }
            val viewModel = DetailFilmViewModel(filmRepositoryImpl, filmId)
            FilmDetailsFragment(viewModel = viewModel, idFilm = filmId )
        }
    }
}



