import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.faces.bean.ManagedBean;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@SuppressWarnings("serial")
@ManagedBean
public class ProgressBean implements Serializable {

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
    
    ArrayList<LinkedHashMap<String,Object>> result = null;
	
    //private HashMap values = new HashMap();
	private static String currentGraph = "";

	public void setCurrentGraph(String currentGraph) {
		ProgressBean.currentGraph = currentGraph;
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
		//System.out.println("In getLineModel1");
		//System.out.println(lineModel.getTitle());
		return lineModel;
	}

	public LineChartModel getLineLeg() {
		lineModel = lineLeg;
		setCurrentGraph("leg");
		//System.out.println("In getLineModel2");
		//System.out.println(getCurrentGraph());
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
		traverseAndSet(measure, series);

//		if(measure.equals("Height")) {
//			//System.out.println(result);
//			traverseAndSet(measure, series);
//		}
//		else if(measure.equals("Weight")) {
//			traverseAndSet(measure, series);
//		}
//		else if(measure.equals("Arm")) {
//			traverseAndSet(measure, series);
//		}
//		else if(measure.equals("Shoulder")) {
//			traverseAndSet(measure, series);
//		}
//		else if(measure.equals("Leg")) {
//			traverseAndSet(measure, series);
//		}
//		else if(measure.equals("Chest")) {
//			traverseAndSet(measure, series);
//		}
//		else if(measure.equals("Abdomen")) {
//			traverseAndSet(measure, series);
//		}
//		else if(measure.equals("Waist")) {
//			traverseAndSet(measure, series);
//		}
//		else if(measure.equals("Hip")) {
//			traverseAndSet(measure, series);
//		}

		model.setTitle(measure);
		model.setLegendPosition("sw");
		model.addSeries(series);
		model.setExtender("ext");
		model.setSeriesColors("ffffff");
		model.setShadow(false);
		//System.out.println("1");
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
		//System.out.println("Get içinde");
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		//System.out.println(getCurrentGraph() + value);
		//System.out.println("Set içinde");
	}
	
	public void traverseAndSet(String measure, LineChartSeries series) {
		
		String measureX = measure.toUpperCase(Locale.ENGLISH) + "X";
		String measureY = measure.toUpperCase(Locale.ENGLISH) + "Y";
		Subject currentUser = SecurityUtils.getSubject();
		
		//System.out.println("measureX : " + measureX);
		//System.out.println("measureY : " + measureY);
        try {
            DatabaseBean database = new DatabaseBean();
            result = database.execute_fetch_all("SELECT * FROM Progress",-1);
            database.destruct_connection();
        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE PULLING COURSES " + e.getMessage());
        }
		
		if (currentUser.hasRole("member")) {
			//System.out.println("role member");
			String memberID = ShiroAuthenticationClass.getId();
			
			for(LinkedHashMap<String, Object> memberInfo : result) {
				if(memberInfo.get("TC").equals(memberID)) {
					series.set((Number) memberInfo.get(measureX), (Number) memberInfo.get(measureY));
				}
			}
		}
		else if(currentUser.hasRole("trainer")) {
			System.out.println("role trainer");
		}
	}
}