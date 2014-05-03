package advance.modelling.yourvisit;

import advance.modelling.yourvistit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class Hotels extends Activity implements OnItemSelectedListener {

	Spinner spinnerPrices;
	Spinner spinnerDistance;
	private String mPrice;
	private String mDistance;

	Intent pastIntent;

	private final String TAG = "GPS-Tracking";
	private RadioButton rbHotel;
	private RadioButton rbMotel;
	private RadioGroup rGBreakfast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		pastIntent = getIntent();

		setContentView(R.layout.hotel_layout);

		spinnerPrices = (Spinner) findViewById(R.id.spinner1);
		spinnerDistance = (Spinner) findViewById(R.id.spinnerDistance);

		ArrayAdapter<CharSequence> adapterPrices = ArrayAdapter
				.createFromResource(this, R.array.range_prices,
						android.R.layout.simple_spinner_item);

		ArrayAdapter<CharSequence> adapterDistance = ArrayAdapter
				.createFromResource(this, R.array.range_distance,
						android.R.layout.simple_spinner_item);

		adapterPrices
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		adapterDistance
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerPrices.setAdapter(adapterPrices);
		spinnerDistance.setAdapter(adapterDistance);

		spinnerPrices.setOnItemSelectedListener(this);
		spinnerDistance.setOnItemSelectedListener(this);

		rGBreakfast = (RadioGroup) findViewById(R.id.radioGroupHotel);

		rbHotel = (RadioButton) findViewById(R.id.radioButtonHotel);
		rbMotel = (RadioButton) findViewById(R.id.radioButtonMotel);

		rGBreakfast.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			TextView tvHotel = (TextView) findViewById(R.id.textViewRating);
			RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
			TextView tvMotel = (TextView) findViewById(R.id.textViewBreakfast);
			RadioGroup rGroupB = (RadioGroup) findViewById(R.id.radioGroupBreakfast);

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (checkedId == rbHotel.getId()) {
					if (tvMotel.getVisibility() == View.VISIBLE
							|| rGroupB.getVisibility() == View.VISIBLE) {
						tvMotel.setVisibility(View.GONE);
						rGroupB.setVisibility(View.GONE);
					}
					tvHotel.setVisibility(View.VISIBLE);
					ratingBar.setVisibility(View.VISIBLE);
				}

				if (checkedId == rbMotel.getId()) {
					if (tvHotel.getVisibility() == View.VISIBLE
							|| ratingBar.getVisibility() == View.VISIBLE) {
						tvHotel.setVisibility(View.GONE);
						ratingBar.setVisibility(View.GONE);
					}
					tvMotel.setVisibility(View.VISIBLE);
					rGroupB.setVisibility(View.VISIBLE);

				}
			}
		});

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		mPrice = (String) spinnerPrices.getSelectedItem().toString();
		mDistance = (String) spinnerDistance.getSelectedItem().toString();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	
	public void generateHotel(View v) {

		Intent i = new Intent(this, ShowHotel.class);
		i.putExtra("Price", mPrice);
		i.putExtra("Distance", mDistance);
		//RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupHotel);
		int aux = rGBreakfast.getCheckedRadioButtonId();
		RadioButton rdb = (RadioButton) findViewById(aux);
		String mChoice = (String) rdb.getText();
		i.putExtra("Choice", mChoice);
		if (mChoice.equals("Hotel")) {
			RatingBar rBar = (RatingBar) findViewById(R.id.ratingBar1);
			float stars = rBar.getRating();
			Log.i(TAG,String.valueOf(stars));
			i.putExtra("Stars", stars);
			
		}
		if (mChoice.equals("Motel")) {
			RadioGroup rgB = (RadioGroup) findViewById(R.id.radioGroupBreakfast);
			aux = rgB.getCheckedRadioButtonId();
			RadioButton rbB = (RadioButton) findViewById(aux);
			String breakfast = (String) rbB.getText();
			i.putExtra("breakfast", breakfast);
			
		}
		i.putExtra("Lat", pastIntent.getDoubleExtra("Lat", 0));
		i.putExtra("Long", pastIntent.getDoubleExtra("Long", 0));
//		
//		if (rdb == rbHotel) {
//			
//		}
//		
//		if (rdb == rbMotel) {
//			aux = rGBreakfast.getCheckedRadioButtonId();
//			RadioButton rbB = (RadioButton) findViewById(aux);
//			String breakfast = (String) rbB.getText();
//			i.putExtra("breakfast", breakfast);
//		}

		startActivity(i);
		
	}

}
