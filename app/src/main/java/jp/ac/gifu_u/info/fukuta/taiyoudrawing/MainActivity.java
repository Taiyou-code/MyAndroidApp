package jp.ac.gifu_u.info.fukuta.taiyoudrawing;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // レイアウトXMLを使わず、MyViewを直接画面に表示する
        setContentView(new MyView(this));
    }
}
