# X-LAUNCHER - Implementation Guide

## 🎯 Overview

هذا المشروع يحتوي على نظام launcher متقدم لـ Minecraft Java Edition على الأندرويد مع:
- ✅ دعم حسابات متعددة (Offline, Microsoft, GitHub)
- ✅ متجر مودات ذكي (Smart Store)
- ✅ مساعد ذكي (XAI)
- ✅ نظام تحليلات
- ✅ تحميل dependencies تلقائي

---

## 📁 Project Structure

```
app/
├── src/main/
│   ├── java/com/xlauncher/minecraft/
│   │   ├── ui/
│   │   │   ├── activities/          # Main activities
│   │   │   ├── fragments/           # UI fragments
│   │   │   └── adapters/            # RecyclerView adapters
│   │   ├── data/
│   │   │   └── models/              # Data models
│   │   ├── managers/                # Business logic managers
│   │   ├── network/
│   │   │   └── api/                 # API interfaces & models
│   │   └── utils/                   # Utility classes
│   ├── res/
│   │   ├── layout/                  # XML layouts
│   │   ├── drawable/                # Vector drawables
│   │   ├── values/                  # Colors, strings, themes
│   │   └── menu/                    # Menu resources
│   └── AndroidManifest.xml
├── build.gradle
└── proguard-rules.pro
```

---

## 🔑 Key Classes

### Activities

#### SplashActivity
- Splash screen on app launch
- Redirects to MainActivity

#### MainActivity
- Main launcher interface
- Shows user profile, version, play button
- Left robot (XAI) → Opens XAIActivity
- Right card → Profile/Version/Play

#### ProfileActivity
- Edit profile information
- Change username
- Change profile picture
- Add additional accounts

#### SmartStoreActivity
- Browse and search mods
- Download with auto dependencies
- View mod details
- Rating and reviews

#### XAIActivity
- Chatting with AI assistant
- Performance recommendations
- Mod suggestions
- Crash analysis

#### GitHubLoginActivity
- GitHub OAuth login
- Personal access token input
- Account verification

#### GitHubModsActivity
- Show user's GitHub mods
- Publish mods to Smart Store
- Manage versions

### Managers

#### AccountManager
- Multi-account management
- Save/load accounts from SharedPreferences
- Account switching
- Account authentication

#### GitHubAuthManager
- GitHub API authentication
- Token validation
- User info retrieval

#### GitHubModManager
- Fetch user repositories
- Detect mod repositories
- Process releases and assets
- Publish to Smart Store

#### ModStoreManager
- Search mods
- Get trending/best mods
- Get similar mods
- Download mods with dependencies

#### GameProfileManager
- Create/delete game profiles
- Manage mod lists per profile
- Save game settings

#### FileManager
- Manage launcher directories
- Handle version storage
- Mod directory management
- Cache management

#### NetworkManager
- HTTP client setup
- API initialization
- Network operations

#### AuthenticationManager
- Overall auth orchestration
- Account type routing
- Token management

#### MicrosoftAuthManager
- Microsoft OAuth2 flow
- Xbox token acquisition
- Account refresh

#### PerformanceManager
- Monitor device performance
- Memory management
- Java arguments generation
- Optimization suggestions

#### AnalyticsManager
- Track launch history
- Track crash history
- Calculate statistics

#### AutoDependencyManager
- Analyze mod dependencies
- Download dependencies automatically
- Dependency resolution

#### XAIAssistant
- AI responses
- Performance advice
- Mod recommendations
- Crash analysis

---

## 🔌 API Integration

### GitHub API
- Get user info
- List user repositories
- Get repository releases
- Download assets

### Mod Store API
- Search mods
- Get trending mods
- Publish mods
- Get similar mods

### Minecraft Launcher API
- Get version manifest
- Download game files
- Get launcher profiles

---

## 🎨 UI Components

### Main Interface
```
┌─────────────────────────────────────────┐
│  Logo        X-LAUNCHER        ☰        │
├─────────────────────────────────────────┤
│  🤖  │        [X Logo]        │   Card  │
│ XAI  │     (Center)            │  User   │
│ Bot  │                         │ Profile │
│      │                         │  Edit   │
│      │                         │ Version │
│      │                         │  Play   │
│      │                         │  Btn    │
└─────────────────────────────────────────┘
```

### Color Scheme
- Background: #000000 (Pure Black)
- Primary: #f5e6b3 (Gold)
- Accent: #00bcd4 (Cyan)
- Text Primary: #f5e6b3
- Text Secondary: #b39c7d

---

## 📦 Dependencies

```gradle
// AndroidX
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
implementation 'androidx.recyclerview:recyclerview:1.3.1'
implementation 'com.google.android.material:material:1.9.0'

// Networking
implementation 'com.squareup.okhttp3:okhttp:4.11.0'
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

// JSON
implementation 'com.google.code.gson:gson:2.10.1'

// Image Loading
implementation 'com.github.bumptech.glide:glide:4.15.1'

// Database
implementation 'androidx.room:room-runtime:2.5.2'

// Coroutines
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1'
```

---

## 🚀 Getting Started

### Clone Repository
```bash
git clone https://github.com/kiuaxv/XL-LAUNCHER.git
cd XL-LAUNCHER
```

### Open in Android Studio
1. File → Open → Select XL-LAUNCHER folder
2. Wait for Gradle sync
3. Build → Make Project

### Run App
1. Connect device or start emulator
2. Run → Run 'app'
3. App will launch in debug mode

### Build Release APK
```bash
./gradlew clean
./gradlew assembleRelease
```

---

## 🔧 Configuration

### API Endpoints
Edit `NetworkManager.java`:
```java
private static final String MINECRAFT_API_URL = "https://launcher.mojang.com/";
private static final String MOD_API_URL = "https://api.modstore.example.com/";
private static final String GITHUB_API_URL = "https://api.github.com/";
```

### Build Variants
```gradle
flavorDimensions "runtime"
productFlavors {
    noruntime {
        dimension "runtime"
        applicationIdSuffix ".noruntime"
    }
    withruntime {
        dimension "runtime"
    }
}
```

---

## 🧪 Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

---

## 📝 Development Tips

### Adding New Feature
1. Create Activity/Manager class
2. Add XML layout
3. Implement in corresponding manager
4. Update AndroidManifest.xml
5. Test on device

### Adding New API
1. Create API interface in `network/api/`
2. Create response models
3. Update `NetworkManager`
4. Create corresponding manager
5. Implement in activity

---

## 🐛 Troubleshooting

### Build Issues
- Clean build: `./gradlew clean`
- Invalidate cache: File → Invalidate Caches
- Update SDK: SDK Manager

### Runtime Crashes
- Check logcat
- Look for NULL pointer exceptions
- Verify API endpoints
- Check network permissions

### Performance Issues
- Check memory leaks
- Optimize image loading
- Use ProGuard
- Profile with Android Profiler

---

**Happy Coding! 🚀**
