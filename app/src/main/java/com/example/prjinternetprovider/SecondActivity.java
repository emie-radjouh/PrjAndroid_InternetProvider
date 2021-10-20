package com.example.prjinternetprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import model.InternetPlan;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvAllPlans;
    Button btnReturn;
    ArrayList<InternetPlan> listOfPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    private void initialize() {
        tvAllPlans = findViewById(R.id.tvAllPlans);
        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);

        listOfPlans = (ArrayList<InternetPlan>) getIntent().getExtras().getSerializable("plans");

        StringBuilder list = new StringBuilder("");
        float totalPlans = 0;
        for (InternetPlan onePlan : listOfPlans) {
            list.append(onePlan + "\n");
            totalPlans += onePlan.getTotal();
        }
        tvAllPlans.setText(list);
        tvAllPlans.append(listOfPlans + " ");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.btnReturn:
                exit();
                break;


        }
    }

    private void exit() {
        finish();
    }
}