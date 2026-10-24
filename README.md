# TV-UI-CHANGE
CHANGED UI without booting 
# CleanYourTV 📺

### A lightweight, premium Android TV launcher built for a true 10-foot TV experience.

CleanYourTV is a minimal Android TV launcher focused on **speed, simplicity, D-pad navigation, customization, and an immersive TV-first interface**.

The project started as a lightweight launcher and was redesigned into a complete Home experience with a cinematic interface, 16:9 application cards, App Library, settings, themes, persistent app organization, and physical-TV launcher takeover.

---

## ✨ Preview

![CleanYourTV Home](shots/release_final_home.png)

> CleanYourTV running as the actual Home launcher on a Changhong AI PONT Android TV.

---

## 🚀 Highlights

- 🎯 True Android TV Home / Launcher experience
- 🎮 Full D-pad navigation
- 📺 Designed for 10-foot TV viewing
- 🖼️ Cinematic hero section
- 📱 16:9 application cards
- 📚 App Library
- ⚙️ TV-focused Settings interface
- 🎨 Theme and wallpaper support
- 🔄 Persistent application organization
- 🔐 Safe launcher lifecycle handling
- ⚡ Lightweight architecture
- 📦 Extremely small release APK
- 📴 No unnecessary third-party runtime libraries
- 🌐 Native Android TV architecture

---

## 🎨 UI Redesign

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

## 📚 App Library

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

## ⚙️ Settings

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

## 🎨 Themes & Wallpapers

CleanYourTV supports the project's existing theme architecture, including:

- Wallpaper configuration
- Theme defaults
- Rotating wallpapers
- External theme configuration
- Theme-aware launcher configuration
- Persistent user overrides

The existing external theme architecture was preserved rather than replaced with a heavier theme framework.

---

## 🎮 TV Remote Navigation

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

## 📦 APK Size

One of the project's major constraints was maintaining an extremely small release package.

### Final release

**490,381 bytes**

### Hard limit

**491,520 bytes / 480 KiB**

### Remaining margin

**1,139 bytes**

The final APK remained below the project's strict binary-size ceiling while retaining the redesigned interface and launcher functionality.

---

## 🧱 Architecture

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

## 📺 Physical TV Validation

The launcher was tested on:

**Changhong AI PONT DVB91RM**

- Android 9
- API 28
- ARMv7
- 1280 × 720
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
- Application → HOME → CleanYourTV

The launcher was also verified as the actual Android HOME activity after disabling the OEM Google TV launcher on the test device.

---

## 🔄 OEM Launcher Takeover

On the target Changhong firmware, the stock Google TV launcher has a higher HOME priority and the firmware does not expose a normal default-launcher selector.

For this specific device, the stock launcher can be disabled using:

```bash
adb shell pm disable-user --user 0 com.google.android.tvlauncher
```

After disabling it:

```text
TV Power On
     ↓
Android Boot
     ↓
CleanYourTV
     ↓
HOME button
     ↓
CleanYourTV
```

The original Google TV launcher remains installed and can be restored with:

```bash
adb shell pm enable --user 0 com.google.android.tvlauncher
```

This device-specific configuration does not require modifying the CleanYourTV APK.

---

## 📊 Final Verification

| Area | Result |
|---|---|
| Release build | ✅ |
| Unit tests | ✅ |
| Lint | ✅ |
| APK size limit | ✅ |
| Physical TV | ✅ |
| D-pad navigation | ✅ |
| App Library | ✅ |
| Settings | ✅ |
| Themes | ✅ |
| Persistence | ✅ |
| Cold boot | ✅ |
| HOME button | ✅ |
| Standby/wake | ✅ |
| OEM launcher takeover | ✅ |
| Rollback | ✅ |
| ANRs | 0 |
| Crashes | 0 |

---

## 🛠️ Tech Stack

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

## 🎯 Project Goals

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

## 📸 Screenshots

Recommended screenshots for the repository:

```text
shots/release_final_home.png
shots/p12_02_library_initial.png
shots/p12_03_library_scrolled.png
shots/p12_04_settings_top.png
shots/p12_05_settings_wallpaper.png
```

---

## 📄 Release

Final release APK:

`release/CleanYourTV-release.apk`

SHA-256:

```text
64F252878CD538921B6F1747F2DFAF6F7DC8D9E142F0B593B7423068E85F6433
```

---

## 👨‍💻 Project

Built and redesigned as a hands-on Android TV engineering project, with emphasis on:

**UI/UX → Android architecture → D-pad interaction → performance → binary-size optimization → real hardware validation.**

---

## ⭐ If you find the project interesting

Feel free to explore the code, experiment with the launcher, or build your own Android TV experience from it.

[GitHub Repository](https://github.com/Gathin23/cleanyourtv)
