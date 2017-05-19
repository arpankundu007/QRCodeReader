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

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    Button scanQR;
    TextView scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScannerView = new ZXingScannerView(MainActivity.this);
        scanQR = (Button) findViewById(R.id.scanbutton);

        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(mScannerView);
                mScannerView.setResultHandler( MainActivity.this);
                mScannerView.startCamera();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(final Result result) {
        //Do anything with result here :D
        Log.w("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText()).setCancelable(false);
        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                reinitViewsAndSetResult(result.getText().toString());
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Resume scanning
        //mScannerView.resumeCameraPreview(this);
    }

    public void reinitViewsAndSetResult(String string){
        setContentView(R.layout.activity_main);
        scanResult = (TextView) findViewById(R.id.textView);
        scanResult.setText(string);
    }
}
