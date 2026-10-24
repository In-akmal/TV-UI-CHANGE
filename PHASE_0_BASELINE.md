# CleanYourTV — Phase 0 Baseline Report

**Date**: 2026-10-23  
**Status**: COMPLETED (Baseline established)

---

## 1. Repository Information

| Parameter | Value |
|---|---|
| **Git Commit** | `f9f0c5353e65c5003d5f2f45784a30c5dee19ecb` |
| **Git Branch** | `main` |
| **Compile SDK** | 34 (Android 14) |
| **Min SDK** | 26 (Android 8.0 Oreo) |
| **Target SDK** | 34 (Android 14) |
| **Build Tools** | 34.0.0 (prescribed in CI and AGENTS.md) |
| **Android Gradle Plugin (AGP)** | 8.5.2 |
| **Gradle Version** | 8.9 (via wrapper) |
| **Java Target** | Java 17 (`sourceCompatibility` & `targetCompatibility`) |
| **Host Java Version** | Oracle JDK 24 (`class file major version 68`) |

---

## 2. Architecture Map

### 2.1 Component Mapping

| Subsystem | Primary Java Classes | Primary Layouts / XML Resources | Responsibility |
|---|---|---|---|
| **Home Screen** | `MainActivity.java` | `activity_main.xml` | Orchestrates screen lifecycle, D-pad dispatch, header, clock, ambient canvas, and grid. |
| **App Grid** | `AppAdapter.java`, `MainActivity.java` | `item_app.xml`, `activity_main.xml` (`@id/grid`) | 4-column GridLayoutManager, tile binding (16:9 banner or 48dp fallback icon + label). |
| **Favorites / Dock** | `DockDecoration.java`, `DockSlots.java` | Code-drawn on `@id/grid` | `RecyclerView.ItemDecoration` rendering backing plate, frosted rim, and padding for items `0..columns-1`. |
| **Top Shelf / Header** | `MainActivity.java`, `HeroArt.java`, `SkyView.java`, `Sky.java`, `WallpaperPlayer.java` | `activity_main.xml` (`@id/leftBlock`, `@id/topButtons`, `@id/hero`, `@id/heroArt`) | Header clock, greeting, date, quick action pills, hero title, metadata, recents dots, and dynamic ambient background. |
| **Focus Highlight** | `GlassView.java`, `Motion.java` | `activity_main.xml` (`@id/glass`) | Hardware-layer cached canvas view with specular sheen and glow, moved via AndroidX `SpringAnimation`. |
| **Navigation Guard** | `GridNavigation.java` | N/A | Intercepts edge `DPAD_LEFT` and `DPAD_RIGHT` events to prevent accidental row-wrapping. |
| **App Library** | `AppLibraryDialog.java`, `LibraryApps.java`, `LibraryLoads.java` | `row_app_library.xml` | Full app drawer dialog (categorized TV / sideloaded apps, custom ordering, hiding/showing apps). |
| **Settings** | `LauncherSettingsActivity.java`, `SystemSettings.java`, `ThemeFiles.java`, `ThemeProvider.java` | `activity_launcher_settings.xml` | Remote-friendly settings for columns, dock rows, dock opacity, animations, wallpaper, theme sync. |
| **Quick Actions** | `QuickSettingsActivity.java`, `InputsRequestActivity.java`, `CoocaaSources.java` | `activity_quick_settings.xml`, `popup_inputs.xml`, `item_input.xml` | Translucent settings overlay, hardware TV input picker, and Wi-Fi shortcut. |
| **Configuration** | `Config.java` | `res/values/config.xml`, `/sdcard/.../config.json` | Two-tier config system: compiled XML defaults overridden at runtime by external JSON. |
| **Background Services** | `HomeKeyService.java`, `InputKeyService.java`, `ClockDream.java` | `res/xml/home_key_service.xml`, `res/xml/input_key_service.xml` | Optional accessibility services for remote Home/Input remapping, and screensaver dream service. |

---

## 3. Build & Test Baseline

