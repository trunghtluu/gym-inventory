package com.trunghtluu.gyminventory2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trunghtluu.gyminventory2.R;
import com.trunghtluu.gyminventory2.model.ItemData;

import java.util.Collections;
import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private ItemAdapterDelegate itemAdapterDelegate;


    public interface ItemAdapterDelegate{
        void itemSelected(ItemData selected);
    }

    private List<ItemData> list
            = Collections.emptyList();

    public ItemRecyclerViewAdapter(List<ItemData> list, ItemAdapterDelegate itemAdapterDelegate) {
        this.list = list;
        this.itemAdapterDelegate = itemAdapterDelegate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getNameTextView()
                .setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public ViewHolder(@NonNull View view) {
            super(view);

            nameTextView = view.findViewById(R.id.name_textView);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }
    }
}
