package com.minesweepers.bohni;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeViewActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ProductModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = TestDataProvider.getTestProducts();

        adapter = new HomeViewAdapter(data, recyclerView, new HomeViewAdapter.HomeViewCardActionsListener() {
            @Override
            public void onCardClicked(ProductModel productModel) {
                final Toast toast = Toast.makeText(getApplicationContext(), "tapped on ".concat(productModel.getName()), Toast.LENGTH_SHORT);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toast.show();
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
