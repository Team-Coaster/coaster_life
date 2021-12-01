package com.teamcoaster.coasterlife.Fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.teamcoaster.coasterlife.R;

import java.util.Map;

public class PeopleFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    private Geocoder geocoder;
    private int ACCESS_LOCATION_REQUEST_CODE = 1001;
    public static final String TAG = "PeopleFragment";
    FusedLocationProviderClient fusedLocationProviderClient;

    public PeopleFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_people, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        // Gets the map to display immediately
        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                //googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng dallas = new LatLng(32.776, -96.796);
                googleMap.addMarker(new MarkerOptions().position(dallas).title("Marker Title").snippet("Marker Description"));


                //Sample People markers (FRIENDS)
                LatLng Amanda = new LatLng(32.730957, -97.110976);
                LatLng Emmanuel = new LatLng(32.729675, -97.112199);
                LatLng Jacob = new LatLng(32.731607, -97.116619);
                googleMap.addMarker(new MarkerOptions().position(Amanda).title("Amanda").snippet("Friend Nearby"));
                googleMap.addMarker(new MarkerOptions().position(Emmanuel).title("Emmanuel").snippet("Friend Nearby"));
                googleMap.addMarker(new MarkerOptions().position(Jacob).title("Jacob").snippet("Friend Nearby"));


                geocoder = new Geocoder(getActivity());
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    enableUserLocation();
//            googleMap.setMyLocationEnabled(true);
                    zoomToUserLocation();

                } else {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)){
                        //Show User dialog why this permission is necessary
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
                    }else{
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
                    }
                }

                // For zooming automatically to the location of the marker
//                CameraPosition cameraPosition = new CameraPosition.Builder().target(dallas).zoom(12).build();
//                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });



        return rootView;
    }

    @SuppressLint("MissingPermission")
    private void enableUserLocation(){
        googleMap.setMyLocationEnabled(true);
    }

    private void zoomToUserLocation(){
        @SuppressLint("MissingPermission")
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            enableUserLocation();
            zoomToUserLocation();
        } else {
            // We can show a dialog that permission is not grated
        }
    }
//    ALTERNATIVE TO DEPRECATED onRequestPermissionResult
//    private ActivityResultLauncher<String> mPermissionResult = registerForActivityResult(
//            new ActivityResultContracts.RequestPermission(),
//            new ActivityResultCallback<Boolean>() {
//                @Override
//                public void onActivityResult(Boolean result) {
//                    if(result) {
//                        Log.e(TAG, "onActivityResult: PERMISSION GRANTED");
//                        enableUserLocation();
//                        zoomToUserLocation();
//                    } else {
//                        Log.e(TAG, "onActivityResult: PERMISSION DENIED");
//                    }
//                }
//            });
}