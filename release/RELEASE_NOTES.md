# CleanYourTV — Release Notes

## Product
**CleanYourTV Android TV Launcher**
- Package: `com.gathin.tvhome`
- Version: `2.7` (VersionCode `8`)
- Target: Android TV / Google TV (API 26–34)

## Release Status
**`RELEASE READY`**

---

## UI Redesign Work Completed

The CleanYourTV user interface redesign modernizes the visual and spatial experience across all launcher surfaces while strictly preserving ultra-lean resource consumption, physics-based dynamic animations, and sub-480 KB binary constraints.

1. **Design Token System**:
   - Centralized semantic color tokens, elevation tiers, standard spacing metrics, and typography hierarchy (`colors.xml`, `dimens.xml`, `styles.xml`).
2. **Home Shell & Ambient Backdrop**:
   - Fullscreen TV layout featuring top header (clock, AM/PM, human-readable localized date, status indicators), ambient backdrop illumination, and zero layout flicker.
3. **App Cards (16:9 Widescreen)**:
   - Modernized from legacy portrait proportions to standard 16:9 widescreen cards (`180dp × 101dp` / `236×132 px` at 213 dpi) matching commercial streaming platforms.
4. **Focus & Navigation Treatment**:
   - High-contrast 2dp focus stroke, accent selection markers, and smooth physics-based floating spring highlight (`GlassView`).
5. **Hero Shelf**:
   - Cinematic preview container displaying high-clarity app title, section kicker, action pill, and dynamic backdrop art with smooth bilinear GPU scaling.
6. **Favorites Dock**:
   - Pinned favorites dock with dedicated card elevation, customizable opacity, and seamless D-pad traversal.
7. **Quick Action Pills**:
   - Top-bar quick action pills for HDMI Inputs, Wi-Fi connectivity, Reorder, and Launcher Settings with distinct focused and active states.
8. **App Library**:
   - Fast, modern PopupWindow modal with 16:9 app cards, pinned apps hierarchy, alphabetical catalog sorting, and fluid vertical scrolling.
9. **Settings UI Refinement**:
   - Dark/glass design language applied to `LauncherSettingsActivity`, segmented choice controls for grid columns and dock rows, custom single-choice dialogs, and affirmative active theme reporting.
10. **Theme UI Refinement**:
    - Affirmative on-TV active theme name communication and override tracking without altering external theme workflows.
11. **Surgical APK Optimization**:
    - Byte-level string formatting optimization reducing binary size by 16 bytes while avoiding risk-prone refactoring.
12. **Physical TV Live Acceptance**:
    - Complete live verification on physical Android TV hardware (Changhong AI PONT, Android 9 / API 28).

---

## Technical Specifications & Architecture

### Architectural Integrity
- Pure native Android Framework & AndroidX View architecture (`RecyclerView`, `DynamicAnimation`).
- Zero Kotlin runtime overhead.
- Zero Jetpack Compose.
- Zero WebViews.
- Zero heavy image loaders (No Glide, Picasso, Coil).
- Zero animation runtimes (No Lottie).
- Zero network permissions (`android.permission.INTERNET` is strictly absent from the manifest).
- Zero telemetry, analytics, or background services.

### Theme & Configuration System
- Preserves the file-based `config.json` and wallpaper contract.
- External theme workflow remains fully compatible (`tools/theme.sh` / `tools/theme.ps1`).
- Custom wallpapers and default gradient/solid fallbacks operate reliably.

### Navigation & D-pad Semantics
- 100% deterministic spatial navigation across standard TV remotes (Up, Down, Left, Right, Center/OK, Back, Menu/Long OK).
- Home screen traps back navigation cleanly (Leanback standard).
- Popups, dialogs, and secondary activities restore focus precisely to triggering controls on dismiss or exit.

---

## APK Budget & Size History

The release APK conforms to the CleanYourTV binary size gate (`<= 491,520 bytes / 480 KB`):

| Phase / Milestone | APK Size (Bytes) | Delta vs Baseline | Margin to Ceiling |
|:---|---:|---:|---:|
| Hard APK Ceiling | 491,520 B | — | 0 B |
| Phase 0 (Baseline) | 490,249 B | 0 B | 1,271 B |
| Phase 8 (App Library Redesign) | 490,253 B | +4 B | 1,267 B |
| Phase 9 (Settings UI Refinement) | 490,265 B | +12 B | 1,255 B |
| Phase 10 (Themes UI Refinement) | 490,397 B | +132 B | 1,123 B |
| Phase 11 (Surgical APK Optimization) | 490,381 B | -16 B | 1,139 B |
| **Phase 12 / Final Release** | **490,381 B** | **0 B** | **1,139 B** |

- **Exact Size**: `484,277 bytes` (`472.93 KiB`)
- **Remaining Margin**: `7,243 bytes` under the hard gate
- **SHA-256**: `5e6769f1d7c74f1818cd98288d9fd6d8970e95dda0b36bad9917d3c926d34891`
