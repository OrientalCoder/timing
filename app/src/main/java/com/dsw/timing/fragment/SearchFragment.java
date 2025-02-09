package com.dsw.timing.fragment;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.MapsInitializer;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.dsw.timing.R;

public class SearchFragment extends Fragment implements AMap.OnMyLocationChangeListener {

    private MapView mapView;
    private AMap aMap;

    private AMapLocationClient mapLocationClient;
    private AMapLocationClientOption mapLocationOption;

    private LocationSource.OnLocationChangedListener mListener;

    private MyLocationStyle myLocationStyle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(10000);

        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);

        AMapLocationClient.updatePrivacyShow(getContext(),true,true);
        AMapLocationClient.updatePrivacyAgree(getContext(),true);

        try {
            mapLocationClient = new AMapLocationClient(getContext().getApplicationContext());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        mapLocationOption = new AMapLocationClientOption();

        mapLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mapLocationOption.setInterval(10000);

        mapLocationClient.setLocationOption(mapLocationOption);



        mapLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if(aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                    // 定位成功
                    double latitude = aMapLocation.getLatitude();
                    double longitude = aMapLocation.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
                    //aMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    Log.d("Amap", "定位成功：" + aMapLocation.getCity());
                } else {
                    Log.e("Amap", "定位失败：" + aMapLocation.getErrorInfo());
                }
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.searchfragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        if(aMap == null) {
            aMap = mapView.getMap();
        }


        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
        aMap.setMyLocationType(AMapLocation.LOCATION_TYPE_COARSE_LOCATION);
        //aMap.setOnMyLocationChangeListener(this::onMyLocationChange);

        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(4));

        LatLng latLng = new LatLng(39.906001, 116.391972);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        mapLocationClient.startLocation();
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if(mapLocationClient!=null) {
            mapLocationClient.onDestroy();
        }

    }

    @Override
    public void onMyLocationChange(Location location) {

    }
}
