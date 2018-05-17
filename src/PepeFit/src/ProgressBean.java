import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		
        try {
            DatabaseBean database = new DatabaseBean();
            result = database.execute_fetch_all("SELECT * FROM Progress",-1);
            database.destruct_connection();
        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE PULLING COURSES " + e.getMessage());
        }
		
		createLineModels();
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public LineChartModel getLineArm() {
		lineArm = initLinearModel("Arm", 0, 10);
		lineModel = lineArm;
		setCurrentGraph("arm");
		//System.out.println("In getLineModel1");
		//System.out.println(lineModel.getTitle());
		return lineModel;
	}

	public LineChartModel getLineLeg() {
		lineLeg = initLinearModel("Leg", 0, 10);
		lineModel = lineLeg;
		setCurrentGraph("leg");
		//System.out.println("In getLineModel2");
		//System.out.println(getCurrentGraph());
		return lineModel;
	}
	
	public LineChartModel getLineHeight() {
		lineHeight = initLinearModel("Height", 100, 250); /* 100 olabilecek min deger, 250 olabilecek max deger */
		lineModel = lineHeight;
		setCurrentGraph("height");
		return lineModel;
	}

	public LineChartModel getLineWeight() {
		lineWeight = initLinearModel("Weight", 40, 150);
		lineModel = lineWeight;
		setCurrentGraph("weight");
		return lineModel;
	}

	public LineChartModel getLineShoulder() {
		lineShoulder = initLinearModel("Shoulder", 80, 140);
		lineModel = lineShoulder;
		setCurrentGraph("shoulder");
		return lineModel;
	}

	public LineChartModel getLineChest() {
		lineChest = initLinearModel("Chest", 70, 120);
		lineModel = lineChest;
		setCurrentGraph("chest");
		return lineModel;
	}

	public LineChartModel getLineAbdomen() {
		lineAbdomen = initLinearModel("Abdomen", 40, 80);
		lineModel = lineAbdomen;
		setCurrentGraph("abdomen");
		return lineModel;
	}

	public LineChartModel getLineWaist() {
		lineWaist = initLinearModel("Waist", 30, 60);
		lineModel = lineWaist;
		setCurrentGraph("waist");
		return lineModel;
	}

	public LineChartModel getLineHip() {
		lineHip = initLinearModel("Hip", 40, 90);
		lineModel = lineHip;
		setCurrentGraph("hip");
		return lineModel;
	}

	private void createLineModels() {
			
		lineModel = initLinearModel("Choose Chart", 0, 1); /* ilk olarak gordugu chart */
	}

	private LineChartModel initLinearModel(String measure, int min, int max) {

		LineChartModel model = new LineChartModel();
		LineChartSeries series = new LineChartSeries();
		
		series.setLabel(measure);
		if(!measure.equals("Choose Chart")) {
			traverseAndSet(measure, series);
		} else {
			series.set(0, 0);
		}
		

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

	private String value = "";

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

		
		if (currentUser.hasRole("member")) {
			//System.out.println("role member");
			String memberID = ShiroAuthenticationClass.getId();
			
			for(LinkedHashMap<String, Object> memberInfo : result) {
				if(memberInfo.get("TC").equals(memberID)) {
					if((Number) memberInfo.get(measureX) != null && (Number) memberInfo.get(measureY) != null) {
						series.set((Number) memberInfo.get(measureX), (Number) memberInfo.get(measureY));
					}
				}
			}
		}
		else if(currentUser.hasRole("trainer")) {
			//System.out.println("role trainer");
			//System.out.println("MEMBERID : " + MemberTemp.getId());
			
			String memberID = MemberTemp.getId();
			
			for(LinkedHashMap<String, Object> memberInfo : result) {
				if(memberInfo.get("TC").equals(memberID)) {
					if((Number) memberInfo.get(measureX) != null && (Number) memberInfo.get(measureY) != null) {
						series.set((Number) memberInfo.get(measureX), (Number) memberInfo.get(measureY));
					}
				}
			}
		

			
//	    	ArrayList<LinkedHashMap<String,Object>> members = null;
//	    	
//	        try {
//	            DatabaseBean database = new DatabaseBean();
//	            members = database.execute_fetch_all("SELECT * FROM Member",-1);
//	            database.destruct_connection();
//	        } catch (SQLException e) {
//	            System.out.println("ERROR OCCURED WHILE PULLING COURSES " + e.getMessage());
//	        }
//	    	
//	        System.out.println(members);
//	        
//	        for(LinkedHashMap<String, Object> member : members) {
//	        	ListMemberBean.userNames.add(new MemberTemp(member.get("NAME") + " " + member.get("SURNAME"), (String) member.get("EMAIL")));
//	        }
//	        
//	        System.out.println(ListMemberBean.userNames);
			
		}
		
		
	}
	
	public void addValue() throws SQLException {
		if(!getValue().equals("") && !getCurrentGraph().equals("")) {
			
			System.out.println("VALUE : " + getValue());
			System.out.println("CURRENT GRAPH : " + getCurrentGraph());
			System.out.println("MEMBER ID : " + MemberTemp.getId());
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			DatabaseBean database = new DatabaseBean();
			//database.execute("Insert into Progresstemp(HEIGHTX) values(\"2018-01-01\")",1);
			//database.execute("Insert into Progresstemp(HEIGHTX) values("+ dateFormat.format(date).toString() + ")",1);
			
			//database.execute("Insert into Progress(TC,HEIGHTX,HEIGHTY) values(" + MemberTemp.getId() + ",\"2018-01-01\"," + getValue() + ")",1);
			//database.execute("Insert into Progress(TC,HEIGHTX,HEIGHTY) values(" + MemberTemp.getId() + ",7," + getValue() + ")",1);
			database.execute("Insert into Progress(TC,ARMX,ARMy) values(" + MemberTemp.getId() + ",6," + getValue() + ")",1);
			
			database.commit_trans();

			database.destruct_connection();
		}
	}
}