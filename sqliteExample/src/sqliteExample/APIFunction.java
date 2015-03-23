package sqliteExample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import sqliteExample.TableAttributeServer;

public class APIFunction extends TableAttributeServer {

    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
        String[] dbName = { DB_NAME };
        createDB(dbName);
        testUserTableFunction();
        testGroupTableFunction();
        testChatTableFunction();
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
        insertDataJob.put(USER_INVITES, "Acer");
        insertDataJob.put(USER_BE_INVITED, "Acer");
        insertDataJob.put(USER_GROUP, "Acer");
        insertDataJob.put(USER_GROUP_BE_INVITED, "Acer");
        insertDataJob.put(USER_IMEI, "Acer");
        insertDataJob.put(USER_REGISTER_ID, "Acer");
        insertDataJob.put(USER_PICTURE, "Acer");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray1 is :" + insertDataJsa.toJSONString());
        insertDataJob = new JSONObject();
        insertDataJob.put(USER_NAME, "paul");
        insertDataJob.put(USER_MOBILE, "0933913329");
        insertDataJob.put(USER_EXTENSION, "5122");
        insertDataJob.put(USER_EMAIL, "paul1211@gmail.com");
        insertDataJob.put(USER_DEPT, "intel");
        insertDataJob.put(USER_CONTACTS, "intel");
        insertDataJob.put(USER_INVITES, "intel");
        insertDataJob.put(USER_BE_INVITED, "intel");
        insertDataJob.put(USER_GROUP, "intel");
        insertDataJob.put(USER_GROUP_BE_INVITED, "intel");
        insertDataJob.put(USER_IMEI, "intel");
        insertDataJob.put(USER_REGISTER_ID, "intel");
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

        insertDataJob.put(USER_INVITES, "Acer2");
        insertDataJob.put(USER_BE_INVITED, "Acer2");
        insertDataJob.put(USER_GROUP, "Acer2");
        insertDataJob.put(USER_GROUP_BE_INVITED, "Acer2");
        insertDataJob.put(USER_PICTURE, "Acer2");
        System.out.println("jsonObj is :" + insertDataJob.toJSONString());
        
