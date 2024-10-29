package com.example.a1helloworldapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1helloworldapp.ContactsActivity;
import com.example.a1helloworldapp.R;
import com.example.a1helloworldapp.model.Contact;
import com.google.android.material.snackbar.Snackbar;

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

        holder.editName.setText(contact.getName());
        holder.editPhone.setText(contact.getPhoneNumber());
        holder.editName.setVisibility(View.GONE);
        holder.editPhone.setVisibility(View.GONE);
        holder.saveButton.setVisibility(View.GONE);
        holder.editButton.setVisibility(View.VISIBLE);

        //Edit Button
        holder.editButton.setOnClickListener(v -> {
            holder.nameTextView.setVisibility(View.GONE);
            holder.phoneTextView.setVisibility(View.GONE);
            holder.editName.setVisibility(View.VISIBLE);
            holder.editPhone.setVisibility(View.VISIBLE);
            holder.saveButton.setVisibility(View.VISIBLE);
            holder.editButton.setVisibility(View.GONE);
        });

        //Save Button
        holder.saveButton.setOnClickListener(v -> {
            String newName = holder.editName.getText().toString().trim();
            String newPhone = holder.editPhone.getText().toString().trim();

            if (newName.isEmpty() || newPhone.isEmpty()) {
                Snackbar.make(holder.itemView, "Name and phone number cannot be empty", Snackbar.LENGTH_SHORT).show();
                return;
            }

            contact.setName(newName);
            contact.setPhoneNumber(newPhone);
            holder.nameTextView.setText(newName);
            holder.phoneTextView.setText(newPhone);

            //Handle visibility
            holder.nameTextView.setVisibility(View.VISIBLE);
            holder.phoneTextView.setVisibility(View.VISIBLE);
            holder.editName.setVisibility(View.GONE);
            holder.editPhone.setVisibility(View.GONE);
            holder.saveButton.setVisibility(View.GONE);
            holder.editButton.setVisibility(View.VISIBLE);

            notifyItemChanged(position);
            Snackbar.make(holder.itemView, "Contact updated", Snackbar.LENGTH_SHORT).show();
        });

        //Deletion
        holder.deleteButton.setOnClickListener(v -> {
            listener.onContactDelete(position);
        });
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView phoneTextView;
        EditText editName;
        EditText editPhone;
        Button editButton;
        Button saveButton;
        Button deleteButton;

        ContactViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.contactName);
            phoneTextView = itemView.findViewById(R.id.contactPhone);
            editName = itemView.findViewById(R.id.editContactName);
            editPhone = itemView.findViewById(R.id.editContactPhone);
            editButton = itemView.findViewById(R.id.buttonEdit);
            saveButton = itemView.findViewById(R.id.buttonSave);
            deleteButton = itemView.findViewById(R.id.buttonDelete);
        }

    }
}