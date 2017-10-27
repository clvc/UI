package com.palfund.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.palfund.ui.actionbar.ActionBarActivity;
import com.palfund.ui.autocompletetextview.AutoCompleteTextViewActivity;
import com.palfund.ui.cardview.CardViewActivity;
import com.palfund.ui.coordinatorlayout.CoordinatorLayoutActivity;
import com.palfund.ui.countdowntimer.CountDownTimerActivity;
import com.palfund.ui.drawerlayout.DrawerLayoutActivity;
import com.palfund.ui.flexboxlayout.FlexBoxLayoutActivity;
import com.palfund.ui.navigationview.NavigationViewActivity;
import com.palfund.ui.progressbar.ProgressBarActivity;
import com.palfund.ui.recyclerview.RecyclerViewActivity;
import com.palfund.ui.swiperefreshlayout.SwipeRefreshLayoutActivity;
import com.palfund.ui.textinputlayout.TextInputLayoutActivity;
import com.palfund.ui.textswitcher.TextSwitcherActivity;
import com.palfund.ui.toolbar.ToolbarActivity;
import com.palfund.ui.topfloat.TopFloatActivity;
import com.palfund.ui.viewstub.ViewStubActivity;
import com.palfund.ui.webview.WebViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "---onCreate()--->" + savedInstanceState);
        Log.i("MainActivity", "---onCreate()--->" + isTaskRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 000, 1, "ActionBar");
        menu.add(Menu.NONE, 111, 2, "Toolbar");
        menu.add(Menu.NONE, 222, 3, "DrawerLayout");
        menu.add(Menu.NONE, 333, 4, "NavigationView");
        menu.add(Menu.NONE, 444, 5, "CoordinatorLayout");
        menu.add(Menu.NONE, 555, 6, "WebView");
        menu.add(Menu.NONE, 666, 7, "RecyclerView");
        menu.add(Menu.NONE, 777, 8, "TextInputLayout");
        menu.add(Menu.NONE, 888, 9, "CardView");
        menu.add(Menu.NONE, 999, 10, "SwipeRefreshLayout");
        menu.add(Menu.NONE, 1, 10, "FlexBoxLayout");
        menu.add(Menu.NONE, 2, 10, "TextSwitcher");
        menu.add(Menu.NONE, 3, 10, "TopFloat");
        menu.add(Menu.NONE, 4, 10, "CountDownTimer");
        menu.add(Menu.NONE, 5, 10, "ProgressBar");
        menu.add(Menu.NONE, 6, 10, "ViewStub");
        menu.add(Menu.NONE, 7, 10, "AutoCompleteTextView");
        menu.add(Menu.NONE, 1000, 10, "CustomToast");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 000:
                startActivity(new Intent(this, ActionBarActivity.class));
                break;
            case 111:
                startActivity(new Intent(this, ToolbarActivity.class));
                break;
            case 222:
                startActivity(new Intent(this, DrawerLayoutActivity.class));
                break;
            case 333:
                startActivity(new Intent(this, NavigationViewActivity.class));
                break;
            case 444:
                startActivity(new Intent(this, CoordinatorLayoutActivity.class));
                break;
            case 555:
                startActivity(new Intent(this, WebViewActivity.class));
                break;
            case 666:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case 777:
                startActivity(new Intent(this, TextInputLayoutActivity.class));
                break;
            case 888:
                startActivity(new Intent(this, CardViewActivity.class));
                break;
            case 999:
                startActivity(new Intent(this, SwipeRefreshLayoutActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, FlexBoxLayoutActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, TextSwitcherActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, TopFloatActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, CountDownTimerActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, ProgressBarActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, ViewStubActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, AutoCompleteTextViewActivity.class));
                break;
            case 1000:
                customToast(this, "123", Toast.LENGTH_LONG);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private Toast mToast;

    private void customToast(final Context context, String msg, int length) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View toastView = inflater.inflate(R.layout.toast, null);
        TextView text = (TextView) toastView.findViewById(R.id.tv_toast_txt0);
        text.setText(msg);
        if (mToast == null) {
            mToast = new Toast(context.getApplicationContext());
        }
        //加上Gravity.FILL_HORIZONTAL使得toast横向充满屏幕
        mToast.setGravity(Gravity.TOP, 0, 0);
        mToast.setDuration(length);
        mToast.setView(toastView);
        mToast.show();
    }
}