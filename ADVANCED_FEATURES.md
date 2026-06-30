# X-Launcher Advanced Features Documentation

## 🎮 GitHub Account Integration

### نظام GitHub المتقدم
حساب GitHub ليس فقط للتسجيل - بل هو نظام متكامل لنشر المودات:

#### المميزات:
1. **Automatic Mod Detection**
   - يكتشف المودات تلقائياً من مستودعاتك
   - يتحقق من اسم المستودع أو الوصف
   - يجمع معلومات من GitHub releases

2. **Mod Publishing to Smart Store**
   - نشر المودات الخاصة بك للمتجر
   - عرض عدد النجوم والتحميلات
   - إدارة الإصدارات تلقائياً

3. **Dependency Management**
   - كشف المودات المطلوبة تلقائياً
   - تحميل الاعتمادات معاً
   - دعم Fabric و Forge

### استخدام GitHub Account:

```java
// الخطوة 1: الحصول على Personal Access Token من GitHub
// GitHub Settings → Developer settings → Personal access tokens

// الخطوة 2: تسجيل الدخول
gitHubAuthManager.authenticateWithToken(token, new GitHubAuthManager.AuthCallback() {
    @Override
    public void onSuccess(Account account) {
        // تم التسجيل
        // يمكن الآن نشر المودات
    }
});

// الخطوة 3: جلب المودات الخاصة بك
gitHubModManager.fetchUserMods(token, new GitHubModManager.ModsCallback() {
    @Override
    public void onSuccess(List<GitHubMod> mods) {
        // عرض المودات المكتشفة
    }
});

// الخطوة 4: نشر مود للمتجر
gitHubModManager.publishModToSmartStore(mod, callback);
```

---

## 🏪 Smart Store Features

### متجر المودات الذكي

#### 1. **Search & Discovery**
- البحث المتقدم عن المودات
- تصنيفات ذكية
- اقتراحات شخصية

#### 2. **Verified Mods**
- علامة تحقق للمودات الموثوقة
- معلومات المنشئ
- التقييمات والمراجعات

#### 3. **Trending & Best Rated**
- المودات الرائجة هذا الأسبوع
- أفضل المودات تقييماً
- المودات المخفية (Hidden Gems)

#### 4. **Similar Mods**
- اقتراح مودات مشابهة
- نظام الربط الذكي
- تحسين التجربة

### Auto Dependency Download (AD)

```java
// عند تحميل مود، يتم تحميل الاعتمادات تلقائياً
autoDependencyManager.downloadModWithDependencies(mod, new AutoDependencyManager.DownloadCallback() {
    @Override
    public void onSuccess(String message) {
        // تم تحميل المود والاعتمادات
    }
});
```

---

## 🔐 Authentication Systems

### 1. Offline Account
```java
authenticationManager.loginOffline("MyUsername");
```
- حساب محلي بسيط
- بدون اتصال إنترنت
- لاعب واحد

### 2. Microsoft Account
```java
microsoftAuthManager.authenticateWithCredentials(
    email, password, 
    new MicrosoftAuthManager.AuthCallback() {
        @Override
        public void onSuccess(Account account) {
            // تم التسجيل عبر Microsoft
        }
    }
);
```
- حساب Microsoft رسمي
- مزامنة السحابة
- دعم Xbox

### 3. GitHub Account
- حساب GitHub برمز وصول شخصي
- نشر مودات للمتجر
- ربط المستودعات

---

## 📊 Analytics System

### المقاييس المتتبعة:

```java
// نسبة النجاح
Double successRate = analyticsManager.getSuccessRate();

// متوسط مدة الجلسة
Double avgDuration = analyticsManager.getAverageSessionDuration();

// عدد الكراشات
Int totalCrashes = analyticsManager.getTotalCrashes();

// سجل الإطلاقات
List<LaunchEvent> history = analyticsManager.getLaunchHistory();
```

### تسجيل الأحداث:

```java
LaunchEvent event = new LaunchEvent(profileId);
event.setFps(120);
event.setRamUsed(2048);
event.setSuccessful(true);
analyticsManager.recordLaunch(event);
```

---

## ⚙️ Performance Optimization

### Java Arguments Generator

```java
PerformanceManager performanceManager = new PerformanceManager(context);
PerformanceManager.OptimalJavaSettings settings = 
    performanceManager.generateOptimalJavaSettings();

// معلومات النظام
Long availableMemory = performanceManager.getAvailableMemoryMB();
Int cores = performanceManager.getCoreCount();
```

### JVM Arguments المولدة:

