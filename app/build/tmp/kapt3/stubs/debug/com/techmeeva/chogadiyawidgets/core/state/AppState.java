package com.techmeeva.chogadiyawidgets.core.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001BB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u000eJ\u000e\u0010=\u001a\u00020;2\u0006\u0010>\u001a\u00020?J\u000e\u0010@\u001a\u00020;2\u0006\u0010<\u001a\u00020\u000eJ\u0014\u0010A\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00168F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00168F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0019R$\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0005\u001a\u00020\u001d8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010#\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'R$\u0010)\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020(8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010/\u001a\u00020.2\u0006\u0010\u0005\u001a\u00020.8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b0\u00101\"\u0004\b2\u00103R$\u00105\u001a\u0002042\u0006\u0010\u0005\u001a\u0002048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b6\u00107\"\u0004\b8\u00109\u00a8\u0006C"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/state/AppState;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "apiBaseURL", "getApiBaseURL", "()Ljava/lang/String;", "setApiBaseURL", "(Ljava/lang/String;)V", "availableCities", "", "Lcom/techmeeva/chogadiyawidgets/models/SeedCity;", "getAvailableCities", "()Ljava/util/List;", "defaults", "Landroid/content/SharedPreferences;", "fallbackCity", "gson", "Lcom/google/gson/Gson;", "", "hasCompletedOnboarding", "getHasCompletedOnboarding", "()Z", "setHasCompletedOnboarding", "(Z)V", "isPremium", "Lcom/techmeeva/chogadiyawidgets/models/LocationMode;", "locationMode", "getLocationMode", "()Lcom/techmeeva/chogadiyawidgets/models/LocationMode;", "setLocationMode", "(Lcom/techmeeva/chogadiyawidgets/models/LocationMode;)V", "selectedCity", "getSelectedCity", "()Lcom/techmeeva/chogadiyawidgets/models/SeedCity;", "setSelectedCity", "(Lcom/techmeeva/chogadiyawidgets/models/SeedCity;)V", "Lcom/techmeeva/chogadiyawidgets/core/localization/AppLanguage;", "selectedLanguage", "getSelectedLanguage", "()Lcom/techmeeva/chogadiyawidgets/core/localization/AppLanguage;", "setSelectedLanguage", "(Lcom/techmeeva/chogadiyawidgets/core/localization/AppLanguage;)V", "Lcom/techmeeva/chogadiyawidgets/models/SubscriptionPlan;", "subscriptionPlan", "getSubscriptionPlan", "()Lcom/techmeeva/chogadiyawidgets/models/SubscriptionPlan;", "setSubscriptionPlan", "(Lcom/techmeeva/chogadiyawidgets/models/SubscriptionPlan;)V", "Lcom/techmeeva/chogadiyawidgets/core/localization/AppThemeMode;", "themeMode", "getThemeMode", "()Lcom/techmeeva/chogadiyawidgets/core/localization/AppThemeMode;", "setThemeMode", "(Lcom/techmeeva/chogadiyawidgets/core/localization/AppThemeMode;)V", "applyCurrentLocation", "", "city", "applyRemoteConfig", "config", "Lcom/techmeeva/chogadiyawidgets/core/network/RemoteAppConfig;", "selectCity", "validatedAPIBaseURL", "Keys", "app_debug"})
public final class AppState {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences defaults = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.techmeeva.chogadiyawidgets.models.SeedCity> availableCities = null;
    @org.jetbrains.annotations.NotNull()
    private final com.techmeeva.chogadiyawidgets.models.SeedCity fallbackCity = null;
    
    public AppState(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.techmeeva.chogadiyawidgets.models.SeedCity> getAvailableCities() {
        return null;
    }
    
    public final boolean getHasCompletedOnboarding() {
        return false;
    }
    
    public final void setHasCompletedOnboarding(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.localization.AppLanguage getSelectedLanguage() {
        return null;
    }
    
    public final void setSelectedLanguage(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.localization.AppLanguage value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.models.SubscriptionPlan getSubscriptionPlan() {
        return null;
    }
    
    public final void setSubscriptionPlan(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SubscriptionPlan value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.models.LocationMode getLocationMode() {
        return null;
    }
    
    public final void setLocationMode(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.LocationMode value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.localization.AppThemeMode getThemeMode() {
        return null;
    }
    
    public final void setThemeMode(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.localization.AppThemeMode value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.models.SeedCity getSelectedCity() {
        return null;
    }
    
    public final void setSelectedCity(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getApiBaseURL() {
        return null;
    }
    
    public final void setApiBaseURL(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final boolean isPremium() {
        return false;
    }
    
    public final void selectCity(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city) {
    }
    
    public final void applyCurrentLocation(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city) {
    }
    
    public final void applyRemoteConfig(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.network.RemoteAppConfig config) {
    }
    
    private final java.lang.String validatedAPIBaseURL(java.lang.String value) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/state/AppState$Keys;", "", "()V", "apiBaseURL", "", "language", "locationMode", "onboarding", "selectedCity", "subscriptionPlan", "themeMode", "app_debug"})
    static final class Keys {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String onboarding = "app.onboarding.completed";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String selectedCity = "app.selected.city";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String language = "app.language";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String subscriptionPlan = "app.subscription.plan";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String locationMode = "app.location.mode";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String themeMode = "app.theme.mode";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String apiBaseURL = "app.api.base.url";
        @org.jetbrains.annotations.NotNull()
        public static final com.techmeeva.chogadiyawidgets.core.state.AppState.Keys INSTANCE = null;
        
        private Keys() {
            super();
        }
    }
}