package com.techmeeva.chogadiyawidgets.core.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bm\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0010H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\bH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\fH\u00c6\u0003J\t\u0010*\u001a\u00020\u000eH\u00c6\u0003Jq\u0010+\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00c6\u0001J\u0013\u0010,\u001a\u00020\b2\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010.\u001a\u00020/H\u00d6\u0001J\t\u00100\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0016\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013\u00a8\u00061"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/network/RemoteAppConfig;", "", "apiBaseURL", "", "appStoreURL", "minimumSupportedVersion", "latestVersion", "forceUpdate", "", "updateTitle", "updateMessage", "rating", "Lcom/techmeeva/chogadiyawidgets/core/network/RatingPromptConfig;", "notifications", "Lcom/techmeeva/chogadiyawidgets/core/network/NotificationPromptConfig;", "engagement", "Lcom/techmeeva/chogadiyawidgets/core/network/EngagementPromptConfig;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lcom/techmeeva/chogadiyawidgets/core/network/RatingPromptConfig;Lcom/techmeeva/chogadiyawidgets/core/network/NotificationPromptConfig;Lcom/techmeeva/chogadiyawidgets/core/network/EngagementPromptConfig;)V", "getApiBaseURL", "()Ljava/lang/String;", "getAppStoreURL", "getEngagement", "()Lcom/techmeeva/chogadiyawidgets/core/network/EngagementPromptConfig;", "getForceUpdate", "()Z", "getLatestVersion", "getMinimumSupportedVersion", "getNotifications", "()Lcom/techmeeva/chogadiyawidgets/core/network/NotificationPromptConfig;", "getRating", "()Lcom/techmeeva/chogadiyawidgets/core/network/RatingPromptConfig;", "getUpdateMessage", "getUpdateTitle", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
@androidx.annotation.Keep()
public final class RemoteAppConfig {
    @com.google.gson.annotations.SerializedName(value = "apiBaseURL")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String apiBaseURL = null;
    @com.google.gson.annotations.SerializedName(value = "appStoreURL")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String appStoreURL = null;
    @com.google.gson.annotations.SerializedName(value = "minimumSupportedVersion")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String minimumSupportedVersion = null;
    @com.google.gson.annotations.SerializedName(value = "latestVersion")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String latestVersion = null;
    @com.google.gson.annotations.SerializedName(value = "forceUpdate")
    private final boolean forceUpdate = false;
    @com.google.gson.annotations.SerializedName(value = "updateTitle")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String updateTitle = null;
    @com.google.gson.annotations.SerializedName(value = "updateMessage")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String updateMessage = null;
    @com.google.gson.annotations.SerializedName(value = "rating")
    @org.jetbrains.annotations.NotNull()
    private final com.techmeeva.chogadiyawidgets.core.network.RatingPromptConfig rating = null;
    @com.google.gson.annotations.SerializedName(value = "notifications")
    @org.jetbrains.annotations.NotNull()
    private final com.techmeeva.chogadiyawidgets.core.network.NotificationPromptConfig notifications = null;
    @com.google.gson.annotations.SerializedName(value = "engagement")
    @org.jetbrains.annotations.NotNull()
    private final com.techmeeva.chogadiyawidgets.core.network.EngagementPromptConfig engagement = null;
    
    public RemoteAppConfig(@org.jetbrains.annotations.Nullable()
    java.lang.String apiBaseURL, @org.jetbrains.annotations.Nullable()
    java.lang.String appStoreURL, @org.jetbrains.annotations.NotNull()
    java.lang.String minimumSupportedVersion, @org.jetbrains.annotations.NotNull()
    java.lang.String latestVersion, boolean forceUpdate, @org.jetbrains.annotations.NotNull()
    java.lang.String updateTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String updateMessage, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.network.RatingPromptConfig rating, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.network.NotificationPromptConfig notifications, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.network.EngagementPromptConfig engagement) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getApiBaseURL() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAppStoreURL() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMinimumSupportedVersion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLatestVersion() {
        return null;
    }
    
    public final boolean getForceUpdate() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUpdateTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUpdateMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.network.RatingPromptConfig getRating() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.network.NotificationPromptConfig getNotifications() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.network.EngagementPromptConfig getEngagement() {
        return null;
    }
    
    public RemoteAppConfig() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.network.EngagementPromptConfig component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
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
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.network.RatingPromptConfig component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.network.NotificationPromptConfig component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.network.RemoteAppConfig copy(@org.jetbrains.annotations.Nullable()
    java.lang.String apiBaseURL, @org.jetbrains.annotations.Nullable()
    java.lang.String appStoreURL, @org.jetbrains.annotations.NotNull()
    java.lang.String minimumSupportedVersion, @org.jetbrains.annotations.NotNull()
    java.lang.String latestVersion, boolean forceUpdate, @org.jetbrains.annotations.NotNull()
    java.lang.String updateTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String updateMessage, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.network.RatingPromptConfig rating, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.network.NotificationPromptConfig notifications, @org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.network.EngagementPromptConfig engagement) {
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