package com.gzeinnumer.phonecall;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.btn)
    Button btn;
    private String sNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        sNum = number.getText().toString();
        if (TextUtils.isEmpty(sNum))
            Toast.makeText(this, "Masukan no dulu dong", Toast.LENGTH_SHORT).show();
        else
            intent.setData(Uri.parse("tel:"+sNum));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Kasih izin dulu dong!!", Toast.LENGTH_SHORT).show();
            requestPermition();
        } else {
            startActivity(intent);
        }
    }

    private void requestPermition() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
