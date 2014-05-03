package advance.modelling.yourvisit;

import advance.modelling.yourvistit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Drinks extends Activity implements OnItemSelectedListener {

	Spinner spinnerPrices;
	Spinner spinnerDistance;
	private String mPrice;
	private String mDistance;

	Intent pastIntent;

	private final String TAG = "GPS-Tracking";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		pastIntent = getIntent();

		setContentView(R.layout.drinks_layout);

		spinnerPrices = (Spinner) findViewById(R.id.spinner1);
		spinnerDistance = (Spinner) findViewById(R.id.spinnerDistance);

		ArrayAdapter<CharSequence> adapterPrices = ArrayAdapter
				.createFromResource(this, R.array.range_prices_drinks,
						android.R.layout.simple_spinner_item);

		ArrayAdapter<CharSequence> adapterDistance = ArrayAdapter
				.createFromResource(this, R.array.range_distance_drinks,
						android.R.layout.simple_spinner_item);

		adapterPrices
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		adapterDistance
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerPrices.setAdapter(adapterPrices);
		spinnerDistance.setAdapter(adapterDistance);

		spinnerPrices.setOnItemSelectedListener(this);
		spinnerDistance.setOnItemSelectedListener(this);

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		// An item was selected. You can retrieve the selected item using
		// parent.getItemAtPosition(pos)
		mPrice = (String) spinnerPrices.getSelectedItem().toString();
		mDistance = (String) spinnerDistance.getSelectedItem().toString();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	
	public void generateDrinks(View v) {

		Intent i = new Intent(this, ShowDrinks.class);
		i.putExtra("Price", mPrice);
		i.putExtra("Distance", mDistance);
		RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupRestaurant);
		int aux = rg.getCheckedRadioButtonId();
		RadioButton rdb = (RadioButton) findViewById(aux);
		String mChoice = (String) rdb.getText();
		i.putExtra("Choice", mChoice);
		i.putExtra("Lat", pastIntent.getDoubleExtra("Lat", 0));
		i.putExtra("Long", pastIntent.getDoubleExtra("Long", 0));

		startActivity(i);

		Toast.makeText(this, mChoice + " " + mPrice + " " + mDistance,
				Toast.LENGTH_LONG).show();

	}
}
