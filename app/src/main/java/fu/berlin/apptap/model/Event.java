package fu.berlin.apptap.model;

import java.util.UUID;

public class Event {
    private int mEventId;
    private String mAppId;
    private String mTime;
    private String mName;
    private String mOrigin;
    private String mParams;

//    public Event() {
//        this(UUID.randomUUID());
//    }

    public Event(int index_id) {
        mEventId = index_id;
    }

    public int getEventId() {
        return mEventId;
    }

    public String getAppId() {
        return mAppId;
    }

    public void setAppId(String mAppId) {
        this.mAppId = mAppId;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getOrigin() {
        return mOrigin;
    }

    public void setOrigin(String mOrigin) {
        this.mOrigin = mOrigin;
    }

    public String getParams() {
        return mParams;
    }

    public void setParams(String mParams) {
        this.mParams = mParams;
    }
}
