package com.trunghtluu.gyminventory2.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trunghtluu.gyminventory2.R;
import com.trunghtluu.gyminventory2.database.ItemDBHelper;
import com.trunghtluu.gyminventory2.model.ItemData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyItemFragment extends Fragment {

    @BindView(R.id.announce_textView)
    TextView announceTextView;

    @BindView(R.id.quantity_editText)
    EditText quantityEditText;

    @BindView(R.id.submit_Button)
    Button  submitButton;

    private ItemDBHelper itemDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.buy_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ItemData item = (ItemData) getArguments().getParcelable("my_parcel");
        itemDB = new ItemDBHelper(view.getContext(), null);

        if (item != null) {
            System.out.println(item.getName());
            announceTextView.setText("BUYING " + item.getName().toUpperCase());

            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = Integer.parseInt(quantityEditText.getText().toString());

                    for (int i = 0; i < quantity; ++i)
                        itemDB.insertItem(item);

                    getActivity().getSupportFragmentManager().popBackStack();
                    ((MainActivity) getActivity()).onResume();
                }
            });
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("TAG_X", "Attach");
    }
}
