package com.techmeeva.chogadiyawidgets;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J \u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0010H\u0002J\u0018\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0010H\u0002J\b\u0010 \u001a\u00020\u0014H\u0002J4\u0010!\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00102\b\u0010\"\u001a\u0004\u0018\u00010\u00102\u0006\u0010#\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010\u0010H\u0002J\b\u0010%\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "appState", "Lcom/techmeeva/chogadiyawidgets/core/state/AppState;", "binding", "Lcom/techmeeva/chogadiyawidgets/databinding/ActivityMainBinding;", "engagementManager", "Lcom/techmeeva/chogadiyawidgets/core/state/EngagementManager;", "hasEvaluatedEngagementThisSession", "", "latestRemoteConfig", "Lcom/techmeeva/chogadiyawidgets/core/network/RemoteAppConfig;", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "androidStoreURL", "remoteURL", "applySystemBarInsets", "", "checkRemoteConfig", "evaluateEngagementIfReady", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showMilestoneDialog", "days", "", "title", "message", "showNotificationDialog", "showRatingDialog", "showUpdateDialog", "appStoreURL", "force", "targetVersion", "updateBottomNavTitles", "app_release"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.techmeeva.chogadiyawidgets.databinding.ActivityMainBinding binding;
    private com.techmeeva.chogadiyawidgets.core.state.AppState appState;
    private com.techmeeva.chogadiyawidgets.core.state.EngagementManager engagementManager;
    @org.jetbrains.annotations.Nullable()
    private com.techmeeva.chogadiyawidgets.core.network.RemoteAppConfig latestRemoteConfig;
    private boolean hasEvaluatedEngagementThisSession = false;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> requestPermissionLauncher = null;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void checkRemoteConfig() {
    }
    
    private final void applySystemBarInsets() {
    }
    
    private final void evaluateEngagementIfReady() {
    }
    
    private final void showUpdateDialog(java.lang.String title, java.lang.String message, java.lang.String appStoreURL, boolean force, java.lang.String targetVersion) {
    }
    
    private final void showRatingDialog() {
    }
    
    private final void showNotificationDialog(java.lang.String title, java.lang.String message) {
    }
    
    private final void showMilestoneDialog(int days, java.lang.String title, java.lang.String message) {
    }
    
    private final void updateBottomNavTitles() {
    }
    
    private final java.lang.String androidStoreURL(java.lang.String remoteURL) {
        return null;
    }
}