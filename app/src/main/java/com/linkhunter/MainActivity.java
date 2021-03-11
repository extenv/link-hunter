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
import android.content.*;
import java.util.*;
import android.widget.AdapterView.*;
import android.util.*;
import android.text.*;

public class MainActivity extends AppCompatActivity 
{
	Toolbar toolbar;
	EditText uRl;
	ImageButton bt;
	ListView listView;
	Spinner spinner;
	ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
	SharedPreferences shared;
	String value,cek;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		if (Build.VERSION.SDK_INT >= 21) {
			getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.NavBar)); 
			getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.StatusBar)); 
		}
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		
        setSupportActionBar(toolbar);
		uRl = (EditText) findViewById(R.id.link);
		bt = (ImageButton) findViewById(R.id.bt);
		 spinner = (Spinner) findViewById(R.id.spin);
		ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
            .createFromResource(this, R.array.target,
								android.R.layout.simple_spinner_dropdown_item);
			staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		listView = (ListView) findViewById(R.id.lv);
		listItems = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, listItems);
		listView.setAdapter(adapter);
		
		
		
		//RECENT SEACRH
		listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
										int position, long id) {
					value= adapter.getItem(position);
					
					uRl.setText(value);
					adapter.notifyDataSetChanged();
					if (spinner.getSelectedItemPosition() == 0)
					{
						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									//JIKA TIDAK PILIH TARGET
									Toast.makeText(MainActivity.this,"Please Select Target !" ,Toast.LENGTH_SHORT).show();

								}
							});
					}
					else if(spinner.getSelectedItemPosition() == 1)
					{

						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									//ZippyShare
										Intent i = new Intent(MainActivity.this,Search.class);
										i.putExtra("key","https://cse.google.com/cse?cx=partner-pub-2958868595034693:9969718365&q=" + uRl.getText().toString());
										startActivity(i);
										finish();
										listItems.add(uRl.getText().toString());
										save();
										adapter.notifyDataSetChanged();
									
								}
							});
					}
					else if (spinner.getSelectedItemPosition() == 2)
					{
						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									//GoogleDrive
									Intent i = new Intent(MainActivity.this,Search.class);
									i.putExtra("key","https://cse.google.com/cse?cx=010187004032568060719:iwsy79yqaek&q=" + uRl.getText().toString());
									startActivity(i);
									finish();
									listItems.add(uRl.getText().toString());
									save();
									adapter.notifyDataSetChanged();
								}
							});
					}
					else if (spinner.getSelectedItemPosition() == 3)
					{
						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									//Solidfiles
									Intent i = new Intent(MainActivity.this,Search.class);
									i.putExtra("key","https://cse.google.com/cse?cx=010187004032568060719:g892t2pmihu&q=" + uRl.getText().toString());
									startActivity(i);
									finish();
									listItems.add(uRl.getText().toString());
									save();
									adapter.notifyDataSetChanged();
								}
							});
					}
					else if (spinner.getSelectedItemPosition() == 4)
					{
						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									//Mega
									Intent i = new Intent(MainActivity.this,Search.class);
									i.putExtra("key","https://cse.google.com/cse?cx=010187004032568060719:vhrvrth0luc&q=" + uRl.getText().toString());
									startActivity(i);
									finish();
									listItems.add(uRl.getText().toString());
									save();
									adapter.notifyDataSetChanged();
								}
							});
					}
					else if (spinner.getSelectedItemPosition() == 5)
					{
						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									//Uptobox
									Intent i = new Intent(MainActivity.this,Search.class);
									i.putExtra("key","https://cse.google.com/cse?cx=010187004032568060719:klsgmozfmxy&q=" + uRl.getText().toString());
									startActivity(i);
									finish();
									listItems.add(uRl.getText().toString());
									save();
									adapter.notifyDataSetChanged();
								}
					});}
				}
			});
		shared = getSharedPreferences("App_settings",Context.MODE_PRIVATE);
		//check
		Set<String> x = shared.getStringSet("DATA_LIST",null);
		if(x == null){
		
		}else{
			read();
		}
		
	
		//SPINNER TARGET
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
										   int position, long id) {
					
					cek = uRl.getText().toString().trim();
					if (spinner.getSelectedItemPosition() == 0)
					{
						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									//JIKA TIDAK PILIH TARGET
									Toast.makeText(MainActivity.this,"Please Select Target !" ,Toast.LENGTH_SHORT).show();

								}
							});
					}
					else if(spinner.getSelectedItemPosition() == 1)
					{

						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									
									//Zippyshare
									if(TextUtils.isEmpty(cek)){
										Toast.makeText(MainActivity.this,"Please Enter Text to Search !" ,Toast.LENGTH_SHORT).show();
									}else{
									Intent i = new Intent(MainActivity.this,Search.class);
									i.putExtra("key","https://cse.google.com/cse?cx=partner-pub-2958868595034693:9969718365&q=" + uRl.getText().toString());
									startActivity(i);
									finish();
									listItems.add(uRl.getText().toString());
									save();
									adapter.notifyDataSetChanged();}
								}
							});
					}
					else if (spinner.getSelectedItemPosition() == 2)
					{
						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									//GoogleDrive
									if(TextUtils.isEmpty(cek)){
									Toast.makeText(MainActivity.this,"Please Enter Text to Search !" ,Toast.LENGTH_SHORT).show();
								}else{
									Intent i = new Intent(MainActivity.this,Search.class);
									i.putExtra("key","https://cse.google.com/cse?cx=010187004032568060719:iwsy79yqaek&q=" + uRl.getText().toString());
									startActivity(i);
									finish();
									listItems.add(uRl.getText().toString());
									save();
									adapter.notifyDataSetChanged();
								}}
							});
					}
					else if (spinner.getSelectedItemPosition() == 3)
					{
						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									//Solidfiles
									if(TextUtils.isEmpty(cek)){
										Toast.makeText(MainActivity.this,"Please Enter Text to Search !" ,Toast.LENGTH_SHORT).show();
									}else{
									Intent i = new Intent(MainActivity.this,Search.class);
									i.putExtra("key","https://cse.google.com/cse?cx=010187004032568060719:g892t2pmihu&q=" + uRl.getText().toString());
									startActivity(i);
									finish();
									listItems.add(uRl.getText().toString());
									save();
									adapter.notifyDataSetChanged();
								}}
							});
					}
					else if (spinner.getSelectedItemPosition() == 4)
					{
						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									//Mega
									if(TextUtils.isEmpty(cek)){
										Toast.makeText(MainActivity.this,"Please Enter Text to Search !" ,Toast.LENGTH_SHORT).show();
									}else{
									Intent i = new Intent(MainActivity.this,Search.class);
									i.putExtra("key","https://cse.google.com/cse?cx=010187004032568060719:vhrvrth0luc&q=" + uRl.getText().toString());
									startActivity(i);
									finish();
									listItems.add(uRl.getText().toString());
									save();
									adapter.notifyDataSetChanged();
								}}
							});
					}
					else if (spinner.getSelectedItemPosition() == 5)
					{
						bt.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									//Uptobox
									if(TextUtils.isEmpty(cek)){
										Toast.makeText(MainActivity.this,"Please Enter Text to Search !" ,Toast.LENGTH_SHORT).show();
									}else{
									Intent i = new Intent(MainActivity.this,Search.class);
									i.putExtra("key","https://cse.google.com/cse?cx=010187004032568060719:klsgmozfmxy&q=" + uRl.getText().toString());
									startActivity(i);
									finish();
									listItems.add(uRl.getText().toString());
									save();
									adapter.notifyDataSetChanged();
								}}
							});}
				
				
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
				}
			});
		
		spinner.setAdapter(staticAdapter);
		
    }
	
	//options Menu
	@Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.history:
				SharedPreferences.Editor editor = shared.edit();
                editor.remove("DATA_LIST").commit();
				Toast.makeText(MainActivity.this,"Clear Success!" ,Toast.LENGTH_LONG).show();
				adapter.clear();
				adapter.notifyDataSetChanged();
                return true;
            case R.id.about:
                Toast.makeText(MainActivity.this,"Created By Ivan Maulana" ,Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu); //menu nya ea
        return super.onCreateOptionsMenu(menu);
    }

	private void save() {
		SharedPreferences.Editor editor = shared.edit();
		Set<String> set = new HashSet<String>();
		set.addAll(listItems);
		editor.putStringSet("DATA_LIST", set);
		editor.commit();
		Log.d("storesharedPreferences",""+set);
	}private void read() {
		Set<String> set = shared.getStringSet("DATA_LIST",null);
		listItems.addAll(set);
		Log.d("retrivesharedPreferences",""+set);
	}
	public boolean onPrepareOptionsMenu(Menu menu) {

		return super.onPrepareOptionsMenu(menu);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK)) 
		{
			finishAffinity();
			}
		return super.onKeyDown(keyCode, event);
	}

	
}
