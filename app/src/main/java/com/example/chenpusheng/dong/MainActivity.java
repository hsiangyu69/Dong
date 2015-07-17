package com.example.chenpusheng.dong;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.*;


public class MainActivity extends Activity {


    private Button scan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan_btn=(Button)findViewById(R.id.dong);
        scan_btn=(Button)findViewById(R.id.dong);
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this); //zxing integration android
                scanIntegrator.initiateScan();
                Intent intentservice = new Intent(MainActivity.this, ControlService.class);
                startService(intentservice);
            }
        });

        // Initialize the ViewPager and set an adapter

    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scanningResult!=null){
            String scanContent=scanningResult.getContents();
            String url=scanContent;
            Intent ie = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(ie);


        }else{
            Toast.makeText(getApplicationContext(), "No Scan Result!", Toast.LENGTH_SHORT).show();
        }
    }
}
