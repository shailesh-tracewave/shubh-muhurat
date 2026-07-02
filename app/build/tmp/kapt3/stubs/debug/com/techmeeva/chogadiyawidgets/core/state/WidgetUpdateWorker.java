package com.techmeeva.chogadiyawidgets.core.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/state/WidgetUpdateWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "allWidgetIds", "", "appWidgetManager", "Landroid/appwidget/AppWidgetManager;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateWidgetsFromStore", "", "appState", "Lcom/techmeeva/chogadiyawidgets/core/state/AppState;", "dataStore", "Lcom/techmeeva/chogadiyawidgets/core/state/ChoghadiyaDataStore;", "date", "Ljava/util/Date;", "Companion", "app_debug"})
public final class WidgetUpdateWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.techmeeva.chogadiyawidgets.core.state.WidgetUpdateWorker.Companion Companion = null;
    
    public WidgetUpdateWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters workerParams) {
        super(null, null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> $completion) {
        return null;
    }
    
    private final void updateWidgetsFromStore(com.techmeeva.chogadiyawidgets.core.state.AppState appState, com.techmeeva.chogadiyawidgets.core.state.ChoghadiyaDataStore dataStore, java.util.Date date) {
    }
    
    private final int[] allWidgetIds(android.appwidget.AppWidgetManager appWidgetManager) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\b"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/state/WidgetUpdateWorker$Companion;", "", "()V", "enqueueInitial", "", "context", "Landroid/content/Context;", "enqueuePeriodic", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void enqueueInitial(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
        
        public final void enqueuePeriodic(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
    }
}