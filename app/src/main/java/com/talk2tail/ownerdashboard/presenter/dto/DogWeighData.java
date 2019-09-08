package com.talk2tail.ownerdashboard.presenter.dto;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import lombok.Getter;


public class DogWeighData {

    private final int MILISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;
    private final double aspectX = 0.5;

    @Getter
//    private Map<Integer, Float> weighPoints = new HashMap<>();
    private List<WaightPoint> weightPoints = new ArrayList<>();
    private long firstDay;

    public void addWeightPoint(Date date, float weight){
        if (weightPoints.isEmpty()) firstDay = date.getTime()/MILISECONDS_IN_A_DAY;
        weightPoints.add(new WaightPoint(date, weight));
//        if (weighPoints.isEmpty()) firstDay = date.getDay();
//        weighPoints.put(date.getDay(), weight);
    }
    
    public LineGraphSeries<DataPoint> getPoints(){
        DataPoint[] dataPoints = new DataPoint[weightPoints.size()];

        for (int i = 0; i < weightPoints.size(); i++) {
            int a = weightPoints.get(i).date.getDay();
            dataPoints[i] = new DataPoint((weightPoints.get(i).date.getTime()/MILISECONDS_IN_A_DAY - firstDay)*aspectX, weightPoints.get(i).weight);
        }
//        int i = 0;
//        for (Map.Entry entry : weighPoints.entrySet()) {
//
//            System.out.println("Key: " + entry.getKey() + " Value: "
//                    + entry.getValue());
//            dataPoints[i] = new DataPoint((Integer) entry.getKey() - firstDay, (Float)entry.getValue());
//            i++;
//        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        return series;
    }

    private class WaightPoint {
        private Date date;
        private float weight;
        private WaightPoint(Date date, float weight){
            this.date = date;
            this.weight = weight;
        }
    }

//    public Map<Integer, Float> getWeghPoints(){
//        return weighPoints;
//    }
    
}
