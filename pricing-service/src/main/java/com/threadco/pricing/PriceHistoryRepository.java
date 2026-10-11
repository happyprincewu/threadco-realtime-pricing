package com.threadco.pricing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/** Price history used by the pricing agent ("what happened at the last comparable event"). */
public class PriceHistoryRepository {

    private static final String DB_URL = System.getenv("PRICE_HISTORY_DB_URL");
    private static final String DB_USER = System.getenv("PRICE_HISTORY_DB_USER");
    private static final String DB_PASSWORD = System.getenv("PRICE_HISTORY_DB_PASSWORD");

    public ResultSet outcomesForEvent(String eventName) throws Exception {
        Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement s = c.createStatement();
        // builds SQL from a request parameter
        return s.executeQuery("SELECT day, demand_vs_normal, price_change, units_change "
                + "FROM event_outcomes WHERE event = '" + eventName + "' ORDER BY day");
    }
}
