package gaba.cinay.sos;

import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Phone extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        Button save=(Button) findViewById(R.id.save);
        final EditText phone= (EditText)findViewById(R.id.phone);
        final String KEY_ID = "id";
    	final String KEY_NAME = "name";
        final String KEY_PH_NO = "phone_number";
        final DatabaseHandler db = new DatabaseHandler(this);
List<Contact> entries=db.getAllContacts();
        
        if(entries.size()!=0)
     	{
        Contact link=db.getContact(1);	
		String number1=link.getName();
		phone.setText(number1);
     	}
save.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				
			String number2=phone.getText().toString();
			//db.addContact(new Contact(" ", number));
			if(number2.length()==0)
	         {
	        	Toast.makeText(Phone.this, "Please enter a Phone Number", Toast.LENGTH_SHORT).show();
	         }
			else
	         {
	        	List<Contact> entries2=db.getAllContacts();
	        	if(entries2.size()==0)
	        	{
	        		db.addContact(new Contact("123", ""));   
	        	}
	         db.updateContact(new Contact(number2, ""));
	         
	        Toast.makeText(Phone.this, "Phone Number Saved", Toast.LENGTH_SHORT).show();
	         }
			
			}
			});

        
	}

	
	
}
