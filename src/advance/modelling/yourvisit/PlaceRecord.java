package advance.modelling.yourvisit;

import android.graphics.Bitmap;
import android.location.Location;

public class PlaceRecord {
	private String mFlagUrl;
	private String mCountryName;
	private String mPlaceName;
	private Bitmap mFlagBitmap;
	private Location mLocation;
	private String mAdminName1;

	public PlaceRecord(String flagUrl, String country, String place, String adminName) {
		this.mFlagUrl = flagUrl;
		this.mCountryName = country;
		this.mPlaceName = place;
		this.mAdminName1 = adminName;
	}

	public PlaceRecord(Location location) {
		mLocation = location;
	}

	public String getFlagUrl() {
		return mFlagUrl;
	}

	public void setFlagUrl(String flagUrl) {
		this.mFlagUrl = flagUrl;
	}

	public String getCountryName() {
		return mCountryName;
	}
	
	public String getAdminName1() {
		return mAdminName1;
	}
	
	public void setAdminName1(String adminName) {
		this.mAdminName1 = adminName;
	}

	public void setCountryName(String country) {
		this.mCountryName = country;
	}

	public String getPlace() {
		return mPlaceName;
	}

	public void setPlace(String place) {
		this.mPlaceName = place;
	}

	public Bitmap getFlagBitmap() {
		return mFlagBitmap;
	}

	public void setFlagBitmap(Bitmap mFlagBitmap) {
		this.mFlagBitmap = mFlagBitmap;
	}

	public boolean intersects(Location location) {

		double tolerance = 1000;

		return (mLocation.distanceTo(location) <= tolerance);

	}

	public void setLocation(Location location) {
		mLocation = location;
	}

	public Location getLocation() {
		return mLocation;
	}
	
	@Override
	public String toString(){
		return "Place: " + mPlaceName + " Country: " + mCountryName;
		
	}
}
