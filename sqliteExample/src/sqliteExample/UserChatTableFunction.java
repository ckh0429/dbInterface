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

public class UserChatTableFunction extends ServerTableAttribute {
    private static Connection mConnection = null;
    private static Statement mStatement = null;

    public static boolean create(String dbName) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "CREATE TABLE if not exists " + USER_CHAT_TABLE + "(" + USER_CHAT_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_CHAT_FROM_WHO + " CHAR(20) NOT NULL, "
                    + USER_CHAT_TO_WHO + " CHAR(20) NOT NULL, " + USER_CHAT_CONTENT + " TEXT, " + USER_CHAT_TIME
                    + " CHAR(20) NOT NULL, " + USER_CHAT_READ_STATUS + " TEXT " + ")";
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

        ArrayList<String> userChatFromWho = new ArrayList<String>();
        ArrayList<String> userChatToWho = new ArrayList<String>();
        ArrayList<String> userChatContent = new ArrayList<String>();
        ArrayList<String> userChatTime = new ArrayList<String>();
        ArrayList<String> userChatReadStatus = new ArrayList<String>();
        String value = "";

        for (int i = 0; i < rawData.size(); i++) {
            JSONObject jobj = (JSONObject) rawData.get(i);
            userChatFromWho.add(jobj.get(USER_CHAT_FROM_WHO).toString());
            userChatToWho.add(jobj.get(USER_CHAT_TO_WHO).toString());
            userChatContent.add(jobj.get(USER_CHAT_CONTENT).toString());
            userChatTime.add(jobj.get(USER_CHAT_TIME).toString());
            userChatReadStatus.add(jobj.get(USER_CHAT_READ_STATUS).toString());

            value = value + "('" + userChatFromWho.get(i) + "', '" + userChatToWho.get(i) + "', '"
                    + userChatContent.get(i) + "', '" + userChatTime.get(i) + "', '" + userChatReadStatus.get(i) + "')";

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
            String sql = "INSERT INTO " + USER_CHAT_TABLE + " (" + USER_CHAT_FROM_WHO + "," + USER_CHAT_TO_WHO + ","
                    + USER_CHAT_CONTENT + "," + USER_CHAT_TIME + "," + USER_CHAT_READ_STATUS + ") " + "VALUES " + value;

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
            String sql = "UPDATE " + USER_CHAT_TABLE + " set " + setValue + " where " + USER_CHAT_ID + " = "
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
            String sql = "DELETE from " + USER_CHAT_TABLE + " where " + USER_CHAT_ID + "= " + userChatID + ";";
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
