package fu.berlin.apptap.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fu.berlin.apptap.R;
import fu.berlin.apptap.model.Event;
import fu.berlin.apptap.ui.EventFragment.OnListFragmentInteractionListener;
import fu.berlin.apptap.util.Utillity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Event} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyEventRecyclerViewAdapter extends RecyclerView.Adapter<MyEventRecyclerViewAdapter.EventViewHolder> {

    private final List<Event> mEventList;
    private final OnListFragmentInteractionListener mListener;

    MyEventRecyclerViewAdapter(List<Event> events, OnListFragmentInteractionListener listener) {
        mEventList = events;
        mListener = listener;
    }

    //Called when creating a ViewHolder instance
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false);
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

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final View mView;
        private final TextView mTimeTextView;
        private final TextView mNameTextView;
        private Event mEvent;

        public EventViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);
            mView = view;
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

        @Override
        public String toString() {
            return super.toString() + " '" + mTimeTextView.getText() + "'";
        }
    }
}
