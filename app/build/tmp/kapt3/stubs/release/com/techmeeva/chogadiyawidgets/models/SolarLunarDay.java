package com.techmeeva.chogadiyawidgets.models;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\u0002\u0010\u0011J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u000bH\u00c6\u0003J\t\u0010*\u001a\u00020\u000bH\u00c6\u0003J\u0087\u0001\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00c6\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u000200H\u00d6\u0001J\t\u00101\u001a\u00020\u0003H\u00d6\u0001R\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015\u00a8\u00062"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/models/SolarLunarDay;", "", "date", "", "sunrise", "sunset", "solarNoon", "moonrise", "moonset", "moonPhaseKey", "moonPhaseAngle", "", "illuminationFraction", "auspiciousWindows", "", "Lcom/techmeeva/chogadiyawidgets/models/TimingWindow;", "inauspiciousWindows", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDLjava/util/List;Ljava/util/List;)V", "getAuspiciousWindows", "()Ljava/util/List;", "getDate", "()Ljava/lang/String;", "getIlluminationFraction", "()D", "getInauspiciousWindows", "getMoonPhaseAngle", "getMoonPhaseKey", "getMoonrise", "getMoonset", "getSolarNoon", "getSunrise", "getSunset", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"})
public final class SolarLunarDay {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String date = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String sunrise = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String sunset = null;
    @com.google.gson.annotations.SerializedName(value = "solar_noon")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String solarNoon = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String moonrise = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String moonset = null;
    @com.google.gson.annotations.SerializedName(value = "moon_phase_key")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String moonPhaseKey = null;
    @com.google.gson.annotations.SerializedName(value = "moon_phase_angle")
    private final double moonPhaseAngle = 0.0;
    @com.google.gson.annotations.SerializedName(value = "illumination_fraction")
    private final double illuminationFraction = 0.0;
    @com.google.gson.annotations.SerializedName(value = "auspicious_windows")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.techmeeva.chogadiyawidgets.models.TimingWindow> auspiciousWindows = null;
    @com.google.gson.annotations.SerializedName(value = "inauspicious_windows")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.techmeeva.chogadiyawidgets.models.TimingWindow> inauspiciousWindows = null;
    
    public SolarLunarDay(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String sunrise, @org.jetbrains.annotations.NotNull()
    java.lang.String sunset, @org.jetbrains.annotations.NotNull()
    java.lang.String solarNoon, @org.jetbrains.annotations.Nullable()
    java.lang.String moonrise, @org.jetbrains.annotations.Nullable()
    java.lang.String moonset, @org.jetbrains.annotations.NotNull()
    java.lang.String moonPhaseKey, double moonPhaseAngle, double illuminationFraction, @org.jetbrains.annotations.NotNull()
    java.util.List<com.techmeeva.chogadiyawidgets.models.TimingWindow> auspiciousWindows, @org.jetbrains.annotations.NotNull()
    java.util.List<com.techmeeva.chogadiyawidgets.models.TimingWindow> inauspiciousWindows) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSunrise() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSunset() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSolarNoon() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMoonrise() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMoonset() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMoonPhaseKey() {
        return null;
    }
    
    public final double getMoonPhaseAngle() {
        return 0.0;
    }
    
    public final double getIlluminationFraction() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.techmeeva.chogadiyawidgets.models.TimingWindow> getAuspiciousWindows() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.techmeeva.chogadiyawidgets.models.TimingWindow> getInauspiciousWindows() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.techmeeva.chogadiyawidgets.models.TimingWindow> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.techmeeva.chogadiyawidgets.models.TimingWindow> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.models.SolarLunarDay copy(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String sunrise, @org.jetbrains.annotations.NotNull()
    java.lang.String sunset, @org.jetbrains.annotations.NotNull()
    java.lang.String solarNoon, @org.jetbrains.annotations.Nullable()
    java.lang.String moonrise, @org.jetbrains.annotations.Nullable()
    java.lang.String moonset, @org.jetbrains.annotations.NotNull()
    java.lang.String moonPhaseKey, double moonPhaseAngle, double illuminationFraction, @org.jetbrains.annotations.NotNull()
    java.util.List<com.techmeeva.chogadiyawidgets.models.TimingWindow> auspiciousWindows, @org.jetbrains.annotations.NotNull()
    java.util.List<com.techmeeva.chogadiyawidgets.models.TimingWindow> inauspiciousWindows) {
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