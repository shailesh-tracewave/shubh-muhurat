package com.techmeeva.chogadiyawidgets.core.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ,\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ6\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u000e\u001a\u00020\b2\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/network/ChoghadiyaApi;", "", "fetchAstronomyDay", "Lcom/techmeeva/chogadiyawidgets/models/SolarLunarDayResponse;", "lat", "", "lng", "date", "", "(DDLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchDay", "Lcom/techmeeva/chogadiyawidgets/models/ChoghadiyaDayResponse;", "fetchRange", "Lcom/techmeeva/chogadiyawidgets/models/ChoghadiyaRangeResponse;", "startDate", "days", "", "(DDLjava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface ChoghadiyaApi {
    
    @retrofit2.http.GET(value = "v1/choghadiya/day")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchDay(@retrofit2.http.Query(value = "lat")
    double lat, @retrofit2.http.Query(value = "lng")
    double lng, @retrofit2.http.Query(value = "date")
    @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.techmeeva.chogadiyawidgets.models.ChoghadiyaDayResponse> $completion);
    
    @retrofit2.http.GET(value = "v1/choghadiya/range")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchRange(@retrofit2.http.Query(value = "lat")
    double lat, @retrofit2.http.Query(value = "lng")
    double lng, @retrofit2.http.Query(value = "start_date")
    @org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @retrofit2.http.Query(value = "days")
    int days, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.techmeeva.chogadiyawidgets.models.ChoghadiyaRangeResponse> $completion);
    
    @retrofit2.http.GET(value = "v1/astronomy/day")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchAstronomyDay(@retrofit2.http.Query(value = "lat")
    double lat, @retrofit2.http.Query(value = "lng")
    double lng, @retrofit2.http.Query(value = "date")
    @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.techmeeva.chogadiyawidgets.models.SolarLunarDayResponse> $completion);
}