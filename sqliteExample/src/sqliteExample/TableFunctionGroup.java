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

public class TableFunctionGroup extends TableAttributeServer {
    private static Connection mConnection = null;
    private static Statement mStatement = null;

    public static boolean create(String dbName) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "CREATE TABLE if not exists " + GROUP_TABLE + "(" + GROUP_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GROUP_NAME + " CHAR(10) NOT NULL, " + GROUP_MEMBERS
                    + " TEXT, " + GROUP_INVITERS + " TEXT, " + GROUP_PICTURE + " TEXT " + ")";
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

        ArrayList<String> groupName = new ArrayList<String>();
        ArrayList<String> groupMembers = new ArrayList<String>();
        ArrayList<String> groupInviters = new ArrayList<String>();
        ArrayList<String> groupPicture = new ArrayList<String>();
        String value = "";

        for (int i = 0; i < rawData.size(); i++) {
            JSONObject jobj = (JSONObject) rawData.get(i);
            groupName.add(jobj.get(GROUP_NAME).toString());
            groupMembers.add(jobj.get(GROUP_MEMBERS).toString());
            groupInviters.add(jobj.get(GROUP_INVITERS).toString());
            groupPicture.add(jobj.get(GROUP_PICTURE).toString());

            value = value + "('" + groupName.get(i) + "', '" + groupMembers.get(i) + "', '" + groupInviters.get(i)
                    + "', '" + groupPicture.get(i) + "' )";

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
            String sql = "INSERT INTO " + GROUP_TABLE + " (" + GROUP_NAME + "," + GROUP_MEMBERS + "," + GROUP_INVITERS
                    + "," + GROUP_PICTURE + ") " + "VALUES " + value;

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

    public static boolean update(int groupID, JSONObject rawData) throws FileNotFoundException, IOException,
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
            String sql = "UPDATE " + GROUP_TABLE + " set " + setValue + " where " + GROUP_ID + " = " + groupID + ";";
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

    public static boolean delete(int groupID) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".db");
            mConnection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "DELETE from " + GROUP_TABLE + " where " + GROUP_ID + "= " + groupID + ";";
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
