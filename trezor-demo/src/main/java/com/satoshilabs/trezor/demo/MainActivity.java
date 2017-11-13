package com.satoshilabs.trezor.demo;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.satoshilabs.trezor.lib.ui.activity.TrezorActivity;
import com.satoshilabs.trezor.lib.ui.data.GetPublicKeyRequest;
import com.satoshilabs.trezor.lib.ui.data.GetPublicKeyResult;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_GET_PUBLIC_KEY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.plant(new Timber.DebugTree());

        Button exportPublicKeyBtn = findViewById(R.id.btn_export_public_key);
        exportPublicKeyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] path = new int[] {44, 0, 0};
                Intent intent = TrezorActivity.createIntent(MainActivity.this,
                    new GetPublicKeyRequest(path));
                startActivityForResult(intent, REQUEST_GET_PUBLIC_KEY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_GET_PUBLIC_KEY:
                if (resultCode == RESULT_OK) {
                    GetPublicKeyResult result = (GetPublicKeyResult) TrezorActivity.getResult(data);
                    new AlertDialog.Builder(this)
                        .setMessage(result.getXPubKey())
                        .show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}