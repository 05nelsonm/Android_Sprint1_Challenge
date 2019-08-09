package sats.stackemhigh.android_sprint1_challenge.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_movie_list.*
import sats.stackemhigh.android_sprint1_challenge.R
import sats.stackemhigh.android_sprint1_challenge.model.Movie

class MovieListActivity : AppCompatActivity() {

    private val movieList = mutableListOf<Movie>()
    private var editMovieCheck: Boolean = false
    private var addMovieCheck: Boolean = false
    private var deleteMovieCheck: Boolean = false
    private var workingIndexValue: Int = -1

    companion object {
        const val EDIT_MOVIE_REQUEST_CODE = 9143657
        const val DELETE_MOVIE_REQUEST_CODE = 7719971
        const val MOVIE_DETAILS_REQUEST_KEY = "ALKDJSHP9IN1OI8JAXLKVH"
        const val MOVIE_DETAILS_REQUEST_KEY_MODIFY = "P98I1BH3IREUHF09UPOKAUHIKUH"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        btn_add_movie.setOnClickListener {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            startActivityForResult(intent, EDIT_MOVIE_REQUEST_CODE)
        }
    }

    override fun onResume() {
        super.onResume()

        when {
            addMovieCheck -> addToListView(workingIndexValue)
            editMovieCheck -> editListView(workingIndexValue)
            deleteMovieCheck -> refreshListView()
        }
    }

    private fun createTextView(movie: Movie, index: Int): TextView {

        // Set the index of the movie class = to movieList index
        movie.index = index
        val newMovieView = TextView(this)
        newMovieView.text = movie.title
        newMovieView.textSize = 18f
        newMovieView.id = movie.index
        if (movie.watched) {
            // set strike through text
        }

        newMovieView.setOnClickListener {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DETAILS_REQUEST_KEY, movieList[index])
            startActivityForResult(intent, EDIT_MOVIE_REQUEST_CODE)
        }

        return newMovieView
    }

    private fun addToListView(index: Int) {
        addMovieCheck = false
        val view = createTextView(movieList[index], index)
        sv_ll.addView(view)
    }

    private fun editListView(index: Int) {
        editMovieCheck = false
        val newMovieInfo = movieList[index]
        val existingView = findViewById<TextView>(index)
        existingView.text = newMovieInfo.title
        if (newMovieInfo.watched) {
            // set strike through text for existingView
        }
    }

    private fun refreshListView() {
        deleteMovieCheck = false
        sv_ll.removeAllViews()
        for (i in 0 until movieList.size) {
            sv_ll.addView(createTextView(movieList[i], i))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val newMovie = data?.getSerializableExtra(MOVIE_DETAILS_REQUEST_KEY_MODIFY) as Movie

        // hitting save button returns MOVIE_DETAILS_REQUEST_KEY_MODIFY back
        if (requestCode == EDIT_MOVIE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // If that movie is a new Movie, add it to movieList, update the index of that
            // new Movie, and set addMovieCheck to true
            if (newMovie.index < 0) {
                movieList.add(newMovie)
                newMovie.index = movieList.size - 1
                workingIndexValue = newMovie.index
                addMovieCheck = true

                // Otherwise, modify movieList and set editMovieCheck to true
            } else {
                movieList[newMovie.index] = newMovie
                workingIndexValue = newMovie.index
                editMovieCheck = true
            }

        // Hitting delete button returns DELETE_MOVIE_REQUEST_CODE back
        } else if (requestCode == DELETE_MOVIE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // If user hits the delete button for a Movie that already exists
            if (newMovie.index > 0) {

                // Remove that Movie from the list and set deleteMovieCheck to true to refresh listView
                movieList.removeAt(newMovie.index)
                deleteMovieCheck = true

                // Refactor Movie.index values for each Movie in the list
                for (i in 0 until movieList.size) {
                    movieList[i].index = i
                }
            }
        }
    }
}