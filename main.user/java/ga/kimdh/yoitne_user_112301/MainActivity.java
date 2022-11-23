package ga.kimdh.yoitne_user_112301;


import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.StyleSpan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    String busid_1= "1";
    String buslatitude_1=" ";
    String buslongitude_1="";
    double buslatitude_1_d;
    double buslongitude_1_d;

    String busid_2= "2";
    String buslatitude_2=" ";
    String buslongitude_2="";
    double buslatitude_2_d;
    double buslongitude_2_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);


//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference bus_latitude_1 = database.getReference("https://yoitne-be594-default-rtdb.asia-southeast1.firebasedatabase.app/Bus/1/bus_latitude_1");
//        bus_latitude_1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                buslatitude_1 = snapshot.getValue(String.class);
//                buslatitude_1_d = Double.parseDouble(buslatitude_1);
//                Toast.makeText(getApplicationContext(),buslatitude_1,Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        //busid =  1 조회
//        DatabaseReference bus_latitued_1 = database.getReference().child("bus_latitude_1");
//        bus_latitued_1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                buslatitude_1 = snapshot.getValue(String.class);
//                buslatitude_1_d = Double.parseDouble(buslatitude_1);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        DatabaseReference bus_longitude_1 = database.getReference().child("bus_longitude_1");
//        bus_longitude_1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                buslongitude_1 = snapshot.getValue(String.class);
//                buslongitude_1_d = Double.parseDouble(buslongitude_1);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        //busid = 2 조회
//        DatabaseReference bus_latitued_2 = database.getReference().child("bus_latitued_2");
//        bus_latitued_2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                buslatitude_2 = snapshot.getValue(String.class);
//                buslatitude_2_d = Double.parseDouble(buslatitude_2);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        DatabaseReference bus_longitude_2 = database.getReference().child("bus_longitude_2");
//        bus_longitude_2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                buslongitude_2 = snapshot.getValue(String.class);
//                buslongitude_2_d = Double.parseDouble(buslongitude_2);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {

        LatLng Center = new LatLng(35.267060 ,129.085442);
        LatLng schoolBusStop = new LatLng(35.267497, 129.080308);
        LatLng oeseongDormitory = new LatLng(35.269923 ,129.085253 );
        LatLng beomeosa = new LatLng(35.272811, 129.092510);
        LatLng namsan = new LatLng(35.265219 ,129.092313);
        LatLng namsanFireStation= new LatLng(35.261057, 129.087109);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Center));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));

        MarkerOptions markerscho0lBusStop = new MarkerOptions().position(schoolBusStop).title("셔틀 졍류소");
        googleMap.addMarker(markerscho0lBusStop);
        MarkerOptions markeroeseongDormitory = new MarkerOptions().position(oeseongDormitory).title("외성 기숙사");
        googleMap.addMarker(markeroeseongDormitory);
        MarkerOptions markerbeomeosa = new MarkerOptions().position(beomeosa).title("범어사 역");
        googleMap.addMarker(markerbeomeosa);
        MarkerOptions markernamsan = new MarkerOptions().position(namsan).title("남산 역");
        googleMap.addMarker(markernamsan);
        MarkerOptions markernamsanFireStation = new MarkerOptions().position(namsanFireStation).title("남산 소방서");
        googleMap.addMarker(markernamsanFireStation);

        LatLng bus1 = new LatLng(35.2678155,129.0780476);
        LatLng bus2 = new LatLng(35.2679378,129.0780536);

        MarkerOptions markerbus1= new MarkerOptions().position(bus1).title("1번 버스").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerbus1);
        MarkerOptions markerbus2 = new MarkerOptions().position(bus2).title("2번 버스").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerbus2);



    }
}
