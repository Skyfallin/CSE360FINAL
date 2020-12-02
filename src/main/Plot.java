package main;

import java.awt.Color;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Plot extends JFrame {
    private String X_AXIS = "Percent Attendance", Y_AXIS = "Number of Students";
    private final int MAX_MINUTES = 75,
            PERCENT_MULTIPLIER = 10,
            MAX_ATTENDANCE = 10;

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

    private HashMap<String, int[]> getPlotData(HashMap<String, Student> studentMap){
        HashMap<String, int[]> plotData = new HashMap<>();

        for (Student student : studentMap.values()){
            for (Date date: student.getAttendance().keySet()){
                int minutes = student.getAttendance().get(date);
                int percentAttend = Math.min( minutes * PERCENT_MULTIPLIER / MAX_MINUTES, MAX_ATTENDANCE);

                if(!plotData.containsKey(date.toString())){
                    plotData.put(date.toString(), new int[11]);
                }
                int[] attendList = plotData.get(date.toString());
                attendList[percentAttend]++;
                plotData.put(date.toString(), attendList);
            }
        }
        return plotData;
    }

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
