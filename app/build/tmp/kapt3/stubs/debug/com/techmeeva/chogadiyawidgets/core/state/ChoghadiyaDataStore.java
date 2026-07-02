package com.techmeeva.chogadiyawidgets.core.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\bH\u0002J \u0010&\u001a\u00020\"2\u0006\u0010#\u001a\u00020\'2\u0006\u0010(\u001a\u00020)2\u0006\u0010%\u001a\u00020\bH\u0002J\u0018\u0010*\u001a\u00020\"2\u0006\u0010#\u001a\u00020\'2\u0006\u0010%\u001a\u00020\bH\u0002J\u0018\u0010+\u001a\u00020\b2\u0006\u0010(\u001a\u00020)2\u0006\u0010,\u001a\u00020\u001eH\u0002J\u0016\u0010-\u001a\u00020\t2\u0006\u0010(\u001a\u00020)2\u0006\u0010,\u001a\u00020\u001eJ\u000e\u0010.\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020)J\u0016\u0010/\u001a\u00020\r2\u0006\u0010(\u001a\u00020)2\u0006\u00100\u001a\u00020\u001eJ\u0010\u00101\u001a\u00020\b2\u0006\u0010(\u001a\u00020)H\u0002J@\u00102\u001a\u00020\"2\u0006\u00103\u001a\u00020\b2\u0006\u0010(\u001a\u00020)2\u0006\u0010,\u001a\u00020\u001e2\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u000208H\u0086@\u00a2\u0006\u0002\u00109J0\u0010:\u001a\u00020\"2\u0006\u00103\u001a\u00020\b2\u0006\u0010(\u001a\u00020)2\u0006\u00100\u001a\u00020\u001e2\b\b\u0002\u00107\u001a\u000208H\u0086@\u00a2\u0006\u0002\u0010;J8\u0010<\u001a\u00020\"2\u0006\u00103\u001a\u00020\b2\u0006\u0010(\u001a\u00020)2\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u000208H\u0086@\u00a2\u0006\u0002\u0010=J\u0018\u0010>\u001a\u00020\b2\u0006\u0010(\u001a\u00020)2\u0006\u00100\u001a\u00020\u001eH\u0002J\u0016\u0010?\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020\bJ\u0012\u0010A\u001a\u0004\u0018\u00010\u001e2\u0006\u0010B\u001a\u00020\bH\u0002J.\u0010C\u001a\u00020\"2\u0006\u00103\u001a\u00020\b2\u0006\u0010(\u001a\u00020)2\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u000206H\u0086@\u00a2\u0006\u0002\u0010DJ \u0010E\u001a\u0002082\u0006\u0010F\u001a\u00020\b2\u0006\u00107\u001a\u0002082\u0006\u0010G\u001a\u00020HH\u0002R \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r0\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013\u00a8\u0006I"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/state/ChoghadiyaDataStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_astronomyStates", "Landroidx/lifecycle/MutableLiveData;", "", "", "Lcom/techmeeva/chogadiyawidgets/core/state/AstronomyState;", "_homeStates", "Lcom/techmeeva/chogadiyawidgets/core/state/HomeTimelineState;", "_monthStates", "Lcom/techmeeva/chogadiyawidgets/core/state/MonthScheduleState;", "apiClient", "Lcom/techmeeva/chogadiyawidgets/core/network/APIClient;", "astronomyStates", "Landroidx/lifecycle/LiveData;", "getAstronomyStates", "()Landroidx/lifecycle/LiveData;", "cacheDao", "Lcom/techmeeva/chogadiyawidgets/core/database/CacheDao;", "db", "Lcom/techmeeva/chogadiyawidgets/core/database/ChoghadiyaDatabase;", "gson", "Lcom/google/gson/Gson;", "homeStates", "getHomeStates", "lastRefreshMoments", "", "Ljava/util/Date;", "monthStates", "getMonthStates", "applyAstronomy", "", "response", "Lcom/techmeeva/chogadiyawidgets/models/SolarLunarDayResponse;", "stateKey", "applyHome", "Lcom/techmeeva/chogadiyawidgets/models/ChoghadiyaRangeResponse;", "city", "Lcom/techmeeva/chogadiyawidgets/models/SeedCity;", "applyMonth", "astronomyStateKey", "date", "getAstronomyState", "getHomeState", "getMonthState", "month", "homeStateKey", "loadAstronomy", "baseURL", "subscriptionStatus", "language", "Lcom/techmeeva/chogadiyawidgets/core/localization/AppLanguage;", "forceRefresh", "", "(Ljava/lang/String;Lcom/techmeeva/chogadiyawidgets/models/SeedCity;Ljava/util/Date;Ljava/lang/String;Lcom/techmeeva/chogadiyawidgets/core/localization/AppLanguage;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadCalendarMonth", "(Ljava/lang/String;Lcom/techmeeva/chogadiyawidgets/models/SeedCity;Ljava/util/Date;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadHome", "(Ljava/lang/String;Lcom/techmeeva/chogadiyawidgets/models/SeedCity;Ljava/lang/String;Lcom/techmeeva/chogadiyawidgets/core/localization/AppLanguage;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "monthStateKey", "normalizedMonth", "timezone", "parseISO8601", "value", "preloadInitial", "(Ljava/lang/String;Lcom/techmeeva/chogadiyawidgets/models/SeedCity;Ljava/lang/String;Lcom/techmeeva/chogadiyawidgets/core/localization/AppLanguage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldSkipRefresh", "throttleKey", "minimumIntervalSeconds", "", "app_debug"})
public final class ChoghadiyaDataStore {
    @org.jetbrains.annotations.NotNull()
    private final com.techmeeva.chogadiyawidgets.core.database.ChoghadiyaDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.techmeeva.chogadiyawidgets.core.database.CacheDao cacheDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.techmeeva.chogadiyawidgets.core.network.APIClient apiClient = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.String, com.techmeeva.chogadiyawidgets.core.state.HomeTimelineState>> _homeStates = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, com.techmeeva.chogadiyawidgets.core.state.HomeTimelineState>> homeStates = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.String, com.techmeeva.chogadiyawidgets.core.state.MonthScheduleState>> _monthStates = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, com.techmeeva.chogadiyawidgets.core.state.MonthScheduleState>> monthStates = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.String, com.techmeeva.chogadiyawidgets.core.state.AstronomyState>> _astronomyStates = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, com.techmeeva.chogadiyawidgets.core.state.AstronomyState>> astronomyStates = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.util.Date> lastRefreshMoments = null;
    
    public ChoghadiyaDataStore(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, com.techmeeva.chogadiyawidgets.core.state.HomeTimelineState>> getHomeStates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, com.techmeeva.chogadiyawidgets.core.state.MonthScheduleState>> getMonthStates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, com.techmeeva.chogadiyawidgets.core.state.AstronomyState>> getAstronomyStates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.state.HomeTimelineState getHomeState(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.state.MonthScheduleState getMonthState(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city, @org.jetbrains.annotations.NotNull()
    java.util.Date month) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.state.AstronomyState getAstronomyState(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city, @org.jetbrains.annotations.NotNull()
    java.util.Date date) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object preloadInitial(@org.jetbrains.annotations.NotNull()
    java.lang.String baseURL, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city, @org.jetbrains.annotations.NotNull()
    java.lang.String subscriptionStatus, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.localization.AppLanguage language, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Suppress(names = {"UNUSED_PARAMETER"})
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadHome(@org.jetbrains.annotations.NotNull()
    java.lang.String baseURL, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city, @org.jetbrains.annotations.NotNull()
    java.lang.String subscriptionStatus, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.localization.AppLanguage language, boolean forceRefresh, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadCalendarMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String baseURL, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city, @org.jetbrains.annotations.NotNull()
    java.util.Date month, boolean forceRefresh, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Suppress(names = {"UNUSED_PARAMETER"})
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadAstronomy(@org.jetbrains.annotations.NotNull()
    java.lang.String baseURL, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city, @org.jetbrains.annotations.NotNull()
    java.util.Date date, @org.jetbrains.annotations.NotNull()
    java.lang.String subscriptionStatus, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.localization.AppLanguage language, boolean forceRefresh, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void applyHome(com.techmeeva.chogadiyawidgets.models.ChoghadiyaRangeResponse response, com.techmeeva.chogadiyawidgets.models.SeedCity city, java.lang.String stateKey) {
    }
    
    private final void applyMonth(com.techmeeva.chogadiyawidgets.models.ChoghadiyaRangeResponse response, java.lang.String stateKey) {
    }
    
    private final void applyAstronomy(com.techmeeva.chogadiyawidgets.models.SolarLunarDayResponse response, java.lang.String stateKey) {
    }
    
    private final boolean shouldSkipRefresh(java.lang.String throttleKey, boolean forceRefresh, int minimumIntervalSeconds) {
        return false;
    }
    
    private final java.lang.String homeStateKey(com.techmeeva.chogadiyawidgets.models.SeedCity city) {
        return null;
    }
    
    private final java.lang.String monthStateKey(com.techmeeva.chogadiyawidgets.models.SeedCity city, java.util.Date month) {
        return null;
    }
    
    private final java.lang.String astronomyStateKey(com.techmeeva.chogadiyawidgets.models.SeedCity city, java.util.Date date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Date normalizedMonth(@org.jetbrains.annotations.NotNull()
    java.util.Date date, @org.jetbrains.annotations.NotNull()
    java.lang.String timezone) {
        return null;
    }
    
    private final java.util.Date parseISO8601(java.lang.String value) {
        return null;
    }
}