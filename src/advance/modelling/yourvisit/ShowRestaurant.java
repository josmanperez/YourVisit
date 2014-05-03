package advance.modelling.yourvisit;

import advance.modelling.yourvistit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowRestaurant extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.show);

		TextView mTextView = (TextView) findViewById(R.id.textViewShowRestaurant);

		Intent myIntent = getIntent();

		mTextView.setText(myIntent.getStringExtra("Choice") + " - "
				+ myIntent.getStringExtra("Price") + "NOK - "
				+ myIntent.getStringExtra("Distance") + "m - " + "Lat: "
				+ myIntent.getDoubleExtra("Lat", 0) + " Long:"
				+ myIntent.getDoubleExtra("Long", 0));
	}
}
