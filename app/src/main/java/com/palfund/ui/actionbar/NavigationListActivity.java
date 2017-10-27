package com.palfund.ui.actionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.palfund.ui.R;

public class NavigationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        String[] titles = new String[]{"德国", "法国", "中国", "英国"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout
                .simple_list_item_1, titles);
        actionBar.setListNavigationCallbacks(adapter, new NavigationListener());
    }

    class NavigationListener implements ActionBar.OnNavigationListener {

        @Override
        public boolean onNavigationItemSelected(int itemPosition, long itemId) {
            Toast.makeText(NavigationListActivity.this, "position"+itemPosition, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
