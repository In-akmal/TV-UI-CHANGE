# TV-UI-CHANGE
CHANGED UI without booting 
# CleanYourTV =ƒô¦

### A lightweight, premium Android TV launcher built for a true 10-foot TV experience.

CleanYourTV is a minimal Android TV launcher focused on **speed, simplicity, D-pad navigation, customization, and an immersive TV-first interface**.

The project started as a lightweight launcher and was redesigned into a complete Home experience with a cinematic interface, 16:9 application cards, App Library, settings, themes, persistent app organization, and physical-TV launcher takeover.

---

## G£¿ Preview

![CleanYourTV Home](shots/release_final_home.png)

> CleanYourTV running as the actual Home launcher on a Changhong AI PONT Android TV.

---

## =ƒÜÇ Highlights

- =ƒÄ» True Android TV Home / Launcher experience
- =ƒÄ« Full D-pad navigation
- =ƒô¦ Designed for 10-foot TV viewing
- =ƒû+n+Å Cinematic hero section
- =ƒô¦ 16:9 application cards
- =ƒôÜ App Library
- GÜÖn+Å TV-focused Settings interface
- =ƒÄ¿ Theme and wallpaper support
- =ƒöä Persistent application organization
- =ƒöÉ Safe launcher lifecycle handling
- GÜí Lightweight architecture
- =ƒôª Extremely small release APK
- =ƒô¦ No unnecessary third-party runtime libraries
- =ƒîÉ Native Android TV architecture

---

## =ƒÄ¿ UI Redesign

The launcher was redesigned around a **premium minimal TV interface** rather than a traditional Android application layout.

### Home Screen

![Home Screen](shots/release_final_home.png)

The Home screen contains:

- Greeting
- Large clock
- Date
- Quick actions
- Cinematic featured section
- App shelf
- Focus-aware application cards
- Dedicated App Library entry

The design prioritizes visual hierarchy and readability from a distance.

---

## =ƒôÜ App Library

The App Library provides a dedicated TV-friendly application browser with:

- 16:9 application cards
- Alphabetical sorting
- D-pad navigation
- Focus animations
- Application launching
- Add/remove from Home
- Application information
- Persistent Home organization

Example screenshots:

```text
shots/p8_05_library_initial.png
shots/p8_06_library_first_card_focused.png
shots/p8_08_library_scrolled.png
shots/p8_09_library_context_menu.png
```

---

## GÜÖn+Å Settings

The Settings interface was redesigned to match the main launcher:

- Dark glass-inspired surfaces
- TV-friendly controls
- D-pad navigation
- Column configuration
- Dock configuration
- Animation controls
- Wallpaper selection
- Theme integration

![Settings](shots/p12_04_settings_top.png)

---

## =ƒÄ¿ Themes & Wallpapers

CleanYourTV supports the project's existing theme architecture, including:

- Wallpaper configuration
- Theme defaults
- Rotating wallpapers
- External theme configuration
- Theme-aware launcher configuration
- Persistent user overrides

The existing external theme architecture was preserved rather than replaced with a heavier theme framework.

---

## =ƒÄ« TV Remote Navigation

The launcher was designed around the physical TV remote rather than mouse/touch interaction.

Supported navigation includes:

| Input | Behavior |
|---|---|
| UP | Navigate upward |
| DOWN | Navigate downward |
| LEFT | Navigate left |
| RIGHT | Navigate right |
| OK | Launch / select |
| BACK | Return / dismiss |
| LONG OK | Context / reorder actions |

Navigation boundaries are deterministic and do not unexpectedly wrap around the interface.

---

## =ƒôª APK Size

One of the project's major constraints was maintaining an extremely small release package.

### Final release

**484,277 bytes**

### Hard limit

**491,520 bytes / 480 KiB**

### Remaining margin

**7,243 bytes**

The final APK remained below the project's strict binary-size ceiling while retaining the redesigned interface and launcher functionality.

---

## =ƒº¦ Architecture

