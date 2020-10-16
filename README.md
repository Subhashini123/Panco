
CircleCI Build 

Production
[![CircleCI](https://circleci.com/gh/planoco/android-sg/tree/master.svg?style=svg&circle-token=70ad048de202c58850800a346008cac01192dc7e)](https://circleci.com/gh/planoco/android-sg/tree/master)

Staging
[![CircleCI](https://circleci.com/gh/planoco/android-sg/tree/staging.svg?style=svg&circle-token=70ad048de202c58850800a346008cac01192dc7e)](https://circleci.com/gh/planoco/android-sg/tree/staging)


# Plano Android

Plano is a child management app that promotes children's eye health and device use moderation.

It has the following features:

- Myopia records
- Reminder popups when child brings the device too close to their face
- Schedule, app, location and remote restrictions
- Blue light filter
- Remote locking
- Plano shop
- Plano games: Plano Pairs and Eyexercise

Parents can add multiple children to the app. Each child has a profile with different settings. When a device is in Child Mode, the child will see pop ups restricting device use in the following scenarios:

- Device is too close to their face
- Device is used in improper position
- Device is brought out of geofenced area
- Device is used outside of scheduled timings
- Blocked app on device is used
- Browser on device is used

## Overview

This project has a primitive architecture. No MVP/MVVM/Clean architecture is employed. Most of the business logic is in activities/fragments.

Networking library in use is Retrofit + Gson
EventBus is widely used for communication between activities and fragments.
Glide is used for image loading.

Plano runs a background service that "ticks" every second, checking a wide variety of conditions and phone sensors.

### Eye Distance Tracking
Eye distance tracking is done by [Google's Mobile Vision library](https://developers.google.com/vision/). Due to the wide variety of Android devices and different camera specifications, users will experience mixed results. The distance between the  child's eyes is continuously tracked. Also see TODO section at the end of this document.

### Posture Tracking
Posture tracking uses the gyroscope and accelerometer of the device to determine if the phone is in a defined "incorrect" position.

### Geofencing
[Google's Android geofencing](https://developer.android.com/training/location/geofencing.html) is utilized to track the device's location and fire triggers when the device enters/exits a geofence.

## Development

Production CMS: https://plano.co/planocms/

UAT CMS: https://uat.plano.co/planocms/

Staging CMS: https://staging.plano.co/planocms/

Staging API Documentation: https://staging.plano.co/planoapi/HelpPage.html

Clone and create a branch, prefixed with either of the following

- cr_feature_description
- bugfix_bug_description

Commit your code frequently and as concisely as possible to facilitate code review. Git commit messages should be short and sweet.

Once your work is complete and ready, create a pull request to `master`. Pull requests will be reviewed and merged (squashed). 

Notes and caveats:

- Backend post and response models are not consistent. Booleans are represented in 1/0 or sometimes as "True"/"False" strings.
- No pagination is in place for potentially long lists e.g. plano shop data
- No reset/timeout mechanism is in place for Child Mode. Once a device is in child mode, backend holds the child access token until the device returns to parent mode. This is a hindrance during development and should be fixed asap.
- Notification list is kept on server for 1 week

## Build and Deployment

Android Studio 3.0.1

Build variants used in this project:

- Staging
- UAT
- Production

This project utilizes [Fastlane](https://github.com/fastlane/fastlane) to generate APKs for submission to PM/client. 
Plano Android's signing key and credentials are included in the repository and in `build.gradle`.

## Code Style Guidelines

English, please.

Add comments where appropriate, remove system generated comments like contributor's name, sample code from Android doc, etc unless necessary.

Wrap your code at 100 characters. Indent where it makes sense.

Image resources should be processed as follows:

- Export verbatim from Photoshop PSD file
- Size the exported image with [Final Android Resizer.jar](https://github.com/asystat/Final-Android-Resizer/blob/master/Executable%20Jar/Final%20Android%20Resizer.jar?raw=true). Select `xxhdpi` for source density
- *Do not do any image compression*; this will be done upon merge to `master`
- Do not be lazy; create simple drawables like circles or lines via xml instead of exporting and using costly image resources

## Notes

The developer taking over this project must do the following:
- Create a new project in Firebase for `google-services.json` for Staging and UAT environments as they currently belong to Codigo Pte Ltd and will be removed upon project handover.
- Modify (or remove) fastlane as the required `.env` file will not be provided.




