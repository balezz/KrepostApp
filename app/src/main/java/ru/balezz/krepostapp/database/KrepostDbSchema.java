package ru.balezz.krepostapp.database;

public class KrepostDbSchema {
    public static final class RoomsTable {
        public static final String NAME = "rooms";
        public static final class Cols {
            public static final String ID = "id";
            public static final String TITLE = "title";
            public static final String DETAIL = "detail";
            public static final String IMAGE = "image";
            public static final String SENSORS = "sensors";
        }
    }

    public static final class SensorsTable {
        public static final String NAME = "sensor";
        public static final class Cols {
            public static final String ID = "id";
            public static final String TITLE = "title";
            public static final String DETAIL = "detail";
            public static final String IMAGE = "image";
        }
    }

    public static final class NotifiesTable {
        public static final String NAME = "notify";
        public static final class Cols {
            public static final String ID = "id";
            public static final String TITLE = "title";
            public static final String DETAIL = "detail";
            public static final String DATE = "date";
            public static final String IMAGE = "image";
        }
    }
}
