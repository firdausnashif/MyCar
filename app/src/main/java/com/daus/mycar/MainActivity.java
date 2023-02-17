package com.daus.mycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.daus.mycar.CarAdapter;
import com.daus.mycar.CarItem;
import com.daus.mycar.CarResponse;
import com.daus.mycar.MyService;
import com.daus.mycar.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    MyService myService;
    CarAdapter adapter;
    RecyclerView rv_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_car = findViewById(R.id.rv_car);

        myService = new MyService(this);

        rv_car.setHasFixedSize(true);
        rv_car.setLayoutManager(new LinearLayoutManager(this));

        getDataCar();

    }

    public void getDataCar(){

        try{
            myService.getInstance().getCar().enqueue(new Callback<CarResponse<CarItem>>() {
                @Override
                public void onResponse(Call<CarResponse<CarItem>> call, Response<CarResponse<CarItem>> response) {

                    if(response.isSuccessful()) {


                        CarResponse resp = response.body();

                        if (resp.getData() != null && resp.getData().size() > 0) {

                            adapter = new CarAdapter(resp.getData(), MainActivity.this);
                            rv_car.setAdapter(adapter);
                        }else{
                            Toast.makeText(MainActivity.this, resp.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<CarResponse<CarItem>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch(Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}