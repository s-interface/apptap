package fu.berlin.apptap.data;

public class AppTabDbSchema {
    public static final class EventTable {
        public static final String NAME = "events";

        public static final class Cols {
            //            public static final String UUID = "uuid";
            public static final String INDEX_ID = "_id";

            public static final String APPID = "app_id";
            public static final String TIME = "ev_time";
            public static final String NAME = "ev_name";
            public static final String ORIGIN = "ev_origin";
            public static final String PARAMS = "ev_params";
        }

    }
}
