package app.trackching

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trackching.db.TrackchingDatabase
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import trackching.shared.generated.resources.Res
import trackching.shared.generated.resources.ic_categories
import trackching.shared.generated.resources.ic_events
import trackching.shared.generated.resources.ic_home
import trackching.shared.generated.resources.ic_ious
import trackching.shared.generated.resources.ic_transactions

/** The 5 bottom tabs. IOUs stays disabled until Phase 3. */
enum class Tab(
    val label: String,
    val icon: DrawableResource,
    val addLabel: String,
    val enabled: Boolean = true,
) {
    Home("Home", Res.drawable.ic_home, "New transaction"),
    Transactions("Transactions", Res.drawable.ic_transactions, "New transaction"),
    Events("Events", Res.drawable.ic_events, "New event"),
    Categories("Categories", Res.drawable.ic_categories, "New category"),
    Ious("IOUs", Res.drawable.ic_ious, "New IOU", enabled = false),
}

@Composable
fun AppShell(database: TrackchingDatabase) {
    val c = TrackchingTheme.colors
    var tab by rememberSaveable { mutableStateOf(Tab.Home) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                Tab.entries.forEach { t ->
                    NavigationBarItem(
                        selected = t == tab,
                        onClick = { tab = t },
                        enabled = t.enabled,
                        icon = { Icon(painterResource(t.icon), contentDescription = null) },
                        label = { Text(t.label, fontWeight = FontWeight.Bold) },
                    )
                }
            }
        },
        floatingActionButton = {
            // What it opens per tab is wired up in P1-4, P1-8, P1-11 and P1-14.
            OffsetBlock(
                Modifier.clickable(role = Role.Button) { },
                color = c.blue,
                edge = c.orange,
            ) {
                Text(
                    "+ ${tab.addLabel.uppercase()}",
                    Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                    color = c.onBlue,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
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