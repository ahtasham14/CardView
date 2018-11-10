package com.example.hp.cardview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class sendReminder extends AppCompatActivity {

    ImageView sendmessage;
    String API = "ApdxGzrLY5c-8jFQI96L2uE3MMgBNPVOJHZwNCdbEW";
    EditText ed1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_reminder);

        ed1 = findViewById(R.id.message);

        sendmessage = findViewById(R.id.sendmessage);
        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    // Construct data
                    String apiKey = API;
                    String message = ed1.getText().toString();
                    String sender = "&sender=" + "TXTLCL";
                    String numbers = "&numbers=" + "03017879828";

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                     //   stringBuffer.append(line);

                        Toast.makeText(getApplicationContext(), "This message is "+line, Toast.LENGTH_SHORT).show();
                    }
                    rd.close();

                   // return stringBuffer.toString();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error Message is "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                  //  return "Error "+e;
                }


            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        MaterialSpinner spinner = findViewById(R.id.spinner);
        spinner.setItems("Ahtasham Ul Hassan (+92-301-7879828)", "Hashim Shafiq (+92-320-6717208)", "Muneeb Jan (+92-342-5099984)", "Muttyab Usama (+92-312-5755236)", "Kaleem Ur Rehman (+92-324-5271377)", "Mumtaz Hussain (+92-334-5489131)");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void requestSmsPermission() {
        String permission = Manifest.permission.READ_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(sendReminder.this,"permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(sendReminder.this,"permission not granted", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
