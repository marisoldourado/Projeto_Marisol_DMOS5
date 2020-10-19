package com.dmos5.projeto_marisol_dmos5.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dmos5.projeto_marisol_dmos5.R;
import com.dmos5.projeto_marisol_dmos5.model.Ticket;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.RepositoryViewHolder>  {

    private List<Ticket> tickets;
    private Context mContext;

    public TicketAdapter(@NonNull List<Ticket> tickets, Context context) {
        this.tickets = tickets;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TicketAdapter.RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item, parent, false);
        RepositoryViewHolder viewHolder =new RepositoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.RepositoryViewHolder holder, int position) {

        final Ticket ticketItem = tickets.get(position);
        holder.txtvTicketNumber.setText(tickets.get(position).getTicketNumber());
        holder.txtvTicketState.setText(tickets.get(position).getState());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show details
                Intent intent = new Intent(mContext, TicketDetailsActivity.class);
                intent.putExtra("TicketID", ticketItem.getTicketID());
                intent.putExtra("TicketNumber", ticketItem.getTicketNumber());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tickets != null){
            return tickets.size();
        }
        return 0;
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtvTicketNumber, txtvTicketState;

        public RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtvTicketNumber = itemView.findViewById(R.id.main_line_ticket_number);
            txtvTicketState  = itemView.findViewById(R.id.main_line_state);
        }
    }

    public void update(List<Ticket> tickets, RecyclerView recyclerView) {
      //  public void update(List<Ticket> tickets, ImageView imageView, RecyclerView recyclerView) {
        this.tickets = tickets;
       // imageView.setVisibility(View.GONE);

        recyclerView.setVisibility(View.VISIBLE);
        notifyDataSetChanged();
    }
}
