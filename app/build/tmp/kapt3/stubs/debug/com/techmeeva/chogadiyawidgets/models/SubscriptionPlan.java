package com.techmeeva.chogadiyawidgets.models;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0019B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\r8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u00138F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018\u00a8\u0006\u001a"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/models/SubscriptionPlan;", "", "rawValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "displayName", "getDisplayName", "()Ljava/lang/String;", "fallbackPrice", "getFallbackPrice", "id", "getId", "isPremium", "", "()Z", "productID", "getProductID", "getRawValue", "sortPriority", "", "getSortPriority", "()I", "FREE", "PREMIUM_MONTHLY", "PREMIUM_YEARLY", "Companion", "app_debug"})
public enum SubscriptionPlan {
    /*public static final*/ FREE /* = new FREE(null) */,
    /*public static final*/ PREMIUM_MONTHLY /* = new PREMIUM_MONTHLY(null) */,
    /*public static final*/ PREMIUM_YEARLY /* = new PREMIUM_YEARLY(null) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String rawValue = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.techmeeva.chogadiyawidgets.models.SubscriptionPlan.Companion Companion = null;
    
    SubscriptionPlan(java.lang.String rawValue) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRawValue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDisplayName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getProductID() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFallbackPrice() {
        return null;
    }
    
    public final int getSortPriority() {
        return 0;
    }
    
    public final boolean isPremium() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.techmeeva.chogadiyawidgets.models.SubscriptionPlan> getEntries() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\nR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\r"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/models/SubscriptionPlan$Companion;", "", "()V", "premiumCases", "", "Lcom/techmeeva/chogadiyawidgets/models/SubscriptionPlan;", "getPremiumCases", "()Ljava/util/List;", "fromProductID", "productID", "", "fromRawValue", "value", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.techmeeva.chogadiyawidgets.models.SubscriptionPlan> getPremiumCases() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.techmeeva.chogadiyawidgets.models.SubscriptionPlan fromRawValue(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.techmeeva.chogadiyawidgets.models.SubscriptionPlan fromProductID(@org.jetbrains.annotations.NotNull()
        java.lang.String productID) {
            return null;
        }
    }
}