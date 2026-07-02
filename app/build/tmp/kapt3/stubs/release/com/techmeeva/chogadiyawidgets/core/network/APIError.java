package com.techmeeva.chogadiyawidgets.core.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0005\b\t\n\u000b\fB\u001b\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007\u0082\u0001\u0005\r\u000e\u000f\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/network/APIError;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "HTTPError", "InvalidResponse", "InvalidURL", "NetworkUnavailable", "TimedOut", "Lcom/techmeeva/chogadiyawidgets/core/network/APIError$HTTPError;", "Lcom/techmeeva/chogadiyawidgets/core/network/APIError$InvalidResponse;", "Lcom/techmeeva/chogadiyawidgets/core/network/APIError$InvalidURL;", "Lcom/techmeeva/chogadiyawidgets/core/network/APIError$NetworkUnavailable;", "Lcom/techmeeva/chogadiyawidgets/core/network/APIError$TimedOut;", "app_release"})
public abstract class APIError extends java.lang.Exception {
    
    private APIError(java.lang.String message, java.lang.Throwable cause) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/network/APIError$HTTPError;", "Lcom/techmeeva/chogadiyawidgets/core/network/APIError;", "code", "", "(I)V", "getCode", "()I", "app_release"})
    public static final class HTTPError extends com.techmeeva.chogadiyawidgets.core.network.APIError {
        private final int code = 0;
        
        public HTTPError(int code) {
        }
        
        public final int getCode() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/network/APIError$InvalidResponse;", "Lcom/techmeeva/chogadiyawidgets/core/network/APIError;", "()V", "app_release"})
    public static final class InvalidResponse extends com.techmeeva.chogadiyawidgets.core.network.APIError {
        @org.jetbrains.annotations.NotNull()
        public static final com.techmeeva.chogadiyawidgets.core.network.APIError.InvalidResponse INSTANCE = null;
        
        private InvalidResponse() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/network/APIError$InvalidURL;", "Lcom/techmeeva/chogadiyawidgets/core/network/APIError;", "()V", "app_release"})
    public static final class InvalidURL extends com.techmeeva.chogadiyawidgets.core.network.APIError {
        @org.jetbrains.annotations.NotNull()
        public static final com.techmeeva.chogadiyawidgets.core.network.APIError.InvalidURL INSTANCE = null;
        
        private InvalidURL() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/network/APIError$NetworkUnavailable;", "Lcom/techmeeva/chogadiyawidgets/core/network/APIError;", "()V", "app_release"})
    public static final class NetworkUnavailable extends com.techmeeva.chogadiyawidgets.core.network.APIError {
        @org.jetbrains.annotations.NotNull()
        public static final com.techmeeva.chogadiyawidgets.core.network.APIError.NetworkUnavailable INSTANCE = null;
        
        private NetworkUnavailable() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/techmeeva/chogadiyawidgets/core/network/APIError$TimedOut;", "Lcom/techmeeva/chogadiyawidgets/core/network/APIError;", "()V", "app_release"})
    public static final class TimedOut extends com.techmeeva.chogadiyawidgets.core.network.APIError {
        @org.jetbrains.annotations.NotNull()
        public static final com.techmeeva.chogadiyawidgets.core.network.APIError.TimedOut INSTANCE = null;
        
        private TimedOut() {
        }
    }
}