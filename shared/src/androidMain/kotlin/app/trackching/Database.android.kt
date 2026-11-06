package app.trackching

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.trackching.db.TrackchingDatabase

/** Opens (or creates) trackching.db in the app's private storage. */
fun createDatabase(context: Context): TrackchingDatabase {
    val driver = AndroidSqliteDriver(
        schema = TrackchingDatabase.Schema,
        context = context,
        name = "trackching.db",
    )
    return TrackchingDatabase(driver)
}