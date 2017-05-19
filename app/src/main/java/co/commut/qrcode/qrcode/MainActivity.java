package co.commut.qrcode.qrcode;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity {

    Button scanQR;
    TextView scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanResult = (TextView) findViewById(R.id.textView);
        scanQR = (Button) findViewById(R.id.scanbutton);
        if(getIntent().getStringExtra("scanData") != null) {
            Log.v("intent stat", "Intent not null");
            Intent receive = getIntent();
            String data = receive.getStringExtra("scanData");
            scanResult.setText(data.toString());
        }
        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });
    }
}
