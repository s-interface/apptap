package fu.berlin.apptap.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fu.berlin.apptap.R;
import fu.berlin.apptap.model.Event;
import fu.berlin.apptap.ui.EventFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Event} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyEventRecyclerViewAdapter extends RecyclerView.Adapter<MyEventRecyclerViewAdapter.EventListRowViewHolder> {

    private final List<Event> mEventList;
    private final OnListFragmentInteractionListener mListener;

    MyEventRecyclerViewAdapter(List<Event> events, OnListFragmentInteractionListener listener) {
        mEventList = events;
        mListener = listener;
    }

    @Override
    public EventListRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false);
        return new EventListRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventListRowViewHolder holder, int position) {
        final Event event = mEventList.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    public class EventListRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final View mView;
        private final TextView mNameTextView;
        private final TextView mContentTextView;
        private Event mEvent;

        public EventListRowViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);
            mView = view;
            mNameTextView = view.findViewById(R.id.event_name);
            mContentTextView = view.findViewById(R.id.event_content);
        }

        public void bind(Event event) {
            mEvent = event;
            mNameTextView.setText(mEvent.getName());
            mContentTextView.setText(mEvent.getAppId());

        }

        @Override
        public void onClick(View v) {
            mListener.onListFragmentInteraction(mEvent);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameTextView.getText() + "'";
        }
    }
}
