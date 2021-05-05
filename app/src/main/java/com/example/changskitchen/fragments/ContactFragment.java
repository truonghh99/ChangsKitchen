package com.example.changskitchen.fragments;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.changskitchen.R;
import com.example.changskitchen.adapters.DishAdapter;
import com.example.changskitchen.databinding.FragmentContactBinding;
import com.example.changskitchen.databinding.FragmentMenuBinding;
import com.example.changskitchen.models.Dish;
import com.example.changskitchen.models.Menu;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.MessageDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {

    private static final String TAG = "ContactFragment";
    private static final String ORDER_KEY = "OrderKey";
    String orderId;

    EditText etOrderId;
    EditText etMessage;
    Button btSend;
    Button btCall;

    public ContactFragment() {
    }

    public static ContactFragment newInstance(String orderId) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ORDER_KEY, orderId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderId = getArguments().getString(ORDER_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentContactBinding fragmentContactBinding = FragmentContactBinding.inflate(getLayoutInflater());

        etMessage = fragmentContactBinding.etMessage;
        etOrderId = fragmentContactBinding.etOrderId;
        btCall = fragmentContactBinding.btCall;
        btSend = fragmentContactBinding.btSend;

        if (!orderId.isEmpty()) etOrderId.setText(orderId);

        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+84913989706"));
                startActivity(intent);
            }
        });

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderId = etOrderId.getText().toString();
                String message = etMessage.getText().toString();
                if (!orderId.isEmpty()) {
                    message = "About order: " + orderId + "\n" + message;
                }

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.facebook.orca");

                try {
                    startActivity(sendIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "Please Install Facebook Messenger", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return fragmentContactBinding.getRoot();
    }
}