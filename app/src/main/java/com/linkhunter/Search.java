package com.linkhunter;

import android.app.*;
import android.os.*;
import android.view.*;
import android.support.v4.content.*;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.*;
import android.webkit.*;
import android.support.v4.widget.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;

public class Search extends AppCompatActivity 
{
	Toolbar toolbar;
	WebView wv;
	ProgressBar pb;
	SwipeRefreshLayout mSwipeRefreshLayout;
	
	String link;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

		if (Build.VERSION.SDK_INT >= 21) {
			getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.NavBar)); 
			getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.StatusBar)); 
		}
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("");
        setSupportActionBar(toolbar);
		String data = getIntent().getExtras().getString("key");
		link = data;
		wv = (WebView) findViewById(R.id.my_web);
		wv.setWebViewClient(new wap());
		wv.getSettings().setJavaScriptEnabled(true);
		wv.clearCache(true);
		wv.loadUrl(link);


		wv.setDownloadListener(new DownloadListener() {
				public void onDownloadStart(String url, String userAgent,
											String contentDisposition, String mimetype,
											long contentLength) {
					//kosong

				}
			});
		pb = (ProgressBar) findViewById(R.id.progress);
		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        // Mengeset listener yang akan dijalankan saat layar di refresh/swipe
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
				@Override
				public void onRefresh() {
					// Handler digunakan untuk menjalankan jeda selama 1 detik
					new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								// Berhenti berputar/refreshing
								if (!Koneksi.checkInternetConnection(Search.this)) {
									Toast.makeText(Search.this, "Tidak Terhubung Internet", Toast.LENGTH_SHORT).show();
									mSwipeRefreshLayout.setRefreshing(false);
									wv.loadUrl("file:///android_res/raw/error.html");
									AlertDialog alertDialog = new AlertDialog.Builder(Search.this).create();
									alertDialog.setTitle("Error");
									alertDialog.setCancelable(false);
									alertDialog.setMessage("Something Wrong...");
									alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try Again", new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog, int which) {
												wv.loadUrl(link);
											}
										});
									alertDialog.show();
								} else { 
									mSwipeRefreshLayout.setRefreshing(false);
									wv.loadUrl(link);}
							}
						},1000); //1000 millisecond = 1 detik 
				}
			}); return;}



	public class wap extends WebViewClient
	{
		public void onPageStarted(WebView view, String url, Bitmap favicon)
		{
			super.onPageStarted(view, url, favicon);
			pb.setVisibility(View.VISIBLE);
			pb.setProgress(0);

		}

		public boolean shouldOverrideUrlLoading(WebView view, String Url)
		{
			pb.setVisibility(View.VISIBLE);
			view.loadUrl(Url);


			if(Url != null)
			{                       

				
			}
			else
			{
				//kosong
			}
			return false;

		}




		public void onPageFinished(WebView view, String url)
		{

			pb.setVisibility(View.GONE);
			pb.setProgress(100);
			super.onPageFinished(view, url);
		}
		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingurl)
		{
			wv.loadUrl("file:///android_res/raw/error.html");
			AlertDialog alertDialog = new AlertDialog.Builder(Search.this).create();
			alertDialog.setTitle("Error");
			alertDialog.setCancelable(false);
			alertDialog.setMessage("Something Wrong...");
			alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try Again", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						wv.loadUrl(link);
					}
				});
			alertDialog.show();
			super.onReceivedError(view, errorCode, description,failingurl);
		}

		private class WebChromeClientDemo extends WebChromeClient {
			public void onProgressChanged(WebView view, int progress) {
				pb.setProgress(progress);
			}
		}

	}
	public boolean onPrepareOptionsMenu(Menu menu) {

		return super.onPrepareOptionsMenu(menu);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK)) 
		{
			Intent i = new Intent(Search.this,MainActivity.class);
			startActivity(i);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	//options Menu
	@Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.back:
				wv.goBack();
                return true;
            case R.id.copy:
				String ul = wv.getUrl().toString();
				if(ul.contains("cse.google.com")){
					Toast.makeText(Search.this,"Copy link failed!" ,Toast.LENGTH_LONG).show();
				}else{
				ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE); 
				ClipData clip = ClipData.newPlainText("Link : ", ul);
				clipboard.setPrimaryClip(clip);
                Toast.makeText(Search.this,"Copy link success!" ,Toast.LENGTH_LONG).show();}
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menusearch, menu); //menu nya ea
		menu.findItem(R.id.copy).setIcon(resizeImage(R.drawable.clip,180,180));
		menu.findItem(R.id.back).setIcon(resizeImage(R.drawable.arrow_left,180,180));
        return super.onCreateOptionsMenu(menu);
    }
	private Drawable resizeImage(int resId, int w, int h)
	{
		// load the origial Bitmap
		Bitmap BitmapOrg = BitmapFactory.decodeResource(getResources(), resId);
		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;
		// calculate the scale
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,width, height, matrix, true);
		return new BitmapDrawable(resizedBitmap);
	}
}

