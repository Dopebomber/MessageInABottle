package com.bilal.messageinabottle;

import java.util.List;

import com.example.messageinabottle.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText field;
	private Button store;
	private Button displayData;
	private String answer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "EHMG8Vyge2X5j4WIVySsqautcelpLReUPLKxTmel",
				"DxDsI9ooumMOiIEd48l0LcgXycWL9274cDraXcGW");
		setContentView(R.layout.activity_main);
		store = (Button) findViewById(R.id.storeButton);
		field = (EditText) findViewById(R.id.editText);
		displayData = (Button) findViewById(R.id.displayButton);

	}

	public void sendMessageClicked(View v) {
		ParseObject testObject = new ParseObject("myObject");
		testObject.put("Message", field.getText().toString());
		testObject.saveInBackground();
	}

	public void displayButtonClicked(View v) {

		ParseQuery<ParseObject> query = ParseQuery.getQuery("myObject");
		query.whereExists("Message");
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> results,
					com.parse.ParseException e) {
				TextView resultsText = (TextView) findViewById(R.id.resultsView);
				if (e == null) {

					resultsText.setText("");
					for (int i = 0; i < results.size(); i++) {
						resultsText.append(results.get(i).getString("Message"));
					}
				} else {

				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
