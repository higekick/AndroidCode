package com.higekick.dialogtest;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDynamic();
            }
        });
    }

    private void showDialogDynamic(){
        ArrayList<MyItem> itemList = new ArrayList<MyItem>();
        for(int i=1;i<=5;i++){
            MyItem item = new MyItem();
            item.name = i + " name ";
            item.id = i + "";
            itemList.add(item);
        }

        MyAdapter adapter = new MyAdapter(getApplicationContext(), 0, itemList);
        ListView listView = new ListView(this);
        listView.setAdapter(adapter);

        AlertDialog dlg = new AlertDialog.Builder(this)
                .setTitle("test dialog")
                .setView(listView)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        dlg.show();
    }

    private class MyItem{
        String name;
        String id;
    }

    private class MyAdapter extends ArrayAdapter<MyItem>{
        private LayoutInflater inflater;

        public MyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MyItem> objects) {
            super(context, resource, objects);
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
            final MyItem item = getItem(position);
            if (null == v) v = inflater.inflate(R.layout.custom_listview, null);

            TextView intTextView = (TextView)v.findViewById(R.id.int_item);
            intTextView.setText(item.name);

            Button btnView = (Button)v.findViewById(R.id.btn_list);
            btnView.setText(item.id);
            btnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, item.id, Toast.LENGTH_SHORT).show();
                }
            });
            return v;
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
