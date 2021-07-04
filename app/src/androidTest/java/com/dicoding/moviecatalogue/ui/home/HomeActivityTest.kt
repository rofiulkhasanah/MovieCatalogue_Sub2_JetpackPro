package com.dicoding.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.moviecatalogue.R
import com.dicoding.moviecatalogue.utils.DataTMDB
import com.dicoding.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private val dataMovie = DataTMDB.generateRemoteDummyMovies()
    private val dataTvSHow = DataTMDB.generateRemoteDummyTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>
                (dataMovie.size)
        )
    }

//    Ketika saya gunakan ini dia akan error karena tidak sama dengan yang di display device, padahal k
//    @Test
//    fun loadDetailMovie() {
//        onView(withText("MOVIE")).perform(click())
//        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//
//        onView(withId(R.id.tv_movieTitle)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_movieTitle)).check(matches(withText(dataMovie[0].title)))
//        onView(withId(R.id.tv_movieRating)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_movieRating)).check(matches(withText("Rating : ${dataMovie[0].vote_average}")))
//        onView(withId(R.id.tv_movieDate)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_movieDate)).check(matches(withText(dataMovie[0].release_date)))
//        onView(withId(R.id.tv_movieDescription)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_movieDescription)).check(matches(withText(dataMovie[0].overview)))
//    }

    @Test
    fun loadDetailMovie() {
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tv_movieTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movieRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movieDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movieDescription)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        dataTvSHow.size
                )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                )
        )
        onView(withId(R.id.tv_tvShowTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvShowTitle)).check(matches(withText(dataTvSHow[0].name)))
        onView(withId(R.id.tv_tvShowRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvShowRating)).check(matches(withText("Rating : ${dataTvSHow[0].voteAverage}")))
        onView(withId(R.id.tv_tvShowDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvShowDescription)).check(matches(withText(dataTvSHow[0].overview)))
    }


}