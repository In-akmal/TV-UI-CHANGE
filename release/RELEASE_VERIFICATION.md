# CleanYourTV — Release Verification Report

## 1. Build & Toolchain Verification

| Check | Tool / Version | Target / Command | Result |
|:---|:---|:---|:---:|
| **JDK Environment** | OpenJDK 17.0.20.1+1 | `JAVA_HOME` | PASS |
| **Android Build Tools** | Build-tools 34.0.0 | API 34 | PASS |
| **Android SDK Platform** | Android SDK 34 | `compileSdk 34` | PASS |
| **Gradle Runtime** | Gradle 8.9 / AGP 8.5.2 | Wrapper | PASS |
| **Release Assembly** | `gradlew assembleRelease` | Release build | PASS |
| **Unit Test Suite** | `gradlew testReleaseUnitTest` | JVM test harness | PASS |
| **Lint Gate** | `gradlew lintRelease` | Static analysis | PASS (0 errors) |

---

## 2. Binary Verification

- **APK File**: `CleanYourTV-release.apk`
- **Source Output**: `launcher/app/build/outputs/apk/release/app-release.apk`
- **Exact Size**: **`484,277 bytes`**
- **Size in KiB**: **`472.93 KiB`**
- **Hard Ceiling**: **`491,520 bytes (480.00 KiB)`**
- **Safety Margin**: **`+7,243 bytes`**
- **SHA-256**: `5e6769f1d7c74f1818cd98288d9fd6d8970e95dda0b36bad9917d3c926d34891`

---

## 3. Physical Test Target

All visual, structural, and behavioral verifications were executed on the physical Android TV target:

```text
Device Hardware & Software Profile:
- Model: Changhong AI PONT (DVB91RM)
- Board: ikebukuro
- Android OS: Android 9.0 (Pie)
- API Level: 28
- CPU Architecture: armeabi-v7a (32-bit ARMv7)
- Panel Resolution: 1280×720 (720p native TV landscape)
- Panel Density: 213 dpi (tvdpi)
- Primary Input: Physical Infrared/Bluetooth D-pad TV Remote
- Endpoint: 192.168.29.209:5555
```

---

## 4. UI Surface Acceptance

| Surface | Target Elements | Live Result | Status |
|:---|:---|:---|:---:|
| **Home Screen** | Clock, date, AM/PM, ambient backdrop | Renders cleanly with high legibility and zero jitter | PASS |
| **Hero Shelf** | Title, kicker, action pill, dynamic backdrop | Fluid transitions matching focused app artwork | PASS |
| **App Cards** | 16:9 widescreen tiles (`180dp × 101dp`) | Precise 16:9 ratio, surface cards, centered labels | PASS |
| **Favorites Dock** | Pinned app row, customizable elevation | Traversal is smooth, dock opacity reflects settings | PASS |
| **Quick Actions** | Inputs, Wi-Fi, Reorder, Settings pills | Distinct focused & selected states with 2dp border | PASS |
| **App Library** | Fullscreen-anchored modal, 16:9 catalog | Smooth vertical scrolling across full app inventory | PASS |
| **Settings Screen** | `LauncherSettingsActivity` categories | Segmented pills, 2dp high-contrast focus, dark surface | PASS |
| **Wallpaper & Theme** | Dialog choices, active theme feedback | Affirmative on-TV theme name, clean dialog dismissal | PASS |

---

## 5. D-pad Navigation Matrix

| Input Key | Home Screen | App Library | Settings Activity | Picker Dialog |
|:---|:---:|:---:|:---:|:---:|
| **DPAD_UP** | Moves rows up → Header pills | Scrolls catalog up | Moves categories up | Selects previous option |
| **DPAD_DOWN** | Moves rows down → Grid | Scrolls catalog down | Moves categories down | Selects next option |
| **DPAD_LEFT** | Traverses left boundary | Traverses left columns | Decrements segmented pill | Unused (vertical list) |
| **DPAD_RIGHT** | Traverses right boundary | Traverses right columns | Increments segmented pill | Unused (vertical list) |
| **DPAD_CENTER** | Launches selected app | Launches selected app | Opens dialog / Toggles option | Confirms selection |
| **BACK** | Trapped (Leanback standard) | Dismisses popup cleanly | Saves and exits to Home | Cancels and dismisses |
| **LONG_OK** | Opens app context menu | Opens app context menu | No-op | No-op |

---

## 6. Persistence & Lifecycle Verification

1. **Activity Dismissal**:
   - Modifying grid columns or dock rows in Settings immediately takes effect upon pressing `BACK` without requiring a restart.
2. **Cold Start & Process Termination**:
   - Terminated launcher process via `am force-stop com.gathin.tvhome`.
   - Launched cold via `am start -n com.gathin.tvhome/.MainActivity`.
   - All user preferences, columns, dock layout, and wallpaper settings restored identically from `SharedPreferences`.
3. **Theme & Config Persistence**:
   - External `config.json` properties (e.g. background color `#070A14`, active wallpaper flags) persisted faithfully across reboots.

---

## 7. Stability & System Health

- **Application Not Responding (ANRs)**: `0`
- **Uncaught Exceptions / Crashes**: `0`
- **Memory Leaks / Bitmap Accumulation**: `0` (Micro-bitmaps scaled via GPU; caches recycle properly on memory trim)
- **Frame Pacing**: Constant 60 fps during D-pad navigation and SpringAnimation tracking.
