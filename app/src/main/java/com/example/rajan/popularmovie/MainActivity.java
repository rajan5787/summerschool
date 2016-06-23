package com.example.rajan.popularmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridview;
    private String API_KEY = "69961736ee88b39584ba695c3da7a759";
    private int page_number =1;
    private String FEED_URL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="+API_KEY+"&page="+""+page_number;
    private ArrayList<Item> mGridData;
    private CustomAdapter mCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridview = (GridView) findViewById(R.id.gridview);
        mGridData = new ArrayList<Item>();

        mCustomAdapter = new CustomAdapter(this, mGridData);
        gridview.setAdapter(mCustomAdapter);

        getdata();
       // System.out.println("rajann");
      /*  gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),Detail.class);
                i.putExtra("movie",  mGridData.get(position));
                startActivity(i);
            }
        });*/

    }



    public void getdata(){

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, FEED_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            parseResult(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"asdfghjk"+error+"   ",Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }

    private void parseResult(String response) throws JSONException {

        JSONObject obj = new JSONObject(response);
        JSONArray jsonArray = obj.optJSONArray("results");
        Item item;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject post = jsonArray.optJSONObject(i);
            String title = post.optString("original_title");
            item = new Item();
            item.setTitle(title);
            String image = post.optString("poster_path");
            image = "http://image.tmdb.org/t/p/w500/"+image.substring(1);
            item.setImage(image);
            String backdrop = post.optString("backdrop_path");
            backdrop = "http://image.tmdb.org/t/p/w500/"+backdrop.substring(1);
            item.setBackdrop(backdrop);
            int id = post.optInt("id");
            item.setId(id);
            String release_date = post.optString("release_date");
            item.setRelease_date(release_date);
            String adult = post.optString("adult");
            item.setAdult(adult);
            String overview = post.optString("overview");
            item.setOverview(overview);
            String original_language = post.optString("original_language");
            item.setOriginal_language(original_language);
            int vote_count = post.optInt("vote_count");
            item.setVote_count(vote_count);
            double popularity = post.optDouble("popularity");
            item.setPopularity(popularity);
            double vote_average = post.optDouble("vote_average");
            item.setVote_average(vote_average);
            mGridData.add(item);
        }
            mCustomAdapter.setdata(mGridData);
    }
}
