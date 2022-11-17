package com.gruia.courseplanet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;
import com.gruia.courseplanet.R;
import com.gruia.courseplanet.ar.ARNode;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class AnatomyARActivity extends AppCompatActivity implements Scene.OnUpdateListener{

    private ArSceneView arView;
    private Session session;
    private boolean shouldConfigureSession = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anatomy_aractivity);
        arView = (ArSceneView) findViewById(R.id.arSceneView);
        try {
            initSceneView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initSceneView() throws IOException {
        setupSession();
        arView.getScene().addOnUpdateListener(this);
    }

    private void setupSession() throws IOException {
        if(session == null)
        {
            try {
                session = new Session(this);
            } catch (UnavailableDeviceNotCompatibleException | UnavailableSdkTooOldException | UnavailableArcoreNotInstalledException | UnavailableApkTooOldException e) {
                e.printStackTrace();
            }
            shouldConfigureSession = true;
        }

        if(shouldConfigureSession)
        {
            configSession();
            shouldConfigureSession = false;
            arView.setSession(session);
        }

        try {
            session.resume();
            arView.resume();
        } catch (Exception e) {
            e.printStackTrace();
            session = null;
            return;
        }
    }

    private void configSession() throws IOException {
        Config config = new Config(session);
        if(!buildDatabase(config))
        {
            Toast.makeText(this,"Error database",Toast.LENGTH_SHORT).show();
        }
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        session.configure(config);
    }

    private boolean buildDatabase(Config config) throws IOException {
        AugmentedImageDatabase augmentedImageDatabase;
        Bitmap bitmap = loadImage();
        if(bitmap == null)
        {
            return false;
        }
        augmentedImageDatabase = new AugmentedImageDatabase(session);
        augmentedImageDatabase.addImage("cube",bitmap);
        config.setAugmentedImageDatabase(augmentedImageDatabase);
        return true;
    }

    private Bitmap loadImage() throws IOException {
        InputStream is = getAssets().open("cube_qr.png");
        return BitmapFactory.decodeStream(is);
    }

    @Override
    public void onUpdate(FrameTime frameTime) {
        Frame frame = arView.getArFrame();
        Collection<AugmentedImage> updateAugmentedImg = frame.getUpdatedTrackables(AugmentedImage.class);

        for(AugmentedImage image:updateAugmentedImg)
        {
            if(image.getTrackingState() == TrackingState.TRACKING)
            {
                if(image.getName().equals("cube"))
                {
                    ARNode node = new ARNode(this,R.raw.cube);
                    node.setImage(image);
                    arView.getScene().addChild(node);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        try {
                            setupSession();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(AnatomyARActivity.this, "Permission needed to display camera.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(session != null)
        {
            arView.pause();
            session.pause();
        }
    }
}