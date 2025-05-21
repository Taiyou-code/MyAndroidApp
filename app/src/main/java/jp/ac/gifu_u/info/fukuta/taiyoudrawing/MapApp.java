package jp.ac.gifu_u.info.fukuta.taiyoudrawing;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapApp extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // XMLファイルとつながる部分

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);  // 地図の準備ができたら onMapReady を呼ぶ
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // 岐阜大学の位置にマーカーを設置し、ズームイン
        LatLng gifu = new LatLng(35.4723, 136.7669);
        mMap.addMarker(new MarkerOptions().position(gifu).title("岐阜大学"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gifu, 16));
    }
}
