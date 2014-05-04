package advance.modelling.yourvisit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ViewMap extends Activity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.view_map);

		Intent intent = getIntent();
		double latitude = intent.getDoubleExtra("lat", 0);
		double longitude = intent.getDoubleExtra("lon", 0);
		String place = intent.getStringExtra("place");

		LatLng latLng = new LatLng(latitude, longitude);

		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();

		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

		Marker marker = map.addMarker(new MarkerOptions().position(latLng)
				.title("Where am I?").snippet(place));

		map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

		map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

	}
}
