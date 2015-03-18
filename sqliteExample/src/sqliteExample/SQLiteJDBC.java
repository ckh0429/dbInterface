package sqliteExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SQLiteJDBC {
    // for test use data//
    public final static String DB_NAME = "test_DB";
    public final static String TABLE_NAME = "test_Table";
    // table1 name and attribute which display user info //
    public final static String USER_INFO_TABLE = "User_Info_Table";
    public final static String CHAT_ID = "ChatID";
    public final static String MY_NAME = "MyName";
    public final static String MY_MOBILE = "MyMobile";
    public final static String MY_EXT = "MyExt";
    public final static String MY_EMAIL = "MyEmail";
    public final static String MY_DEPT = "MyDept";

    // table2 name and attribute which display contact info//
    public final String CONTACT_INFO_Table = "CONTACT_INFO_Table";
    public final String NAME = "Name";
    public final String MOBILE = "Mobile";
    public final String EXTENSION = "Extension";
    public final String EMAIL = "Email";
    public final String FAVORITE = "Favorite";
    public final String CONTACT_TYPE = "Contact type";
    public final String DEPT = "Dept";
    public final String LAST_CONTACT = "Last contact";
    public final String LAST_CONTACT_TIME = "Last contact time";
    public final String MEMBER = "Member";
    public final String TOTAL_MEMBERS = "Total members";
    public final String SUBJECT = "Subjects";
    public final String UNREAD = "Unread";

    // table3 name and attribute which display chat record //
    public final String CHAT_RECORD_TABLE = "Chat_Info_Table";
    public final String TARGET = "Target";
    public final String TYPE = "Type";
    public final String IS_GROUP = "IsGroup";
    public final String WRITER = "Writer";
    public final String CONTENT_TYPE = "Content type";
    public final String CONTENT = "Content";
    public final String READ = "Read";
    public final String READ_MEMBER = "ReadMember";
    public final String READ_TIME = "Read time";

    // table 4 name and attribute which display group meeting record //
    public final String GROUP_MEETING_RECORD_TABLE = "Group_Meeting_Record_Table";
    public final String MEETING_NAME = "MeetingName";
    public final String GROUP = "Group";
    public final String START = "Start";
    public final String END = "End";
    public final String INITIATOR = "Initiator";
    public final String STATE = "State";

    public static void main(String args[]) {
        String[] dbName = { DB_NAME };
        createDB(dbName);
        createTable(DB_NAME, TABLE_NAME);
        JSONArray insertDataJsa = new JSONArray();
        JSONObject insertDataJob = new JSONObject();
        insertDataJob.put(MY_NAME, "KH");
        insertDataJob.put(MY_MOBILE, "0933913329");
        insertDataJob.put(MY_EXT, "5173");
        insertDataJob.put(MY_EMAIL, "ckh0429@gmail.com");
        insertDataJob.put(MY_DEPT, "Acer");
        insertDataJsa.add(insertDataJob);
        System.out.println("jsonarray1 is :"+insertDataJsa.toJSONString());
        insertDataJob = new JSONObject();
        insertDataJob.put(MY_NAME, "paul");
        insertDataJob.put(MY_MOBILE, "0912345678");
        insertDataJob.put(MY_EXT, "5222");
        insertDataJob.put(MY_EMAIL, "paul0429@gmail.com");
        insertDataJob.put(MY_DEPT, "aoi");
        insertDataJsa.add(insertDataJob);
        
        System.out.println("jsonarray2 is :"+insertDataJsa.toJSONString());

        insertData(DB_NAME, TABLE_NAME, insertDataJsa);
    }

    public static boolean createDB(String[] dbName) {
        //TODO is DB exist?
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            for (int dbCount = 0; dbCount < dbName.length; dbCount++) {
                c = DriverManager.getConnection("jdbc:sqlite:"
                        + dbName[dbCount] + ".db");
            }
            return (true);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return (false);
        }
    }

    // TODO 4 table in a function or 16 function for each table create /insert /
    // update /delete ?
    public static boolean createTable(String dbName, String tableName) {
      //TODO isTableName Exist?
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "CREATE TABLE if not exists " + tableName + "(" 
            + CHAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MY_NAME + " CHAR(20) NOT NULL, "
            + MY_MOBILE + " CHAR(20) NOT NULL, "
            + MY_EXT + " CHAR(20) NOT NULL, "
            + MY_EMAIL + " CHAR(30) NOT NULL UNIQUE, "
            + MY_DEPT + " CHAR(20) NOT NULL "
            + ")";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return (false);

        }
        System.out.println("Table created successfully");
        return true;
    }

    public static void testJSArray(){
        JSONArray js = null ;
        JSONObject jobj = null;
        jobj.put("test1", "test1");
        jobj.put("test2", "test2");
        js.add(jobj);
        jobj.clear();
        jobj.put("test3", "love");
        js.add(jobj);
    }
    public static boolean insertData(String dbName, String tableName, JSONArray rawData) {
        ArrayList <String> myName = new ArrayList() ;
        ArrayList <String> myMobile = new ArrayList() ;
        ArrayList <String> myEmail = new ArrayList() ;
        ArrayList <String> myExt = new ArrayList() ;
        ArrayList <String> myDept = new ArrayList() ;
        Connection c = null;
        Statement stmt = null;
        String value = "";
        for (int i=0;i<rawData.size();i++){
            JSONObject jobj = (JSONObject) rawData.get(i);
            myName.add(jobj.get(MY_NAME).toString());
            myMobile.add(jobj.get(MY_MOBILE).toString());
            myEmail.add(jobj.get(MY_EMAIL).toString());
            myExt.add(jobj.get(MY_EXT).toString());
            myDept.add(jobj.get(MY_DEPT).toString());
            value = value + "('"+myName.get(i)+"', '"+myMobile.get(i)+"', '"+myEmail.get(i)+"', "
                    + "'"+myExt.get(i)+"', '"+myDept.get(i)+"' )";
            if (i!=rawData.size()-1){
                value = value +", ";
            } else {
                value = value + ";";
            }
        }
        System.out.println("value is : "+value);

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+DB_NAME+".db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "INSERT INTO "+TABLE_NAME+" ("+MY_NAME+","+MY_MOBILE+","+MY_EXT+","
            +MY_EMAIL+","+MY_DEPT+") " 
            + "VALUES " + value;
            //+"VALUES ('"+myName.get(0)+"', 'richard', 'Johnny', 'KH', 'kavi' );"; 
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
          } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
          }
          System.out.println("Records created successfully");
        /*for(int jsCount=0;jsCount<insertDataJS.size();jsCount++){
            JSONObject rawData = (JSONObject) insertDataJS.get(jsCount);
            rawData.get("test");
        }*/
        return true;
    }

    public static void showAllData (){
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:"+TABLE_NAME+".db");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
          while ( rs.next() ) {
             int id = rs.getInt("id");
             String  name = rs.getString("name");
             int age  = rs.getInt("age");
             String  address = rs.getString("address");
             float salary = rs.getFloat("salary");
             System.out.println( "ID = " + id );
             System.out.println( "NAME = " + name );
             System.out.println( "AGE = " + age );
             System.out.println( "ADDRESS = " + address );
             System.out.println( "SALARY = " + salary );
             System.out.println();
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    
    public static boolean updateData(String tableName, JSONArray JS) {
        return true;
    };

    public static boolean deleteData(String tableName, String ChatID) {
        return true;
    }
}
