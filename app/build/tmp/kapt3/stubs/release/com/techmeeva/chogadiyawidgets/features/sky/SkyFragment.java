package com.techmeeva.chogadiyawidgets.features.sky;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J&\u0010\u0010\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0012\u0010\u001c\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J$\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010\u0014\u001a\u0004\u0018\u00010#2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010$\u001a\u00020\rH\u0016J\b\u0010%\u001a\u00020\rH\u0016J\u001a\u0010&\u001a\u00020\r2\u0006\u0010\'\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020\rH\u0002J\u0010\u0010,\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/features/sky/SkyFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/techmeeva/chogadiyawidgets/databinding/FragmentSkyBinding;", "appState", "Lcom/techmeeva/chogadiyawidgets/core/state/AppState;", "binding", "getBinding", "()Lcom/techmeeva/chogadiyawidgets/databinding/FragmentSkyBinding;", "dataStore", "Lcom/techmeeva/chogadiyawidgets/core/state/ChoghadiyaDataStore;", "bindAstronomyState", "", "state", "Lcom/techmeeva/chogadiyawidgets/core/state/AstronomyState;", "bindWindowsToContainer", "windows", "", "Lcom/techmeeva/chogadiyawidgets/models/TimingWindow;", "container", "Landroid/widget/LinearLayout;", "colorRes", "", "illuminationTitle", "", "language", "Lcom/techmeeva/chogadiyawidgets/core/localization/AppLanguage;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "onDestroyView", "onResume", "onViewCreated", "view", "refreshAstronomyData", "force", "", "setupStaticText", "skyTitle", "app_release"})
public final class SkyFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.techmeeva.chogadiyawidgets.databinding.FragmentSkyBinding _binding;
    private com.techmeeva.chogadiyawidgets.core.state.AppState appState;
    private com.techmeeva.chogadiyawidgets.core.state.ChoghadiyaDataStore dataStore;
    
    public SkyFragment() {
        super();
    }
    
    private final com.techmeeva.chogadiyawidgets.databinding.FragmentSkyBinding getBinding() {
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
    public void onDestroyView() {
    }
    
    private final void refreshAstronomyData(boolean force) {
    }
    
    private final void setupStaticText() {
    }
    
    private final void bindAstronomyState(com.techmeeva.chogadiyawidgets.core.state.AstronomyState state) {
    }
    
    private final void bindWindowsToContainer(java.util.List<com.techmeeva.chogadiyawidgets.models.TimingWindow> windows, android.widget.LinearLayout container, int colorRes) {
    }
    
    private final java.lang.String skyTitle(com.techmeeva.chogadiyawidgets.core.localization.AppLanguage language) {
        return null;
    }
    
    private final java.lang.String illuminationTitle(com.techmeeva.chogadiyawidgets.core.localization.AppLanguage language) {
        return null;
    }
}