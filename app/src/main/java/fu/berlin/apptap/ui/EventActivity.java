package fu.berlin.apptap.ui;

import androidx.appcompat.app.AppCompatActivity;
import fu.berlin.apptap.R;
import fu.berlin.apptap.model.Event;
import fu.berlin.apptap.model.EventStash;
import fu.berlin.apptap.util.Utillity;

import android.os.Bundle;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    Event mEvent;

    TextView mEventNameTextView;
    TextView mEventDetailTextView;
    TextView mEventContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mEventNameTextView = findViewById(R.id.activity_event_name_textview);
        mEventDetailTextView = findViewById(R.id.activity_event_detail_textView);
        mEventContentTextView = findViewById(R.id.activity_event_content_textview);

        int eventID = getIntent().getIntExtra(EventBrowserActivity.EXTRA_EVENT_ID, -1);
        mEvent = EventStash.getInstance(this).getEvent(eventID);

        if (mEvent != null) {
            mEventNameTextView.setText(mEvent.getName());
            String detail = Utillity.getDateTimeFromTimestamp(mEvent.getTime(), null) + "\n" +
                    "\n" +
                    "AppId: " + mEvent.getAppId() + "\n" +
                    "Origin: " + mEvent.getOrigin();
            mEventDetailTextView.setText(detail);
            mEventContentTextView.setText(mEvent.getParams());
        }
    }
}
