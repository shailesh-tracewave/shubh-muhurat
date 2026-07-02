package com.techmeeva.chogadiyawidgets.features.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001b\u001a\u00020\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010$\u001a\u00020\u000fH\u0016J\b\u0010%\u001a\u00020\u000fH\u0016J\b\u0010&\u001a\u00020\u000fH\u0016J\u001a\u0010\'\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u001f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020\u000fH\u0002J\b\u0010-\u001a\u00020\u000fH\u0002J\b\u0010.\u001a\u00020\u000fH\u0002J\b\u0010/\u001a\u00020\u000fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/features/home/HomeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/techmeeva/chogadiyawidgets/databinding/FragmentHomeBinding;", "appState", "Lcom/techmeeva/chogadiyawidgets/core/state/AppState;", "binding", "getBinding", "()Lcom/techmeeva/chogadiyawidgets/databinding/FragmentHomeBinding;", "dataStore", "Lcom/techmeeva/chogadiyawidgets/core/state/ChoghadiyaDataStore;", "timerJob", "Lkotlinx/coroutines/Job;", "bindAstronomyState", "", "state", "Lcom/techmeeva/chogadiyawidgets/core/state/AstronomyState;", "bindHomeState", "Lcom/techmeeva/chogadiyawidgets/core/state/HomeTimelineState;", "getSlotColorRes", "", "type", "", "moonLabel", "language", "Lcom/techmeeva/chogadiyawidgets/core/localization/AppLanguage;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onPause", "onResume", "onViewCreated", "view", "refreshHomeData", "force", "", "showLocationDialog", "startTimer", "stopTimer", "updateCountdown", "app_debug"})
public final class HomeFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.techmeeva.chogadiyawidgets.databinding.FragmentHomeBinding _binding;
    private com.techmeeva.chogadiyawidgets.core.state.AppState appState;
    private com.techmeeva.chogadiyawidgets.core.state.ChoghadiyaDataStore dataStore;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job timerJob;
    
    public HomeFragment() {
        super();
    }
    
    private final com.techmeeva.chogadiyawidgets.databinding.FragmentHomeBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void refreshHomeData(boolean force) {
    }
    
    private final void startTimer() {
    }
    
    private final void stopTimer() {
    }
    
    private final void updateCountdown() {
    }
    
    private final void bindHomeState(com.techmeeva.chogadiyawidgets.core.state.HomeTimelineState state) {
    }
    
    private final void bindAstronomyState(com.techmeeva.chogadiyawidgets.core.state.AstronomyState state) {
    }
    
    private final java.lang.String moonLabel(com.techmeeva.chogadiyawidgets.core.localization.AppLanguage language) {
        return null;
    }
    
    private final void showLocationDialog() {
    }
    
    private final int getSlotColorRes(java.lang.String type) {
        return 0;
    }
}