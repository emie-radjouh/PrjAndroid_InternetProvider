package com.example.prjinternetprovider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import model.InternetPlan;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edClientNumber, edTps, edTvq, edSubtotal, edTotal, edNbMonths;
    RadioButton rbBell, rbVideotron, rbAcanac;
    Button btnSave, btnShowAll, btnExit;

    private InternetPlan plan;
    private String internetProvider;
    private float subTotal;
    private float total;

    private ArrayList<InternetPlan> listOfPlans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }


    private void initialize() {
        edClientNumber = findViewById(R.id.edClientNumber);
        edNbMonths = findViewById(R.id.edNbMonths);
        edSubtotal = findViewById(R.id.edSubtotal);
        edTotal = findViewById(R.id.edTotal);
        edTps = findViewById(R.id.edTPS);
        edTvq = findViewById(R.id.editTVQ);

        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
        btnShowAll = findViewById(R.id.btnShowAll);
        btnSave.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);


        rbBell = findViewById(R.id.rbBell);
        rbAcanac = findViewById(R.id.rbAcanac);
        rbVideotron = findViewById(R.id.rbVideotron);
        rbBell.setOnClickListener(this);
        rbVideotron.setOnClickListener(this);
        rbAcanac.setOnClickListener(this);

        listOfPlans = new ArrayList<InternetPlan>();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.btnSave:
                savePlan(view);
                break;
            case R.id.btnExit:
                exit();
                break;
            case R.id.btnShowAll:
                showAllPlans();
                break;
            case R.id.rbBell:
                internetProvider = "Bell";
                showTotal();
                break;
            case R.id.rbVideotron:
                internetProvider = "Videotron";
                showTotal();
                break;
            case R.id.rbAcanac:
                internetProvider = "Acanac";
                showTotal();
                break;


        }

    }

    private void exit() {
        finish();
    }

    private void showAllPlans(){
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("plans",listOfPlans);
        startActivity(intent);

    }

    private void savePlan(View view) {
        try {
            int clientNumber = Integer.valueOf(edClientNumber.getText().toString());
            int nbMonths = Integer.valueOf(edNbMonths.getText().toString());

            InternetPlan onePlan = new InternetPlan(clientNumber, internetProvider, nbMonths);
            listOfPlans.add(onePlan);

            Snackbar.make(view, "The plan for the client " + clientNumber +
                    " is successfully created", Snackbar.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    public double getTotal(String internetProvider, float nbMonths) {
        float tps,tvq,mPrice;
        if (internetProvider.equals("Bell")) {
            mPrice = nbMonths * 60;
            tps = (float) (0.06 * mPrice);
            tvq = (float) ((mPrice + tps) * 0.095);
            total = mPrice + tps + tvq;
        } else if (internetProvider.equals("Videotron")) {
            mPrice = nbMonths * 70;
            tps = (float) (0.06 * mPrice);
            tvq = (float) ((mPrice + tps) * 0.095);
            total = mPrice + tps + tvq;
        } else if (internetProvider.equals("Acanac")) {
            mPrice = nbMonths * 45;
            tps = (float) (0.06 * mPrice);
            tvq = (float) ((mPrice + tps) * 0.095);
            total = mPrice + tps + tvq;
        }

        return  Math.round(total*100.0)/100.0;
    }

    public double getSubTotal(String internetProvider, float nbMonths) {
        if (internetProvider.equals("Bell")) {
            subTotal = 60 * nbMonths;
        } else if (internetProvider.equals("Videotron")) {
            subTotal = 70 * nbMonths;

        } else if (internetProvider.equals("Acanac")) {
            subTotal = 45 * nbMonths;
        }

        return  Math.round(subTotal*100.0)/100.0;
    }


    private void showTotal(){
        float nbMonthsByUser = Float.valueOf(edNbMonths.getText().toString());
         total = (float) getTotal(internetProvider,nbMonthsByUser);
         subTotal = (float) getSubTotal(internetProvider, nbMonthsByUser);
         edTotal.setText(String.format(String.valueOf(total)));
         edSubtotal.setText(String.format(String.valueOf(subTotal)));
         edTps.setText("6%");
         edTvq.setText("9%");
    }
}