CleanYourTV intentionally avoids heavyweight UI frameworks.

The project uses:

- Native Android Views
- Java
- Android XML layouts
- Android framework APIs
- AndroidX DynamicAnimation
- AndroidX RecyclerView

The project does **not** depend on:

- Jetpack Compose
- WebView
- Glide
- Coil
- Lottie
- Custom font packages
- Large UI frameworks

This keeps the launcher lightweight and suitable for constrained Android TV hardware.

---

## =ƒô¦ Physical TV Validation

The launcher was tested on:

**Changhong AI PONT DVB91RM**

- Android 9
- API 28
- ARMv7
- 1280 +ù 720
- 213 dpi

Physical verification included:

- Cold boot
- Home button
- D-pad navigation
- App launching
- App Library
- Settings
- Wallpaper selection
- Theme behavior
- Persistence
- Standby/wake
- Launcher re-entry
- Application GåÆ HOME GåÆ CleanYourTV

The launcher was also verified as the actual Android HOME activity after disabling the OEM Google TV launcher on the test device.

---

## =ƒöä OEM Launcher Takeover

On the target Changhong firmware, the stock Google TV launcher has a higher HOME priority and the firmware does not expose a normal default-launcher selector.

For this specific device, the stock launcher can be disabled using:

```bash
adb shell pm disable-user --user 0 com.google.android.tvlauncher
```

After disabling it:

```text
TV Power On
     Gåô
Android Boot
     Gåô
CleanYourTV
     Gåô
HOME button
     Gåô
CleanYourTV
```

The original Google TV launcher remains installed and can be restored with:

```bash
adb shell pm enable --user 0 com.google.android.tvlauncher
```

This device-specific configuration does not require modifying the CleanYourTV APK.

---

## =ƒôè Final Verification

| Area | Result |
|---|---|
| Release build | G£à |
| Unit tests | G£à |
| Lint | G£à |
| APK size limit | G£à |
| Physical TV | G£à |
| D-pad navigation | G£à |
| App Library | G£à |
| Settings | G£à |
| Themes | G£à |
| Persistence | G£à |
| Cold boot | G£à |
| HOME button | G£à |
| Standby/wake | G£à |
| OEM launcher takeover | G£à |
| Rollback | G£à |
| ANRs | 0 |
| Crashes | 0 |

---

## =ƒ¢án+Å Tech Stack

**Language**

Java

**UI**

Android Views + XML

**Platform**

Android TV

**Build**

Gradle + Android Gradle Plugin

**Target**

Android API 34

**Minimum**

Android API 26

---

## =ƒÄ» Project Goals

The main goals of the redesign were:

1. Build a genuinely TV-first interface.
2. Make D-pad navigation predictable.
3. Improve visual hierarchy.
4. Keep the launcher lightweight.
5. Preserve the existing architecture.
6. Avoid unnecessary dependencies.
7. Maintain a strict APK size budget.
8. Validate the experience on real TV hardware.

---

## =ƒô+ Screenshots

Recommended screenshots for the repository:

```text
shots/release_final_home.png
shots/p12_02_library_initial.png
shots/p12_03_library_scrolled.png
shots/p12_04_settings_top.png
shots/p12_05_settings_wallpaper.png
```

---

## =ƒôä Release

Final release APK:

`release/CleanYourTV-release.apk`

SHA-256:

```text
5e6769f1d7c74f1818cd98288d9fd6d8970e95dda0b36bad9917d3c926d34891
```

---

## =ƒæ¿GÇì=ƒÆ+ Project

Built and redesigned as a hands-on Android TV engineering project, with emphasis on:

**UI/UX GåÆ Android architecture GåÆ D-pad interaction GåÆ performance GåÆ binary-size optimization GåÆ real hardware validation.**

---

## G¡É If you find the project interesting

Feel free to explore the code, experiment with the launcher, or build your own Android TV experience from it.

[GitHub Repository](https://github.com/Gathin23/cleanyourtv)
