package advance.modelling.yourvisit;

import advance.modelling.yourvisit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowHotel extends Activity {

	private final String TAG = "GPS-Tracking";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.show);

		TextView mTextView = (TextView) findViewById(R.id.textViewShowRestaurant);

		Intent myIntent = getIntent();

		Log.i(TAG, String.valueOf(myIntent.getFloatExtra("stars", 0)));

		if (myIntent.getStringExtra("Choice").equals("Hotel")) {
			mTextView.setText(myIntent.getStringExtra("Choice") + " - "
					+ myIntent.getStringExtra("Price") + "NOK - "
					+ myIntent.getStringExtra("Distance") + "m - "
					+ myIntent.getFloatExtra("Stars", 0) + " stars " + " Lat: "
					+ myIntent.getDoubleExtra("Lat", 0) + " - Long: "
					+ myIntent.getDoubleExtra("Long", 0));
		}

		if (myIntent.getStringExtra("Choice").equals("Motel")) {
			mTextView.setText(myIntent.getStringExtra("Choice") + " - "
					+ myIntent.getStringExtra("Price") + "NOK - "
					+ myIntent.getStringExtra("Distance") + "m - "
					+ "with breakfast: " + myIntent.getStringExtra("breakfast")
					+ " Lat: " + myIntent.getDoubleExtra("Lat", 0)
					+ " - Long: " + myIntent.getDoubleExtra("Long", 0));
		}
	}
}
