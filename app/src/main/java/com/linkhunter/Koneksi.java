package com.linkhunter;

import android.webkit.*;
import android.content.*;
import android.net.*;

public class Koneksi extends WebViewClient {             
	public static boolean checkInternetConnection(Context context) {   

		ConnectivityManager con_manager = (ConnectivityManager) 
			context.getSystemService(Context.CONNECTIVITY_SERVICE);

		return (con_manager.getActiveNetworkInfo() != null
			&& con_manager.getActiveNetworkInfo().isAvailable()
			&& con_manager.getActiveNetworkInfo().isConnected());
	}
}


