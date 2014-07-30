package com.example.databasestudy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

	EditText edName, edEmail;
	DBAdapter db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edName = (EditText) findViewById(R.id.editText1);
		edEmail = (EditText) findViewById(R.id.editText2);

		db = new DBAdapter(this);
		
		//open databsase
		db.open();
		
		//perform opration
		
		//close db
		db.close();
		
		

	}

	public void CopyDB(InputStream inputStream, OutputStream outputStream)
			throws IOException {
		byte[] buffer = new byte[1024];
		int length;

		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();

	}

	public void displayContact(Cursor c) {
		Toast.makeText(this,
				c.getString(0) + " " + c.getString(1) + " " + c.getString(2),
				Toast.LENGTH_LONG).show();

	}

	
	public void onSave(View v) {

		String name = edName.getText().toString();
		String email = edEmail.getText().toString();

		db.open();
		long id = db.insertContact(name, email);

		db.close();

		Toast.makeText(getBaseContext(), "Data saved in database",
				Toast.LENGTH_LONG).show();

		// db.open();
		// Cursor c = db.getAllContacts();
		// if (c.moveToFirst()) {
		//
		// do {
		// displayContact(c);
		// } while (c.moveToNext());
		//
		// }
		//
		// db.close();

		Log.d("Gufran", "This is from log info ");

	}

	public void readData(View v) {

		db.open();

		Cursor c = db.getAllContacts();
		if (c.moveToFirst()) {

			do {
				String id = c.getString(0);
				
				String name = c.getString(1);
				String email = c.getString(2);

				Toast.makeText(getBaseContext(),
						"Details " + id + name + email, Toast.LENGTH_SHORT)
						.show();

			} while (c.moveToNext());

		}

		db.close();

	}

	public void onSaveData(View v) {
		String name = edName.getText().toString();
		String email = edEmail.getText().toString();

		db.open();

		db.insertContact(name, email);

		Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_SHORT)
				.show();

		db.close();

	}
	
	public void  onLogSomething(View v) {
		Log.d("GUFRAN","Hey you pressed button !");
	}
}
