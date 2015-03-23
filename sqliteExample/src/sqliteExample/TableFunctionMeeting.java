package sqliteExample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import sqliteExample.TableAttributeServer;

public class TableFunctionMeeting extends TableAttributeServer {
    private static Connection mConnection = null;
    private static Statement mStatement = null;

    public static boolean create(String dbName) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "CREATE TABLE if not exists " + MEETING_TABLE + "(" + MEETING_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MEETING_FROM_WHO + " CHAR(20) NOT NULL, "
                    + MEETING_TO_WHO + " CHAR(20) NOT NULL, " + MEETING_NAME + " TEXT, " + MEETING_START_TIME+ " CHAR(20) NOT NULL, " 
                    + MEETING_END_TIME + " CHAR(20) NOT NULL, " + MEETING_STATE + " CHAR(20) NOT NULL, " 
                    + MEETING_NOTES + " TEXT, "+ MEETING_DETAILS + " TEXT, "+ MEETING_WHO_ACCEPT + " TEXT, " 
                    + MEETING_WHO_REJECT + " TEXT " + ")";

            mStatement.executeUpdate(sql);
            mStatement.close();
            mConnection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return (false);
        }
        System.out.println("Table created successfully");
        return true;
    }

    public static boolean insert(JSONArray rawData) {

        ArrayList<String> meetingFromWho = new ArrayList<String>();
        ArrayList<String> meetingToWho = new ArrayList<String>();
        ArrayList<String> meetingName = new ArrayList<String>();
        ArrayList<Long> meetingStartTime = new ArrayList<Long>();
        ArrayList<Long> meetingEndTime = new ArrayList<Long>();
        ArrayList<String> meetingState = new ArrayList<String>();
        ArrayList<String> meetingNotes = new ArrayList<String>();
        ArrayList<String> meetingDetails = new ArrayList<String>();
        ArrayList<String> meetingWhoAccept = new ArrayList<String>();
        ArrayList<String> meetingWhoReject = new ArrayList<String>();
        String value = "";

        for (int i = 0; i < rawData.size(); i++) {
            JSONObject jobj = (JSONObject) rawData.get(i);
            meetingFromWho.add(jobj.get(MEETING_FROM_WHO).toString());
            meetingToWho.add(jobj.get(MEETING_TO_WHO).toString());
            meetingName.add(jobj.get(MEETING_NAME).toString());
            meetingStartTime.add((Long)jobj.get(MEETING_START_TIME));
            meetingEndTime.add((Long)jobj.get(MEETING_END_TIME));
            meetingState.add(jobj.get(MEETING_STATE).toString());
            meetingNotes.add(jobj.get(MEETING_NOTES).toString());
            meetingDetails.add(jobj.get(MEETING_DETAILS).toString());
            meetingWhoAccept.add(jobj.get(MEETING_WHO_ACCEPT).toString());
            meetingWhoReject.add(jobj.get(MEETING_WHO_REJECT).toString());

            value = value + "('" + meetingFromWho.get(i) + "', '" + meetingToWho.get(i) + "', '" + meetingName.get(i)
                    + "', '" + meetingStartTime.get(i) + "', '" + meetingEndTime.get(i) + "', '" + meetingState.get(i)+ "', '" 
                    + meetingNotes.get(i) + "', '"+ meetingDetails.get(i) + "', '"+ meetingWhoAccept.get(i) + "', '" 
                    + meetingWhoReject.get(i) + "')";

            if (i != rawData.size() - 1) {
                value = value + ", ";
            } else {
                value = value + ";";
            }
        }
        System.out.println("value is : " + value);

        try {

            mConnection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".db");
            mConnection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "INSERT INTO " + MEETING_TABLE + " (" + MEETING_FROM_WHO + "," + MEETING_TO_WHO + ","
                    + MEETING_NAME + "," + MEETING_START_TIME + "," + MEETING_END_TIME + "," + MEETING_STATE + ","
                    + MEETING_NOTES + ","+ MEETING_DETAILS + ","+ MEETING_WHO_ACCEPT + "," 
                    + MEETING_WHO_REJECT + ") " + "VALUES " + value;

            mStatement.executeUpdate(sql);
            mStatement.close();
            mConnection.commit();
            mConnection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
        return true;
    }

    public static boolean update(int meetingID, JSONObject rawData) throws FileNotFoundException, IOException,
            ParseException {
        String setValue = "";
        for (@SuppressWarnings("rawtypes")
        Iterator iterator = rawData.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            setValue = setValue + key + " = " + "'" + rawData.get(key) + "',";
        }
        setValue = setValue.substring(0, setValue.length() - 1);
        System.out.println("setValue is : " + setValue);
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".db");
            mConnection.setAutoCommit(false);
            System.out.println("update Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "UPDATE " + MEETING_TABLE + " set " + setValue + " where " + MEETING_ID + " = " + meetingID
                    + ";";
            System.out.println("test : " + sql);
            mStatement.executeUpdate(sql);
            mConnection.commit();
            mStatement.close();
            mConnection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return true;
    }

    public static boolean delete(int meetingID) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".db");
            mConnection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "DELETE from " + MEETING_TABLE + " where " + MEETING_ID + "= " + meetingID + ";";
            mStatement.executeUpdate(sql);
            mConnection.commit();
            mStatement.close();
            mConnection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return true;
    }
}
