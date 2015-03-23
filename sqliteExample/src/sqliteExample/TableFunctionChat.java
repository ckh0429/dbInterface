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

public class TableFunctionChat extends TableAttributeServer {
    private static Connection mConnection = null;
    private static Statement mStatement = null;

    public static boolean create(String dbName) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "CREATE TABLE if not exists " + CHAT_TABLE + "(" + CHAT_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CHAT_FROM_WHO + " TEXT NOT NULL, "
                    + CHAT_TO_WHO + " TEXT NOT NULL, " + CHAT_CONTENT + " TEXT, " 
                    + CHAT_TIME + " INTEGER NOT NULL, " + CHAT_IS_GROUP + " BOOLEAN NOT NULL, "+ CHAT_POST_ID + " INTEGER, "
                    + CHAT_READ_STATUS + " TEXT " + ")";
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

        ArrayList<String> chatFromWho = new ArrayList<String>();
        ArrayList<String> chatToWho = new ArrayList<String>();
        ArrayList<String> chatContent = new ArrayList<String>();
        ArrayList<Long> chatTime = new ArrayList<Long>();
        ArrayList<Boolean> chatIsGroup = new ArrayList<Boolean>();
        ArrayList<Long> chatPostID = new ArrayList<Long>();
        ArrayList<String> chatReadStatus = new ArrayList<String>();
        String value = "";

        for (int i = 0; i < rawData.size(); i++) {
            JSONObject jobj = (JSONObject) rawData.get(i);
            chatFromWho.add(jobj.get(CHAT_FROM_WHO).toString());
            chatToWho.add(jobj.get(CHAT_TO_WHO).toString());
            chatContent.add(jobj.get(CHAT_CONTENT).toString());
            chatTime.add((Long)jobj.get(CHAT_TIME));
            chatIsGroup.add((Boolean)jobj.get(CHAT_IS_GROUP));
            chatPostID.add((Long)jobj.get(CHAT_POST_ID));
            chatReadStatus.add(jobj.get(CHAT_READ_STATUS).toString());

            value = value + "('" + chatFromWho.get(i) + "', '" + chatToWho.get(i) + "', '"
                    + chatContent.get(i) + "', '" + chatTime.get(i) + "', '" + chatIsGroup.get(i) + "', '"+ chatPostID.get(i) + "', '"
                    + chatReadStatus.get(i) + "')";

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
            String sql = "INSERT INTO " + CHAT_TABLE + " (" + CHAT_FROM_WHO + "," + CHAT_TO_WHO + ","
                    + CHAT_CONTENT + "," + CHAT_TIME + "," + CHAT_IS_GROUP + ","+ CHAT_POST_ID + ","
                    + CHAT_READ_STATUS + ") " + "VALUES " + value;

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

    public static boolean update(int userChatID, JSONObject rawData) throws FileNotFoundException, IOException,
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
            String sql = "UPDATE " + CHAT_TABLE + " set " + setValue + " where " + CHAT_ID + " = "
                    + userChatID + ";";
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

    public static boolean delete(int userChatID) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".db");
            mConnection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "DELETE from " + CHAT_TABLE + " where " + CHAT_ID + "= " + userChatID + ";";
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
