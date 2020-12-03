package main;

/*
 * @author: Jessica Huber and Dimetrius Hightower
 * ClassID: 2020Fall-T-CSE360-70606
 *  FINAL PROJECT
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * The Plot class allows roster and attendance data to be plotted in a popup
 * JFrame using a JFreeChart.
 */
public class Plot extends JFrame {
    private String X_AXIS = "Percent Attendance", Y_AXIS = "Number of Students";
    private final int MAX_MINUTES = 75,
            PERCENT_MULTIPLIER = 10,
            MAX_ATTENDANCE = 10,
            ATTENDANCE_BLOCKS = 11;

    /**
     * Initializes the plot components.
     * @param studentMap data to populate plot
     * @param title of plot being created
     */
    public Plot (HashMap<String, Student> studentMap, String title){
        super(title);
        XYDataset dataSet = createDataset(studentMap);
        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                title,
                X_AXIS,
                Y_AXIS,
                dataSet
        );
        XYPlot plot = (XYPlot)scatterPlot.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        ChartPanel panel = new ChartPanel(scatterPlot);
        setContentPane(panel);
    }

    /**
     *  Converts the hashmap of students to a hashmap that contains attendance
     *  counts by percent of attendance for each date of attendance that was loaded.
     * @param studentMap provides the attendance data
     * @return plotData
     */
    private HashMap<String, int[]> getPlotData(HashMap<String, Student> studentMap){
        HashMap<String, int[]> plotData = new HashMap<>();

        for (Student student : studentMap.values()){
            for (String date: student.getAttendance().keySet()){
                int minutes = student.getAttendance().get(date);
                int percentAttend = Math.min( minutes * PERCENT_MULTIPLIER / MAX_MINUTES, MAX_ATTENDANCE);

                if(!plotData.containsKey(date.toString())){
                    plotData.put(date.toString(), new int[ATTENDANCE_BLOCKS]);
                }
                int[] attendList = plotData.get(date.toString());
                attendList[percentAttend]++;
                plotData.put(date.toString(), attendList);
            }
        }
        return plotData;
    }

    /**
     * Creates a XYDataset from the studentMap attendance attribute.
     * @param studentMap provides attendance data
     * @return dataCollection XYDataset
     */
    private XYDataset createDataset(HashMap<String, Student> studentMap){
        HashMap<String, int[]> plotData = getPlotData(studentMap);

        XYSeriesCollection dataCollection = new XYSeriesCollection();
        for (String date : plotData.keySet()){
            XYSeries dataSeries = new XYSeries(date);
            int[] data = plotData.get(date);
            for (int index = 0; index < data.length; index++){
                dataSeries.add((index * PERCENT_MULTIPLIER), data[index]);
            }
            dataCollection.addSeries(dataSeries);
        }
        return dataCollection;
    }


}
