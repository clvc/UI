package com.palfund.ui.actionbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.palfund.ui.R;

public class ActionModeActivity extends AppCompatActivity {

    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode);
        setTitle("ActionMode");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,1,"Mode");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ActionModeCallBack callBack = new ActionModeCallBack();
        mActionMode = startSupportActionMode(callBack);
        mActionMode.setTitle("palfund");
        return super.onOptionsItemSelected(item);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActionMode = null;
    }

    class ActionModeCallBack implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.actionbar, menu);
            MenuItem menuItem = menu.findItem(R.id.action_share);
            //返回true表示action mode会被创建  false if entering this mode should be aborted.
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_add:
                    Toast.makeText(ActionModeActivity.this, "add", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_share:
                    Toast.makeText(ActionModeActivity.this, "share", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_updata:
                    Toast.makeText(ActionModeActivity.this, "updata", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }
}
