package com.techmeeva.chogadiyawidgets.core.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 +2\u00020\u0001:\u0001+B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J\u000e\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0014J\u0006\u0010\u001f\u001a\u00020\u000fJ\u0006\u0010 \u001a\u00020\u000fJ\u0012\u0010!\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0011\u001a\u00020\"H\u0002J\u0006\u0010#\u001a\u00020\u000fJ\u0010\u0010$\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\'H\u0002J\u000e\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020\u001bJ\u0012\u0010*\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006,"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/state/EngagementManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_surface", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/techmeeva/chogadiyawidgets/core/state/EngagementSurface;", "prefs", "Landroid/content/SharedPreferences;", "surface", "Lkotlinx/coroutines/flow/StateFlow;", "getSurface", "()Lkotlinx/coroutines/flow/StateFlow;", "dismiss", "", "evaluate", "config", "Lcom/techmeeva/chogadiyawidgets/core/network/RemoteAppConfig;", "getDaysBetween", "", "start", "Ljava/util/Date;", "end", "isVersionOlderThan", "", "v1", "", "v2", "markMilestoneShown", "days", "markNotificationPrompted", "markRatingPrompted", "milestoneSurface", "Lcom/techmeeva/chogadiyawidgets/core/network/EngagementPromptConfig;", "recordLaunch", "shouldShowNotification", "Lcom/techmeeva/chogadiyawidgets/core/network/NotificationPromptConfig;", "shouldShowRating", "Lcom/techmeeva/chogadiyawidgets/core/network/RatingPromptConfig;", "skipUpdate", "version", "updateSurface", "Companion", "app_debug"})
public final class EngagementManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.techmeeva.chogadiyawidgets.core.state.EngagementSurface> _surface = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.techmeeva.chogadiyawidgets.core.state.EngagementSurface> surface = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String FIRST_OPEN_KEY = "engagement.firstOpenDate";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String OPEN_COUNT_KEY = "engagement.openCount";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String LAST_RATING_PROMPT_KEY = "engagement.lastRatingPrompt";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String LAST_NOTIFICATION_PROMPT_KEY = "engagement.lastNotificationPrompt";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SKIPPED_UPDATE_VERSION_KEY = "engagement.skippedUpdateVersion";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SHOWN_MILESTONES_KEY = "engagement.shownMilestones";
    @org.jetbrains.annotations.NotNull()
    public static final com.techmeeva.chogadiyawidgets.core.state.EngagementManager.Companion Companion = null;
    
    public EngagementManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.techmeeva.chogadiyawidgets.core.state.EngagementSurface> getSurface() {
        return null;
    }
    
    public final void recordLaunch() {
    }
    
    public final void evaluate(@org.jetbrains.annotations.NotNull()
    com.techmeeva.chogadiyawidgets.core.network.RemoteAppConfig config) {
    }
    
    public final void dismiss() {
    }
    
    public final void skipUpdate(@org.jetbrains.annotations.NotNull()
    java.lang.String version) {
    }
    
    public final void markRatingPrompted() {
    }
    
    public final void markNotificationPrompted() {
    }
    
    public final void markMilestoneShown(int days) {
    }
    
    private final com.techmeeva.chogadiyawidgets.core.state.EngagementSurface updateSurface(com.techmeeva.chogadiyawidgets.core.network.RemoteAppConfig config) {
        return null;
    }
    
    private final com.techmeeva.chogadiyawidgets.core.state.EngagementSurface milestoneSurface(com.techmeeva.chogadiyawidgets.core.network.EngagementPromptConfig config) {
        return null;
    }
    
    private final boolean shouldShowRating(com.techmeeva.chogadiyawidgets.core.network.RatingPromptConfig config) {
        return false;
    }
    
    private final boolean shouldShowNotification(com.techmeeva.chogadiyawidgets.core.network.NotificationPromptConfig config) {
        return false;
    }
    
    private final int getDaysBetween(java.util.Date start, java.util.Date end) {
        return 0;
    }
    
    private final boolean isVersionOlderThan(java.lang.String v1, java.lang.String v2) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/state/EngagementManager$Companion;", "", "()V", "FIRST_OPEN_KEY", "", "LAST_NOTIFICATION_PROMPT_KEY", "LAST_RATING_PROMPT_KEY", "OPEN_COUNT_KEY", "SHOWN_MILESTONES_KEY", "SKIPPED_UPDATE_VERSION_KEY", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}