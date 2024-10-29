package com.example.a1helloworldapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1helloworldapp.ContactsActivity;
import com.example.a1helloworldapp.R;
import com.example.a1helloworldapp.model.Contact;

import java.util.List;

/**
 * Adapter to receive the list of contacts and a click listener
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {
    private List<Contact> contactsList;
    private OnContactClickListener listener;

    public interface OnContactClickListener {
        void onContactClick(int position);
        void onContactDelete(int position);
    }

    public ContactsAdapter(List<Contact> contactsList, OnContactClickListener listener) {
        this.contactsList = contactsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    //Binding views for each contact item
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactsList.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneTextView.setText(contact.getPhoneNumber());

        holder.itemView.setOnClickListener(v -> listener.onContactClick(position));
        holder.deleteButton.setOnClickListener(v -> {
            ((ContactsActivity) holder.itemView.getContext()).onContactDelete(position);
        });
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView phoneTextView;
        Button deleteButton;

        ContactViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.contactName);
            phoneTextView = itemView.findViewById(R.id.contactPhone);
            deleteButton = itemView.findViewById(R.id.buttonDelete);
        }
    }
}