package movie.data.db

import java.sql.DriverManager

object DatabaseManager {
    val connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "")
}
