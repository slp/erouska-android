# erouska-android

## Erouška for Android

This application is based on an Open Source version of TraceTogether app which has been successfullt used in Singapore and Czech Republic.

## How Erouška for Android works (information taken from [this Twitter thread](https://twitter.com/slpnix/status/1242345488969498630) (in Spanish)

The first time you open the app, you're asked to verify the phone number via SMS, just like other popular apps such as WhatsApp or Telegram.
During this registration process, a Central Authority (CA) stores the phone number and generates an encryption key that is returned to the mobile phone.
From this moment, the mobile phone begins to make and listen periodically announcements using Bluetooth Low-Energy (BLE), a variant of Bluetooth that consumes less energy, designed for close contacts.
During this communication, an identifier is shared based on the encryption key obtained during the central authority's registration. For greater security, this identifier is periodically regenerated.
In this way, the mobile phone collects identifiers that it "has seen", while announcing its own. The presence of an identifier on the mobile implies that two users have been close to each other at a given time.
This information is stored locally on the mobile exclusively, without the need to upload it to any server. The user *CAN DELETE the stored information and/or uninstall the application AT ANY TIME*.
This system allows that, when a citizen tests positive in COVID-19, *if he decides that*, he can transfer his tracing data stored in his mobile.
The CA can extract the identifiers of other citizens collected by the infected person, and would contact their owners to suggest preventive measures.

### Citizens' Privacy
To guarantee citizens' privacy, only the CA has the ability to link an identifier to their telephone number. And no geolocation information needs to be collected.

## What is Firebase Cloud Messaging (FCM)

This application is using as its Authentication platform FCM which is a cross-platform messaging solution that lets you reliably send messages at no cost.
Using FCM, you can notify a client app that new email or other data is available to sync. You can send notification messages to drive user re-engagement and retention. (More information [here](https://firebase.google.com/docs/cloud-messaging/) )

### Firebase Cloud Messaging Architecture
Perhaps you could understand a bit better this key component if you take a look to its simplified Architecture diagram:
![FCM Archictural Overview](/doc/FCM_architectural_overview.png)
You could find additional information about FCM [here](https://firebase.google.com/docs/cloud-messaging/fcm-architecture).

## How to build it

The application can be built and executed on a device or emulator using Android Studio 3.2 or higher.
One can also compile the application and run tests from the command line:

```
   > ./gradlew :androidApp:build
```

## How to test it
The first requirement which is needed it should be able to access to *two* Android mobile phones in which you should install the app so you could test it properly.

To test the application use standard Android Studio (https://developer.android.com/studio). 

Bugs are reported via Trello board (https://trello.com/b/4xN2Eeqv/bug-wf)

Improvements let's discuss via COVID-19 slack conference (https://covid19cz.slack.com/), channel *_#bt_android_* (for Android version).

