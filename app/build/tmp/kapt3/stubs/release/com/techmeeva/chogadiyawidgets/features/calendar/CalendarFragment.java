package com.techmeeva.chogadiyawidgets.features.calendar;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001?B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0016\u0010\u0013\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002J\u001e\u0010\u0017\u001a\u00020\u00102\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u000bH\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010#\u001a\u00020\u000bH\u0002J\u001e\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00152\u0006\u0010\'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020%H\u0002J\u0010\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020%H\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010#\u001a\u00020\u000bH\u0002J\u0018\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u000bH\u0002J\u0012\u00100\u001a\u00020\u00102\b\u00101\u001a\u0004\u0018\u000102H\u0016J$\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\b\u0010\u001a\u001a\u0004\u0018\u0001072\b\u00101\u001a\u0004\u0018\u000102H\u0016J\b\u00108\u001a\u00020\u0010H\u0016J\b\u00109\u001a\u00020\u0010H\u0016J\u001a\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u0002042\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0010\u0010<\u001a\u00020\u00102\u0006\u0010=\u001a\u00020,H\u0002J\b\u0010>\u001a\u00020\u0010H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006@"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/features/calendar/CalendarFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/techmeeva/chogadiyawidgets/databinding/FragmentCalendarBinding;", "appState", "Lcom/techmeeva/chogadiyawidgets/core/state/AppState;", "binding", "getBinding", "()Lcom/techmeeva/chogadiyawidgets/databinding/FragmentCalendarBinding;", "currentMonth", "Ljava/util/Date;", "dataStore", "Lcom/techmeeva/chogadiyawidgets/core/state/ChoghadiyaDataStore;", "selectedDate", "bindMonthState", "", "state", "Lcom/techmeeva/chogadiyawidgets/core/state/MonthScheduleState;", "bindSelectedDayDetails", "days", "", "Lcom/techmeeva/chogadiyawidgets/models/ChoghadiyaDay;", "bindSlotsToContainer", "slots", "Lcom/techmeeva/chogadiyawidgets/models/ChoghadiyaSlot;", "container", "Landroid/widget/LinearLayout;", "changeMonth", "offset", "", "clearTimeFields", "cal", "Ljava/util/Calendar;", "firstDayOfMonth", "date", "formatStateKey", "", "getDaysForMonth", "monthDate", "timezone", "getSlotColorRes", "type", "isPremiumLocked", "", "isSameMonth", "first", "second", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "onDestroyView", "onResume", "onViewCreated", "view", "refreshMonthData", "force", "setupStaticTranslations", "CalendarGridAdapter", "app_release"})
public final class CalendarFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.techmeeva.chogadiyawidgets.databinding.FragmentCalendarBinding _binding;
    private com.techmeeva.chogadiyawidgets.core.state.AppState appState;
    private com.techmeeva.chogadiyawidgets.core.state.ChoghadiyaDataStore dataStore;
    private java.util.Date currentMonth;
    private java.util.Date selectedDate;
    
    public CalendarFragment() {
        super();
    }
    
    private final com.techmeeva.chogadiyawidgets.databinding.FragmentCalendarBinding getBinding() {
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
    
    private final void setupStaticTranslations() {
    }
    
    private final void refreshMonthData(boolean force) {
    }
    
    private final void changeMonth(int offset) {
    }
    
    private final boolean isPremiumLocked(java.util.Date date) {
        return false;
    }
    
    private final void clearTimeFields(java.util.Calendar cal) {
    }
    
    private final void bindMonthState(com.techmeeva.chogadiyawidgets.core.state.MonthScheduleState state) {
    }
    
    private final void bindSelectedDayDetails(java.util.List<com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay> days) {
    }
    
    private final void bindSlotsToContainer(java.util.List<com.techmeeva.chogadiyawidgets.models.ChoghadiyaSlot> slots, android.widget.LinearLayout container) {
    }
    
    private final int getSlotColorRes(java.lang.String type) {
        return 0;
    }
    
    private final java.util.List<java.util.Date> getDaysForMonth(java.util.Date monthDate, java.lang.String timezone) {
        return null;
    }
    
    private final java.lang.String formatStateKey(java.util.Date date) {
        return null;
    }
    
    private final java.util.Date firstDayOfMonth(java.util.Date date) {
        return null;
    }
    
    private final boolean isSameMonth(java.util.Date first, java.util.Date second) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\fH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\fH\u0016J\"\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0004R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/features/calendar/CalendarFragment$CalendarGridAdapter;", "Landroid/widget/BaseAdapter;", "days", "", "Ljava/util/Date;", "monthDaysData", "Lcom/techmeeva/chogadiyawidgets/models/ChoghadiyaDay;", "(Lcom/techmeeva/chogadiyawidgets/features/calendar/CalendarFragment;Ljava/util/List;Ljava/util/List;)V", "sdf", "Ljava/text/SimpleDateFormat;", "selectedDay", "getCount", "", "getItem", "position", "getItemId", "", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "isSameDay", "", "d1", "d2", "setSelectedDate", "", "date", "app_release"})
    final class CalendarGridAdapter extends android.widget.BaseAdapter {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.util.Date> days = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay> monthDaysData = null;
        @org.jetbrains.annotations.NotNull()
        private java.util.Date selectedDay;
        @org.jetbrains.annotations.NotNull()
        private final java.text.SimpleDateFormat sdf = null;
        
        public CalendarGridAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends java.util.Date> days, @org.jetbrains.annotations.NotNull()
        java.util.List<com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay> monthDaysData) {
            super();
        }
        
        public final void setSelectedDate(@org.jetbrains.annotations.NotNull()
        java.util.Date date) {
        }
        
        @java.lang.Override()
        public int getCount() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.util.Date getItem(int position) {
            return null;
        }
        
        @java.lang.Override()
        public long getItemId(int position) {
            return 0L;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public android.view.View getView(int position, @org.jetbrains.annotations.Nullable()
        android.view.View convertView, @org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            return null;
        }
        
        private final boolean isSameDay(java.util.Date d1, java.util.Date d2) {
            return false;
        }
    }
}