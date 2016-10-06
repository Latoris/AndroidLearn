package com.example.latoris.menupractice;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.PopupMenu;
        import android.view.ContextMenu;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView ContextMenuText = (TextView)findViewById(R.id.ContextMenu);
        registerForContextMenu(ContextMenuText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.contextmenu, menu);
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        int nID = item.getItemId();
        if(nID == R.id.MainSetting){
            Intent i = new Intent(MainActivity.this, SetitngActivity.class);
            startActivity(i);
        }
        else
            Toast.makeText(MainActivity.this, "选项菜单-其他 ",Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int nID = item.getItemId();
        if(nID == R.id.ContextSettings)
            Toast.makeText(MainActivity.this, "上下文菜单-主设置 ",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "上下文菜单-其他设置 ",Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_popup);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.pop_setting:
                Toast.makeText(this, "弹出菜单-主设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pop_otherSettings:
                Toast.makeText(this, "弹出菜单-其他设置", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

}
