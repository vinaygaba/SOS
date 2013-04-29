package gaba.cinay.sos;



import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class SOSActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	
	private ProgressBar progress;
	private boolean gps_enabled = false;
	private boolean network_enabled = false;
	public LocationManager locManager;
	private LocationListener locListener = new MyLocationListener();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button sos=(Button) findViewById(R.id.sos);
        Button phone=(Button) findViewById(R.id.phone);
        //EditText text1= (EditText)findViewById(R.id.text);
        final DatabaseHandler db = new DatabaseHandler(this);
        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
phone.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), Phone.class);
				startActivity(i);
			}	
			});


sos.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				// Launching News Feed Screen
				//Intent i = new Intent(getApplicationContext(), Settings.class);
				//startActivity(i);
				
		        try {
		    		gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		    	} catch (Exception ex) {
		    	}
		    	try {
		    		network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		    	} catch (Exception ex) {
		    	}

		    	// don't start listeners if no provider is enabled
		    	
		    	if (network_enabled) {
		    		locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
		    	}
		    	if (gps_enabled) {
		    		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
		    	}
				
				
			}
		});
    }
	public void onClick(DialogInterface arg0, int which) {
		// TODO Auto-generated method stub
		if(which == DialogInterface.BUTTON_NEUTRAL){
			//editTextShowLocation.setText("Sorry, location is not determined. To fix this please enable location providers");
		}else if 
		(which == DialogInterface.BUTTON_POSITIVE) {
			startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
		}
	}
	class MyLocationListener implements LocationListener {
		public void onLocationChanged(Location location) {
			if (location != null) {
				// This needs to stop getting the location data and save the battery power.
				locManager.removeUpdates(locListener); 
				
				String londitude = "\nLonditude: " + location.getLongitude();
				String latitude = "\nLatitude: " + location.getLatitude();
				String altitiude = "\nAltitiude: " + location.getAltitude();
				String accuracy = "\nAccuracy: " + location.getAccuracy();
				Date date = new Date();
				java.text.DateFormat dateFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext());
				String time = "\nTime: " + dateFormat.format(date);
				
				//sms
				SmsManager sm = SmsManager.getDefault();
				String message="Help!!My current location is:" +latitude+londitude+altitiude+accuracy+time;
				//String number="8082006723";
				// here is where the destination of the text should go
				/*String number = "";
				List<Contact> entries=db2.getAllContacts();
  		        if(entries.size()!=0)
  		     	{
  		        Contact link=db2.getContact(1);	
  				number=link.getName();
  		     	}*/
				//EditText text1= (EditText)findViewById(R.id.text);
				//text1.setText(message);
				Intent i = new Intent(getApplicationContext(), sendsms.class);
				i.putExtra("message", message);
				startActivity(i);
				//this.send1(message);
				//sm.sendTextMessage(number, null, message, null, null);
				//Toast.makeText(VoiceofText.this, "SMS sent", Toast.LENGTH_SHORT).show();
				//sms ends
				/*Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
				try {
					List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
					String address = "";
					 
					      if (addresses.size() > 0) {
					        for (int index = 0; 
						index < addresses.get(0).getMaxAddressLineIndex(); index++)
					          address += addresses.get(0).getAddressLine(index) + " ";
					        //editTextShowLocation.setText(address);
					        //VoiceofText.speakSMS("Your current location is "+address);
					      }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				//editTextShowLocation.setText(londitude + "\n" + latitude + "\n" + altitiude + "\n" + accuracy + "\n" + time);
				//progress.setVisibility(View.GONE);
			} 
		}

		

		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}

		
	}
	
	}
