package movie.data.db

import java.sql.DriverManager

object DatabaseManager {
    val connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "")
}
