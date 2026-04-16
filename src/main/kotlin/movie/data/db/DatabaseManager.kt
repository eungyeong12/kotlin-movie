package movie.data.db

import java.sql.Connection
import java.sql.DriverManager

object DatabaseManager {
    private const val URL = "jdbc:h2:./movie-db"
    private const val USER = "sa"
    private const val PASSWORD = ""

    val connection: Connection
        get() = DriverManager.getConnection(URL, USER, PASSWORD)
}
