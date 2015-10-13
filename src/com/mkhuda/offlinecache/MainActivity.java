package com.mkhuda.offlinecache;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.mkhuda.offlinecache.models.CacheModels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.UUID;

public class MainActivity extends Activity {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
	TextView t1, t2;
	Button b0, b;
	
	CacheModels cm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        
		cm = new CacheModels(getApplicationContext());
		
		
		
		b0 = (Button) findViewById(R.id.button0);
		b0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loadfromserver();
			}
		});
		b = (Button) findViewById(R.id.button);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				t2 = (TextView) findViewById(R.id.text2);
				
				try {
					String a = (String) InternalStorage.readObject(getApplicationContext(), "John1");
					JSONObject jObj = new JSONObject(a);
					t2.setText(jObj.getString("movie_name"));
//					for()
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Toast.makeText(getApplicationContext(), cm.getCache1(), Toast.LENGTH_LONG).show();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		
		
	}
	
	private void loadfromserver() {
		t1 = (TextView) findViewById(R.id.text1);
		StringRequest movieReq = new StringRequest(Method.POST,
				"http://labs.mkhuda.com/bisanonton/movie-random.php", new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						Log.d(TAG, response.toString());

						cm.setCache1(response.toString());
						t1.setText("Data Loaded");
						try {
							JSONObject jObj = new JSONObject(response);
							Toast.makeText(getApplicationContext(), "String saved is: "+jObj.getString("movie_name"), Toast.LENGTH_LONG).show();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// VolleyLog.d(TAG, "Error: " + error.getMessage());
						
						Toast.makeText(getApplicationContext(),
								"Please Connect To Internet!", Toast.LENGTH_SHORT).show();

					}
				});
		
		AppController.getInstance().addToRequestQueue(movieReq);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
