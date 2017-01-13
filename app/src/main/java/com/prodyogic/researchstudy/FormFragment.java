package com.prodyogic.researchstudy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class FormFragment extends Fragment {
    FragmentActivity listener;
    String url = "https://docs.google.com/a/vikrambansal.com/forms/d/e/1FAIpQLScqFqM0VjShiZszmuppVBBY0wn8-RtZVDGlCD5IXNRLsf5Sag/viewform";
    private WebView webview;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View v = inflater.inflate(R.layout.fragment_form, parent, false);
        this.webview = (WebView) v.findViewById(R.id.webview);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        final AlertDialog alertDialog = new AlertDialog.Builder(this.getContext()).create();

        //ProgressDialog progressBar = ProgressDialog.show(MainActivity.this, "Form", "Loading...");

        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("main", "Processing webview url click...");
                view.loadUrl(url);
                return true;
            }


            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                //Toast.makeText(this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(description);

                alertDialog.show();
            }
        });

        webview.loadUrl(url);
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }
}
