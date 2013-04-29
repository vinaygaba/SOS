package gaba.cinay.sos;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

public class sendsms extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);
        SmsManager sm = SmsManager.getDefault();
        DatabaseHandler db= new DatabaseHandler(this);
        Bundle extras = getIntent().getExtras(); 
        String message="";
        if(extras !=null) {
            message = extras.getString("message");
            
        }
		List<Contact> entries=db.getAllContacts();
		String number="";
	        if(entries.size()!=0)
	     	{
	        Contact link=db.getContact(1);	
			number=link.getName();
	     	}
	        sm.sendTextMessage(number, null, message, null, null);
	        Toast.makeText(sendsms.this, "SMS sent", Toast.LENGTH_SHORT).show();
	        Intent i = new Intent(getApplicationContext(), SOSActivity.class);
			startActivity(i);
	}

	

	
}
