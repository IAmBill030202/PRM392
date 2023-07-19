package com.example.practicalexam.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.practicalexam.R;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> contactsList;

    public ContactAdapter(List<Contact> contactsList) {
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactsList.get(position);
        holder.textViewEmpID.setText("Employee ID: " + contact.getEmpId());
        holder.textViewEmpName.setText(contact.getEmpName());
        holder.textViewEmpEmail.setText(contact.getEmpEmail());
        holder.textViewEmpAddress.setText("Address: " + contact.getEmpAddress());
        holder.textViewEmpNotes.setText("Notes: " + contact.getNotes());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textViewEmpID;
        TextView textViewEmpName;
        TextView textViewEmpEmail;
        TextView textViewEmpAddress;
        TextView textViewEmpNotes;

        public ContactViewHolder(View itemView) {
            super(itemView);
            textViewEmpID = itemView.findViewById(R.id.textViewEmpID);
            textViewEmpName = itemView.findViewById(R.id.textViewEmpName);
            textViewEmpEmail = itemView.findViewById(R.id.textViewEmpEmail);
            textViewEmpAddress = itemView.findViewById(R.id.textViewEmpAddress);
            textViewEmpNotes = itemView.findViewById(R.id.textViewEmpNotes);
        }
    }
}
