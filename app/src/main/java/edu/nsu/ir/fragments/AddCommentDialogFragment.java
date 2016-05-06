package edu.nsu.ir.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import edu.nsu.ir.R;
import edu.nsu.ir.api.ConnectServer;
import edu.nsu.ir.model.Comment;
import edu.nsu.ir.util.AppURLS;


/**
 * Created by thomaskofiannan1 on 4/7/16.
 */
public class AddCommentDialogFragment extends DialogFragment {
    private EditText etTitle,etDetail;
    private Button btnAddComment;
    private Comment comment;
    private Gson gson;
    private Bundle bundle;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //getting the post values
        comment= getArguments().getParcelable(AppURLS.KEY_COMMENT);
        final Dialog theDialog = new Dialog(getActivity());

        theDialog.setTitle("Add Comment");
        theDialog.setContentView(R.layout.add_comment);
        theDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        theDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        theDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        theDialog.show();
        etDetail=(EditText)theDialog.findViewById(R.id.etAddCommentDetails);
        etTitle=(EditText)theDialog.findViewById(R.id.etAddCommentTitle);
        btnAddComment=(Button)theDialog.findViewById(R.id.btnAddComment);
        comment.setDetails(etDetail.getText().toString());
        comment.setTitle(etTitle.getText().toString());

        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                ConnectServer.sendComment (comment);
                dismiss();
            }
        });

        return theDialog;

    }



    public void clearFields() {
        etDetail.setText("");
        etTitle.setText("");
    }


}
