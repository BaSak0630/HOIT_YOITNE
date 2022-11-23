package ga.kimdh.testfirebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    private Button addBtn;

    private Double longitude = null;
    private Double latitude = null;
    //입력값 변수에 담기
    String busid = "2";
    String buslatitude=" ";
    String buslongitude="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);
        addBtn = findViewById(R.id.add_btn);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                // 위치 관리자 객체 참조하기
                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,1,locationListener);

            }
        });
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {

            longitude = location.getLongitude();
            latitude = location.getLatitude();

            txtResult.setText("위도 : " + latitude + "\n" + "경도 : " + longitude + "\n" );

            BusDAO dao = new BusDAO();

            buslatitude = String.valueOf(latitude);
            buslongitude = String.valueOf(longitude);



//            BusDAO dao = new BusDAO();
//            buslatitude = String.valueOf(latitude);
//            buslongitude = String.valueOf(longitude);
//            Bus bus = new Bus(busid,buslatitude,buslongitude);
//
//            //데이터 베이스 사용자 등록
//            dao.add(bus).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void unused) {
//                    Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getApplicationContext(), "실패" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });

        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

    };
}