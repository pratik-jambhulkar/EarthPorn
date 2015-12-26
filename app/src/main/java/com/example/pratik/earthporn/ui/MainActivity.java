package com.example.pratik.earthporn.ui;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pratik.earthporn.R;
import com.example.pratik.earthporn.beans.Example;
import com.example.pratik.earthporn.interfaces.EarthPornInterface;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends ActionBarActivity {

    String API = "https://www.reddit.com";
    String url;
    ImageView imageView;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        imageView = (ImageView) findViewById(R.id.image_view);

        imageLoader = ImageLoader.getInstance();

        //Retrofit section start from here...
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API).build();

        //create an adapter for retrofit with base url

        EarthPornInterface earthPornInterface = restAdapter.create(EarthPornInterface.class);

        earthPornInterface.getImagesDetails(new Callback<Example>() {
            @Override
            public void success(Example example, Response response) {
                //url = example.getData().getChildren().get(1).getData().getPreview().getImages().get(0).getResolutions().get(0).getUrl();
                url = example.getData().getChildren().get(2).getData().getPreview().getImages().get(0).getSource().getUrl();
                Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
                System.out.print(url);
                Bitmap bitmap = null;
                try {
                    bitmap = getBitmapFromURL(url);
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                } catch (CertificateException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                }

                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }

//                imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
//                imageLoader.displayImage(url, imageView);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });






    }

    public Bitmap getBitmapFromURL(String src) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException {
            CertificateFactory cf;
            try {
                cf = CertificateFactory.getInstance("X.509");
                InputStream in = this.getResources().openRawResource(R.raw.cert);
                Certificate ca;
                ca = cf.generateCertificate(in);
                System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
                in.close();


                String keyStoreType = KeyStore.getDefaultType();
                KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                keyStore.load(null, null);
                keyStore.setCertificateEntry("ca", ca);// my question shows how to get 'ca'
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(
                        TrustManagerFactory.getDefaultAlgorithm());
// Initialise the TMF as you normally would, for example:
                tmf.init(keyStore);

                TrustManager[] trustManagers = tmf.getTrustManagers();
                final X509TrustManager origTrustmanager = (X509TrustManager)trustManagers[0];

                TrustManager[] wrappedTrustManagers = new TrustManager[]{
                        new X509TrustManager() {
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return origTrustmanager.getAcceptedIssuers();
                            }

                            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                                try {
                                    origTrustmanager.checkClientTrusted(certs, authType);
                                } catch (CertificateException e) {
                                    e.printStackTrace();
                                }
                            }

                            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                                try {
                                    origTrustmanager.checkServerTrusted(certs, authType);
                                } catch (CertificateException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                };

                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, wrappedTrustManagers, null);
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());


                URL url = new URL(src);
                HttpsURLConnection urlConnection =
                        (HttpsURLConnection)url.openConnection();
                in = urlConnection.getInputStream();
//                byte[] responsedata = CommonUtil.readInputStream(in);
//                Log.w(TAG, "response is " + CommonUtil.convertBytesToHexString(responsedata));
//                in.close();
//
//                URL url = new URL(src);
//            HttpsURLConnection connection = (HttpsURLConnection) url
//                    .openConnection();
//
//            connection.setSSLSocketFactory(context.getSocketFactory());

//            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(in);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
