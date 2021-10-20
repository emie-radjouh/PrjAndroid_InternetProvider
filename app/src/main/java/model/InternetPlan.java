package model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class InternetPlan implements Serializable{

    private int clientNumber;
    private String internetProvider;
    private float nbMonths;
    private float subTotal;
    private float tps;
    private float tvq;
    private float total;

    public InternetPlan(int clientNumber, String internetProvider,
                        float nbMonths) {
        this.clientNumber = clientNumber;
        this.internetProvider = internetProvider;
        this.nbMonths = nbMonths;
        this.subTotal = subTotal;
        this.tps = tps;
        this.tvq = tvq;
        this.total = total;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getInternetProvider() {
        return internetProvider;
    }

    public void setInternetProvider(String internetProvider) {
        this.internetProvider = internetProvider;
    }

    public float getNbMonths() {
        return nbMonths;
    }

    public void setNbMonths(float nbMonths) {
        this.nbMonths = nbMonths;
    }


    public float getTps() {
        return tps;
    }

    public void setTps(float tps) {
        this.tps = tps;
    }

    public float getTvq() {
        return tvq;
    }

    public void setTvq(float tvq) {
        this.tvq = tvq;
    }


    @NonNull
    @Override
    public String toString() {

        String client = "Client Number:" + clientNumber + " Internet provider:" + internetProvider + " Number of Months:" +
                nbMonths + " Subtotal:" + getSubTotal() + " Tps:" + tps + " Tvq:" + tvq + " Total:" + getTotal();
        return client;


    }


    public Float getTotal() {
        if (internetProvider.equals("Bell")) {
            float mPrice;
            mPrice = nbMonths * 60;
            tps = (float) (0.06 * mPrice);
            tvq = (float) ((mPrice + tps) * 0.095);
            total = mPrice + tps + tvq;

        } else if (internetProvider.equals("Videotron")) {
            float mPrice;
            mPrice = nbMonths * 70;
            tps = (float) (0.06 * mPrice);
            tvq = (float) ((mPrice + tps) * 0.095);
            total = mPrice + tps + tvq;

        } else if (internetProvider.equals("Acanac")) {
            float mPrice;
            mPrice = nbMonths * 45;
            tps = (float) (0.06 * mPrice);
            tvq = (float) ((mPrice + tps) * 0.095);
            total = mPrice + tps + tvq;
        }
        return total;
    }

    public float getSubTotal(){
        if(internetProvider.equals("Bell")){
            subTotal = 60 * nbMonths;
        }else if(internetProvider.equals("Videotron")){
            subTotal = 70 * nbMonths;

        }else if(internetProvider.equals("Acanac")){
            subTotal = 45 * nbMonths;
        }

        return subTotal;

    }


}
