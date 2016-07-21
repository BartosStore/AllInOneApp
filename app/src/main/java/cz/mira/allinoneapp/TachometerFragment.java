package cz.mira.allinoneapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

/**
 * Created by Mira on 21.7.2016.
 */
public class TachometerFragment extends Fragment {

    WebView browser;

    public static TachometerFragment newInstance() {
        TachometerFragment tachometer = new TachometerFragment();
        return tachometer;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tachometer, container, false);

        browser = (WebView) view.findViewById(R.id.wvBrowser);
        browser.getSettings().setJavaScriptEnabled(true);
        //browser.loadUrl("https://www.seznam.cz/");
        browser.loadUrl("file:///android_asset/index.html");

        return view;
    }
}
