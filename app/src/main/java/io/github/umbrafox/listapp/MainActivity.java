package io.github.umbrafox.listapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView myListView;
    String[] items;
    String[] prices;
    String[] desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        items = res.getStringArray(R.array.items);
        prices = res.getStringArray(R.array.prices);
        desc = res.getStringArray(R.array.desc);

        ItemAdapter itemAdapter =
                new ItemAdapter(this, items, prices, desc);
        myListView
                .setAdapter(itemAdapter);
        myListView
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showDetailActivity =
                        new Intent(getApplicationContext(), DetailActivity.class);
                showDetailActivity
                        .putExtra("io.github.umbrafox.listapp.ITEM_INDEX", position);
                startActivity(showDetailActivity);
            }
        });

        //myListView.setAdapter(new ArrayAdapter<String>(this, R.layout.my_listview_detail, items));

    }
}
