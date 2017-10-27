package com.palfund.ui.actionbar;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import com.palfund.ui.R;

/**
 * Created by clvc on 2017/7/30.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class BlankActionProvider extends ActionProvider {

    public BlankActionProvider(Context context) {
        super(context);
        Log.i("BlankActionProvider", "---BlankActionProvider()--->" + context);
    }

    @Override
    public View onCreateActionView() {
        return null;
    }


    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        Log.i("BlankActionProvider", "---onPrepareSubMenu()--->");
        subMenu.clear();
        subMenu.add(0, 0, 0, "sub item 1").setIcon(R.mipmap.newer).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.i("BlankActionProvider", "---onMenuItemClick()--->sub item 1");
                return true;
            }
        });
        subMenu.add(1, 1, 1, "sub item 2").setIcon(R.mipmap.palace).setOnMenuItemClickListener
                (new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.i("BlankActionProvider", "---onMenuItemClick()--->sub item 2");
                return false;
            }
        });

    }

    @Override
    public boolean hasSubMenu() {
        return true;
    }

}
