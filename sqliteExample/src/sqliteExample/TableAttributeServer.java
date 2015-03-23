package sqliteExample;

public class TableAttributeServer {
    public final static String DB_NAME = "test_DB";
    // IM VOIP server DB and table //
    public final static String USER_TABLE = "UserTable";
    public final static String USER_ID = "UserID";
    public final static String USER_NAME = "UserName";
    public final static String USER_MOBILE = "UserMobile";
    public final static String USER_EXTENSION = "UserExtension";
    public final static String USER_EMAIL = "UserEmail";
    public final static String USER_DEPT = "UserDept";
    public final static String USER_CONTACTS = "UserContacts";
    public final static String USER_INVITES = "UserInvites";
    public final static String USER_BE_INVITED = "UserBeInvited";
    public final static String USER_GROUP = "UserGroup";
    public final static String USER_GROUP_BE_INVITED = "UserGroupBeInvited";
    public final static String USER_PICTURE = "UserPicture";
    public final static String USER_IMEI = "UserIMEI";
    public final static String USER_REGISTER_ID = "UserRegisterID";

    public final static String GROUP_TABLE = "GroupTable";
    public final static String GROUP_ID = "GroupID";
    public final static String GROUP_NAME = "GroupName";
    public final static String GROUP_MEMBERS = "GroupMembers";
    public final static String GROUP_INVITERS = "GroupInviters";
    public final static String GROUP_PICTURE = "GroupPicture";

    public final static String CHAT_TABLE = "UserChatTable";
    public final static String CHAT_ID = "UserChatID";
    public final static String CHAT_FROM_WHO = "UserChatFromWho";
    public final static String CHAT_TO_WHO = "UserChatToWho";
    public final static String CHAT_CONTENT = "UserChatContent";
    public final static String CHAT_TIME = "UserChatTime";
    public final static String CHAT_IS_GROUP = "isGroup";
    public final static String CHAT_POST_ID = "ChatPostID";
    public final static String CHAT_READ_STATUS = "UserChatReadStatus";

    public final static String POST_TABLE = "PostTable";
    public final static String POST_ID = "PostID";
    public final static String POST_FROM_WHO = "PostFromWho";
    public final static String POST_TO_WHO = "PostToWho";
    public final static String POST_SUBJECT = "PostSubject";
    public final static String POST_CONTENT = "PostContent";
    public final static String POST_TIME = "PostTime";
    public final static String POST_READ_STATUS = "PostReadStatus";

    public final static String MEETING_TABLE = "MeetingTable";
    public final static String MEETING_ID = "MeetingID";
    public final static String MEETING_FROM_WHO = "MeetingFromWho";
    public final static String MEETING_TO_WHO = "MeetingToWho";
    public final static String MEETING_NAME = "MeetingName";
    public final static String MEETING_START_TIME = "MeetingStartTime";
    public final static String MEETING_END_TIME = "MeetingEndTime";
    public final static String MEETING_STATE = "MeetingState";
    public final static String MEETING_NOTES = "MeetingNotes";
    public final static String MEETING_DETAILS = "MeetingDetails";
    public final static String MEETING_WHO_ACCEPT = "MeetingWhoAccept";
    public final static String MEETING_WHO_REJECT = "MeetingWhoReject";

    public void test() {
        /*
         * String UserID, String UserMobile, String UserExtension, String
         * UserEmail, String UserDept, String UserContacts, String UserInvites,
         * String UserBeInvited, String UserGroup, String UserGroupBeInvited,
         * String UserPicture
         */
    }
    /*
    public static void showAllData() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + TABLE_NAME + ".db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("AGE = " + age);
                System.out.println("ADDRESS = " + address);
                System.out.println("SALARY = " + salary);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    */
}
