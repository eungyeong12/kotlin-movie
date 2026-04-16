package movie.data.db

import movie.data.MovieData
import movie.data.db.movie.MovieRepository

object DatabaseInitializer {
    fun initialize() {
        DatabaseManager.connection.use { connection ->
            SchemaInitializer.initialize(connection)

            val movieRepository = MovieRepository(connection)
            val movies = MovieData.createMovies()

            if (movieRepository.isEmpty()) {
                movies.forEach {
                    movieRepository.save(it, 120)
                }
            }
        }
    }
}
