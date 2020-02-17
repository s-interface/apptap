package fu.berlin.apptap.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import fu.berlin.apptap.R;
import fu.berlin.apptap.model.Event;
import fu.berlin.apptap.model.EventStash;
import fu.berlin.apptap.util.Utillity;

import static fu.berlin.apptap.provider.EventReceiverProvider.LOG_TAG;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class EventListFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private RecyclerView mEventRecyclerView;
    private EventAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        mEventRecyclerView = view.findViewById(R.id.events_recycler_view);
        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSwipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                updateUI();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        updateUI();

        return view;
    }

    private void updateUI() {
        List<Event> events = EventStash.getInstance(getActivity()).getEvents();
        mAdapter = new EventAdapter(events);
        mEventRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Event mEvent;

        private final TextView mTimeTextView;
        private final TextView mNameTextView;

        public EventViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);

            mTimeTextView = view.findViewById(R.id.event_time);
            mNameTextView = view.findViewById(R.id.event_name);
        }

        public void bind(Event event) {
            mEvent = event;
            mTimeTextView.setText(Utillity.getDateTimeFromTimestamp(event.getTime(), null));
            mNameTextView.setText(mEvent.getName());
        }

        @Override
        public void onClick(View v) {
            mListener.onListFragmentInteraction(mEvent);
        }
    }

    private class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

        private final List<Event> mEventList;

        EventAdapter(List<Event> events) {
            mEventList = events;
        }

        //Called when creating a ViewHolder instance
        @Override
        public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_event, parent, false);
            return new EventViewHolder(view);
        }

        //Called when binding a ViewHolder instance to data
        @Override
        public void onBindViewHolder(final EventViewHolder holder, int position) {
            final Event event = mEventList.get(position);
            holder.bind(event);
        }

        @Override
        public int getItemCount() {
            return mEventList.size();
        }

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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Event event);
    }
}
