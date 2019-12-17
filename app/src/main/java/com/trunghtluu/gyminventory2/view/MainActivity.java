package com.trunghtluu.gyminventory2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.trunghtluu.gyminventory2.R;
import com.trunghtluu.gyminventory2.adapter.ItemRecyclerViewAdapter;
import com.trunghtluu.gyminventory2.model.ItemData;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ItemRecyclerViewAdapter.ItemAdapterDelegate {

    private ItemRecyclerViewAdapter shopRecyclerViewAdpater;
    private ItemRecyclerViewAdapter inventoryRecyclerViewAdpater;

    @BindView(R.id.shop_recyclerView)
    RecyclerView shopRecyclerView;
    @BindView(R.id.inventory_recyclerView)
    RecyclerView inventoryRecyclerView;

    private List<ItemData> shop = new ArrayList<>();
    private List<ItemData> inventory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shop.add(new ItemData("1", "Treadmill"));
        shop.add(new ItemData("2", "Dumbbell"));
        shop.add(new ItemData("3", "Exercise Bike"));

        ButterKnife.bind(this);

        loadShopRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void loadShopRecyclerView() {
        shopRecyclerViewAdpater = new ItemRecyclerViewAdapter(shop, this);
        //DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        //shopRecyclerView.addItemDecoration(itemDecoration);
        shopRecyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        shopRecyclerView.setAdapter(shopRecyclerViewAdpater);
    }

    private void loadInventoryRecyclerView() {
        inventoryRecyclerViewAdpater = new ItemRecyclerViewAdapter(inventory, this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        inventoryRecyclerView.addItemDecoration(itemDecoration);
        inventoryRecyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        inventoryRecyclerView.setAdapter(shopRecyclerViewAdpater);
    }

    @Override
    public void itemSelected(ItemData selected) {

    }
}
