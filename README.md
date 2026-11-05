# Trackching

A personal finance tracker. It tracks spending, income, accounts, credit and IOUs by the
day and by event. Android comes first. An iPhone version (installed from the browser) comes later.

## Your data never goes in this repository

Trackching keeps your money data **only on your phone**, in a local database. This repository
holds only code. Do not commit real transactions, backups, screenshots with real amounts,
keystores or passwords. `.gitignore` blocks the common ones (`*.db`, `*.keystore`, backups).

## Project layout

| Folder | What lives there |
| --- | --- |
| `shared/` | Everything both apps share: screens (Compose Multiplatform), data and money logic. |
| `androidApp/` | The small Android shell: starts the app and shows the shared `App()` screen. |
| `.github/workflows/` | CI: builds the app and runs all tests on every push and pull request. |

Built with Kotlin Multiplatform, Compose Multiplatform and (soon) SQLDelight.

## Try the app on your phone

You don't need Android Studio for this.

1. Open the **Actions** tab on GitHub and pick the latest green run on `main`.
2. Under **Artifacts**, download `trackching-debug-apk` and unzip it.
3. Copy the `.apk` to your phone and open it. Android will ask you to allow installs from that app once.

## Build it yourself

Needs JDK 21 and the Android SDK (Android Studio installs both).

```sh
./gradlew build                      # build everything and run all tests
./gradlew :androidApp:installDebug   # install on a connected phone
```

## How work is done

Work happens on a branch and comes in by pull request. CI must be green before a merge.
