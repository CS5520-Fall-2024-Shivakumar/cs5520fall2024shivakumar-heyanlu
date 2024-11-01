package com.example.a1helloworldapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1helloworldapp.adapter.ContactsAdapter;
import com.example.a1helloworldapp.model.Contact;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * This activity handles the contacts list and displays them in a RecyclerView
 */
public class ContactsActivity extends AppCompatActivity {
    private static final int ADD_CONTACT_REQUEST = 1;

    private List<Contact> contactsList;
    private ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactsList = new ArrayList<>();

        contactsAdapter = new ContactsAdapter(contactsList, new ContactsAdapter.OnContactClickListener() {
            @Override
            //Dialing intent
            public void onContactClick(int position) {
                Contact contact = contactsList.get(position);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(android.net.Uri.parse("tel:" + contact.getPhoneNumber()));
                startActivity(intent);
            }

            @Override
            public void onContactDelete(int position) {
                ContactsActivity.this.onContactDelete(position);
            }
        });

        RecyclerView contactsRecyclerView = findViewById(R.id.contactsCollectors);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactsRecyclerView.setAdapter(contactsAdapter);

        // FAB: new Intent to add a new contact
        findViewById(R.id.floatingActionButton).setOnClickListener(view -> {
            Intent intent = new Intent(ContactsActivity.this, AddContactActivity.class);
            startActivityForResult(intent, ADD_CONTACT_REQUEST);
        });

        Button backButton = findViewById(R.id.backButton2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("contactsList", new ArrayList<>(contactsList));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            contactsList.clear();
            contactsList.addAll((List<Contact>) savedInstanceState.getSerializable("contactsList"));
            contactsAdapter.notifyDataSetChanged();
        }
    }

    public void onContactDelete(int position) {
        contactsList.remove(position);
        contactsAdapter.notifyItemRemoved(position);
        Snackbar.make(findViewById(R.id.contactsCollectors), "Contact deleted", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CONTACT_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            String name = data.getStringExtra("name");
            String phoneNumber = data.getStringExtra("phone_number");
            contactsList.add(new Contact(name, phoneNumber));
            contactsAdapter.notifyItemInserted(contactsList.size() - 1);
            Snackbar.make(findViewById(R.id.contactsCollectors), "Contact added", Snackbar.LENGTH_SHORT).show();
        }
    }
}
