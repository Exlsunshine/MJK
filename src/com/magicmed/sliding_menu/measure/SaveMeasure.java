package com.magicmed.sliding_menu.measure;

import android.os.Environment;

import com.magicmed.app.MagicCallback;
import com.magicmed.app.MagicClient;

import java.io.File;
import java.util.Date;

/**
 * Created by rei on 2015/7/2.
 */
public class SaveMeasure {
    private MagicClient server = null;
    private int count = 0;
    private int saveFlag = 0;
    private int resultCode = -2;

    public void setResultCode(int result){
        resultCode = result;

    }

    public int getResultCode(){
        return resultCode;
    }

    public SaveMeasure(MagicClient s){
        server = s;
    }

    public void saveMeasure(){
        this.testSaveMeasure();
        while(saveFlag == 0){

        }
    }

    protected void testSaveMeasure() {
        System.out.println("\n===========testSaveMeasure===================\n");
        com.magicmed.app.Measure measure = new com.magicmed.app.Measure();
        measure.setBpHigh((short) 123);
        measure.setBpLow((short) 60);
        measure.setAbi((short) 100);
        measure.setBpMean((short) 200);
        measure.setPwv((short) 120);
        measure.setBpPlus((short) 60);
        measure.setSpo2((short) 60);
        measure.setMeasureTime(new Date());
        measure.setSaveTime(new Date());
        measure.setWave(new File(Environment.getExternalStorageDirectory()+"/wave.data"));
        this.server.saveMeasure(measure, new MagicCallback<com.magicmed.app.Measure>() {
            public void onJSONObjectFormatError(String json, String msg) {
                System.out.println("onJSONObjectFormatError: , msg:" + msg);
                saveFlag = 1;
            }

            public void onNetworkError(int status, String msg) {
                System.out.println("onNetworkError: status:" + status + ", msg:" + msg);
                setResultCode(status);
                saveFlag = 1;
            }

            public void onFailure(int result, String msg, Object obj) {
                System.out.println("testSaveMeasure onFailure: result:" + result + ", msg:" + msg + ", obj:" + obj == null?"":obj.toString());
                setResultCode(result);
                saveFlag = 1;
            }

            public void onSuccess(int result, String msg, com.magicmed.app.Measure measure) {

                if(measure != null) {
                    System.out.println("save wave:" + msg);
                } else {
                    System.out.println("save wave success:");
                }

                SaveMeasure.this.count =  SaveMeasure.this.count + 1;
                if( SaveMeasure.this.count < 20) {
                    SaveMeasure.this.testSaveMeasure();
                } else {
                    saveFlag = 1;
                }
                setResultCode(result);
            }
        });
    }

}
