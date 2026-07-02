package com.techmeeva.chogadiyawidgets.core.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\fJ\u000b\u0010!\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010$\u001a\u00020\tH\u00c6\u0003J\t\u0010%\u001a\u00020\tH\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003JK\u0010\'\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010(\u001a\u00020\t2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020+H\u00d6\u0001J\t\u0010,\u001a\u00020\u0005H\u00d6\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0017\"\u0004\b\u001a\u0010\u0019R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014\u00a8\u0006-"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/state/AstronomyState;", "", "day", "Lcom/techmeeva/chogadiyawidgets/models/SolarLunarDay;", "provider", "", "lastUpdated", "Ljava/util/Date;", "isLoading", "", "isRefreshing", "errorMessage", "(Lcom/techmeeva/chogadiyawidgets/models/SolarLunarDay;Ljava/lang/String;Ljava/util/Date;ZZLjava/lang/String;)V", "getDay", "()Lcom/techmeeva/chogadiyawidgets/models/SolarLunarDay;", "setDay", "(Lcom/techmeeva/chogadiyawidgets/models/SolarLunarDay;)V", "getErrorMessage", "()Ljava/lang/String;", "setErrorMessage", "(Ljava/lang/String;)V", "hasContent", "getHasContent", "()Z", "setLoading", "(Z)V", "setRefreshing", "getLastUpdated", "()Ljava/util/Date;", "setLastUpdated", "(Ljava/util/Date;)V", "getProvider", "setProvider", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_release"})
public final class AstronomyState {
    @org.jetbrains.annotations.Nullable()
    private com.techmeeva.chogadiyawidgets.models.SolarLunarDay day;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String provider;
    @org.jetbrains.annotations.Nullable()
    private java.util.Date lastUpdated;
    private boolean isLoading;
    private boolean isRefreshing;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String errorMessage;
    
    public AstronomyState(@org.jetbrains.annotations.Nullable()
    com.techmeeva.chogadiyawidgets.models.SolarLunarDay day, @org.jetbrains.annotations.NotNull()
    java.lang.String provider, @org.jetbrains.annotations.Nullable()
    java.util.Date lastUpdated, boolean isLoading, boolean isRefreshing, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.techmeeva.chogadiyawidgets.models.SolarLunarDay getDay() {
        return null;
    }
    
    public final void setDay(@org.jetbrains.annotations.Nullable()
    com.techmeeva.chogadiyawidgets.models.SolarLunarDay p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProvider() {
        return null;
    }
    
    public final void setProvider(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date getLastUpdated() {
        return null;
    }
    
    public final void setLastUpdated(@org.jetbrains.annotations.Nullable()
    java.util.Date p0) {
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public final void setLoading(boolean p0) {
    }
    
    public final boolean isRefreshing() {
        return false;
    }
    
    public final void setRefreshing(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    public final void setErrorMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final boolean getHasContent() {
        return false;
    }
    
    public AstronomyState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.techmeeva.chogadiyawidgets.models.SolarLunarDay component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.state.AstronomyState copy(@org.jetbrains.annotations.Nullable()
    com.techmeeva.chogadiyawidgets.models.SolarLunarDay day, @org.jetbrains.annotations.NotNull()
    java.lang.String provider, @org.jetbrains.annotations.Nullable()
    java.util.Date lastUpdated, boolean isLoading, boolean isRefreshing, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}