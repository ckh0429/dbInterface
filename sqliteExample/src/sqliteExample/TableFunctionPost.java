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

public class TableFunctionPost extends TableAttributeServer {
    private static Connection mConnection = null;
    private static Statement mStatement = null;

    public static boolean create(String dbName) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "CREATE TABLE if not exists " + POST_TABLE + "(" + POST_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + POST_FROM_WHO + " TEXT NOT NULL, " + POST_TO_WHO
                    + " TEXT NOT NULL, " + POST_SUBJECT + " TEXT, " + POST_CONTENT + " TEXT, " + POST_TIME
                    + " INTEGER NOT NULL, " + POST_READ_STATUS + " TEXT " + ")";
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

        ArrayList<String> postFromWho = new ArrayList<String>();
        ArrayList<String> postToWho = new ArrayList<String>();
        ArrayList<String> postSubject = new ArrayList<String>();
        ArrayList<String> postContent = new ArrayList<String>();
        ArrayList<Long> postTime = new ArrayList<Long>();
        ArrayList<String> postReadStatus = new ArrayList<String>();
        String value = "";

        for (int i = 0; i < rawData.size(); i++) {
            JSONObject jobj = (JSONObject) rawData.get(i);
            postFromWho.add(jobj.get(POST_FROM_WHO).toString());
            postToWho.add(jobj.get(POST_TO_WHO).toString());
            postSubject.add(jobj.get(POST_SUBJECT).toString());
            postContent.add(jobj.get(POST_CONTENT).toString());
            postTime.add((Long)jobj.get(POST_TIME));
            postReadStatus.add(jobj.get(POST_READ_STATUS).toString());

            value = value + "('" + postFromWho.get(i) + "', '" + postToWho.get(i) + "', '" + postSubject.get(i)
                    + "', '" + postContent.get(i) + "', '" + postTime.get(i) + "', '" + postReadStatus.get(i) + "')";

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
            String sql = "INSERT INTO " + POST_TABLE + " (" + POST_FROM_WHO + "," + POST_TO_WHO + "," + POST_SUBJECT
                    + "," + POST_CONTENT + "," + POST_TIME + "," + POST_READ_STATUS + ") " + "VALUES " + value;

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

    public static boolean update(int postID, JSONObject rawData) throws FileNotFoundException, IOException,
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
            String sql = "UPDATE " + POST_TABLE + " set " + setValue + " where " + POST_ID + " = " + postID + ";";
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

    public static boolean delete(int postID) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".db");
            mConnection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "DELETE from " + POST_TABLE + " where " + POST_ID + "= " + postID + ";";
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
