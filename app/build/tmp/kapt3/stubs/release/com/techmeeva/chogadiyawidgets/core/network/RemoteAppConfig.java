package com.techmeeva.chogadiyawidgets.core.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001By\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u000fH\u00c6\u0003J\t\u0010%\u001a\u00020\u0011H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\tH\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\rH\u00c6\u0003J}\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u00c6\u0001J\u0013\u0010/\u001a\u00020\t2\b\u00100\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00101\u001a\u000202H\u00d6\u0001J\t\u00103\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0016\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0016\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014\u00a8\u00064"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/network/RemoteAppConfig;", "", "apiBaseURL", "", "appStoreURL", "playStoreURL", "minimumSupportedVersion", "latestVersion", "forceUpdate", "", "updateTitle", "updateMessage", "rating", "Lcom/techmeeva/chogadiyawidgets/core/network/RatingPromptConfig;", "notifications", "Lcom/techmeeva/chogadiyawidgets/core/network/NotificationPromptConfig;", "engagement", "Lcom/techmeeva/chogadiyawidgets/core/network/EngagementPromptConfig;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lcom/techmeeva/chogadiyawidgets/core/network/RatingPromptConfig;Lcom/techmeeva/chogadiyawidgets/core/network/NotificationPromptConfig;Lcom/techmeeva/chogadiyawidgets/core/network/EngagementPromptConfig;)V", "getApiBaseURL", "()Ljava/lang/String;", "getAppStoreURL", "getEngagement", "()Lcom/techmeeva/chogadiyawidgets/core/network/EngagementPromptConfig;", "getForceUpdate", "()Z", "getLatestVersion", "getMinimumSupportedVersion", "getNotifications", "()Lcom/techmeeva/chogadiyawidgets/core/network/NotificationPromptConfig;", "getPlayStoreURL", "getRating", "()Lcom/techmeeva/chogadiyawidgets/core/network/RatingPromptConfig;", "getUpdateMessage", "getUpdateTitle", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_release"})
@androidx.annotation.Keep()
public final class RemoteAppConfig {
    @com.google.gson.annotations.SerializedName(value = "apiBaseURL")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String apiBaseURL = null;
    @com.google.gson.annotations.SerializedName(value = "appStoreURL")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String appStoreURL = null;
    @com.google.gson.annotations.SerializedName(value = "playStoreURL")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String playStoreURL = null;
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
    java.lang.String appStoreURL, @org.jetbrains.annotations.Nullable()
    java.lang.String playStoreURL, @org.jetbrains.annotations.NotNull()
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPlayStoreURL() {
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
    public final com.techmeeva.chogadiyawidgets.core.network.NotificationPromptConfig component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.network.EngagementPromptConfig component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.network.RatingPromptConfig component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.techmeeva.chogadiyawidgets.core.network.RemoteAppConfig copy(@org.jetbrains.annotations.Nullable()
    java.lang.String apiBaseURL, @org.jetbrains.annotations.Nullable()
    java.lang.String appStoreURL, @org.jetbrains.annotations.Nullable()
    java.lang.String playStoreURL, @org.jetbrains.annotations.NotNull()
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