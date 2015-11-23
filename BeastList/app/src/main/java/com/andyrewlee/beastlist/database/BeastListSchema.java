package com.andyrewlee.beastlist.database;

/**
 * Created by dev1 on 11/23/15.
 */
public class BeastListSchema {
    public static final class BeastsTable {
        public static final String NAME = "beasts";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String OBJECTIVE = "objective";
            public static final String CREATED_AT = "created_at";
            public static final String BEASTED = "beasted";
        }
    }
}