| Check | Target / Command | Result | Notes |
|---|---|---|---|
| **Gradle Wrapper** | `./gradlew --version` | **PASS** | Gradle 8.9 verified. |
| **Unit Tests** | `./gradlew testReleaseUnitTest` | **BLOCKED (Environment)** | Host is running JDK 24 (`major version 68`); Gradle 8.9/AGP 8.5.2 requires JDK 17–21. |
| **Lint** | `./gradlew lintRelease` | **BLOCKED (Environment)** | Blocked by host JDK version. |
| **Release Build** | `./gradlew assembleRelease` | **BLOCKED (Environment)** | Blocked by host JDK version and missing local Android SDK 34 platform. |
| **Repository Python Tests** | `python -m unittest discover -s tests` | **PARTIAL** | 658 passed. 99 POSIX/bash-specific tests failed due to Windows environment calling `/bin/bash`. |

---

## 4. Binary Size & Gate Verification

| Metric | Measurement / Specification |
|---|---|
| **Baseline Artifact** | `launcher/app/build/outputs/apk/release/app-release.apk` |
| **Baseline Size (documented)** | ~471 KB (~482,304 bytes) |
| **Hard Size Gate** | **480 KB (491,520 bytes)** |
| **Current Local Artifact** | None present in working tree prior to build setup. |
| **Gate Status** | Hard gate invariant active. No file additions or changes may violate the 491,520 byte limit. |

---

## 5. Dependency Audit

```text
Third-party runtime dependencies: NONE
AndroidX dependencies:
  - androidx.recyclerview:recyclerview:1.3.2
  - androidx.dynamicanimation:dynamicanimation:1.0.0
Google dependencies: NONE
Other runtime dependencies: NONE
Build-only dependencies:
  - com.android.application:8.5.2 (Android Gradle Plugin)
  - junit:junit:4.13.2 (testImplementation only)
```

### Explicit Verification of Forbidden Libraries
- **Compose**: NOT PRESENT (No compose dependencies or plugins)
- **Glide**: NOT PRESENT
- **Coil**: NOT PRESENT
- **Lottie**: NOT PRESENT
- **WebView**: NOT PRESENT
- **Material Components**: NOT PRESENT (No `com.google.android.material`)
- **Large UI libraries**: NOT PRESENT
- **Image-loading libraries**: NOT PRESENT
- **Animation libraries**: Only standard AndroidX physics springs (`dynamicanimation:1.0.0`)

---

## 6. Resource Audit

| Resource Type | Count | Exact File Inventory |
|---|---|---|
| **Raster Bitmaps** | 2 | `drawable-nodpi/banner.jpg` (4.5 KB), `mipmap-xhdpi/ic_launcher.jpg` (3.4 KB) |
| **Vector Drawables** | 13 | `ic_apps.xml`, `ic_av.xml`, `ic_ethernet.xml`, `ic_home_settings.xml`, `ic_home_settings_pill.xml`, `ic_input.xml`, `ic_livetv.xml`, `ic_lock.xml`, `ic_picture.xml`, `ic_settings.xml`, `ic_swap_vert.xml`, `ic_wifi.xml`, `library_check.xml` |
| **XML State Selectors** | 7 | `drawable/input_row_bg.xml`, `drawable/pill_bg.xml`, `drawable/pill_text.xml`, `drawable/pill_text_dim.xml`, `drawable/pill_text_mid.xml`, `drawable/settings_choice_bg.xml`, `color/pill_icon_online.xml` |
| **XML Shapes** | 3 | `drawable/race_bar.xml`, `drawable/status_dot.xml`, `drawable/tile_bg.xml` |
| **XML Layer-Lists** | 2 | `drawable/banner_tv.xml`, `drawable/glass_panel.xml` |
| **Font Binaries** | 1 | `font/outfit.ttf` (110,884 bytes = ~108 KB) |
| **Font XML Aliases** | 4 | `font/outfit_extralight.xml`, `font/outfit_light.xml`, `font/outfit_medium.xml`, `font/outfit_regular.xml` |
| **Layouts** | 14 | 11 base (`activity_main.xml`, `item_app.xml`, `activity_launcher_settings.xml`, `activity_quick_settings.xml`, `dialog_card_prompt.xml`, `dream_clock.xml`, `item_card_row.xml`, `item_input.xml`, `popup_cards.xml`, `popup_inputs.xml`, `row_app_library.xml`) + 3 RTL `layout-iw` overrides |
| **Values Files** | 5 | `values/bools.xml`, `values/config.xml`, `values/fonts.xml`, `values/strings.xml`, `values/styles.xml` *(Note: No `colors.xml` or `dimens.xml` currently exist)* |
| **Existing Background Textures** | 0 external | Pure procedural / dynamic micro-bitmaps |

