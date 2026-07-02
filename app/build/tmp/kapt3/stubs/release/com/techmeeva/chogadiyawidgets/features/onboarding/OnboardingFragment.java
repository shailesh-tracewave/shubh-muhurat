package com.techmeeva.chogadiyawidgets.features.onboarding;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001)B\u0005\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0011H\u0002J\u0012\u0010\u001c\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J$\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010%\u001a\u00020\u0017H\u0016J\u001a\u0010&\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010(\u001a\u00020\u0017H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/features/onboarding/OnboardingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/techmeeva/chogadiyawidgets/databinding/FragmentOnboardingBinding;", "appState", "Lcom/techmeeva/chogadiyawidgets/core/state/AppState;", "binding", "getBinding", "()Lcom/techmeeva/chogadiyawidgets/databinding/FragmentOnboardingBinding;", "locationPageViewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "requestLocationPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "calculateDistance", "", "lat1", "lon1", "lat2", "lon2", "detectLocation", "", "matchLocationToCity", "Lcom/techmeeva/chogadiyawidgets/models/SeedCity;", "lat", "lng", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onViewCreated", "view", "updateLocationPageVisuals", "OnboardingPagerAdapter", "app_release"})
public final class OnboardingFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.techmeeva.chogadiyawidgets.databinding.FragmentOnboardingBinding _binding;
    private com.techmeeva.chogadiyawidgets.core.state.AppState appState;
    @org.jetbrains.annotations.Nullable()
    private androidx.recyclerview.widget.RecyclerView.ViewHolder locationPageViewHolder;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> requestLocationPermissionLauncher = null;
    
    public OnboardingFragment() {
        super();
    }
    
    private final com.techmeeva.chogadiyawidgets.databinding.FragmentOnboardingBinding getBinding() {
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
    public void onDestroyView() {
    }
    
    private final void detectLocation() {
    }
    
    private final com.techmeeva.chogadiyawidgets.models.SeedCity matchLocationToCity(double lat, double lng) {
        return null;
    }
    
    private final double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        return 0.0;
    }
    
    private final void updateLocationPageVisuals() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u0010B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016J \u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H\u0016J \u0010\f\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0016\u00a8\u0006\u0011"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/features/onboarding/OnboardingFragment$OnboardingPagerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/techmeeva/chogadiyawidgets/features/onboarding/OnboardingFragment$OnboardingPagerAdapter$ViewHolder;", "Lcom/techmeeva/chogadiyawidgets/features/onboarding/OnboardingFragment;", "(Lcom/techmeeva/chogadiyawidgets/features/onboarding/OnboardingFragment;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_release"})
    final class OnboardingPagerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.techmeeva.chogadiyawidgets.features.onboarding.OnboardingFragment.OnboardingPagerAdapter.ViewHolder> {
        
        public OnboardingPagerAdapter() {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.techmeeva.chogadiyawidgets.features.onboarding.OnboardingFragment.OnboardingPagerAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public int getItemViewType(int position) {
            return 0;
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.techmeeva.chogadiyawidgets.features.onboarding.OnboardingFragment.OnboardingPagerAdapter.ViewHolder holder, int position) {
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/features/onboarding/OnboardingFragment$OnboardingPagerAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/techmeeva/chogadiyawidgets/features/onboarding/OnboardingFragment$OnboardingPagerAdapter;Landroid/view/View;)V", "app_release"})
        public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            
            public ViewHolder(@org.jetbrains.annotations.NotNull()
            android.view.View itemView) {
                super(null);
            }
        }
    }
}