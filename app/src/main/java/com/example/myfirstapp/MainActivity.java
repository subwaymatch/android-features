package com.example.myfirstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
	public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * Called when the user tabs the Send button
	 */
	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.editText);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	/**
	 * Called when the user clicks on the Get Location button
	 */
	public void getLocation(View view) {
		RequestQueue httpRequestQueue = Volley.newRequestQueue(this);

		String url1 = "http://ip-api.com/json";

		Log.d("http", "pre-request url=" + url1);

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					Log.d("http", "Response: " + response.toString());
				}

			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					Log.d("http", "onErrorResponse");
					Log.e("http", error.getMessage());
				}
			}
		);

		String baseUrl = "https://postman-echo.com/get";

		Uri builtUri = Uri.parse(baseUrl)
				.buildUpon()
				.appendQueryParameter("paramOne", "1")
				.appendQueryParameter("paramTwo", "2")
				.build();

		String urlWithParams = builtUri.toString();

		Log.d("http", "pre-request url=" + urlWithParams);

		JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(
				Request.Method.GET, urlWithParams, null, new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					Log.d("http", "Response: " + response.toString());
				}

			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					Log.d("http", "onErrorResponse");
					Log.e("http", error.getMessage());
				}
			}
		);

		httpRequestQueue.getCache().clear();

		httpRequestQueue.add(jsonObjectRequest);
		httpRequestQueue.add(jsonObjectRequest2);
	}
}
