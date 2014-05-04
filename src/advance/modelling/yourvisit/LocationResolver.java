package advance.modelling.yourvisit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import advance.modelling.yourvisit.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LocationResolver extends Activity {

	Location location;
	PlaceRecord place = null;
	private final String TAG = "GPS-Tracking";

	// Change to false if you don't have network access
	private static boolean HAS_NETWORK = true;

	double latitude;
	double longitude;

	ProgressBar mProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.location_resolver);

		Intent pastIntent = getIntent();

		mProgressBar = (ProgressBar) findViewById(R.id.progressBarLocationResolver);

		latitude = pastIntent.getDoubleExtra("Latitude", 0);
		longitude = pastIntent.getDoubleExtra("Longitude", 0);

		// Check if we really had a GPS unit working on the device
		if ((latitude == 0.0) && (longitude == 0.0)) {
			HAS_NETWORK = false;
			Log.i(TAG,
					String.valueOf(latitude + longitude) + " "
							+ String.valueOf(HAS_NETWORK));
		}

		Log.i(TAG,
				String.valueOf(latitude + longitude) + " "
						+ String.valueOf(HAS_NETWORK));

		location = new Location("locationGeoNames");
		location.setLatitude(latitude);
		location.setLongitude(longitude);

		// PlaceRecord placeRecord = new PlaceRecord(location);

		new PlaceDownloaderTask2().execute(location);

		// String countryName = placeRecord.getCountryName();
		// Log.i("Resolver", countryName);
		// Toast.makeText(this, countryName, Toast.LENGTH_LONG).show();

	}

	public void showMap(View v) {
		if (place == null) {
			Toast.makeText(getApplicationContext(),
					"Please find a location first", Toast.LENGTH_LONG).show();

		} else {
			Intent i = new Intent(this, ViewMap.class);
			i.putExtra("lat", latitude);
			i.putExtra("lon", longitude);
			i.putExtra("place",place.getPlace());
			startActivity(i);

		}

	}

	class PlaceDownloaderTask2 extends
			AsyncTask<Location, Integer, PlaceRecord> {

		Bitmap bmp;

		// TODO - put your www.geonames.org account name here.
		private static final String USERNAME = "josmanperez";

		private HttpURLConnection mHttpUrl;
		// private WeakReference<PlaceViewActivity> mParent;
		private final Bitmap mStubBitmap = null;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected PlaceRecord doInBackground(Location... location) {

			if (HAS_NETWORK) {

				place = getPlaceFromURL(generateURL(USERNAME, location[0]));

				if ("" != place.getCountryName()) {
					place.setLocation(location[0]);
				} else {
					Log.i(TAG, "null");
					place = null;
				}

			} else {
				place = new PlaceRecord(location[0]);
				// Log.i(TAG,location[0].toString());
				place.setCountryName("No country name");
				place.setPlace("No place name");
				place.setAdminName1("No admin name");
				place.setFlagBitmap(mStubBitmap);

			}

			return place;

		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			mProgressBar.setProgress(values[0]);
			super.onProgressUpdate(values);
		}

		private PlaceRecord getPlaceFromURL(String... params) {
			String result = null;
			BufferedReader in = null;

			try {

				URL url = new URL(params[0]);
				mHttpUrl = (HttpURLConnection) url.openConnection();
				in = new BufferedReader(new InputStreamReader(
						mHttpUrl.getInputStream()));

				StringBuffer sb = new StringBuffer("");
				String line = "";
				while ((line = in.readLine()) != null) {
					sb.append(line + "\n");
				}
				result = sb.toString();

			} catch (MalformedURLException e) {

			} catch (IOException e) {

			} finally {
				try {
					if (null != in) {
						in.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				mHttpUrl.disconnect();
			}

			// Log.e(TAG,"result: " +result);
			return placeDataFromXml(result);
		}

		private PlaceRecord placeDataFromXml(String xmlString) {
			DocumentBuilder builder;
			String countryName = "";
			String countryCode = "";
			String placeName = "";
			String adminName1 = "";

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			try {
				builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(
						new StringReader(xmlString)));
				NodeList list = document.getDocumentElement().getChildNodes();
				for (int i = 0; i < list.getLength(); i++) {
					Node curr = list.item(i);

					NodeList list2 = curr.getChildNodes();

					for (int j = 0; j < list2.getLength(); j++) {

						Node curr2 = list2.item(j);
						if (curr2.getNodeName() != null) {

							if (curr2.getNodeName().equals("countryName")) {
								countryName = curr2.getTextContent();
							} else if (curr2.getNodeName()
									.equals("countryCode")) {
								countryCode = curr2.getTextContent();
							} else if (curr2.getNodeName().equals("name")) {
								placeName = curr2.getTextContent();
							} else if (curr2.getNodeName().equals("adminName1")) {
								adminName1 = curr2.getTextContent();
							}
						}
					}
				}
			} catch (DOMException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Toast.makeText(getApplicationContext(), "nombre: "+countryName,
			// Toast.LENGTH_LONG).show();

			// Log.e(TAG, "nombre: " + countryName);
			// Log.e(TAG, "place: " + placeName);

			URL url;
			try {
				url = new URL(generateFlagURL(countryCode.toLowerCase()));
				bmp = BitmapFactory.decodeStream(url.openConnection()
						.getInputStream());
				// // imageFlag.setImageBitmap(bmp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
			}

			return new PlaceRecord(generateFlagURL(countryCode.toLowerCase()),
					countryName, placeName, adminName1);
		}

		private String generateURL(String username, Location location) {

			Log.i(TAG, "http://www.geonames.org/findNearbyPlaceName?username="
					+ username + "&style=full&lat=" + location.getLatitude()
					+ "&lng=" + location.getLongitude());

			return "http://www.geonames.org/findNearbyPlaceName?username="
					+ username + "&style=full&lat=" + location.getLatitude()
					+ "&lng=" + location.getLongitude();

		}

		private String generateFlagURL(String countryCode) {
			return "http://www.geonames.org/flags/x/" + countryCode + ".gif";
		}

		@Override
		protected void onPostExecute(PlaceRecord result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			mProgressBar.setVisibility(View.GONE);

			// Refresh the values
			ImageView imageFlag = (ImageView) findViewById(R.id.imageViewFlag);

			Animation anim = AnimationUtils.makeInAnimation(
					getApplicationContext(), true);

			imageFlag.setImageBitmap(bmp);
			imageFlag.startAnimation(anim);

			if (place != null) {
				TextView textVCountry = (TextView) findViewById(R.id.textViewCountryName);
				textVCountry.setText(place.getCountryName());
				TextView textVAdminName1 = (TextView) findViewById(R.id.textViewResolverAdminName1);
				textVAdminName1.setText(place.getAdminName1());
				TextView textVPlace = (TextView) findViewById(R.id.textViewResolverPlace);
				textVPlace.setText(place.getPlace());
				Button imageMap = (Button) findViewById(R.id.imageButtonMap);
				imageMap.setVisibility(View.VISIBLE);
			}

			if (!HAS_NETWORK) {
				Toast.makeText(getApplicationContext(),
						"Your Lat and Long are wrong, please try again",
						Toast.LENGTH_LONG).show();
			}

		}
	}

}