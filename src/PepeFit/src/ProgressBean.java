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
import org.primefaces.model.chart.DateAxis;
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
    private static String MemberID;
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
        lineArm = initLinearModel("Arm");
        lineModel = lineArm;
        setCurrentGraph("arm");
        //System.out.println("In getLineModel1");
        //System.out.println(lineModel.getTitle());
        return lineModel;
    }

    public LineChartModel getLineLeg() {
        lineLeg = initLinearModel("Leg");
        lineModel = lineLeg;
        setCurrentGraph("leg");
        //System.out.println("In getLineModel2");
        //System.out.println(getCurrentGraph());
        return lineModel;
    }

    public LineChartModel getLineHeight() {
        lineHeight = initLinearModel("Height");
        lineModel = lineHeight;
        setCurrentGraph("height");
        return lineModel;
    }

    public LineChartModel getLineWeight() {
        lineWeight = initLinearModel("Weight");
        lineModel = lineWeight;
        setCurrentGraph("weight");
        return lineModel;
    }

    public LineChartModel getLineShoulder() {
        lineShoulder = initLinearModel("Shoulder");
        lineModel = lineShoulder;
        setCurrentGraph("shoulder");
        return lineModel;
    }

    public LineChartModel getLineChest() {
        lineChest = initLinearModel("Chest");
        lineModel = lineChest;
        setCurrentGraph("chest");
        return lineModel;
    }

    public LineChartModel getLineAbdomen() {
        lineAbdomen = initLinearModel("Abdomen");
        lineModel = lineAbdomen;
        setCurrentGraph("abdomen");
        return lineModel;
    }

    public LineChartModel getLineWaist() {
        lineWaist = initLinearModel("Waist");
        lineModel = lineWaist;
        setCurrentGraph("waist");
        return lineModel;
    }

    public LineChartModel getLineHip() {
        lineHip = initLinearModel("Hip");
        lineModel = lineHip;
        setCurrentGraph("hip");
        return lineModel;
    }

    public static String getMemberID() {
        return MemberID;
    }

    public static void setMemberID(String memberID) {
        MemberID = memberID;
    }

    private void createLineModels() {

        lineModel = initLinearModel("Choose Chart"); /* ilk olarak gordugu chart */
    }

    private LineChartModel initLinearModel(String measure) {

        LineChartModel model = new LineChartModel();
        LineChartSeries series = new LineChartSeries();

        series.setLabel(measure);
        if(!measure.equals("Choose Chart")) {
            traverseAndSet(measure, series);
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            series.set(dateFormat.format(date).toString(), 0);
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
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setTickFormat("%b %#d, %y");
        model.getAxes().put(AxisType.X, axis);
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
    public void buttonAction(String person) {
        System.out.println("clicked on person "+ person);
        setMemberID(person);
    }

    public void traverseAndSet(String measure, LineChartSeries series) {

//		String measureX = measure.toUpperCase(Locale.ENGLISH) + "X";
//		String measureY = measure.toUpperCase(Locale.ENGLISH) + "Y";

        Subject currentUser = SecurityUtils.getSubject();

        //System.out.println("measureX : " + measureX);
        //System.out.println("measureY : " + measureY);


        if (currentUser.hasRole("member")) {
            //System.out.println("role member");
            String memberID = ShiroAuthenticationClass.getId();
            int check = 0 ;
            for(LinkedHashMap<String, Object> memberInfo : result) {
                if(memberInfo.get("TC").equals(memberID+measure.toLowerCase(Locale.ENGLISH)) && memberInfo.get("TYPE").equals(measure.toLowerCase(Locale.ENGLISH))) {

                    String[] date = memberInfo.get("DATE").toString().split(",");
                    String[] value = memberInfo.get("VALUE").toString().split(",");

                    for(int i = 0; i < date.length ; i++) {
//						System.out.println(date[i]);
//						System.out.println(value[i]);
                        series.set(date[i], Integer.parseInt(value[i]));
                    }
                    check++;
                }
            }
            if(check == 0) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();

                series.set(dateFormat.format(date).toString(), 0);
            }
        }
        else if(currentUser.hasRole("trainer")) {
            //System.out.println("role trainer");
            System.out.println("MEMBERID : " + getMemberID());
            int check = 0;
            for(LinkedHashMap<String, Object> memberInfo : result) {
                if(memberInfo.get("TC").equals(getMemberID()+measure.toLowerCase(Locale.ENGLISH)) && memberInfo.get("TYPE").equals(measure.toLowerCase(Locale.ENGLISH))) {

                    String[] date = memberInfo.get("DATE").toString().split(",");
                    String[] value = memberInfo.get("VALUE").toString().split(",");

                    for(int i = 0; i < date.length ; i++) {
//						System.out.println(date[i]);
//						System.out.println(value[i]);
                        series.set(date[i], Integer.parseInt(value[i]));
                    }
                    check++;
                }
            }
            if(check == 0) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();

                series.set(dateFormat.format(date).toString(), 0);
            }
        }


    }

    public void addValue() throws SQLException {
        if(!getValue().equals("") && !getCurrentGraph().equals("")) {

            System.out.println("VALUE : " + getValue());
            System.out.println("CURRENT GRAPH : " + getCurrentGraph());
            System.out.println("MEMBER ID : " + getMemberID());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            DatabaseBean database = new DatabaseBean();
            //database.execute("Insert into Progresstemp(HEIGHTX) values(\"2018-01-01\")",1);
            //database.execute("Insert into Progresstemp(HEIGHTX) values("+ dateFormat.format(date).toString() + ")",1);

            //database.execute("Insert into Progress(TC,HEIGHTX,HEIGHTY) values(" + MemberTemp.getId() + ",\"2018-01-01\"," + getValue() + ")",1);
            //database.execute("Insert into Progress(TC,HEIGHTX,HEIGHTY) values(" + MemberTemp.getId() + ",7," + getValue() + ")",1);
            //database.execute("Insert into Progress(TC,ARMX,ARMy) values(" + getMemberID() + ",7," + getValue() + ")",1);
            //database.execute("Insert into Progress(TC,DATE,VALUE,TYPE) values(" + getMemberID() + getCurrentGraph().toLowerCase(Locale.ENGLISH) + ",7," + getValue() + ")",1);

            int check = 0;
            for(LinkedHashMap<String, Object> memberInfo : result) {
                if(memberInfo.get("TC").equals(getMemberID()+getCurrentGraph().toLowerCase(Locale.ENGLISH))) {
                    String current_date = memberInfo.get("DATE").toString();
                    System.out.println("BEFORE CURRENT_DATE : " + current_date);
                    current_date = current_date + "," + dateFormat.format(date).toString();
                    System.out.println("AFTER CURRENT_DATE : " + current_date);

                    String current_value = memberInfo.get("VALUE").toString();
                    System.out.println("BEFORE CURRENT_VALUE : " + current_value);
                    current_value = current_value + "," + getValue();
                    System.out.println("AFTER CURRENT_VALUE : " + current_value);


                    database.execute("UPDATE Progress SET DATE=?,VALUE=? where TC=?",1,current_date,current_value,getMemberID()+getCurrentGraph().toLowerCase(Locale.ENGLISH));

                    database.commit_trans();
                    database.destruct_connection();
                }
                if(memberInfo.containsKey(getMemberID()+getCurrentGraph().toLowerCase(Locale.ENGLISH))) {
                    check++;

                }
            }
            if(check == 0) {
                database.execute("Insert into Progress values(?,?,?,?)", 1, getMemberID()+getCurrentGraph().toLowerCase(Locale.ENGLISH),dateFormat.format(date).toString(),getValue(),getCurrentGraph().toLowerCase(Locale.ENGLISH));
                database.commit_trans();
                database.destruct_connection();
            }


            database.commit_trans();

            database.destruct_connection();
        }
    }
}