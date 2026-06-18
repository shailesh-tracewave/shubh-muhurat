package com.techmeeva.chogadiyawidgets.core.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0002J&\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0012J&\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0012J.\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001e\u001a\u00020\tH\u0002J2\u0010\u001f\u001a\u0002H \"\u0004\b\u0000\u0010 2\u001c\u0010!\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H 0#\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"H\u0082@\u00a2\u0006\u0002\u0010$R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/network/APIClient;", "", "()V", "gson", "Lcom/google/gson/Gson;", "kotlin.jvm.PlatformType", "okHttpClient", "Lokhttp3/OkHttpClient;", "apiDateString", "", "date", "Ljava/util/Date;", "timezone", "fetchAstronomyDay", "Lcom/techmeeva/chogadiyawidgets/models/SolarLunarDayResponse;", "baseURL", "city", "Lcom/techmeeva/chogadiyawidgets/models/SeedCity;", "(Ljava/lang/String;Lcom/techmeeva/chogadiyawidgets/models/SeedCity;Ljava/util/Date;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchDay", "Lcom/techmeeva/chogadiyawidgets/models/ChoghadiyaDayResponse;", "fetchRange", "Lcom/techmeeva/chogadiyawidgets/models/ChoghadiyaRangeResponse;", "startDate", "days", "", "(Ljava/lang/String;Lcom/techmeeva/chogadiyawidgets/models/SeedCity;Ljava/util/Date;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getApi", "Lcom/techmeeva/chogadiyawidgets/core/network/ChoghadiyaApi;", "parseISO8601", "value", "performWithRetry", "T", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class APIClient {
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient okHttpClient = null;
    
    public APIClient() {
        super();
    }
    
    private final com.techmeeva.chogadiyawidgets.core.network.ChoghadiyaApi getApi(java.lang.String baseURL) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchDay(@org.jetbrains.annotations.NotNull()
    java.lang.String baseURL, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city, @org.jetbrains.annotations.NotNull()
    java.util.Date date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.techmeeva.chogadiyawidgets.models.ChoghadiyaDayResponse> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchRange(@org.jetbrains.annotations.NotNull()
    java.lang.String baseURL, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city, @org.jetbrains.annotations.NotNull()
    java.util.Date startDate, int days, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.techmeeva.chogadiyawidgets.models.ChoghadiyaRangeResponse> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchAstronomyDay(@org.jetbrains.annotations.NotNull()
    java.lang.String baseURL, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.models.SeedCity city, @org.jetbrains.annotations.NotNull()
    java.util.Date date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.techmeeva.chogadiyawidgets.models.SolarLunarDayResponse> $completion) {
        return null;
    }
    
    private final <T extends java.lang.Object>java.lang.Object performWithRetry(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> block, kotlin.coroutines.Continuation<? super T> $completion) {
        return null;
    }
    
    private final java.util.Date parseISO8601(java.lang.String value) {
        return null;
    }
    
    private final java.lang.String apiDateString(java.util.Date date, java.lang.String timezone) {
        return null;
    }
}