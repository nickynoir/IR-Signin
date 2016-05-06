package edu.nsu.ir.util;

public class AppURLS {
    public static String KEY_DELETE_MSGTHREAD="http://api.thomaskofiannan.com/ir/msgthread/delete_msgthread.php";
    public static String LOGIN_URL="http://api.flipmyad.com.ng/testimony/checkLogin.php";
    public static String MSG="http://api.thomaskofiannan.com/ir/message/get_message_by_threadId.php";
    public static String GET_ALL_USERS="http://api.thomaskofiannan.com/ir/profile/get_all_profiles.php";
    public static String GET_BYEMAILPOST="http://api.thomaskofiannan.com/ir/post/get_post_by_email.php";
    public static String INBOX_URL="http://api.thomaskofiannan.com/ir/msgthread/get_msgthread_by_username.php";
    public static String CREATE_MSGTHREAD ="http://api.thomaskofiannan.com/ir/msgthread/create_msgthread.php";
    public static String CREATE_MSG="http://api.thomaskofiannan.com/ir/message/create_message.php";
    public static String PROFILE_UPDATE="http://api.thomaskofiannan.com/ir/profile/update_profile.php";
    public static String ALLMSG ="http://api.thomaskofiannan.com/ir/message/get_all_messages.php";
    public static  String ALLTHREADS = "http://api.thomaskofiannan.com/ir/msgthread/get_all_msgthreads.php";
    public static String CASE_BY_EMAIL="http://api.thomaskofiannan.com/ir/cases/get_all_cases.php";
    public static String CREATE_CASE_URL="http://api.thomaskofiannan.com/ir/cases/create_case.php";

    //http://10.0.2.2/testimony/
    //public static String LOGIN_URL="http://10.0.2.2/testimony/checkLogin.php";
    public static String GET_USER_NAME_URL="http://api.thomaskofiannan.com/ir//get_user_name_by_email.php";
   // public static String REGISTER_URL="http://10.0.2.2/testimony/create_user.php";
    public static String UDPATE_USER_PROFILE_URL="http://api.thomaskofiannan.com/ir//create_user.php";

    public static String ADD_COMMENT_URL="http://api.thomaskofiannan.com/ir/comment/create_comment.php";
    public static String GET_ALL_COMMENT_URL="http://api.thomaskofiannan.com/ir/comment/get_all_comments.php";
    public static String COMMENT_INCREMENT_LIKES_URL="http://api.thomaskofiannan.com/ir/comment/increase_comment_likes.php";
    public static String COMMENT_INCREMENT_DISLIKES_URL="http://api.thomaskofiannan.com/ir/comment/increase_comment_dislikes.php";

    public static String POST_URL="http://api.thomaskofiannan.com/ir/post/create_post.php";
    public static String GETALLPOST_URL="http://api.thomaskofiannan.com/ir/post/getallpost.php";
    public static String POST_INCREMENT_LIKES_URL="http://api.thomaskofiannan.com/ir/post/increase_post_likes.php";
    public static String POST_INCREMENT_DISLIKES_URL="http://api.thomaskofiannan.com/ir/post/increase_post_dislikes.php";
    //post keys
    public static String KEY_POST_TITLE="title";
    public static String KEY_POST_DETAILS="details";
    public static String KEY_POST_DATE="datePosted";
    public static String KEY_POST_AUTHOR="author";
    public static String KEY_MESSAGE_ID="msgId";
    public static String KEY_POST_POSTID="postID";

    public static String CASE ="caseId";
    public static String STATUS ="caseStatus";
    public static String DESC = "desc";
    public static String DATE_MOD ="dateModified";
    public static String CreatorEmail="creatorEmail";
    public static String CASE_NUMBER = "caseNumber";
    public static String OsType="osType";
    public static String SerialNumber ="serialNumber";
    public static String ModelNumber = "modelNumber";
    public static String HostName ="hostName";
    public static String MacAddress ="macAddress";
    public static String IpAddress = "ipAddress";
    public static String Email = "email";

    //inbox keys
    public static String KEY_INBOX_TO_UNAME="toUname";
    public static String KEY_INBOX_FROM_UNAME="fromUname";
    public static String KEY_INBOX_SUBJECT="Subject";
    public static String KEY_INBOX_ThreadId="threadId";
    public static String KEY_INBOX_ImgPath="image_path";
    public static String KEY_MESSAGE_SUBJECT = "Subject";

    //Message
    public static String KEY_TimeStamp="time";
    public static String KEY_MESSAGE_Content = "content";
    public static String KEY_MESSAGE_USER = "username";

    //register keys
    public static String KEY_NAME="name";
    public static String KEY_EMAIL="email";
    public static String KEY_CHURCH="church";
    public static String KEY_LOCATION="location";
    public static String KEY_PASSWORD="password";
    public static String KEY_IMAGEURL="imageURL";
    public static String KEY_USER_GROUPS="imageURL";

    //PROFILE keys
    public static String PROFILE_URL="";

    public static String USER_NAME = "username";

    public static String CONTACT_KEY = "contact";
    public static String EMAIL_Key = "email";
    public static String AGE_KEY = "age";


    public static String REGISTER_URL="http://api.thomaskofiannan.com/ir/profile/create_profile.php";
    public static String GET_PROFILE_URL = "http://api.thomaskofiannan.com/ir/profile/get_profile_by_email.php";
    public static String F_NAME_KEY = "fname";
    public static String L_NAME_KEY = "lname";
    public static String USER_NAME_KEY = "uname";
    public static String AGE_Key ="age";
    public static String EMAIL_KEY = "email";
    public static String PASSWORD_KEY = "password";
    public static String CITY_KEY = "city";
    public static String STATE_KEY = "state";
    public static String COMPANY_NAME_KEY = "company";
    public static String EXPERIENCE_KEY = "experience";
    public static String IMAGE_KEY = "image_path";
//    public static String CONTACT_KEY = "";


    private static String CASE_NAME_KEY = "";
    private static String CASE_STATUS_KEY = "dummy";
    private static String CASE_ID_KEY = "dummy";
    private static String COURT_NUMBER_KEY = "dummy";
    private static String EVIDENCE_KEY = "dummy";
    private static String OS_TYPE_KEY = "dummy";
    //post keys
    public static String KEY_COMMENT_TITLE="title";
    public static String KEY_COMMENT_DETAILS="details";
    public static String KEY_COMMENT_AUTHOR="author";
    public static String KEY_COMMENT_DATE="dateCreated";
    public static String KEY_COMMENT_ID="commentID";

    //group
    public static String KEY_GROUP_NAME="name";
    public static String KEY_GROUP_DESC="description";
    public static String KEY_GROUP_AUTHOR="author";
    public static String KEY_GROUP_DATE="dateCreated";
    //parcelable keys
    public static String KEY_POST="post";
    public static String KEY_COMMENT="comment";

    //preference
    public static String KEY_SHARED_PREF="userPref";
    public static String KEY_IS_LOGGED_IN="isLoggedIn";

    //Dialog fragments
    public static final int PICK_IMAGE_REQUEST = 1;
    public static final int ACTION_REQUEST_GALLERY=10;
    public static final int ACTION_REQUEST_CAMERA=20;
    public static final int CAMERA_PERMISSION_REQUEST_CODE=30;

}
