package com.mock.bookmyticket.ui.bookticket;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.mock.bookmyticket.R;
import com.mock.bookmyticket.data.model.Station;
import com.mock.bookmyticket.data.model.Ticket;
import com.mock.bookmyticket.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookTicketFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookTicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookTicketFragment extends BaseFragment implements BookTicketMvpView, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner atcTvTO ;
    private Spinner atcTVFrom ;
    private TextView tvFare ;

    private OnFragmentInteractionListener mListener;
    private BookTicketPresenter mPresenter;
    private Handler handler;

    public BookTicketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookTicketFragment.
     */

    public static BookTicketFragment newInstance(String param1, String param2) {
        BookTicketFragment fragment = new BookTicketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mPresenter = new BookTicketPresenter(this);
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_book_ticket, container, false);
        setUp(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void setUp(View view) {
        atcTVFrom=(Spinner)view.findViewById(R.id.autoTVFrom);
        atcTvTO =(Spinner)view.findViewById(R.id.autoTVTO);
        tvFare=(TextView)view.findViewById(R.id.tvTotalFare);
        view.findViewById(R.id.btnBookTicket).setOnClickListener(this);
        mPresenter.loadStations();
    }

    @Override
    public void onStationFromSelected(ArrayList<Station> arrayList) {
        ArrayAdapter<Station> adapterTo = new ArrayAdapter<Station>(
                this.getActivity(), android.R.layout.simple_dropdown_item_1line, arrayList);
        atcTvTO.setAdapter(adapterTo);
        tvFare.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStationToSelected(ArrayList<Station> arrayList) {
      /*  ArrayAdapter<Station> adapterFrom = new ArrayAdapter<Station>(
                this.getActivity(), android.R.layout.simple_dropdown_item_1line, arrayList);
        atcTVFrom.setAdapter(adapterFrom);*/
        tvFare.setVisibility(View.INVISIBLE);
    }

    int fare;
    @Override
    public void displayStatus(int fare) {
        this.fare=fare;
        tvFare.setVisibility(View.VISIBLE);
        tvFare.setText("Total Ticket Cost: "+fare);
    }

    @Override
    public void refreshStations(HashMap stations ) {

        ArrayList<Station>  listTo = new ArrayList<Station>(stations.values());
        ArrayList<Station>  listFrom = new ArrayList<Station>(stations.values());
        listTo.remove(0);
        ArrayAdapter<Station> adapterTo = new ArrayAdapter<Station>(
                this.getActivity(), android.R.layout.simple_dropdown_item_1line, listTo);
        atcTvTO.setAdapter(adapterTo);

        ArrayAdapter<Station> adapterFrom = new ArrayAdapter<Station>(
                this.getActivity(), android.R.layout.simple_dropdown_item_1line, listFrom);
        atcTVFrom.setAdapter(adapterFrom);

        atcTVFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              mPresenter.onFromStationSelected(position,(Station) atcTVFrom.getSelectedItem());
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });

        atcTvTO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.onToStationSelected(position,(Station) atcTvTO.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public Ticket getTicketViewModel() {
        Ticket ticket = null;
        String fromText = atcTVFrom.getSelectedItem().toString();
        String toText = atcTvTO.getSelectedItem().toString();
        if (!TextUtils.isEmpty(fromText) && !TextUtils.isEmpty(toText)) {
            ticket = new Ticket();
            ticket.setFromStation(fromText);
            ticket.setToStation(toText);
            ticket.setDateTime(System.currentTimeMillis());
            ticket.setName(getString(R.string.default_user_name));
            ticket.setPurchaseCost(fare);
        }
        return ticket;
    }

    @Override
    public void setBookingStatus(boolean isBookingSuccess) {
        String message = isBookingSuccess ? getString(R.string.booking_success) : getString(R.string.booking_failed);
        Snackbar.make(atcTVFrom, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onBookingStart() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.onTimeLapsed();
            }
        }, 1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBookTicket:
                mPresenter.bookTicketClicked();
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
