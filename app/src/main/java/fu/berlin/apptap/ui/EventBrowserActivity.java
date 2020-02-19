package fu.berlin.apptap.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import fu.berlin.apptap.R;
import fu.berlin.apptap.model.EventStash;

public class EventBrowserActivity extends AppCompatActivity implements EventListFragment.OnListFragmentInteractionListener {

    public static final String EXTRA_EVENT_ID =
            "fu.berlin.apptap.event_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_browser);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countString = String.valueOf(EventStash.getInstance(EventBrowserActivity.this).getEventsCount());
                Snackbar.make(view, "count: " + countString, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Context packageContext, int eventID) {
        Intent intent = new Intent(packageContext, EventActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, eventID);
        return intent;
    }

    @Override
    public void onListFragmentInteraction(int eventID) {

        Intent intent = newIntent(this, eventID);
        startActivity(intent);

//        String eventString = "appID='" +
//                event.getAppId() +
//                "', name='" +
//                event.getName() +
//                "', origin='" +
//                event.getOrigin() +
//                "', time='" +
//                Utillity.getDateTimeFromTimestamp(event.getTime(), null) +
//                "', params=" +
//                event.getParams();
//        View view = findViewById(R.id.view_main_coordinator);
//        Snackbar snackbar = Snackbar.make(view, eventString, 6000)
//                .setAction("Action", null);
//        View snackbarView = snackbar.getView();
//        TextView textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
//        textView.setMaxLines(5);
//        snackbar.show();
    }
}
