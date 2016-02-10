package com.example.chuck.FacebookPhotoGallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by chuck on 2/7/16.
 */

public class MainActivity extends AppCompatActivity {

    private GridView gView;
    public Toolbar toolbar = null;
    public TextView mTitle;
    

    private GridViewAdapter gAdapter;
    private ArrayList<GridItem> gallery_items;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        //sets a toolbar and gives it a title
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Home);


        /**AccessToken = Access Token + FacebookAppID + UserID
        Visit https://developers.facebook.com/tools/explorer/ to obtain an Access Token
        **/
        AccessToken accessToken = new AccessToken(getString(R.string.accessToken), getString(R.string.facebook_app_id), getString(R.string.userId), null, null, null, null, null);
        gView = (GridView) findViewById(R.id.gridView);

        //Initialize with empty data
        gallery_items = new ArrayList<>();
        gAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, gallery_items);
        gView.setAdapter(gAdapter);




        /**
        Graph API method for reading the album data
        Visit https://developers.facebook.com/docs/graph-api/reference/v2.5/album/photos  for more info
         **/

        GraphRequest request = GraphRequest.newGraphPathRequest(
                accessToken,

                /*
                Use your album ID Number/photos.
                 the syntax is : /123456789101112/photos

                 */

                "/123456789101112/photos",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {

                        /**Logs response from facebook, which will be in JSON format**/

                         Log.i("Response", response.toString());
                        try {
                            JSONArray jsonPhotos = response.getJSONObject().getJSONArray("data");
                            GridItem item;

                            /**Parse json array, search for "name" and "id"**/

                            for (int j = 0; j < jsonPhotos.length(); j++) {
                                item = new GridItem();
                                JSONObject jsonFBPhoto = jsonPhotos.getJSONObject(j);

                                if(jsonFBPhoto.getString("name")==null)
                                {
                                    /**If image has no description, set its title to "No Title"**/
                                    item.setTitle("No Title");

                                    /**find "id" which identifies the image**/
                                    String id = jsonFBPhoto.getString("id");
                                    URL image_url = new URL("https://graph.facebook.com/" + id + "/picture?type=normal");
                                    String url = image_url.toString();

                                    /**prepare to send "title" and "image" to the adapter**/
                                    item.setTitle(jsonFBPhoto.getString("name"));
                                    item.setImage(url);

                                }
                                else{
                                    /**Repeat above, but set title to the image's description as is on facebook**/
                                    String id = jsonFBPhoto.getString("id");
                                    String name = jsonFBPhoto.getString("name");
                                    URL image_url = new URL("https://graph.facebook.com/" + id + "/picture?type=normal");
                                    String url = image_url.toString();
                                    item.setTitle(jsonFBPhoto.getString("name"));
                                    item.setImage(url);
                                }
                                /**Image and Title are sent to the adapter**/
                                gallery_items.add(item);
                                gAdapter.setGridData(gallery_items);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        request.executeAsync();

        /**Handle photo clicks in the gallery**/
        gView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                /**gets the item (image and title) which was clicked**/
                GridItem item = (GridItem) parent.getItemAtPosition(position);

                /**Passes the item to a new activity where it will be viewed at its original size**/
                Intent intent = new Intent(MainActivity.this, GridItemActivity.class);
                intent.putExtra("Image Title", item.getTitle());
                intent.putExtra("Image", item.getImage());
                startActivity(intent);
            }
        });
    }

}