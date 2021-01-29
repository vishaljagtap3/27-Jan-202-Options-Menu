package in.bitcode.menudemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int MENU_INFO = 1, MENU_HELP = 2, MENU_SETTINGS = 3;
    public static final int MENU_PHONE_SETTINGS = 11, MENU_SYSTEM_SETTINGS = 12;

    private CheckBox mChkSettings;
    private EditText mEdtInfo;
    private TextView mTxtInfo;

    private String mStr;

    public static final int MENU_COPY = 50, MENU_CUT = 51, MENU_PASTE = 52, MENU_APPEND = 53;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChkSettings = findViewById(R.id.chkSettings);
        mEdtInfo = findViewById(R.id.edtInfo);
        mTxtInfo = findViewById(R.id.txtInfo);

        registerForContextMenu(mEdtInfo);
        registerForContextMenu(mTxtInfo);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        mt("onCreateContextMenu");

        if(view == mEdtInfo) {
            menu.add(0, MENU_COPY, 0, "Copy");
            menu.add(0, MENU_CUT, 0, "Cut");
            menu.setGroupEnabled(0, mChkSettings.isChecked());
        }

        if(view.getId() == R.id.txtInfo) {
            menu.add(1, MENU_PASTE, 0, "Paste");
            menu.add(1, MENU_APPEND, 0, "Append");
            menu.setGroupEnabled(1, mChkSettings.isChecked());
        }

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case MENU_COPY:
                mStr = mEdtInfo.getText().toString();
                break;
            case MENU_CUT:
                mStr = mEdtInfo.getText().toString();
                mEdtInfo.setText("");
                break;
            case MENU_PASTE:
                mTxtInfo.setText(mStr);;
                break;
            case MENU_APPEND:
                mTxtInfo.append(mStr);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        mt("onCreateOptionsMenu");

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity, menu);

        /*
        MenuItem menuItem;
        menuItem = menu.add(0, MENU_INFO, 0, "Info");
        menuItem.setCheckable(true);
        menuItem.setChecked(true);
        menuItem.setIcon(R.mipmap.ic_launcher);
        menuItem.setVisible(true);
        menuItem.setAlphabeticShortcut('i');
        menuItem.setNumericShortcut('0');
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


        //SubMenu  subMenu = menu.addSubMenu(0, MENU_SETTINGS, 0, "Settings");
        //subMenu.add(1, MENU_PHONE_SETTINGS, 0, "Phone Settings");
        //subMenu.add(1, MENU_SYSTEM_SETTINGS, 0, "System Settings");

        SubMenu subMenu = menu.addSubMenu(0, MENU_SETTINGS, 0, R.string.menu_settings);
        //subMenu.getItem().setEnabled(mChkSettings.isChecked()); //not the right place


        subMenu.add(1, MENU_PHONE_SETTINGS, 0, R.string.menu_phone_settings);
        subMenu.add(1, MENU_SYSTEM_SETTINGS, 0, R.string.menu_system_settings);

        menu.add(0, MENU_HELP, 0, "Help");
        */

        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        mt("onPrepareOptionsMenu");


        //menu.setGroupEnabled(0, mChkSettings.isChecked());
        menu.setGroupEnabled(R.id.myGroup, mChkSettings.isChecked());

        /*MenuItem menuItemSettings = menu.findItem(MENU_SETTINGS);
        menuItemSettings.setEnabled(mChkSettings.isChecked());
        */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        mt(item.getItemId()  + " " + item.getTitle());

        /*switch (item.getItemId()){
            case MENU_INFO:
                mt("Info");
        }*/

        return true;
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}