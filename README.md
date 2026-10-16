# RootProps

RootProps is an Android utility prototype built with Kotlin and Material 3 Expressive.

## Design

- **Material You dynamic color:** uses the device wallpaper palette on Android 12 and newer.
- **Material 3 Expressive:** uses the Expressive theme and Material button styles, with rounded cards and clearer visual hierarchy.
- **Light and dark appearance:** follows the device theme.
- **Spanish and English:** switch languages from the main screen.

## Current functionality

- Shows a passive indication of whether a known `su` binary file exists. This does **not** verify that root access is granted.
- Keeps system tools disabled in this prototype.
- Does not edit `build.prop`, remount system partitions, or change system files.

## Build artifacts

The GitHub Actions workflow in [Android APK and AAB](.github/workflows/android-build.yml) builds a debug APK and a release AAB on pushes to `main`, then uploads both as a downloadable workflow artifact. The AAB is an unsigned build unless release signing is configured separately.

Open the repository's **Actions** tab, select the latest **Android APK and AAB** run, and download **RootProps-Android-build** from its Artifacts section after the job succeeds.

## Safety

System-property changes can cause instability or make a device fail to boot. RootProps currently provides an informational dashboard only; system-modifying features are intentionally not implemented.
