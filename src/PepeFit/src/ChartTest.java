import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.*;

@ManagedBean
public class ChartTest implements Serializable {

    private LineChartModel lineModel;
    private LineChartModel lineModel1;
    private LineChartModel lineModel2;

    private HashMap values = new HashMap();

    @PostConstruct
    public void init() {
        createLineModels();
    }

    public LineChartModel getLineModel() { return lineModel; }
    public LineChartModel getLineModel1() {
        lineModel = lineModel1;
        return lineModel;
    }
    public LineChartModel getLineModel2() {
        lineModel = lineModel2;
        return lineModel;
    }

    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Arm");
        lineModel1.setLegendPosition("sw");
        Axis yAxis1 = lineModel1.getAxis(AxisType.Y);
        yAxis1.setMin(0);
        yAxis1.setMax(10);

        lineModel2 = initLinearModel2();
        lineModel2.setTitle("Leg");
        lineModel2.setLegendPosition("sw");
        Axis yAxis2 = lineModel1.getAxis(AxisType.Y);
        yAxis2.setMin(0);
        yAxis2.setMax(10);

        lineModel = lineModel1;
    }

    private LineChartModel initLinearModel() {

        LineChartModel model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Arm");
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
        model.addSeries(series1);
        model.setExtender("ext");
        model.setSeriesColors("ffffff");
        model.setShadow(false);
        System.out.println("1");
        return model;
    }

    private LineChartModel initLinearModel2() {

        LineChartModel model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Arm");
        series1.set(1, 5);
        series1.set(2, 2);
        series1.set(3, 7);
        series1.set(4, 13);
        series1.set(5, 6);
        model.addSeries(series1);
        model.setExtender("ext");
        model.setSeriesColors("ffffff");
        model.setShadow(false);
        System.out.println("2");
        return model;
    }


}