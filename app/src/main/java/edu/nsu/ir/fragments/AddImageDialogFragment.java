package edu.nsu.ir.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by thomaskofiannan1 on 4/7/16.
 */
public class AddImageDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());

        theDialog.setTitle("Add Image");
        theDialog.setMessage("Do you want to add an image to your post");

        theDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Pop up image picker", Toast.LENGTH_SHORT).show();
                ImagePickerDialogFragment myFragment = new ImagePickerDialogFragment();
                myFragment.show(getFragmentManager(), "theDialog");
//                    showFileChooser();

            }
        });

        theDialog.setNegativeButton("No ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "exit to list view", Toast.LENGTH_SHORT).show();
            }
        });

        return theDialog.create(); // from code, not video
    }


}

