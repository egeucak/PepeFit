import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@SuppressWarnings("serial")
@ManagedBean
public class LineChartBean implements Serializable {

	private LineChartModel lineModel;
	private LineChartModel lineHeight;
    private LineChartModel lineWeight;
    private LineChartModel lineArm;
    private LineChartModel lineShoulder;
    private LineChartModel lineLeg;
    private LineChartModel lineChest;
    private LineChartModel lineAbdomen;
    private LineChartModel lineWaist;
    private LineChartModel lineHip;

	//private HashMap values = new HashMap();
	private static String currentGraph = "";

	public void setCurrentGraph(String currentGraph) {
		LineChartBean.currentGraph = currentGraph;
	}

	public String getCurrentGraph() {
		return currentGraph;
	}

	@PostConstruct
	public void init() {
		createLineModels();
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public LineChartModel getLineArm() {
		lineModel = lineArm;
		setCurrentGraph("arm");
		System.out.println("In getLineModel1");
		System.out.println(lineModel.getTitle());
		return lineModel;
	}

	public LineChartModel getLineLeg() {
		lineModel = lineLeg;
		setCurrentGraph("leg");
		System.out.println("In getLineModel2");
		System.out.println(getCurrentGraph());
		return lineModel;
	}
	
	public LineChartModel getLineHeight() {
		lineModel = lineHeight;
		setCurrentGraph("height");
		return lineModel;
	}

	public LineChartModel getLineWeight() {
		lineModel = lineWeight;
		setCurrentGraph("weight");
		return lineModel;
	}

	public LineChartModel getLineShoulder() {
		lineModel = lineShoulder;
		setCurrentGraph("shoulder");
		return lineModel;
	}

	public LineChartModel getLineChest() {
		lineModel = lineChest;
		setCurrentGraph("chest");
		return lineModel;
	}

	public LineChartModel getLineAbdomen() {
		lineModel = lineAbdomen;
		setCurrentGraph("abdomen");
		return lineModel;
	}

	public LineChartModel getLineWaist() {
		lineModel = lineWaist;
		setCurrentGraph("waist");
		return lineModel;
	}

	public LineChartModel getLineHip() {
		lineModel = lineHip;
		setCurrentGraph("hip");
		return lineModel;
	}

	private void createLineModels() {
		
		lineHeight = initLinearModel("Height", 100, 250);
		lineWeight = initLinearModel("Weight", 40, 150);
		lineArm = initLinearModel("Arm", 0, 10);
		lineShoulder = initLinearModel("Shoulder", 80, 140);
		lineLeg = initLinearModel("Leg", 0, 10);
		lineChest = initLinearModel("Chest", 70, 120);
		lineAbdomen = initLinearModel("Abdomen", 40, 80);
		lineWaist = initLinearModel("Waist", 30, 60);
		lineHip = initLinearModel("Hip", 40, 90);
		
		lineModel = lineHeight; /* ilk olarak gordugu chart */
	}

	private LineChartModel initLinearModel(String measure, int min, int max) {

		LineChartModel model = new LineChartModel();
		LineChartSeries series = new LineChartSeries();
		series.setLabel(measure);
		
		//TODO Bu kisimlari db den al for dongusu ile set yap
		if(measure.equals("Height")) {
			series.set(1, 170);
			series.set(2, 171);
			series.set(3, 171);
			series.set(4, 172);
			series.set(5, 173);
		}
		else if(measure.equals("Weight")) {
			series.set(1, 60);
			series.set(2, 65);
			series.set(3, 70);
			series.set(4, 68);
			series.set(5, 67);
		}
		else if(measure.equals("Arm")) {
			series.set(1, 2);
			series.set(2, 1);
			series.set(3, 3);
			series.set(4, 6);
			series.set(5, 8);
		}
		else if(measure.equals("Shoulder")) {
			series.set(1, 90);
			series.set(2, 95);
			series.set(3, 98);
			series.set(4, 100);
			series.set(5, 102);
		}
		else if(measure.equals("Leg")) {
			series.set(1, 5);
			series.set(2, 2);
			series.set(3, 7);
			series.set(4, 8);
			series.set(5, 6);
		}
		else if(measure.equals("Chest")) {
			series.set(1, 70);
			series.set(2, 75);
			series.set(3, 78);
			series.set(4, 80);
			series.set(5, 88);
		}
		else if(measure.equals("Chest")) {
			series.set(1, 70);
			series.set(2, 75);
			series.set(3, 78);
			series.set(4, 80);
			series.set(5, 88);
		}
		else if(measure.equals("Abdomen")) {
			series.set(1, 50);
			series.set(2, 55);
			series.set(3, 58);
			series.set(4, 60);
			series.set(5, 68);
		}
		else if(measure.equals("Waist")) {
			series.set(1, 30);
			series.set(2, 35);
			series.set(3, 38);
			series.set(4, 40);
			series.set(5, 48);
		}
		else if(measure.equals("Hip")) {
			series.set(1, 50);
			series.set(2, 55);
			series.set(3, 58);
			series.set(4, 60);
			series.set(5, 68);
		}

		model.setTitle(measure);
		model.setLegendPosition("sw");
		model.addSeries(series);
		model.setExtender("ext");
		model.setSeriesColors("ffffff");
		model.setShadow(false);
		System.out.println("1");
		model.getAxis(AxisType.Y).setMin(min);
		model.getAxis(AxisType.Y).setMax(max);
		return model;
	}
	
//	private LineChartModel initLinearModel2() {
//
//		LineChartModel model = new LineChartModel();
//		LineChartSeries series1 = new LineChartSeries();
//		series1.setLabel("Arm");
//		series1.set(1, 5);
//		series1.set(2, 2);
//		series1.set(3, 7);
//		series1.set(4, 13);
//		series1.set(5, 6);
//		model.addSeries(series1);
//		model.setExtender("ext");
//		model.setSeriesColors("ffffff");
//		model.setShadow(false);
//		System.out.println("2");
//		return model;
//	}

	private String value;

	public String getValue() {
		System.out.println("Get içinde");
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		System.out.println(getCurrentGraph() + value);
		System.out.println("Set içinde");
	}
}