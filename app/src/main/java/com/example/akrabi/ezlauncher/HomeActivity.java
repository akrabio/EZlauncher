/**
 * Created by Akrabi on 19/08/2015.
 */

package com.example.akrabi.ezlauncher;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.akrabi.ezlauncher.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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


                Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
    }

    public void findContactOnClick(View findContact){


                Intent intent = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
            }


    public void phoneOnClick(final View phone){

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ""));
                startActivity(intent);
            }

    public void sendSMSOnClick(View sendSMS){

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"));
                startActivity(intent);
            }


    public void galleryOnClick(View gallery){

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "content://media/internal/images/media"));
                startActivity(intent);
            }

    public void cameraOnClick(View camera){


                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }

    public void EmailOnClick(View Email){

                Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.email");
                startActivity(intent);
            }


    public void whatsAppOnClick(View whatsApp){

        boolean installed = isInstalled("com.whatsapp");
        if(installed){

                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("com.whatsapp"));
    //                intent.setPackage("com.whatsapp");
                    startActivity(intent);
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

                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
            }


    public void silentOnClick(View silent){

                AudioManager audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                if(audio.getRingerMode() > 1){
                    audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                }
                else{
                    audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
    }

    public void googleSearchOnClick(View googleSearch){

                Intent intent = new Intent(SearchManager.INTENT_ACTION_GLOBAL_SEARCH);
                startActivity(intent);
            }

    public void calendarOnClick(View calendar){

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("com.google.android.calendar"));
                startActivity(intent);
            }



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

