package sats.stackemhigh.android_sprint1_challenge.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_movie_details.*
import sats.stackemhigh.android_sprint1_challenge.R
import sats.stackemhigh.android_sprint1_challenge.model.Movie
import sats.stackemhigh.android_sprint1_challenge.ui.MovieListActivity.Companion.DELETE_MOVIE_REQUEST_CODE
import sats.stackemhigh.android_sprint1_challenge.ui.MovieListActivity.Companion.EDIT_MOVIE_REQUEST_CODE
import sats.stackemhigh.android_sprint1_challenge.ui.MovieListActivity.Companion.MOVIE_DETAILS_REQUEST_KEY
import sats.stackemhigh.android_sprint1_challenge.ui.MovieListActivity.Companion.MOVIE_DETAILS_REQUEST_KEY_MODIFY

class MovieDetailsActivity : AppCompatActivity() {

    private var myMovie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        btn_delete.setOnClickListener {
            val intent = Intent()
            intent.putExtra(MOVIE_DETAILS_REQUEST_KEY_MODIFY,)
        }

        btn_save.setOnClickListener {
            val intent = Intent()
            intent.putExtra(MOVIE_DETAILS_REQUEST_KEY_MODIFY,)

        }

        myMovie = intent.getSerializableExtra(MOVIE_DETAILS_REQUEST_KEY) as Movie
        if (myMovie != null) {
            loadMovie(myMovie)
        }
    }

    fun loadMovie(movie: Movie) {
        et_movie_name.setText(movie.title)
    }
}
