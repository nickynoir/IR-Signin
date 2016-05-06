package edu.nsu.ir.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import edu.nsu.ir.util.AppURLS;


/**
 * Created by thomaskofiannan1 on 4/7/16.
 */
public class ImagePickerDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Select your Source")
                .setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // camera!
                        Intent getCameraImage = new Intent("android.media.action.IMAGE_CAPTURE");
                        startActivityForResult(getCameraImage, AppURLS.ACTION_REQUEST_CAMERA);
                    }
                })
                .setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // GET IMAGE FROM THE GALLERY
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");

                        Intent chooser = Intent.createChooser(intent, "Choose a Picture");
                        startActivityForResult(chooser, AppURLS.ACTION_REQUEST_GALLERY);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