```
-Xmx6144M           # الحد الأقصى للذاكرة
-Xms512M            # الحد الأدنى للذاكرة
-XX:+UseG1GC        # استخدام G1 Garbage Collector
-XX:MaxGCPauseMillis=200
-XX:+UnlockExperimentalVMOptions
-XX:G1NewCollectionPercentThreshold=30
-XX:G1MaxNewGenPercent=40
```

---

## 🎨 UI/UX Improvements

### الواجهة الرئيسية المحسّنة

```
┌─────────────────────────────────────┐
│ [Logo]  X-LAUNCHER        [Menu]    │ ← Header
├─────────────────────────────────────┤
│                                     │
│  [🤖]        [X]        [Card]     │
│   XAI       Logo       Version      │
│                         Profile     │
│                         Play        │
│                                     │
└─────────────────────────────────────┘
```

### الألوان المحسّنة:
- **Primary Gold**: #f5e6b3 (أزرار رئيسية)
- **Background Dark**: #000000 (خلفية)
- **Accent Cyan**: #00bcd4 (عناصر ثانوية)
- **Gold Light**: #fffaeb (بطاقات)

---

## 🚀 Integration Example

```java
// في MainActivity.java

public class MainActivity extends AppCompatActivity {
    private AccountManager accountManager;
    private GitHubAuthManager gitHubAuthManager;
    private GitHubModManager gitHubModManager;
    private ModStoreManager modStoreManager;
    private AnalyticsManager analyticsManager;
    private PerformanceManager performanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_improved);

        // تهيئة الأدوات
        XLauncherApplication app = (XLauncherApplication) getApplication();
        accountManager = app.getAccountManager();
        gitHubAuthManager = new GitHubAuthManager(this, app.getNetworkManager());
        gitHubModManager = new GitHubModManager(this);
        modStoreManager = new ModStoreManager(this);
        analyticsManager = new AnalyticsManager(this);
        performanceManager = new PerformanceManager(this);

        setupClickListeners();
    }

    private void setupClickListeners() {
        findViewById(R.id.xai_robot).setOnClickListener(v -> {
            startActivity(new Intent(this, XAIActivity.class));
        });

        findViewById(R.id.play_btn).setOnClickListener(v -> {
            launchMinecraft();
        });

        findViewById(R.id.edit_profile_btn).setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });
    }

    private void launchMinecraft() {
        // تسجيل بداية الإطلاق
        LaunchEvent event = new LaunchEvent(currentProfile.getId());
        
        // حساب الإعدادات المثلى
        PerformanceManager.OptimalJavaSettings settings = 
            performanceManager.generateOptimalJavaSettings();
        
        // إطلاق اللعبة
        // ...
        
        // تسجيل الحدث
        event.setSuccessful(true);
event.setDuration(System.currentTimeMillis() - event.getLaunchTime());
        analyticsManager.recordLaunch(event);
    }
}
```

---

## 📦 Mod Publishing Workflow

### لنشر مود من GitHub:

1. **تسجيل الدخول عبر GitHub**
   ```java
   githubAuthManager.authenticateWithToken(token, callback);
   ```

2. **جلب مودك**
   ```java
   githubModManager.fetchUserMods(token, callback);
   ```

3. **اختيار المود**
   - سيظهر في قائمة "Your GitHub Mods"
   - يمكن رؤية النجوم والتحميلات

4. **نشر للمتجر**
   ```java
   githubModManager.publishModToSmartStore(mod, callback);
   ```

5. **متاح الآن في Smart Store**
   - سيظهر مع المودات الأخرى
   - يمكن للآخرين تحميله
   - يحصل على تقييمات

---

## ✅ Checklist الميزات المطبقة

- ✅ GitHub Account Integration
- ✅ Microsoft Account Support
- ✅ Offline Account
- ✅ Smart Store
- ✅ Auto Dependency Download
- ✅ Analytics System
- ✅ Performance Manager
- ✅ Multi-Account Support
- ✅ Mod Publishing
- ✅ UI/UX محسّن

---

## 🔗 API Integration

### GitHub API
- `GET /user` - معلومات المستخدم
- `GET /user/repos` - المستودعات
- `GET /repos/{owner}/{repo}/releases` - الإصدارات

### Smart Store API
- `GET /mods/search` - البحث
- `GET /mods/trending` - المودات الرائجة
- `POST /mods/publish` - نشر مود

### Microsoft Graph API
- `GET /me` - بيانات المستخدم
- `POST /oauth2/v2.0/token` - تسجيل الدخول

---

**X-Launcher v1.0** - جاهز للإطلاق! 🚀
