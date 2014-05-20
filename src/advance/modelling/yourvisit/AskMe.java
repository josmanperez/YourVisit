package advance.modelling.yourvisit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import advance.modelling.yourvisit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/* this class is for handling the questions that you want to ask in your query on sparql
 * we pass the name obtained on the activity that retrieves the name on geoNames using
 * SharedPreferences provided by Android */

public class AskMe extends Activity {

	Spinner spinnerType1;
	Spinner spinnerType2;
	Spinner spinnerType3;
	private String mType1;
	private String mType2;
	private String mType3;

	Intent pastIntent;

	private final String TAG = "GPS-Tracking";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		/* Here is where we recover the name of the Country that we are in */
		String CountryName = PreferenceManager.getDefaultSharedPreferences(
				getApplicationContext())
				.getString("CountryName", "No name set");


		pastIntent = getIntent();

		setContentView(R.layout.ask_layout);

		/*
		 * this part of the code is for displaying the spinner with the selected
		 * choice
		 */
		spinnerType1 = (Spinner) findViewById(R.id.spinner_type1);
		spinnerType2 = (Spinner) findViewById(R.id.spinner_type2);
		spinnerType3 = (Spinner) findViewById(R.id.spinner_type3);


		String[] arrayShow = getResources().getStringArray(R.array.range_types);

		ArrayList<String> lst = new ArrayList<String>();
		lst.addAll(Arrays.asList(arrayShow));


		final ArrayAdapter<String> adapterType1 = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, lst);

		final ArrayAdapter<String> adapterType2 = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, lst);

		final ArrayAdapter<String> adapterType3 = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, lst);

		adapterType1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		adapterType2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		adapterType3
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerType1.setAdapter(adapterType1);
		spinnerType2.setAdapter(adapterType2);
		spinnerType3.setAdapter(adapterType3);

		spinnerType1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				mType1 = (String) spinnerType1.getSelectedItem().toString();

				if (adapterType1.getItem(arg2) == adapterType2
						.getItem(spinnerType2.getSelectedItemPosition())) {
					Random r = new Random();
					int i1 = r.nextInt(adapterType2.getCount() - 0);
					while (i1 == arg2)
						i1 = r.nextInt(adapterType2.getCount() - 0);
					spinnerType2.setSelection(i1);
				}
				if (adapterType1.getItem(arg2) == adapterType3
						.getItem(spinnerType3.getSelectedItemPosition())) {
					Random r = new Random();
					int i1 = r.nextInt(adapterType3.getCount() - 0);
					while (i1 == arg2)
						i1 = r.nextInt(adapterType3.getCount() - 0);
					spinnerType3.setSelection(i1);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		spinnerType2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				mType2 = (String) spinnerType2.getSelectedItem().toString();

				if (adapterType2.getItem(arg2) == adapterType1
						.getItem(spinnerType1.getSelectedItemPosition())) {
					Random r = new Random();
					int i1 = r.nextInt(adapterType1.getCount() - 0);
					while (i1 == arg2)
						i1 = r.nextInt(adapterType1.getCount() - 0);
					spinnerType1.setSelection(i1);
				}

				if (adapterType2.getItem(arg2) == adapterType3
						.getItem(spinnerType3.getSelectedItemPosition())) {
					Random r = new Random();
					int i1 = r.nextInt(adapterType3.getCount() - 0);
					while (i1 == arg2)
						i1 = r.nextInt(adapterType3.getCount() - 0);
					spinnerType3.setSelection(i1);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		spinnerType3.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				mType3 = (String) spinnerType3.getSelectedItem().toString();
				// TODO Auto-generated method stub
				if (adapterType3.getItem(arg2) == adapterType2
						.getItem(spinnerType2.getSelectedItemPosition())) {
					Random r = new Random();
					int i1 = r.nextInt(adapterType2.getCount() - 0);
					while (i1 == arg2)
						i1 = r.nextInt(adapterType2.getCount() - 0);
					spinnerType2.setSelection(i1);
				}
				if (adapterType3.getItem(arg2) == adapterType1
						.getItem(spinnerType1.getSelectedItemPosition())) {
					Random r = new Random();
					int i1 = r.nextInt(adapterType1.getCount() - 0);
					while (i1 == arg2)
						i1 = r.nextInt(adapterType1.getCount() - 0);
					spinnerType1.setSelection(i1);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	/* This is for generate the selected query */
	public void generateQuery(View v) {

		Intent i = new Intent(this, ShowAsk.class);
		i.putExtra("Spinner1", mType1);
		i.putExtra("Spinner2", mType2);
		i.putExtra("Spinner3", mType3);
		// i.putExtra("Lat", pastIntent.getDoubleExtra("Lat", 0));
		// i.putExtra("Long", pastIntent.getDoubleExtra("Long", 0));
		startActivity(i);

	}
}
