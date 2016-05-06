package edu.nsu.ir.api;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.nsu.ir.activities.forum.PostActivity;
import edu.nsu.ir.fragments.AddCommentDialogFragment;
import edu.nsu.ir.fragments.AddImageDialogFragment;
import edu.nsu.ir.model.Comment;
import edu.nsu.ir.model.Post;
import edu.nsu.ir.model.StatusReponse;
import edu.nsu.ir.util.AppURLS;


/**
 * Created by thomaskofiannan1 on 4/7/16.
 */
public class ConnectServer {

    private static boolean isSuccess=false;
    private static String returnedValue;
    private static String userName;
    private static Gson gson;
    private static StatusReponse statusReponse;
    private static Dialog pDialog;
    private static Post post=new Post();
    private static ArrayList<Post>allPost=new ArrayList<>();
    private static ArrayList<Comment>allComment=new ArrayList<>();
    private static Comment comment;

    private static Context context=AppController.getContext();
//    private static SharedPreferences pref = context.getSharedPreferences(AppURLS.KEY_SHARED_PREF, 0);
    private static String pEmail;
    private static PostActivity postActivity=new PostActivity();
    // 0 - for private mode
   // private static SharedPreferences.Editor editor = pref.edit();

    public static void sendPost(Post pPost){
        post=pPost;
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());
                        statusReponse= gson.fromJson(response, StatusReponse.class);
                        Toast.makeText(context, statusReponse.toString(), Toast.LENGTH_LONG).show();
                        if(statusReponse.getSuccess()==1){
                            postActivity.clearFields();
                            AddImageDialogFragment myFragment = new AddImageDialogFragment();
                            //     myFragment.show(getFragmentManager(), "theDialog");
                        }else{
                            Toast.makeText(context, "Sorry Post could not be added", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(AppURLS.KEY_POST_TITLE, post.getTitle());
                params.put(AppURLS.KEY_POST_DATE, post.getDateCreated());
                params.put(AppURLS.KEY_POST_DETAILS, post.getDetails());
                params.put(AppURLS.KEY_POST_AUTHOR, post.getAuthor());
                params.put(AppURLS.KEY_POST_POSTID,post.getPostID());


                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static  ArrayList<Post> getAllPost(Dialog dialog){
        pDialog=dialog;

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppURLS.GETALLPOST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        Log.d("TAG-RESPONSE", response.toString());
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray= (JSONArray) jsonObject.get("message");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject row = jsonArray.getJSONObject(i);
                                post=new Post();
                                post.setDetails(row.getString("details"));
                                post.setImageURL(row.getString("imageURL"));
                                post.setTitle(row.getString("title"));
                                post.setDateCreated(row.getString("datePosted"));
                                post.setAuthor(row.getString("author"));
                                //Toast.makeText(context, post.toString(), Toast.LENGTH_LONG).show();
                                allPost.add(post);
                            }
                            // Toast.makeText(context, jsonArray.toString(), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                });

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(AppController.getContext());
        requestQueue.add(stringRequest);
        return allPost;
    }
    public static boolean incrementPostLikes(final String postID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.POST_INCREMENT_LIKES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        statusReponse= gson.fromJson(response, StatusReponse.class);
                        Toast.makeText(context, statusReponse.toString(), Toast.LENGTH_LONG).show();
                        if(statusReponse.getSuccess()==1){
                            isSuccess=true;
                            Toast.makeText(context, "You disliked this comment", Toast.LENGTH_LONG).show();
                            //     myFragment.show(getFragmentManager(), "theDialog");
                        }else{
                            Toast.makeText(context, "Sorry dislike could not work", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(AppURLS.KEY_POST_POSTID, postID);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return isSuccess;
    }
    public static boolean incrementPostDislikes(final String postID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.POST_INCREMENT_DISLIKES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        statusReponse= gson.fromJson(response, StatusReponse.class);
                        Toast.makeText(context, statusReponse.toString(), Toast.LENGTH_LONG).show();
                        if(statusReponse.getSuccess()==1){
                            isSuccess=true;
                            Toast.makeText(context, "You disliked this comment", Toast.LENGTH_LONG).show();
                            //     myFragment.show(getFragmentManager(), "theDialog");
                        }else{
                            Toast.makeText(context, "Sorry dislike could not work", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(AppURLS.KEY_POST_POSTID, postID);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return isSuccess;
    }
    public static boolean incrementCommentLikes(final String commentID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.COMMENT_INCREMENT_LIKES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());
                        statusReponse= gson.fromJson(response, StatusReponse.class);
                        Toast.makeText(context, statusReponse.toString(), Toast.LENGTH_LONG).show();
                        if(statusReponse.getSuccess()==1){
                            isSuccess=true;
                            Toast.makeText(context, "You liked this comment", Toast.LENGTH_LONG).show();
                            //     myFragment.show(getFragmentManager(), "theDialog");
                        }else{
                            Toast.makeText(context, "Sorry dislike could not work", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(AppURLS.KEY_COMMENT_ID, commentID);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return isSuccess;
    }
    public static boolean incrementCommentDisLikes(final String commentID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.COMMENT_INCREMENT_DISLIKES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());
                        statusReponse= gson.fromJson(response, StatusReponse.class);
                        Toast.makeText(context, statusReponse.toString(), Toast.LENGTH_LONG).show();
                        if(statusReponse.getSuccess()==1){
                            isSuccess=true;
                            Toast.makeText(context, "You disliked this comment", Toast.LENGTH_LONG).show();
                            //     myFragment.show(getFragmentManager(), "theDialog");
                        }else{
                            Toast.makeText(context, "Sorry dislike could not work", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(AppURLS.KEY_COMMENT_ID, commentID);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return isSuccess;
    }

    public static void sendComment(Comment thecomment){
        comment=thecomment;
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.ADD_COMMENT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());
                        StatusReponse statusReponse = gson.fromJson(response, StatusReponse.class);
                        Toast.makeText(context, statusReponse.toString(), Toast.LENGTH_LONG).show();
                        if(statusReponse.getSuccess()==1){
                            new AddCommentDialogFragment().clearFields();

                        }else{
                            Toast.makeText(context, "Sorry Comment could not be added", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(AppURLS.KEY_COMMENT_TITLE, comment.getTitle());
                params.put(AppURLS.KEY_COMMENT_DETAILS, comment.getDetails());
                params.put(AppURLS.KEY_COMMENT_DATE, comment.getDateCreated());
                params.put(AppURLS.KEY_COMMENT_AUTHOR, comment.getAuthor());

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    public static  ArrayList<Comment> getAllComment(){

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppURLS.GET_ALL_COMMENT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("TAG-RESPONSE", response.toString());
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray= (JSONArray) jsonObject.get("message");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject row = jsonArray.getJSONObject(i);
                                comment=new Comment();
                                comment.setDetails(row.getString("details"));
                                comment.setTitle(row.getString("title"));
                                comment.setDateCreated(row.getString("datePosted"));
                                comment.setAuthor(row.getString("author"));
                                //Toast.makeText(context, post.toString(), Toast.LENGTH_LONG).show();
                                allComment.add(comment);
                            }
                            // Toast.makeText(context, jsonArray.toString(), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                });

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(AppController.getContext());
        requestQueue.add(stringRequest);
        return allComment;
    }
    public static String getUserNameByEmail(String email){
        final String pEmail=email;
        //   userProfile=profile;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.GET_USER_NAME_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray= (JSONArray) jsonObject.get("message");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject row = jsonArray.getJSONObject(i);
                                userName=row.getString("name");

                            }
                            // Toast.makeText(context, jsonArray.toString(), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(AppURLS.KEY_EMAIL, pEmail);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return userName;
    }
    private void doPP(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();

                        Log.d("TAG-RESPONSE", response.toString());

//                        if(response!=null){
//                        try {
//                            Toast.makeText(getApplicationContext(), "before success value check", Toast.LENGTH_LONG).show();
//                            JSONObject jsonObject=new JSONObject(response);
//                            JSONArray jsonArray= (JSONArray) jsonObject.get("message");
//                            if(jsonObject.getInt("success")==1){
//                                isUserRegistered=true;
//                                Toast.makeText(getApplicationContext(), "inside if", Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                            }else{
//                                Toast.makeText(getApplicationContext(), "else of if", Toast.LENGTH_LONG).show();
//
//                            }
//                            for(int i=0;i<jsonArray.length();i++){
//                                JSONObject row = jsonArray.getJSONObject(i);
//                                user.setImageURL(row.getString("imageURL"));
//                                user.setChurch(row.getString("church"));
//                                user.setEmail(row.getString("email"));
//                                user.setLocation(row.getString("location"));
//                                user.setName(row.getString("name"));
//                                user.setPassword(row.getString("password"));
//                                allUsers.add(user);
//                            }
//                            // Toast.makeText(context, jsonArray.toString(), Toast.LENGTH_LONG).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }}else{
//                            Log.d("ErrorRespnseEmpty","Empty#################");
//                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.d("LoginError",error.getMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
//                params.put(AppURLS.KEY_EMAIL, mEmailView.getText().toString());
//                params.put(AppURLS.KEY_PASSWORD, mPasswordView.getText().toString());
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }



}
