package com.example.smartcity04.fragment;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.smartcity04.R;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.BasicViewModel;
import com.google.gson.JsonObject;

import java.io.File;

public class TestFragment1 extends Fragment {
    private static final String LOG_TAG = TestFragment1.class.getSimpleName();
    private View view;
    private ImageView imageView;
    private BasicViewModel basicViewModel;

    public TestFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basicViewModel = new ViewModelProvider(requireActivity()).get(BasicViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_test1, container, false);
        view.findViewById(R.id.btn_select_file_upload).setOnClickListener(v -> {
            selectImage();
        });
        imageView = view.findViewById(R.id.imageView);
        return view;
    }

    static final int REQUEST_IMAGE_OPEN = 1;

    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Only the system receives the ACTION_OPEN_DOCUMENT, so no need to test.
        startActivityForResult(intent, REQUEST_IMAGE_OPEN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            // Do work with full size photo saved at fullPhotoUri
            imageView.setImageURI(fullPhotoUri);
            String[] filePathColumn = {MediaStore.Images.Media.DATA };
            Cursor cursor = requireActivity().getContentResolver().query(fullPhotoUri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            File file = new File(picturePath);
            Log.d(LOG_TAG,"path:" + file.getAbsolutePath());
            basicViewModel.upload(CommonUtil.getToken(requireActivity()),file).observe(requireActivity(), new Observer<JsonObject>() {
                @Override
                public void onChanged(JsonObject jsonObject) {
                    Log.d(LOG_TAG,jsonObject.toString());
                }
            });

        }
    }
}