package com.cognizant.settingsapp;

import android.view.View;

/**
 * Created by 538015 on 9/28/2016.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