        return insertDataJob;
    }
    public static void testUserTableFunction() throws FileNotFoundException, IOException, ParseException{
        TableFunctionUser.create(DB_NAME);
        TableFunctionUser.insert(createUserTableTestJson());
        TableFunctionUser.update(1,createUserTableUpdateTestJsonObj());
        TableFunctionUser.delete(1);
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
        TableFunctionGroup.create(DB_NAME);
        TableFunctionGroup.insert(createGroupTableTestJson());
        TableFunctionGroup.update(1,createGroupTableUpdateTestJsonObj());
        TableFunctionGroup.delete(1);
    }

    public static JSONArray createUserChatTableTestJson() {
        JSONArray insertDataJsa = new JSONArray();
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(CHAT_FROM_WHO, "KH");
        insertDataJob.put(CHAT_TO_WHO, "Acer");
        insertDataJob.put(CHAT_CONTENT, "Acer");
        insertDataJob.put(CHAT_TIME, 123456L);
        insertDataJob.put(CHAT_IS_GROUP, false);
        insertDataJob.put(CHAT_POST_ID, 123456890L);
        insertDataJob.put(CHAT_READ_STATUS, "Acer");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray1 is :" + insertDataJsa.toJSONString());
        insertDataJob = new JSONObject();
        insertDataJob.put(CHAT_FROM_WHO, "paup");
        insertDataJob.put(CHAT_TO_WHO, "Acer33");
        insertDataJob.put(CHAT_CONTENT, "Acer33");
        insertDataJob.put(CHAT_TIME, "Acer33");
        insertDataJob.put(CHAT_TIME, 987654L);
        insertDataJob.put(CHAT_IS_GROUP, true);
        insertDataJob.put(CHAT_POST_ID, 9876543210L);
        insertDataJob.put(CHAT_READ_STATUS, "Acer33");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray2 is :" + insertDataJsa.toJSONString());
        return insertDataJsa;
    }
    public static JSONObject createUserChatTableUpdateTestJsonObj() {
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(CHAT_FROM_WHO, "KH");
        insertDataJob.put(CHAT_TO_WHO, "Acer");
        insertDataJob.put(CHAT_CONTENT, "Acer");
        insertDataJob.put(CHAT_TIME, "Acer");
        insertDataJob.put(CHAT_READ_STATUS, "Acer");
        System.out.println("jsonObj is :" + insertDataJob.toJSONString());
        
        return insertDataJob;
    }
    
    public static void testChatTableFunction() throws FileNotFoundException, IOException, ParseException{
        TableFunctionChat.create(DB_NAME);
        TableFunctionChat.insert(createUserChatTableTestJson());
        TableFunctionChat.update(1,createUserChatTableUpdateTestJsonObj());
        TableFunctionChat.delete(1);
    }
    
    public static JSONArray createPostTableTestJson() {
        JSONArray insertDataJsa = new JSONArray();
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(POST_FROM_WHO, "KH");
        insertDataJob.put(POST_TO_WHO, "Acer");
        insertDataJob.put(POST_SUBJECT, "Acer");
        insertDataJob.put(POST_CONTENT, "Acer");
        insertDataJob.put(POST_TIME, 123456789L);
        insertDataJob.put(POST_READ_STATUS, "Acer");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray1 is :" + insertDataJsa.toJSONString());
        insertDataJob = new JSONObject();
        insertDataJob.put(POST_FROM_WHO, "paup");
        insertDataJob.put(POST_TO_WHO, "Acer33");
        insertDataJob.put(POST_SUBJECT, "Acer");
        insertDataJob.put(POST_CONTENT, "Acer33");
        insertDataJob.put(POST_TIME, 789546213546849L);
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
        TableFunctionPost.create(DB_NAME);
        TableFunctionPost.insert(createPostTableTestJson());
        TableFunctionPost.update(1,createPostTableUpdateTestJsonObj());
        TableFunctionPost.delete(1);
    }
    
    public static JSONArray createMeetingTableTestJson() {
        JSONArray insertDataJsa = new JSONArray();
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(MEETING_FROM_WHO, "KH");
        insertDataJob.put(MEETING_TO_WHO, "Acer");
        insertDataJob.put(MEETING_NAME, "Acer");
        insertDataJob.put(MEETING_START_TIME, 1234562137910L);
        insertDataJob.put(MEETING_END_TIME, 5566778899021231230L);
        insertDataJob.put(MEETING_STATE, "Acer");
        insertDataJob.put(MEETING_NOTES, "Acer");
        insertDataJob.put(MEETING_DETAILS, "Acer");
        insertDataJob.put(MEETING_WHO_ACCEPT, "Acer224324342");
        insertDataJob.put(MEETING_WHO_REJECT, "Acer2223423423423");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray1 is :" + insertDataJsa.toJSONString());
        insertDataJob = new JSONObject();
        insertDataJob.put(MEETING_FROM_WHO, "paul");
        insertDataJob.put(MEETING_TO_WHO, "Acer22");
        insertDataJob.put(MEETING_NAME, "Acer22");
        insertDataJob.put(MEETING_START_TIME, 123456790L);
        insertDataJob.put(MEETING_END_TIME, 556677889900L);
        insertDataJob.put(MEETING_STATE, "Acer22");
        insertDataJob.put(MEETING_NOTES, "Acer22");
        insertDataJob.put(MEETING_DETAILS, "Acer22");
        insertDataJob.put(MEETING_WHO_ACCEPT, "Acer224324342");
        insertDataJob.put(MEETING_WHO_REJECT, "Acer2223423423423");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray2 is :" + insertDataJsa.toJSONString());
        return insertDataJsa;
    }
    public static JSONObject createMeetingTableUpdateTestJsonObj() {
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(MEETING_FROM_WHO, "KH");
        insertDataJob.put(MEETING_TO_WHO, "intel");
        insertDataJob.put(MEETING_NAME, "intel");
        insertDataJob.put(MEETING_START_TIME, 9874561238521L);
        insertDataJob.put(MEETING_END_TIME, 1427081932873L);
        insertDataJob.put(MEETING_STATE, "intel");
        insertDataJob.put(MEETING_NOTES, "intel");
        insertDataJob.put(MEETING_DETAILS, "intel");
        insertDataJob.put(MEETING_WHO_ACCEPT, "intel224324342");
        insertDataJob.put(MEETING_WHO_REJECT, "intel2223423423423");
        System.out.println("jsonObj is :" + insertDataJob.toJSONString());
        
        return insertDataJob;
    }
    public static void testMeetingTableFunction() throws FileNotFoundException, IOException, ParseException{
        TableFunctionMeeting.create(DB_NAME);
        TableFunctionMeeting.insert(createMeetingTableTestJson());
        TableFunctionMeeting.update(1,createMeetingTableUpdateTestJsonObj());
        TableFunctionMeeting.delete(1);
    }
}
