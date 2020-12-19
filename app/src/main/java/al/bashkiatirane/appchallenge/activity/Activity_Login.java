package al.bashkiatirane.appchallenge.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import al.bashkiatirane.appchallenge.R;
import al.bashkiatirane.appchallenge.model.UserAuthResponse;
import al.bashkiatirane.appchallenge.rest.API;
import al.bashkiatirane.appchallenge.rest.API_CLIENT;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Login extends AppCompatActivity {

    Button Login;
    EditText Email,Pass;
    TextView info;
    Gson gson;
    String EmailData,PassData;
    String SharedEmail,SharedName;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
gson = new GsonBuilder().create();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Login = (Button)findViewById(R.id.bnt_login);
        Email = (EditText) findViewById(R.id.edt_email);
        Pass = (EditText)findViewById(R.id.edt_password);
        info = (TextView) findViewById(R.id.txt_data);

       editor = sharedpreferences.edit();



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailData=Email.getText().toString();
                PassData=Pass.getText().toString();
              //  Toast.makeText(getApplicationContext(),"Ky eshte nje toast",Toast.LENGTH_SHORT).show();
                login(EmailData,PassData);
                info.setText(sharedpreferences.getString("Name","NODATA"));
            }
        });

        Login.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });


    }

    public void login(String Email,String Password){
        API apiclient = API_CLIENT.createAPI_No_Token();

        retrofit2.Call<UserAuthResponse> call = apiclient.login(Email,Password);
        call.enqueue(new Callback<UserAuthResponse>() {
            @Override
            public void onResponse(Call<UserAuthResponse> call, Response<UserAuthResponse> response) {
                //Log.i("LOGINRESPONSE",gson.toJson(response.body()));
                Toast.makeText(getApplicationContext(),"NGA SERVERI: "+gson.toJson(response.body()),Toast.LENGTH_SHORT).show();

                editor.putString("Name", gson.toJson(response.body().getData().getName()));
                editor.putString("Email", gson.toJson(response.body().getData().getEmail()));
                editor.commit();
            }

            @Override
            public void onFailure(Call<UserAuthResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"ERRO"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

}