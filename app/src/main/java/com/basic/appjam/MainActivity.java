package com.basic.appjam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {
    Button memo;
    Double latitude ;
    Double longitude;
    FragmentManager fm = getSupportFragmentManager();
    MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memo = findViewById(R.id.memo);
        memo.setOnClickListener(this);

        permission();
        getLocation();
        startLocationService();
        locationSource =
                new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);



    }

    private void startLocationService() {

    }

    void permission() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getApplicationContext(), "권한허가", Toast.LENGTH_SHORT).show();
                init();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            }

        };
        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage("구글 로그인을 하기 위해서는 주소록 접근 권한이 필요해요")
                .setDeniedMessage("왜 거부하셨어요...\n하지만 [설정] > [권한] 에서 권한을 허용할 수 있어요.")
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }

    void init() {

        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
            mapFragment.getMapAsync(this);
        }
    }

    void getLocation() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        GPSListener gpsListener = new GPSListener(getApplicationContext());
        long minTime = 10000;
        float minDistance = 0;

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance,
                gpsListener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime,
                minDistance, gpsListener);

            Toast.makeText(getApplicationContext(), "위치 확인이 시작되었습니다. ", Toast.LENGTH_SHORT).show();
        }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.memo:

                Intent intent = new Intent(MainActivity.this, MemoEdit.class);
                intent.putExtra("lat",latitude);
                intent.putExtra("lon",longitude);
                startActivity(intent);

        }
    }
    void setMarker(NaverMap naverMap,Memo memo){
        final Memo memo1 = memo;
        Marker marker = new Marker();
        marker.setPosition(new LatLng(memo.getLat(),memo.getLng()));
        marker.setMap(naverMap);
        marker.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                Toast.makeText(getApplicationContext(),"ddd",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MemoDetail.class);
                intent.putExtra("Title",memo1.getTitle());
                intent.putExtra("Content",memo1.getContent());
                startActivity(intent);

                return false;
            }
        });
    }
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
        Realm.init(getApplicationContext());
        Realm realm =Realm.getDefaultInstance();
        RealmResults<Memo> realmResults = realm.where(Memo.class).findAllAsync();
        for(Memo memo : realmResults){
            setMarker(naverMap,memo);
            Log.d("memomarker",memo.toString());
        }


    }

    class GPSListener implements LocationListener {
        Context context;
        GPSListener(Context context){
            this.context = context.getApplicationContext();
        }
        @Override
        public void onLocationChanged(Location location) {

            latitude = location.getLatitude();
            longitude = location.getLongitude();
            String msg = "Latitude : "+ latitude+"\n Longitude : "+longitude;
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) { }

        @Override
        public void onProviderEnabled(String s) { }

        @Override
        public void onProviderDisabled(String s) { }
    }
}
