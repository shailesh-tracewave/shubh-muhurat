package com.techmeeva.chogadiyawidgets.core.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b/\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B{\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\u0012J\u000f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\tH\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\t\u00107\u001a\u00020\tH\u00c6\u0003J\t\u00108\u001a\u00020\tH\u00c6\u0003J\t\u00109\u001a\u00020\u000fH\u00c6\u0003J\t\u0010:\u001a\u00020\u000fH\u00c6\u0003J\u007f\u0010;\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\tH\u00c6\u0001J\u0013\u0010<\u001a\u00020\u000f2\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010>\u001a\u00020?H\u00d6\u0001J\t\u0010@\u001a\u00020\tH\u00d6\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b \u0010!R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010!\"\u0004\b\"\u0010#R\u001a\u0010\u0010\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010!\"\u0004\b$\u0010#R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0018\"\u0004\b*\u0010\u001aR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001c\"\u0004\b,\u0010\u001eR\u001a\u0010\f\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001c\"\u0004\b.\u0010\u001eR\u001a\u0010\r\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001c\"\u0004\b0\u0010\u001e\u00a8\u0006A"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/state/HomeTimelineState;", "", "days", "", "Lcom/techmeeva/chogadiyawidgets/models/ChoghadiyaDay;", "currentSlot", "Lcom/techmeeva/chogadiyawidgets/models/TimelineSlot;", "nextSlots", "provider", "", "lastUpdated", "Ljava/util/Date;", "sunrise", "sunset", "isLoading", "", "isRefreshing", "errorMessage", "(Ljava/util/List;Lcom/techmeeva/chogadiyawidgets/models/TimelineSlot;Ljava/util/List;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;)V", "getCurrentSlot", "()Lcom/techmeeva/chogadiyawidgets/models/TimelineSlot;", "setCurrentSlot", "(Lcom/techmeeva/chogadiyawidgets/models/TimelineSlot;)V", "getDays", "()Ljava/util/List;", "setDays", "(Ljava/util/List;)V", "getErrorMessage", "()Ljava/lang/String;", "setErrorMessage", "(Ljava/lang/String;)V", "hasContent", "getHasContent", "()Z", "setLoading", "(Z)V", "setRefreshing", "getLastUpdated", "()Ljava/util/Date;", "setLastUpdated", "(Ljava/util/Date;)V", "getNextSlots", "setNextSlots", "getProvider", "setProvider", "getSunrise", "setSunrise", "getSunset", "setSunset", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class HomeTimelineState {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay> days;
    @org.jetbrains.annotations.Nullable()
    private com.techmeeva.chogadiyawidgets.models.TimelineSlot currentSlot;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.techmeeva.chogadiyawidgets.models.TimelineSlot> nextSlots;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String provider;
    @org.jetbrains.annotations.Nullable()
    private java.util.Date lastUpdated;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sunrise;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sunset;
    private boolean isLoading;
    private boolean isRefreshing;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String errorMessage;
    
    public HomeTimelineState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay> days, @org.jetbrains.annotations.Nullable()
    com.techmeeva.chogadiyawidgets.models.TimelineSlot currentSlot, @org.jetbrains.annotations.NotNull()
    java.util.List<com.techmeeva.chogadiyawidgets.models.TimelineSlot> nextSlots, @org.jetbrains.annotations.NotNull()
    java.lang.String provider, @org.jetbrains.annotations.Nullable()
    java.util.Date lastUpdated, @org.jetbrains.annotations.NotNull()
    java.lang.String sunrise, @org.jetbrains.annotations.NotNull()
    java.lang.String sunset, boolean isLoading, boolean isRefreshing, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay> getDays() {
        return null;
    }
    
    public final void setDays(@org.jetbrains.annotations.NotNull()
    java.util.List<com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.techmeeva.chogadiyawidgets.models.TimelineSlot getCurrentSlot() {
        return null;
    }
    
    public final void setCurrentSlot(@org.jetbrains.annotations.Nullable()
    com.techmeeva.chogadiyawidgets.models.TimelineSlot p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.techmeeva.chogadiyawidgets.models.TimelineSlot> getNextSlots() {
        return null;
    }
    
    public final void setNextSlots(@org.jetbrains.annotations.NotNull()
    java.util.List<com.techmeeva.chogadiyawidgets.models.TimelineSlot> p0) {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSunrise() {
        return null;
    }
    
    public final void setSunrise(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSunset() {
        return null;
    }
    
    public final void setSunset(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
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
    
    public HomeTimelineState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.techmeeva.chogadiyawidgets.models.TimelineSlot component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.techmeeva.chogadiyawidgets.models.TimelineSlot> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final boolean component8() {
        return false;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.state.HomeTimelineState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay> days, @org.jetbrains.annotations.Nullable()
    com.techmeeva.chogadiyawidgets.models.TimelineSlot currentSlot, @org.jetbrains.annotations.NotNull()
    java.util.List<com.techmeeva.chogadiyawidgets.models.TimelineSlot> nextSlots, @org.jetbrains.annotations.NotNull()
    java.lang.String provider, @org.jetbrains.annotations.Nullable()
    java.util.Date lastUpdated, @org.jetbrains.annotations.NotNull()
    java.lang.String sunrise, @org.jetbrains.annotations.NotNull()
    java.lang.String sunset, boolean isLoading, boolean isRefreshing, @org.jetbrains.annotations.Nullable()
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