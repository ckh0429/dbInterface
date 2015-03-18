package sqliteExample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import sqliteExample.ServerTableAttribute;

public class APIFunction extends ServerTableAttribute {

    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
        String[] dbName = { DB_NAME };
        createDB(dbName);
        //testUserTableFunction();
        testUserChatTableFunction();
        testGroupTableFunction();
        testGroupChatTableFunction();
        testPostTableFunction();
        testMeetingTableFunction();
    }

    public static boolean createDB(String[] dbName) {
        // TODO is DB exist?
        try {
            Connection c = null;
            for (int dbCount = 0; dbCount < dbName.length; dbCount++) {
                c = DriverManager.getConnection("jdbc:sqlite:" + dbName[dbCount] + ".db");
            }
            return (true);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return (false);
        }
    }


    public static JSONArray createUserTableTestJson() {
        JSONArray insertDataJsa = new JSONArray();
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(USER_NAME, "KH");
        insertDataJob.put(USER_MOBILE, "0933913329");
        insertDataJob.put(USER_EXTENSION, "5173");
        insertDataJob.put(USER_EMAIL, "ckh0429@gmail.com");
        insertDataJob.put(USER_DEPT, "Acer");
        insertDataJob.put(USER_CONTACTS, "Acer");
        insertDataJob.put(USER_INVITE, "Acer");
        insertDataJob.put(USER_BE_INVITED, "Acer");
        insertDataJob.put(USER_GROUP, "Acer");
        insertDataJob.put(USER_GROUP_BE_INVITED, "Acer");
        insertDataJob.put(USER_PICTURE, "Acer");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray1 is :" + insertDataJsa.toJSONString());
        insertDataJob = new JSONObject();
        insertDataJob.put(USER_NAME, "paul");
        insertDataJob.put(USER_MOBILE, "0933913329");
        insertDataJob.put(USER_EXTENSION, "5122");
        insertDataJob.put(USER_EMAIL, "paul2@gmail.com");
        insertDataJob.put(USER_DEPT, "intel");
        insertDataJob.put(USER_CONTACTS, "intel");
        insertDataJob.put(USER_INVITE, "intel");
        insertDataJob.put(USER_BE_INVITED, "intel");
        insertDataJob.put(USER_GROUP, "intel");
        insertDataJob.put(USER_GROUP_BE_INVITED, "intel");
        insertDataJob.put(USER_PICTURE, "intel");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray2 is :" + insertDataJsa.toJSONString());
        return insertDataJsa;
    }
    public static JSONObject createUserTableUpdateTestJsonObj() {
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(USER_NAME, "KH1");
        insertDataJob.put(USER_MOBILE, "09339133291");
        insertDataJob.put(USER_EXTENSION, "5173");
        insertDataJob.put(USER_EMAIL, "ckh04291@gmail.com");

        insertDataJob.put(USER_INVITE, "Acer2");
        insertDataJob.put(USER_BE_INVITED, "Acer2");
        insertDataJob.put(USER_GROUP, "Acer2");
        insertDataJob.put(USER_GROUP_BE_INVITED, "Acer2");
        insertDataJob.put(USER_PICTURE, "Acer2");
        System.out.println("jsonObj is :" + insertDataJob.toJSONString());
        
        return insertDataJob;
    }
    public static void testUserTableFunction() throws FileNotFoundException, IOException, ParseException{
        UserTableFunction.create(DB_NAME);
        UserTableFunction.insert(createUserTableTestJson());
        UserTableFunction.update(1,createUserTableUpdateTestJsonObj());
        UserTableFunction.delete(1);
    }
    
    public static JSONArray createGroupTableTestJson() {
        JSONArray insertDataJsa = new JSONArray();
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(GROUP_NAME, "KH");
        insertDataJob.put(GROUP_MEMBERS, "Acer");
        insertDataJob.put(GROUP_INVITERS, "Acer");
        insertDataJob.put(GROUP_PICTURE, "Acer");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray1 is :" + insertDataJsa.toJSONString());
        insertDataJob = new JSONObject();
        insertDataJob.put(GROUP_NAME, "paul");
        insertDataJob.put(GROUP_MEMBERS, "intel");
        insertDataJob.put(GROUP_INVITERS, "intel");
        insertDataJob.put(GROUP_PICTURE, "intel");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray2 is :" + insertDataJsa.toJSONString());
        return insertDataJsa;
    }
    public static JSONObject createGroupTableUpdateTestJsonObj() {
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(GROUP_NAME, "KH1");
        insertDataJob.put(GROUP_MEMBERS, "intel");
        insertDataJob.put(GROUP_INVITERS, "Acer2");
        insertDataJob.put(GROUP_PICTURE, "Acer2");
        System.out.println("jsonObj is :" + insertDataJob.toJSONString());
        
        return insertDataJob;
    }
    public static void testGroupTableFunction() throws FileNotFoundException, IOException, ParseException{
        GroupTableFunction.create(DB_NAME);
        GroupTableFunction.insert(createGroupTableTestJson());
        GroupTableFunction.update(1,createGroupTableUpdateTestJsonObj());
        GroupTableFunction.delete(1);
    }

    public static JSONArray createUserChatTableTestJson() {
        JSONArray insertDataJsa = new JSONArray();
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(USER_CHAT_FROM_WHO, "KH");
        insertDataJob.put(USER_CHAT_TO_WHO, "Acer");
        insertDataJob.put(USER_CHAT_CONTENT, "Acer");
        insertDataJob.put(USER_CHAT_TIME, "Acer");
        insertDataJob.put(USER_CHAT_READ_STATUS, "Acer");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray1 is :" + insertDataJsa.toJSONString());
        insertDataJob = new JSONObject();
        insertDataJob.put(USER_CHAT_FROM_WHO, "paup");
        insertDataJob.put(USER_CHAT_TO_WHO, "Acer33");
        insertDataJob.put(USER_CHAT_CONTENT, "Acer33");
        insertDataJob.put(USER_CHAT_TIME, "Acer33");
        insertDataJob.put(USER_CHAT_READ_STATUS, "Acer33");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray2 is :" + insertDataJsa.toJSONString());
        return insertDataJsa;
    }
    public static JSONObject createUserChatTableUpdateTestJsonObj() {
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(USER_CHAT_FROM_WHO, "KH");
        insertDataJob.put(USER_CHAT_TO_WHO, "Acer");
        insertDataJob.put(USER_CHAT_CONTENT, "Acer");
        insertDataJob.put(USER_CHAT_TIME, "Acer");
        insertDataJob.put(USER_CHAT_READ_STATUS, "Acer");
        System.out.println("jsonObj is :" + insertDataJob.toJSONString());
        
        return insertDataJob;
    }
    
    public static void testUserChatTableFunction() throws FileNotFoundException, IOException, ParseException{
        UserChatTableFunction.create(DB_NAME);
        UserChatTableFunction.insert(createUserChatTableTestJson());
        UserChatTableFunction.update(1,createUserChatTableUpdateTestJsonObj());
        UserChatTableFunction.delete(1);
    }
    
    public static JSONArray createGroupChatTableTestJson() {
        JSONArray insertDataJsa = new JSONArray();
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(GROUP_CHAT_FROM_WHO, "KH");
        insertDataJob.put(GROUP_CHAT_TO_WHO, "Acer");
        insertDataJob.put(GROUP_CHAT_CONTENT, "Acer");
        insertDataJob.put(GROUP_CHAT_TIME, "Acer");
        insertDataJob.put(GROUP_CHAT_READ_STATUS, "Acer");
        insertDataJob.put(GROUP_CHAT_POST_ID, "Acer");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray1 is :" + insertDataJsa.toJSONString());
        insertDataJob = new JSONObject();
        insertDataJob.put(GROUP_CHAT_FROM_WHO, "paup");
        insertDataJob.put(GROUP_CHAT_TO_WHO, "Acer33");
        insertDataJob.put(GROUP_CHAT_CONTENT, "Acer33");
        insertDataJob.put(GROUP_CHAT_TIME, "Acer33");
        insertDataJob.put(GROUP_CHAT_READ_STATUS, "Acer33");
        insertDataJob.put(GROUP_CHAT_POST_ID, "Acer");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray2 is :" + insertDataJsa.toJSONString());
        return insertDataJsa;
    }
    public static JSONObject createGroupChatTableUpdateTestJsonObj() {
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(GROUP_CHAT_FROM_WHO, "KH");
        insertDataJob.put(GROUP_CHAT_TO_WHO, "Acer");
        insertDataJob.put(GROUP_CHAT_CONTENT, "Acer");
        insertDataJob.put(GROUP_CHAT_TIME, "Acer");
        insertDataJob.put(GROUP_CHAT_READ_STATUS, "Acer");
        insertDataJob.put(GROUP_CHAT_POST_ID, "Acer");
        System.out.println("jsonObj is :" + insertDataJob.toJSONString());
        
        return insertDataJob;
    }
    public static void testGroupChatTableFunction() throws FileNotFoundException, IOException, ParseException{
        GroupChatTableFunction.create(DB_NAME);
        GroupChatTableFunction.insert(createGroupChatTableTestJson());
        GroupChatTableFunction.update(1,createGroupChatTableUpdateTestJsonObj());
        GroupChatTableFunction.delete(1);
    }
    
    public static JSONArray createPostTableTestJson() {
        JSONArray insertDataJsa = new JSONArray();
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(POST_FROM_WHO, "KH");
        insertDataJob.put(POST_TO_WHO, "Acer");
        insertDataJob.put(POST_SUBJECT, "Acer");
        insertDataJob.put(POST_CONTENT, "Acer");
        insertDataJob.put(POST_TIME, "Acer");
        insertDataJob.put(POST_READ_STATUS, "Acer");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray1 is :" + insertDataJsa.toJSONString());
        insertDataJob = new JSONObject();
        insertDataJob.put(POST_FROM_WHO, "paup");
        insertDataJob.put(POST_TO_WHO, "Acer33");
        insertDataJob.put(POST_SUBJECT, "Acer");
        insertDataJob.put(POST_CONTENT, "Acer33");
        insertDataJob.put(POST_TIME, "Acer33");
        insertDataJob.put(POST_READ_STATUS, "Acer33");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray2 is :" + insertDataJsa.toJSONString());
        return insertDataJsa;
    }
    public static JSONObject createPostTableUpdateTestJsonObj() {
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(POST_FROM_WHO, "KH");
        insertDataJob.put(POST_TO_WHO, "Acer");
        insertDataJob.put(POST_SUBJECT, "Acer");
        insertDataJob.put(POST_CONTENT, "Acer");
        insertDataJob.put(POST_TIME, "Acer");
        insertDataJob.put(POST_READ_STATUS, "Acer");
        System.out.println("jsonObj is :" + insertDataJob.toJSONString());
        
        return insertDataJob;
    }
    public static void testPostTableFunction() throws FileNotFoundException, IOException, ParseException{
        PostTableFunction.create(DB_NAME);
        PostTableFunction.insert(createPostTableTestJson());
        PostTableFunction.update(1,createPostTableUpdateTestJsonObj());
        PostTableFunction.delete(1);
    }
    
    public static JSONArray createMeetingTableTestJson() {
        JSONArray insertDataJsa = new JSONArray();
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(MEETING_FROM_WHO, "KH");
        insertDataJob.put(MEETING_TO_WHO, "Acer");
        insertDataJob.put(MEETING_NAME, "Acer");
        insertDataJob.put(MEETING_START_TIME, "Acer");
        insertDataJob.put(MEETING_END_TIME, "Acer");
        insertDataJob.put(MEETING_STATE, "Acer");
        insertDataJob.put(MEETING_NOTES, "Acer");
        insertDataJob.put(MEETING_DETAILS, "Acer");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray1 is :" + insertDataJsa.toJSONString());
        insertDataJob = new JSONObject();
        insertDataJob.put(MEETING_FROM_WHO, "paul");
        insertDataJob.put(MEETING_TO_WHO, "Acer22");
        insertDataJob.put(MEETING_NAME, "Acer22");
        insertDataJob.put(MEETING_START_TIME, "Acer22");
        insertDataJob.put(MEETING_END_TIME, "Acer22");
        insertDataJob.put(MEETING_STATE, "Acer22");
        insertDataJob.put(MEETING_NOTES, "Acer22");
        insertDataJob.put(MEETING_DETAILS, "Acer22");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray2 is :" + insertDataJsa.toJSONString());
        return insertDataJsa;
    }
    public static JSONObject createMeetingTableUpdateTestJsonObj() {
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(MEETING_FROM_WHO, "KH");
        insertDataJob.put(MEETING_TO_WHO, "Acer");
        insertDataJob.put(MEETING_NAME, "Acer");
        insertDataJob.put(MEETING_START_TIME, "Acer");
        insertDataJob.put(MEETING_END_TIME, "Acer");
        insertDataJob.put(MEETING_STATE, "Acer");
        insertDataJob.put(MEETING_NOTES, "Acer");
        insertDataJob.put(MEETING_DETAILS, "Acer");
        System.out.println("jsonObj is :" + insertDataJob.toJSONString());
        
        return insertDataJob;
    }
    public static void testMeetingTableFunction() throws FileNotFoundException, IOException, ParseException{
        MeetingTableFunction.create(DB_NAME);
        MeetingTableFunction.insert(createMeetingTableTestJson());
        MeetingTableFunction.update(1,createMeetingTableUpdateTestJsonObj());
        MeetingTableFunction.delete(1);
    }
}
