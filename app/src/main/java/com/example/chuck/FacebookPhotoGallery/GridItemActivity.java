package com.example.chuck.FacebookPhotoGallery;

/**
 * Created by chuck on 2/7/16.
 * This activity recives the item from the gridview and shows it at its original size
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class GridItemActivity extends AppCompatActivity {
    private TextView imgTitle;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);

        /**Enables the toolbar, gives it a title**/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Gallery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        /**from MainActivity**/
        String title = getIntent().getStringExtra("Image Title");
        String image = getIntent().getStringExtra("Image");

        /**Set textview to the "Image Title" and Imageview to the "Image" obtained from MainActivity**/
        imgTitle = (TextView) findViewById(R.id.title);
        imgView = (ImageView) findViewById(R.id.grid_item_image);
        imgTitle.setText(title);

        /**Picasso does the relevant conversions and sets the image to the imageview**/
        Picasso.with(this).load(image).into(imgView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**handles clicks to the back arrow in the toolbar**/
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
