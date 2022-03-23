package com.sathya.tcs_daggergithub;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sathya.tcs_daggergithub.DI_L.CarDi;
import com.sathya.tcs_daggergithub.LooseCoupling.Car_L;
import com.sathya.tcs_daggergithub.LooseCoupling.Engine;
import com.sathya.tcs_daggergithub.REST.GitHubClient;
import com.sathya.tcs_daggergithub.TightCoupling.Car;
import com.sathya.tcs_daggergithub.model.GithubUser;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/*
      To use the Dagger Following Annotations are MUST.

      Annotations use in Dagger 2 are —

@Module
@Provides
@Singleton
@Inject
@Component

@Module — In Dagger 2, @Module class is responsible for providing objects to injected class.
@Provides — In @Module class, there is a method that is annotated as @Provides which is responsible for providing dependencies.
@Singleton — It is responsible for assuring that there should be only one instance of that class.
@Inject — It is used on the constructor so that Dagger should use it to create an instance of the class.
@Component — @Component Interface is a bridge between the provider of objects and object which is going to use its dependency.
Steps to implement Dagger 2 in the project —


Not covered... due to time...
Dagger Hilt : Kotlin


  Step 1 : Add dagger dependency in build.gradle

  we are creating the following step instead of the serviceGenerator( provide Retrofit manually )

  Step 2 : create a Module class NetworkModule ( Gson Retrofit ) : he is the provider..
           Lets create the above step in a package called as modules

  Step 3 : is an intigral part of step 2 Lets provide ApplicationContext
            under module  create AppModule which provides the ApplicationContext

  Step 4 : create a component interface  : AppComponent will create a Dagger Graph tree

             // You need to build and run the project at least once after implementing dagger. It will automatically generate DaggerAppComponent class

    Step 5 : Create an Application class that uses the generated code. : BaseApplication

   step 6 : Add the Application class( BaseApplication ) to the manifest file.

   step 7 : Finally in the mainActivity we inject Retrofit and make a retrofit call.



 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView textViewTitle;
    private TextView textViewCompleted;

    //
    ImageView   imageView1;
    Button      searchBtn ,  listRepoButton  ;
    TextView    responseText ,  textViewreporesults  ;
    EditText    editText;
    ProgressBar progressBar;

    @Inject
    Retrofit retrofit ;

    private GitHubClient gitHubClient ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bad practice..
        Car car = new Car();
        car.start();

        // Right aproach

        // This can be any engine.. Petrol / electrical Di..

        // DI
        Engine engine = new Engine();

        Car_L casr_l = new Car_L( engine);
        car.start();

        // ServiceLocator


        CarDi cardi = new CarDi();
        cardi.start();

        searchBtn    = (Button) findViewById(R.id.main_btn_lookup);
        responseText = (TextView) findViewById(R.id.main_text_response);
        editText     = (EditText) findViewById(R.id.main_edit_username);
        progressBar  = (ProgressBar) findViewById(R.id.main_progress);

        imageView1   = (ImageView) findViewById(R.id.avatar);

        progressBar.setVisibility(View.INVISIBLE);


        listRepoButton      = (Button) findViewById(R.id.btn_repo);
        textViewreporesults = (TextView) findViewById(R.id.text_view_result);


        //

        ((BaseApplication) getApplication()).getBASENetworkComponent().inject(this);
//  BaseApplication returns appComponent that in turn has dependency graph
//  which holds AppModule and NetworkModule takes this into -> inject interface

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchForUser();
            }
        });

    }

    public void  searchForUser(){


        String user = editText.getText().toString();
        progressBar.setVisibility(View.VISIBLE);

        gitHubClient = retrofit.create(GitHubClient.class);
        gitHubClient.getUser(user).enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                Log.d(TAG, "onResponse: " + response.body().getName());

                GithubUser gitModel = response.body();

                if (gitModel != null) {
                    responseText.setText(getString(R.string.main_response_text,
                            gitModel.getName(),
                            gitModel.getBlog(),
                            gitModel.getBio(),
                            gitModel.getCompany()));
                    responseText.setTextSize(18);
                    responseText.setTextColor(Color.RED);
                    //  responseText.setTextColor(colorPrimaryDark);
                    responseText.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            Uri uri = Uri.parse(gitModel.getBlog()); // missing 'http://' will cause crashed
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            return true;
                        }
                    });

                    // imageView1.setImageResource(gitModel.getAvatarUrl());
                    //imageView1.setImageBitmap(getBitmapFromURL(url));
//                    Picasso.with(MainActivity.this)
//                            .load(gitModel.getAvatarUrl())
//                            .into(imageView1);
                    //  Picasso.with(context).load(imageUri).into(ivBasicImage);
                    // imageView.setImageBitmap(imageBitmap);

                    Picasso.get()
                            .load(gitModel.getAvatarUrl())
                            .resize(350, 350)
                            .centerCrop()
                            .into(imageView1);
                } else {

                    responseText.setText("");
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.main_error_text),
                            // stringUserNotFound,
                            Toast.LENGTH_SHORT).show();

                }
                //Hide progressbar when done
                progressBar.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                //   Display error message if the request fails
                responseText.setText("Error Loading " + t.fillInStackTrace().toString()); //Error needs to be handled properly
                responseText.setTextColor(Color.RED);
                responseText.setTextSize(23);
                //Hide progressbar when done
                progressBar.setVisibility(View.INVISIBLE);

            }
        });

        /////



        ///////
    }

}
