package edu.nsu.ir.model;

/**
 * Created by thomaskofiannan1 on 3/15/16.
 */
public class StatusReponse {
    private int success;
    private String message;

    public StatusReponse() {
    }

    public StatusReponse(int success, String message) {
        this.success = success;
        this.message = message;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StatusReponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
