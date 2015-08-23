/**
 * Created by Akrabi on 19/08/2015.
 */

package com.example.akrabi.ezlauncher;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.akrabi.ezlauncher.R;

import java.util.Calendar;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void showApps(View v){
        Intent intent = new Intent(this, com.example.akrabi.ezlauncher.AppsListActivity.class);
        startActivity(intent);
    }

    public void addContactOnClick(View addContact){


        Button button = (Button) addContact;
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
            }
        });
    }
    public void findContactOnClick(View findContact){

        Button button = (Button) findContact;
        findContact.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
            }
        });
    }

    public void phoneOnClick(final View phone){

        Button button = (Button) phone;
        phone.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ""));
                startActivity(intent);
            }
        });
    }

    public void sendSMSOnClick(View sendSMS){

        Button button  = (Button) sendSMS;
        sendSMS.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"));
                startActivity(intent);
            }
        });
    }

    public void galleryOnClick(View gallery){

        Button button = (Button) gallery;
        gallery.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "content://media/internal/images/media"));
                startActivity(intent);
            }
        });
    }

    public void cameraOnClick(View camera){

        Button button = (Button) camera;
        camera.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });
    }

    public void EmailOnClick(View Email){


        Button button = (Button) Email;
        Email.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.email");
                startActivity(intent);
            }
        });
    }


    public void whatsAppOnClick(View whatsApp){

        boolean installed = isInstalled("com.whatsapp");
        if(installed){
            Button button = (Button) whatsApp;
            whatsApp.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick(View v){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.whatsapp");
                    startActivity(intent);
                }
            });
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "WhatsApp not installed\nPlease install WhatsApp and try again";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void settingsOnClick(View settings){

        Button button = (Button) settings;
        settings.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
            }
        });
    }

    public void silentOnClick(View silent){

        Button button = (Button) silent;
        silent.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                AudioManager audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                if(audio.getRingerMode() > 1){
                    audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                }
                else{
                    audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
            }
        });
    }

    public void googleSearchOnClick(View googleSearch){

        Button button = (Button) googleSearch;
        googleSearch.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                Intent intent = new Intent(SearchManager.INTENT_ACTION_GLOBAL_SEARCH);
                startActivity(intent);
            }
        });
    }

    public void calendarOnClick(View calendar){

        Button button = (Button) calendar;
        calendar.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://com.android.calendar/time/"));
                startActivity(intent);
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private boolean isInstalled(String uri){

        PackageManager pm = getPackageManager();
        boolean isInstalled;
        try{
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            isInstalled = true;
        }
        catch(PackageManager.NameNotFoundException e){
            isInstalled = false;
        }
        return isInstalled;
    }
}

