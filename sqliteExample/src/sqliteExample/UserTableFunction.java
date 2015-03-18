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

public class UserTableFunction extends ServerTableAttribute {
    private static Connection mConnection = null;
    private static Statement mStatement = null;

    public static boolean create(String dbName) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "CREATE TABLE if not exists " + USER_TABLE + "(" + USER_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " CHAR(20) NOT NULL, " + USER_MOBILE
                    + " CHAR(20) NOT NULL, " + USER_EXTENSION + " CHAR(20) NOT NULL, " + USER_EMAIL
                    + " CHAR(30) NOT NULL UNIQUE, " + USER_DEPT + " CHAR(20) NOT NULL, " + USER_CONTACTS + " TEXT, "
                    + USER_INVITE + " TEXT, " + USER_BE_INVITED + " TEXT, " + USER_GROUP + " TEXT, "
                    + USER_GROUP_BE_INVITED + " TEXT, " + USER_PICTURE + " TEXT " + ")";
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

        ArrayList<String> userName = new ArrayList<String>();
        ArrayList<String> userMobile = new ArrayList<String>();
        ArrayList<String> userEmail = new ArrayList<String>();
        ArrayList<String> userExtension = new ArrayList<String>();
        ArrayList<String> userDept = new ArrayList<String>();
        ArrayList<String> userContacts = new ArrayList<String>();
        ArrayList<String> userInvite = new ArrayList<String>();
        ArrayList<String> userBeInvited = new ArrayList<String>();
        ArrayList<String> userGroup = new ArrayList<String>();
        ArrayList<String> userGroupBeInvited = new ArrayList<String>();
        ArrayList<String> userPicture = new ArrayList<String>();
        String value = "";

        for (int i = 0; i < rawData.size(); i++) {
            JSONObject jobj = (JSONObject) rawData.get(i);
            userName.add(jobj.get(USER_NAME).toString());
            userMobile.add(jobj.get(USER_MOBILE).toString());
            userExtension.add(jobj.get(USER_EXTENSION).toString());
            userEmail.add(jobj.get(USER_EMAIL).toString());
            userDept.add(jobj.get(USER_DEPT).toString());
            userContacts.add(jobj.get(USER_CONTACTS).toString());
            userInvite.add(jobj.get(USER_INVITE).toString());
            userBeInvited.add(jobj.get(USER_BE_INVITED).toString());
            userGroup.add(jobj.get(USER_GROUP).toString());
            userGroupBeInvited.add(jobj.get(USER_GROUP_BE_INVITED).toString());
            userPicture.add(jobj.get(USER_PICTURE).toString());

            value = value + "('" + userName.get(i) + "', '" + userMobile.get(i) + "', '" + userExtension.get(i)
                    + "', '" + userEmail.get(i) + "', '" + userDept.get(i) + "', '" + userContacts.get(i) + "', '"
                    + userInvite.get(i) + "', '" + userBeInvited.get(i) + "', '" + userGroup.get(i) + "', '"
                    + userGroupBeInvited.get(i) + "', '" + userPicture.get(i) + "' )";

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
            String sql = "INSERT INTO " + USER_TABLE + " (" + USER_NAME + "," + USER_MOBILE + "," + USER_EXTENSION
                    + "," + USER_EMAIL + "," + USER_DEPT + "," + USER_CONTACTS + "," + USER_INVITE + ","
                    + USER_BE_INVITED + "," + USER_GROUP + "," + USER_GROUP_BE_INVITED + "," + USER_PICTURE + ") "
                    + "VALUES " + value;

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

    public static boolean update(int userID, JSONObject rawData) throws FileNotFoundException,
            IOException, ParseException {
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
            String sql = "UPDATE " + USER_TABLE + " set " + setValue + " where " + USER_ID + " = " + userID + ";";
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

    public static boolean delete(int userID) {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".db");
            mConnection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            String sql = "DELETE from " + USER_TABLE + " where " + USER_ID + "= " + userID + ";";
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
