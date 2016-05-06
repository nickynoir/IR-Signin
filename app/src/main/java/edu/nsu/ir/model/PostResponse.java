package edu.nsu.ir.model;

import java.util.ArrayList;

/**
 * Created by thomaskofiannan1 on 3/15/16.
 */
public class PostResponse {
    private int success;
    private ArrayList<String> message;

    public PostResponse() {
    }

    public PostResponse(int success, ArrayList<String> message) {
        this.success = success;
        this.message = message;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public ArrayList<String> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<String> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "success=" + success +
                ", message=" + message +
                '}';
    }
}
