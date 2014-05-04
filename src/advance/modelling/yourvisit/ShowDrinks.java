package advance.modelling.yourvisit;

import advance.modelling.yourvisit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class ShowDrinks extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.show);

		TextView mTextView = (TextView) findViewById(R.id.textViewShowRestaurant);

		Intent myIntent = getIntent();
		
		new AnimationUtils();
		Animation anim = AnimationUtils.makeInAnimation(getApplicationContext(), true);
		

		mTextView.setText(myIntent.getStringExtra("Choice") + " - "
				+ myIntent.getStringExtra("Price") + "NOK - "
				+ myIntent.getStringExtra("Distance") + "m - " + "Lat: "
				+ myIntent.getDoubleExtra("Lat", 0) + " Long:"
				+ myIntent.getDoubleExtra("Long", 0));
		
		mTextView.setAnimation(anim);
	}
}
