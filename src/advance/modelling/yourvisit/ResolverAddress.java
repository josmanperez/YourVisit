package advance.modelling.yourvisit;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

public class ResolverAddress extends AsyncTask<Void, Integer, Void>{
	
	private Context mContext;
	ProgressBar mProgressBar;
	
	public ResolverAddress(Context context) {
		context = this.mContext;
		
	}
	

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		mProgressBar.setVisibility(View.VISIBLE);
		super.onPreExecute();

	}
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		for (int i = 1; i < 11; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			publishProgress(i * 10);
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		mProgressBar.setProgress(values[0]);
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		mProgressBar.setVisibility(View.GONE);
		super.onPostExecute(result);
	}
}
