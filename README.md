# ElderGuard

An Android safety companion for elderly people living alone. Built for **Stonehill Techno Fest 2026**.

ElderGuard runs quietly in the background and alerts a family member or carer when something goes wrong — a hard fall, or the phone about to die — and keeps a simple medication schedule.

## Features

- **Fall detection** — a foreground service watches the accelerometer; a sudden spike in total acceleration (> 35 m/s²) is treated as a fall and fires an emergency WhatsApp message to the saved contact.
- **Low-battery warning** — when the phone drops below 50%, the contact is told once, so a dead phone isn't mistaken for silence.
- **Medication list** — add medicines and times; stored locally with Room so it works offline.
- **One-tap navigation** — opens Google Maps turn-by-turn to a preset destination.
- **Test alert** — sends a dummy "fall detected" message so the family can check the link works.

## Tech

Java · Android SDK 34 (min 26) · Room (via KSP) · Foreground service + `SensorManager` · Material Components

```text
app/src/main/java/com/example/elderguard/
├── activities/   MainActivity (home buttons), MedicationActivity
├── services/     MonitorService — accelerometer fall detection
├── receivers/    BatteryReceiver — low-battery alert
└── data/         Room database, Medication entity + DAO
```

## Running it

1. Open the project in Android Studio (it will create `local.properties` for your SDK).
2. Replace `YOUR_NUMBER` in `MonitorService.java`, `BatteryReceiver.java` and `MainActivity.java` with the emergency contact's WhatsApp number, including country code (e.g. `91XXXXXXXXXX`).
3. Run on a real device — fall detection needs a physical accelerometer.

## Roadmap

- Ask "are you okay?" after a fall and only escalate if there's no response
- Move the emergency contact into in-app settings
- Scheduled notifications for medication times