### Micro-Bitmap Texture Implementation
- Located in: `HeroArt.java`
- Scales source app artwork down to **$48 \times 27$ pixels** (W=48, H=27).
- Applies two successive 2× downsamples to prevent sampling aliasing.
- Saturates RGB color channels mathematically.
- Executes 3 memory box-blur passes along rows and columns to approximate Gaussian smoothing.
- Texture size is ~5 KB in memory; stretched across screen via bilinear hardware filtering in `ImageView`. Zero GPU shader cost.

---

## 7. Remote Navigation Baseline

| Key Input | Static Inspection Behavior | Pre-existing Edge Conditions |
|---|---|---|
| **UP** | From dock row (`index < columns`), escapes up to header quick action pills (`btnInputs`, `btnWifi`, `btnSettings`). From subsequent grid rows, navigates to row above. | Header buttons are grouped horizontally; moving UP from middle dock tile targets closest header button. |
| **DOWN** | Moves down grid row-by-row. From bottom grid row, moves down to `@id/row_app_library` (or modular card rows). | Header buttons explicitly specify `android:nextFocusDown="@+id/grid"`. |
| **LEFT** | Moves to previous column in row. At column 0, `GridNavigation` swallows `DPAD_LEFT` to block horizontal wrapping. | Left edge focus correctly locks at boundary. |
| **RIGHT** | Moves to next column in row. At column `columns - 1`, `GridNavigation` swallows `DPAD_RIGHT` to block horizontal wrapping. | Right edge focus correctly locks at boundary. |
| **OK / CENTER** | Single click launches selected package intent (`launchApp(entry)`). | Fast, direct invocation. |
| **BACK** | Handled in `MainActivity.onKeyDown` / `onBackPressed`. If inside grid (position > 0) or header, scrolls and refocuses tile 0. If already at tile 0, key is consumed to prevent exit to blank screen. | Prevents accidental launcher exit; standard Android TV behavior. |
| **Long OK** | Handled via `itemView.setOnLongClickListener`. Opens context action dialog: Reorder (Move), Hide, App Info. | Functional in `AppAdapter.java`. |

---

## 8. Git Working Tree Status

```text
Branch: main
Commit: f9f0c5353e65c5003d5f2f45784a30c5dee19ecb
Working tree status: Untracked files present
Existing modified files: None
Existing untracked files:
  - docs/UI_REDESIGN_AUDIT.md
Action taken: Existing untracked user file preserved intact. Baseline report added as PHASE_0_BASELINE.md.
```

---

## 9. Existing Issues & Observations

### Pre-existing Architectural Issues
- **Missing Resource Token Files**: The project does not currently have `colors.xml` or `dimens.xml`. Colors and dimensions are dispersed across `styles.xml`, `config.xml`, `item_app.xml`, `activity_main.xml`, and inline Java code.
- **Font File Footprint**: `res/font/outfit.ttf` accounts for 110,884 bytes (~23% of total 480 KB budget).

### Pre-existing Build / Environment Issues
- Local environment runs **Oracle JDK 24**, causing AGP 8.5.2 / Gradle 8.9 to fail with `Unsupported class file major version 68`. Official repository instructions (`AGENTS.md` and `.github/workflows/ci.yml`) require **JDK 17**.
- Local Windows shell lacks bash environment for POSIX-based unit tests in `tests/`.

### Size Gate
- CI enforces `< 491,520 bytes` on `app-release.apk`. Current release APK according to README is ~471 KB.
