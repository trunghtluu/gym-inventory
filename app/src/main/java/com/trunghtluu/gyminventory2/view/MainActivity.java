package com.trunghtluu.gyminventory2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.trunghtluu.gyminventory2.R;
import com.trunghtluu.gyminventory2.adapter.ItemRecyclerViewAdapter;
import com.trunghtluu.gyminventory2.database.ItemDBHelper;
import com.trunghtluu.gyminventory2.model.ItemData;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
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

    private BuyItemFragment buyItemFragment = new BuyItemFragment();

    private ItemDBHelper itemDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shop.add(new ItemData("Treadmill"));
        shop.add(new ItemData("Dumbbell"));
        shop.add(new ItemData("Exercise Bike"));

        ButterKnife.bind(this);

        itemDB = new ItemDBHelper(this, null);

        loadShopRecyclerView();
    }

    @Override
    protected void onResume() {

        super.onResume();
        loadInventoryRecyclerView();
    }

    private void loadShopRecyclerView() {
        shopRecyclerViewAdpater = new ItemRecyclerViewAdapter(shop, this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        shopRecyclerView.addItemDecoration(itemDecoration);
        shopRecyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        shopRecyclerView.setAdapter(shopRecyclerViewAdpater);
    }

    private void loadInventoryRecyclerView() {
        inventory = new ArrayList<>();

        Cursor items = itemDB.retrieveItems();
        items.moveToFirst();

        while (items.moveToNext()) {

            String itemName = items.getString(items.getColumnIndex(ItemDBHelper.COLUMN_ITEM_NAME));
            inventory.add(new ItemData(itemName));
        }

        inventoryRecyclerViewAdpater = new ItemRecyclerViewAdapter(inventory, this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        inventoryRecyclerView.addItemDecoration(itemDecoration);
        inventoryRecyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        inventoryRecyclerView.setAdapter(inventoryRecyclerViewAdpater);
    }

    @Override
    public void itemSelected(ItemData selected) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("my_parcel", selected);
        buyItemFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_framelayout, buyItemFragment)
                .addToBackStack(buyItemFragment.getTag())
                .commit();
    }
}
