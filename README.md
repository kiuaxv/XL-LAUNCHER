# X-Launcher - Minecraft Java Launcher for Android

X-Launcher adalah aplikasi launcher Minecraft Java yang revolusioner untuk Android dengan fitur-fitur canggih seperti manajemen akun multi-platform, smart store untuk mod, AI assistant, dan banyak lagi.

## 🎮 Fitur Utama

### 📱 Antarmuka Pengguna
- Desain modern dengan tema gelap + aksen emas (#f5e6b3)
- Navigasi intuitif dengan 2 robot asisten
- Card-based UI untuk informasi versi dan profil

### 👤 Manajemen Akun
- **Offline Account**: Akun lokal tanpa internet
- **Microsoft Account**: Login resmi Microsoft
- **GitHub Account**: Integrasi GitHub dengan kemampuan add-on mods
- **Multi Account Support**: Kelola beberapa akun
- **Account Switching**: Beralih cepat antar akun

### 🤖 AI Assistant (XAI)
- AI Performance Advisor
- AI Resource Pack Advisor
- AI Shader Advisor
- AI World Assistant
- AI Mod Translator
- Crash Analysis dan suggestions

### 🛍️ Smart Store
- Verified Mods
- Creator Pages
- Mod Ratings & Reviews
- Store Collections
- Seasonal Recommendations
- **Auto Dependency Download (AD)**: Download otomatis dependency mods
- Trending & Best Mods
- Discovery Engine
- Similar Mods

### 📦 Mod Management
- Fabric, Forge, NeoForge Support
- Mod Doctor - Cek kompatibilitas
- Auto Dependency Download
- Compatibility Checker
- Mod Security Scanner
- Smart Collections

### ⚙️ Manajemen Versi
- Version Management lengkap
- Continue Playing - Lanjutkan versi terakhir
- Advanced Profiles
- Launch Rules Engine

### 🚀 Performance
- FPS Optimizer
- RAM Optimizer
- Java Argument Generator
- Smart Java Manager
- Thermal Guard
- Background Process Cleaner

### ☁️ Cloud Features
- Cloud Worlds
- Cloud Modpacks
- Cloud Screenshots
- Cross Device Sync
- Cloud Settings
- Backup Sync

### 🔒 Keamanan
- Anti Corruption System
- Safe Mode Launch
- Sandbox Testing
- Backup Before Changes
- Mod Security Scanner
- Dangerous Mod Detection
- Risk Level System

### 🌍 Sistem Dunia
- World Manager
- World Health Analyzer
- Chunk Repair
- Entity Cleaner
- World Compression
- World Backup Center

### 👥 Komunitas
- Friend System
- Launcher Profiles Sharing
- Recommended By Friends
- Community Mod Lists

### 🛠️ Developer Tools
- Mod Development Center
- Resource Pack Studio
- Datapack Creator
- Script Creator

## 🎯 Fitur Unik X-Launcher
- Smart Game Goals - Saran tujuan permainan
- Adventure Generator - Buat tantangan baru
- Mod Synergy Engine - Cari mod yang cocok bersama
- Auto Fix Startup - Perbaikan otomatis
- Universal Minecraft Library - Database lengkap
- XAI Ecosystem - AI integration penuh

## 📋 Struktur Proyek

```
X-LAUNCHER/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/xlauncher/minecraft/
│   │   │   │   ├── ui/activities/
│   │   │   │   ├── data/models/
│   │   │   │   ├── managers/
│   │   │   │   └── utils/
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── values/
│   │   │   │   ├── drawable/
│   │   │   │   └── menu/
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── gradle/
│   └── wrapper/
├── build.gradle
├── settings.gradle
├── gradle.properties
├── gradlew
└── gradlew.bat
```

## 🔧 Build Setup

### Requirements
- Android Studio 2023.1+
- Java 11+
- Android SDK 34
- Gradle 8.0+

### Build Variants

```bash
# Debug dengan runtime
./gradlew assembleDebug

# Debug tanpa runtime
./gradlew assembleNoruntime

# Release
./gradlew assembleRelease
```

### Output APK
- `app-debug.apk` - Debug dengan runtime
- `app-debug-noruntime.apk` - Debug tanpa runtime
- `app-release.apk` - Release build

## 🎨 Tema Warna

```
Primary Gold:      #f5e6b3
Primary Gold Dark: #d4c699
Background:        #000000
Background Darker: #0d0d0d
Accent Cyan:       #00bcd4
Text Primary:      #f5e6b3
Text Secondary:    #b39c7d
```

## 📚 Dependencies

- AndroidX AppCompat
- Material Design 3
- Retrofit 2
- OkHttp 3
- Gson
- Glide
- Room Database
- Coroutines

## 🚀 Roadmap

### Version 1.0 (Launch)
- Core launcher functionality
- Account management
- Smart Store
- XAI Assistant
- Mod management basics

### Version 2.0
- AI Mod Advisor
- AI Compatibility Advisor
- AI Modpack Builder
- One Click Modpack Setup

### Version 3.0
- World Repair Center
- World Recovery
- World Analytics
- World Optimizer

### Version 4.0
- Community Hub
- Sharing Systems

### Version 5.0
- CA (Create A Mod) - AI-powered mod creation

## 📝 Lisensi

X-Launcher © 2026 KIUA XLL. All rights reserved.

## 🤝 Kontribusi

Untuk kontribusi, silakan fork repository ini dan buat pull request.

## 📧 Kontak

Email: kiuaxv@gmail.com
GitHub: @kiuaxv

---

**Dibuat dengan ❤️ untuk komunitas Minecraft**
