package com.okwy.practiceproject.VolleyPicasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.okwy.practiceproject.R;
import com.okwy.practiceproject.VolleyPicasso.Adapter.Adapter;
import com.okwy.practiceproject.VolleyPicasso.Model.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VolleyActivity extends AppCompatActivity {

    private RecyclerView listRecycler;
    private List<Model> modelList = new ArrayList<>();
    private Adapter listAdapter;
    private RequestQueue volleyRequest;

    public static final String URL = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
    public static final String postURL = "https://api.prashantranjan.com/add-record.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        listRecycler = findViewById(R.id.listRecycler);
        listRecycler.setLayoutManager(new LinearLayoutManager(this));
        listRecycler.setHasFixedSize(true);

        volleyRequest = Volley.newRequestQueue(this);

        parseJSONfromServer();
    }

    private void parseJSONfromServer() {

        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
//                    JSONArray jsonArray = new JSONObject(response).getJSONArray("hits");
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);

                        Model model = new Model(hit.getString("webformatURL"),
                                hit.getString("user"),
                                hit.getInt("likes"));

                        modelList.add(model);
                    }

                    listAdapter = new Adapter(VolleyActivity.this, modelList);
                    listRecycler.setAdapter(listAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        volleyRequest.add(request);

    }



    public void postJSONToServer(View view) {
        final String nameValue = "Name";// nameED.getText().toString();
        final int ageValue = 12; // Integer.parseInt(ageED.getText().toString());
        final String addressValue = "Address";// addressED.getText().toString();

        StringRequest postRequest = new StringRequest(Request.Method.POST, postURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(VolleyActivity.this, response, Toast.LENGTH_SHORT).show();
                volleyRequest.getCache().clear();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this,  "Error: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Volley Error", error.getLocalizedMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", nameValue);
                params.put("age", String.valueOf(ageValue));
                params.put("address", addressValue);

                return params;
            }
        };

        volleyRequest.add(postRequest);
    }
}