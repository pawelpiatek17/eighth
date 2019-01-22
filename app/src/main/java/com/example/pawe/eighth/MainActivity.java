package com.example.pawe.eighth;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savePrivateOnInternal();
        savePrivateOnExternal();
        savePublicOnExternal();
    }

    private void savePrivateOnExternal() {
        if (isExternalStorageWritable()){
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                    "albumName");
            if (!file.mkdirs()){
                Toast.makeText(this, "Not created", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void savePublicOnExternal() {
        if (isExternalStorageWritable()){
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                    "albumName");
            if (!file.mkdirs()){
                Toast.makeText(this, "Not created", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void savePrivateOnInternal() {
        FileOutputStream outputStream;
        String fileContents = "Hello world";
        try {
            outputStream = openFileOutput("file", Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}
