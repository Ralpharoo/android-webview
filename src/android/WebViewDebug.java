package com.jamiestarke.webviewdebug;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaPlugin;

import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.view.View;

import android.util.Log;
/**
 * CordovaDebug is a PhoneGap plugin that enables debugging on the WebView
 * 
 * @author jamie@jamiestarke.com
 * 
 */
public class WebViewDebug extends CordovaPlugin 
{
	private static final String TAG = "WebViewDebug";
	
	/**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        
        WebView.setWebContentsDebuggingEnabled(true);
	
	// Trial to enhance speed? https://stackoverflow.com/questions/32304237/android-webview-loading-data-performance-very-slow
	// For API level below 18 (This method was deprecated in API level 18)
	WebView setWebView = (WebView) WebViewDebug.this.webView.getEngine().getView();
	setWebView.setWebContentsDebuggingEnabled(true);

	WebSettings settings = setWebView.getSettings();
	
	settings.setRenderPriority(WebSettings.RenderPriority.HIGH); 
	settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
	if (Build.VERSION.SDK_INT >= 19) {
		setWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
	}  else {
		setWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}
    }
}
