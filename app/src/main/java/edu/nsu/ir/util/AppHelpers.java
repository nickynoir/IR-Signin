package edu.nsu.ir.util;

import android.content.SharedPreferences;

import java.text.SimpleDateFormat;

import edu.nsu.ir.api.AppController;
import edu.nsu.ir.api.ConnectServer;


/**
 * Created by thomaskofiannan1 on 3/15/16.
 */
public class AppHelpers {
    private static SharedPreferences pref = AppController.getContext().getSharedPreferences(AppURLS.KEY_SHARED_PREF, 0);
    // 0 - for private mode
    private static SharedPreferences.Editor editor = pref.edit();
    private static int start=0;
    public static String getCurrentDate(){
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(date);
        return dateString;
    }

    public static String getCurrentUserName(){
        // returns stored preference value
// If value is not present return second param value - In this case null
      String email= pref.getString(AppURLS.KEY_EMAIL, null); // getting String
      boolean isLogged=  pref.getBoolean(AppURLS.KEY_IS_LOGGED_IN, false); // getting Integer
        return ConnectServer.getUserNameByEmail(email);
    }

    public static String generatePostID(String title,String date){
        start++;
     StringBuffer result=new StringBuffer();
        result.append(title).append(date).append(start);
     return result.toString();
    }
    public static String generateGroupID(String title,String date){
        start++;
        StringBuffer result=new StringBuffer();
        result.append(title).append(date).append(start);
        return result.toString();
    }
    public static String generateCommentID(String title,String date){
        start++;
        StringBuffer result=new StringBuffer();
        result.append(title).append(date).append(start);
        return result.toString();
    }

}
