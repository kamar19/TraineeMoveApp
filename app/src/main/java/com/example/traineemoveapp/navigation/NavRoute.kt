package com.example.traineemoveapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.traineemoveapp.MainActivity.Companion.DETAIL_FILM
import com.example.traineemoveapp.MainActivity.Companion.FILM_ID
import com.example.traineemoveapp.MainActivity.Companion.LIST_FILMS
import com.example.traineemoveapp.R
import com.example.traineemoveapp.compose.filmDetails.FilmDetailsFragment
import com.example.traineemoveapp.compose.filmList.FilmListFragment
import com.example.traineemoveapp.viewModel.DetailFilmViewModel
import com.example.traineemoveapp.viewModel.MainActivityViewModel

sealed class NavRoute(val route: String) {
    object FilmListRoute : NavRoute(LIST_FILMS)
    object DetailsRoute : NavRoute("$DETAIL_FILM/{$FILM_ID}") {
        fun createRoute(filmId: Long) = "$DETAIL_FILM/$filmId"
    }
}

@Composable
fun FilmAppScreen(viewModel: MainActivityViewModel, viewModelDetail: DetailFilmViewModel) {
    val navController = rememberNavController()
    val stateFilmDetail = viewModelDetail.uiFilmDetailState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = NavRoute.FilmListRoute.route,
    ) {
        composable(route = NavRoute.FilmListRoute.route) {
            FilmListFragment(
                onClickToDetailScreen = { filmId ->
                    navController.navigate(NavRoute.DetailsRoute.createRoute(filmId))
                },
                titleText = stringResource(R.string.title_text),
                onClickToSelectCategory = { genreId ->
                    run {
                        viewModel.updateSelectedGenres(genreId)
                        viewModel.findFilms()
                    }
                },
                viewModel = viewModel
            )
        }
        composable(route = NavRoute.DetailsRoute.route, arguments = listOf(navArgument(FILM_ID) {
            type = NavType.LongType
        })) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getLong(FILM_ID)
            requireNotNull(filmId) { "gamesId parameter wasn't found. Please make sure it's set!" }
            viewModelDetail.updateFilm(filmId)
            FilmDetailsFragment(stateFilmDetail)
        }
    }
}



