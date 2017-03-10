package com.rappi.androidtestrappi.Implementation.DashBoard.Controller;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rappi.androidtestrappi.App.Base.MyApp;
import com.rappi.androidtestrappi.Implementation.Main.Controller.MainActivity;
import com.rappi.androidtestrappi.Implementation.Reddits.Controller.RedditsListFragment;
import com.rappi.androidtestrappi.R;
import com.rappi.androidtestrappi.Utils.InternetConection.ConnectionErrorFragment;
import com.rappi.androidtestrappi.Utils.InternetConection.InternetStatusProvider;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashBoardFragment extends Fragment {

    private CardView cardViewErroConnection;
    private InternetStatusProvider internetStatusProvider;
    private Button buttonShowList;
    private BroadcastReceiver mConnectionChangeReceiver;
    private OnFragmentInteractionListener mListener;
    ActionBar actionBar;
    Activity mActivity;

    public DashBoardFragment() {
        super();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        internetStatusProvider = new InternetStatusProvider(MyApp.getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);

        actionBar = ((MainActivity) getActivity()).getSupportActionBar();

        this.cardViewErroConnection = (CardView) view.findViewById(R.id.cardViewErroConnection);
        this.buttonShowList = (Button) view.findViewById(R.id.buttonShowList);

        this.buttonShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment  fragment = new RedditsListFragment();

                String  title = getResources().getString(R.string.menu_list);

                if (fragment != null) {
                    actionBar.setTitle(title);
                    FragmentTransaction fragmentTransaction = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }
            }
        });

        return view;

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
