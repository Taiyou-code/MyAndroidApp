package jp.ac.gifu_u.info.fukuta.taiyoudrawing;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class LocationActivity extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;
    private TextView locationText;
    private static final int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationText = new TextView(this);
        locationText.setText("現在地を取得中...");
        locationText.setTextSize(20);
        setContentView(locationText);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // 権限があるか確認、なければリクエスト
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {
            startLocationUpdates();
        }
    }

    // 位置情報の取得開始
    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000, // 毎秒
                    1,    // 1メートル移動
                    this
            );
        }
    }

    // 位置情報が更新されたとき
    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        locationText.setText("緯度: " + lat + "\n経度: " + lng);
    }

    // 実行時パーミッションの結果処理
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates();
        } else {
            Toast.makeText(this, "位置情報の許可が必要です", Toast.LENGTH_SHORT).show();
        }
    }
}
