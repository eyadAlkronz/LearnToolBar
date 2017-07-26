package com.eyad.alkronz.learntoolbar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActionMode actionMode;

    ArrayList<String> names ;
    myAdapter myAdapter;
    ListView listviewlv1 ;
    int position ;


    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listviewlv1 = (ListView) findViewById(R.id.listviewlv1);
        names = new ArrayList<>();
        names.add("eyad");
        names.add("ali");
        names.add("eyad");
        names.add("eyad");
        myAdapter = new myAdapter(this, names);
        listviewlv1.setAdapter(myAdapter);
        listviewlv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                actionMode  = startSupportActionMode(callbackActionMode);
                position = i;
                return false;
            }

        });



    }

    public void showpopmenu(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext() , view, Gravity.END);
        popupMenu.getMenuInflater().inflate(R.menu.menu , popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){

                        case R.id.item_add:
                            Toast.makeText(getApplicationContext(),"Add",Toast.LENGTH_LONG).show(); break;

                        case R.id.item_search:
                            Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show(); break;

                        case R.id.item_update:
                            Toast.makeText(getApplicationContext(),"item_update",Toast.LENGTH_LONG).show(); break;

                        case R.id.item_delete:
                            Toast.makeText(getApplicationContext(), "item_delete", Toast.LENGTH_SHORT).show();
                            names.remove(position);
                            myAdapter.notifyDataSetChanged();;
                            break;
                        case R.id.item_share:
                            Toast.makeText(getApplicationContext(), "item_share", Toast.LENGTH_SHORT).show(); break;
                 }
                return true;
            }
        });
        popupMenu.show();
    }






    class myAdapter extends BaseAdapter {
        List<String> names ;
        Context context ;

        public  myAdapter(  Context context  , List<String> names){
            this.context =context;
            this.names=names;
        }
        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public Object getItem(int i) {
            return names.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from( context).inflate(R.layout.name_row ,viewGroup, false);

            TextView tvname = (TextView)view.findViewById(R.id.tvname);
            tvname.setText(names.get(i)+"");
            return view;
        }
    }


    private ActionMode.Callback callbackActionMode = new ActionMode.Callback(){

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.menu , menu);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            int id = item.getItemId();
            AdapterView.AdapterContextMenuInfo info=  (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            Toast.makeText(getApplicationContext(), position+"", Toast.LENGTH_SHORT).show();
            switch (id){
                case R.id.item_add:
                    Toast.makeText(getApplicationContext(),"Add",Toast.LENGTH_LONG).show(); break;

                case R.id.item_search:
                    Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show(); break;

                case R.id.item_update:
                    Toast.makeText(getApplicationContext(),"item_update",Toast.LENGTH_LONG).show(); break;

                case R.id.item_delete:
                    Toast.makeText(getApplicationContext(), "item_delete", Toast.LENGTH_SHORT).show();
                    names.remove(position);
                    myAdapter.notifyDataSetChanged();;
                    break;
                case R.id.item_share:
                    Toast.makeText(getApplicationContext(), "item_share", Toast.LENGTH_SHORT).show(); break;
            }
            mode.finish();
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode=null;
        }
    };


}
