package ga.kimdh.yoitine_bus_112301;

import static android.location.LocationManager.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.security.AccessController;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private TextView txtResult;

    String busid = "2";
    String buslatitude=" ";
    String buslongitude="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.add_btn);
        txtResult = (TextView)findViewById(R.id.txtResult);

        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                } else {
                    // 가장최근 위치정보 가져오기
                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {

                        double longitude = location.getLongitude();
                        double latitude = location.getLatitude();

                        txtResult.setText(
                                "위도 : " + latitude + "\n" +
                                "경도 : " + longitude + "\n"
                               );
                    }
                    // 위치정보를 원하는 시간, 거리마다 갱신해준다.
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            1000,
                            1,
                            gpsLocationListener);
                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            1000,
                            1,
                            gpsLocationListener);
                }

            }
        });
    }
    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // 위치 리스너는 위치정보를 전달할 때 호출되므로 onLocationChanged()메소드 안에 위지청보를 처리를 작업을 구현 해야합니다.

            double longitude = location.getLongitude(); // 위도
            double latitude = location.getLatitude(); // 경도
            txtResult.setText( "위도 : " + latitude + "\n" + "경도 : " + longitude + "\n");

            buslatitude = String.valueOf(latitude);
            buslongitude = String.valueOf(longitude);

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("bus_latitude_2",buslatitude );
            hashMap.put("bus_longitude_2",buslongitude);

            BusDAO dao = new BusDAO();

            dao.update(busid, hashMap).addOnSuccessListener(new OnSuccessListener<Void>()
            {
                @Override
                public void onSuccess(Void unused)
                {
                    Toast.makeText(getApplicationContext(), "업데이트 성공", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener()
            {
                @Override
                public void onFailure(@NonNull Exception e)
                {
                    Toast.makeText(getApplicationContext(), "업데이트 실패:" +e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

//            BusDAO dao = new BusDAO();
//            buslatitude = String.valueOf(latitude);
//            buslongitude = String.valueOf(longitude);
//            Bus bus = new Bus(busid,buslatitude,buslongitude);
//
//            //데이터 베이스 사용자 등록
//            dao.add(bus).addOnSuccessListener(new OnSuccessListener<Void>()
//            {
//               @Override
//               public void onSuccess(Void unused)
//               {
//                    Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
//               }
//            }).addOnFailureListener(new OnFailureListener()
//            {
//              @Override
//              public void onFailure(@NonNull Exception e)
//              {
//                    Toast.makeText(getApplicationContext(), "실패" + e.getMessage(), Toast.LENGTH_SHORT).show();
//              }
//            });
        }
    };

}
