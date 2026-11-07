package app.trackching

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import app.trackching.db.TrackchingDatabase

/** The 5 bottom tabs. */
enum class Tab(val label: String, val icon: ImageVector, val enabled: Boolean = true) {
    Home("Home", Icons.Outlined.Home),
    Transactions("Transactions", Icons.AutoMirrored.Outlined.List),
    Events("Events", Icons.Outlined.DateRange),
    Categories("Categories", Icons.Outlined.Star),   // real icon comes with the icon set in step 3
    Ious("IOUs", Icons.Outlined.Person, enabled = false),
}

@Composable
fun AppShell(database: TrackchingDatabase) {
    var tab by rememberSaveable { mutableStateOf(Tab.Home) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                Tab.entries.forEach { t ->
                    NavigationBarItem(
                        selected = t == tab,
                        onClick = { tab = t },
                        enabled = t.enabled,
                        icon = { Icon(t.icon, contentDescription = null) },
                        label = { Text(t.label) },
                    )
                }
            }
        },
        floatingActionButton = {
            // The + button. What it opens per tab is wired up in P1-4, P1-8, P1-11 and P1-14.
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
    ) { padding ->
        Box(Modifier.padding(padding).fillMaxSize()) {
            when (tab) {
                Tab.Home -> App(database)
                else -> Text("${tab.label} is coming soon", Modifier.align(Alignment.Center))
            }
        }
    }
}