package com.mock.bookmyticket.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mock.bookmyticket.R;
import com.mock.bookmyticket.data.model.Ticket;
import com.mock.bookmyticket.ui.base.BaseFragment;
import com.mock.bookmyticket.util.FormattingUtils;

import java.util.List;

public class TicketHistoryFragment extends BaseFragment implements TicketHistoryView {

    private TicketHistoryPresenter ticketHistoryPresenter;

    public TicketHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticketHistoryPresenter = new TicketHistoryPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_history, container, false);
        setUp(view);
        return view;
    }

    @Override
    public void shareTicket(Ticket ticket) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Metro Ticket");
        intent.putExtra(Intent.EXTRA_TEXT, "Your Metro Ticket# " + ticket.getTicketId() +
                "\n From: " + ticket.getFromStation() +
                " \n To: " + ticket.getToStation() +
                " \n Total Fare: " + ticket.getPurchaseCost() +
                "\n \n #From Book My Ticket #");
        startActivity(Intent.createChooser(intent, "How do you want to share?"));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(@StringRes int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(@StringRes int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return true;
    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    protected void setUp(View view) {
        RecyclerView historyRecyclerView = (RecyclerView) view.findViewById(R.id.rvTicketsHistory);
        setUpRecyclerView(historyRecyclerView);
        historyRecyclerView.setAdapter(new TicketHistoryAdapter(ticketHistoryPresenter.getBookedTicketHistory()));
    }

    private void setUpRecyclerView(RecyclerView recyclerView) {
        recyclerView.setItemAnimator(null);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    class TicketHistoryAdapter extends RecyclerView.Adapter {

        private final List<Ticket> ticketList;

        TicketHistoryAdapter(List<Ticket> ticketList) {
            this.ticketList = ticketList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_ticket_history, parent, false);
            return new TicketViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final Ticket ticket = ticketList.get(position);
            TicketViewHolder ticketViewHolder = (TicketViewHolder) holder;
            ticketViewHolder.tvId.setText("Ticket#: " + ticket.getTicketId());
            ticketViewHolder.tvName.setText("By: " + ticket.getName());
            ticketViewHolder.tvFromStation.setText("From: " + ticket.getFromStation());
            ticketViewHolder.tvToStation.setText("To: " + ticket.getToStation());
            ticketViewHolder.tvCost.setText("Amount: " + ticket.getPurchaseCost());
            ticketViewHolder.tvDateTime.setText(FormattingUtils.getFormattedDate(ticket.getDateTime()));
            ticketViewHolder.imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ticketHistoryPresenter.shareTicket(ticket);
                }
            });

        }

        @Override
        public int getItemCount() {
            return null == ticketList ? 0 : ticketList.size();
        }

        private class TicketViewHolder extends RecyclerView.ViewHolder {
            TextView tvId;
            TextView tvName;
            TextView tvFromStation;
            TextView tvToStation;
            TextView tvCost;
            TextView tvDateTime;
            ImageView imgShare;

            TicketViewHolder(View view) {
                super(view);
                tvId = (TextView) view.findViewById(R.id.tvId);
                tvName = (TextView) view.findViewById(R.id.tvName);
                tvFromStation = (TextView) view.findViewById(R.id.tvFromStation);
                tvToStation = (TextView) view.findViewById(R.id.tvToStation);
                tvCost = (TextView) view.findViewById(R.id.tvCost);
                tvDateTime = (TextView) view.findViewById(R.id.tvDateTime);
                imgShare = (ImageView) view.findViewById(R.id.imageView);
            }
        }
    }
}
