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

import sqliteExample.ServerTableAttribute;

public class GroupChatTableFunction extends ServerTableAttribute {
    private static Connection mConnection = null;
    private static Statement mStatement = null;

    public static boolean create(String dbName) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "CREATE TABLE if not exists " + GROUP_CHAT_TABLE + "(" + GROUP_CHAT_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GROUP_CHAT_FROM_WHO + " CHAR(20) NOT NULL, "
                    + GROUP_CHAT_TO_WHO + " CHAR(20) NOT NULL, " + GROUP_CHAT_CONTENT + " TEXT, " + GROUP_CHAT_TIME
                    + " CHAR(20) NOT NULL, " + GROUP_CHAT_READ_STATUS + " TEXT, " + GROUP_CHAT_POST_ID + " CHAR(20) "
                    + ")";
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

        ArrayList<String> groupChatFromWho = new ArrayList<String>();
        ArrayList<String> groupChatToWho = new ArrayList<String>();
        ArrayList<String> groupChatContent = new ArrayList<String>();
        ArrayList<String> groupChatTime = new ArrayList<String>();
        ArrayList<String> groupChatReadStatus = new ArrayList<String>();
        ArrayList<String> groupChatPostID = new ArrayList<String>();
        String value = "";

        for (int i = 0; i < rawData.size(); i++) {
            JSONObject jobj = (JSONObject) rawData.get(i);
            groupChatFromWho.add(jobj.get(GROUP_CHAT_FROM_WHO).toString());
            groupChatToWho.add(jobj.get(GROUP_CHAT_TO_WHO).toString());
            groupChatContent.add(jobj.get(GROUP_CHAT_CONTENT).toString());
            groupChatTime.add(jobj.get(GROUP_CHAT_TIME).toString());
            groupChatReadStatus.add(jobj.get(GROUP_CHAT_READ_STATUS).toString());
            groupChatPostID.add(jobj.get(GROUP_CHAT_POST_ID).toString());

            value = value + "('" + groupChatFromWho.get(i) + "', '" + groupChatToWho.get(i) + "', '"
                    + groupChatContent.get(i) + "', '" + groupChatTime.get(i) + "', '" + groupChatReadStatus.get(i)
                    + "', '" + groupChatPostID.get(i) + "')";

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
            String sql = "INSERT INTO " + GROUP_CHAT_TABLE + " (" + GROUP_CHAT_FROM_WHO + "," + GROUP_CHAT_TO_WHO + ","
                    + GROUP_CHAT_CONTENT + "," + GROUP_CHAT_TIME + "," + GROUP_CHAT_READ_STATUS + ","
                    + GROUP_CHAT_POST_ID + ") " + "VALUES " + value;

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

    public static boolean update(int groupChatID, JSONObject rawData) throws FileNotFoundException, IOException,
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
            String sql = "UPDATE " + GROUP_CHAT_TABLE + " set " + setValue + " where " + GROUP_CHAT_ID + " = "
                    + groupChatID + ";";
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

    public static boolean delete(int groupChatID) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".db");
            mConnection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "DELETE from " + GROUP_CHAT_TABLE + " where " + GROUP_CHAT_ID + "= " + groupChatID + ";";
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
