package com.talk2tail.common.model;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;


public class DogWeighData {

    private final int MILISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;
    private final double aspectX = 0.5;

    @Getter
    private List<WeightPoint> weightPoints = new ArrayList<>();
    private long firstDay;

    public void addWeightPoint(Date date, float weight){
        if (weightPoints.isEmpty()) firstDay = date.getTime()/MILISECONDS_IN_A_DAY;
        weightPoints.add(new WeightPoint(date, weight));

    }
    
    public LineGraphSeries<DataPoint> getPoints(){
        DataPoint[] dataPoints = new DataPoint[weightPoints.size()];

        for (int i = 0; i < weightPoints.size(); i++) {
            dataPoints[i] = new DataPoint((weightPoints.get(i).date.getTime()/MILISECONDS_IN_A_DAY - firstDay)*aspectX, weightPoints.get(i).weight);
        }


        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        return series;
    }


    public float getMaxWeight(){
        float max = 0;
        if (weightPoints.isEmpty()) return -1;
        for (int i = 0; i < weightPoints.size(); i++) {
            if (weightPoints.get(i).weight > max) max = weightPoints.get(i).weight;
        }
        return max;
    }

    public float getMinWeight(){
        float min = 1000000000;
        if (weightPoints.isEmpty()) return -1;
        for (int i = 0; i < weightPoints.size(); i++) {
            if (weightPoints.get(i).weight < min) min = weightPoints.get(i).weight;
        }
        return min;
    }

    public float getLastWeight(){
        if (weightPoints.isEmpty()) return -1;
        return weightPoints.get(weightPoints.size() -1).weight;
    }

    public long getDaysAgo(){
        Date lastDay = weightPoints.get(weightPoints.size() - 1).date;
        Date today = new Date();
        return (today.getTime() - lastDay.getTime())/MILISECONDS_IN_A_DAY;
    }

    private class WeightPoint {
        private Date date;
        private float weight;
        private WeightPoint(Date date, float weight){
            this.date = date;
            this.weight = weight;
        }
    }

}
