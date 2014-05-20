package advance.modelling.yourvisit;

import advance.modelling.yourvisit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

/* In this class we make the query that we are going to retrieve from
 * dbpedia. In the previous activity you selected which three things
 * you wanted to ask to dbpedia
 * 
 * We have the location in the first activity because we asked for it
 * to geonames using reverseGeocoding and lat and long provided by the
 * built-in GPS
 */
public class ShowAsk extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.show);

		/* Here is where we recover the name of the Country that we are in */
		String mCountryName = PreferenceManager.getDefaultSharedPreferences(
				getApplicationContext())
				.getString("CountryName", "No name set");

		// select ?LongName ?Capital ?TotalArea ?TypeMoney ?PoliticSystem{
		// ?country <http://www.w3.org/2000/01/rdf-schema#label>
		// "United Kingdom"@en ;
		// <http://dbpedia.org/ontology/longName> ?LongName ;
		// <http://dbpedia.org/property/capital> ?Capital ;
		// <http://dbpedia.org/ontology/areaTotal> ?TotalArea ;
		// <http://dbpedia.org/property/currencyCode> ?TypeMoney ;
		// <http://dbpedia.org/ontology/leaderTitle> ?PoliticSystem.
		// }

		String query = "select ";

		TextView mTextView = (TextView) findViewById(R.id.textViewShowQuery);

		Intent myIntent = getIntent();

		new AnimationUtils();
		Animation anim = AnimationUtils.makeInAnimation(
				getApplicationContext(), true);

		/*
		 * 
		 */
		switch (myIntent.getStringExtra("Spinner1")) {

		case "Complete Name":
			query = query + "?LongName ";
			break;
		case "Coin":
			query += "?TypeMoney ";
			break;
		case "Capital":
			query += "?Capital ";
			break;
		case "Area(Km)":
			query += "?TotalArea ";
			break;
		case "Political system":
			query += "?PoliticSystem ";
			break;
		}

		switch (myIntent.getStringExtra("Spinner2")) {

		case "Complete Name":
			query = query + "?LongName ";
			break;
		case "Coin":
			query += "?TypeMoney ";
			break;
		case "Capital":
			query += "?Capital ";
			break;
		case "Area(Km)":
			query += "?TotalArea ";
			break;
		case "Political system":
			query += "?PoliticSystem ";
			break;
		}

		switch (myIntent.getStringExtra("Spinner3")) {

		case "Complete Name":
			query = query + "?LongName {";
			break;
		case "Coin":
			query += "?TypeMoney {";
			break;
		case "Capital":
			query += "?Capital {";
			break;
		case "Area(Km)":
			query += "?TotalArea {";
			break;
		case "Political system":
			query += "?PoliticSystem {";
			break;
		}

		query = query
				+ "?Country <http://www.w3.org/2000/01/rdf-schema#label> "
				+ "\"" + mCountryName + "\"" + "@en ; ";

		switch (myIntent.getStringExtra("Spinner1")) {

		case "Complete Name":
			query = query
					+ "<http://dbpedia.org/ontology/longName> ?LongName ;";
			break;
		case "Coin":
			query += "<http://dbpedia.org/property/currencyCode> ?TypeMoney ;";
			break;
		case "Capital":
			query += "<http://dbpedia.org/property/capital> ?Capital ;";
			break;
		case "Area(Km)":
			query += "<http://dbpedia.org/ontology/areaTotal> ?TotalArea ;";
			break;
		case "Political system":
			query += "<http://dbpedia.org/ontology/leaderTitle> ?PoliticSystem ;";
			break;
		}

		switch (myIntent.getStringExtra("Spinner2")) {

		case "Complete Name":
			query = query
					+ "<http://dbpedia.org/ontology/longName> ?LongName ;";
			break;
		case "Coin":
			query += "<http://dbpedia.org/property/currencyCode> ?TypeMoney ;";
			break;
		case "Capital":
			query += "<http://dbpedia.org/property/capital> ?Capital ;";
			break;
		case "Area(Km)":
			query += "<http://dbpedia.org/ontology/areaTotal> ?TotalArea ;";
			break;
		case "Political system":
			query += "<http://dbpedia.org/ontology/leaderTitle> ?PoliticSystem ;";
			break;
		}

		switch (myIntent.getStringExtra("Spinner3")) {

		case "Complete Name":
			query = query
					+ "<http://dbpedia.org/ontology/longName> ?LongName . }";
			break;
		case "Coin":
			query += "<http://dbpedia.org/property/currencyCode> ?TypeMoney . }";
			break;
		case "Capital":
			query += "<http://dbpedia.org/property/capital> ?Capital . }";
			break;
		case "Area(Km)":
			query += "<http://dbpedia.org/ontology/areaTotal> ?TotalArea . }";
			break;
		case "Political system":
			query += "<http://dbpedia.org/ontology/leaderTitle> ?PoliticSystem . }";
			break;
		}
		mTextView.setText(query);
		Log.e("TS", query);

		mTextView.setAnimation(anim);
	}
}
