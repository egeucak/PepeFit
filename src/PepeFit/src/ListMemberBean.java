import org.primefaces.context.RequestContext;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sound.midi.SysexMessage;
import javax.xml.stream.events.StartDocument;

@ManagedBean
@RequestScoped

public class ListMemberBean {
    private ArrayList<MemberTemp> userNames = new ArrayList<MemberTemp>();

    public ArrayList<MemberTemp> getUserNames() {
        userNames.add(new MemberTemp("Ege Uçak", "egeu"));
        userNames.add(new MemberTemp("Bahadır Adak", "baho"));
        userNames.add(new MemberTemp("Eyüpcan Bodur", "konyalı"));
        userNames.add(new MemberTemp("Berk Can Özen", "brkczn"));
        userNames.add(new MemberTemp("Serhat Sağlık", "mavi"));
        userNames.add(new MemberTemp("Sean Green", "seangreen"));
        userNames.add(new MemberTemp("Keegan Alvarado", "keeganalvarado"));
        userNames.add(new MemberTemp("Nguyen","oscarnguyen"));
        userNames.add(new MemberTemp("Carrillo","reginaldcarrillo"));
        userNames.add(new MemberTemp("Isabelle","Strickland"));
        userNames.add(new MemberTemp("Alaina","Willis"));
        userNames.add(new MemberTemp("Tiffani","Phillips"));
        userNames.add(new MemberTemp("Eugene","Miranda"));
        userNames.add(new MemberTemp("Brenna","Chandler"));
        userNames.add(new MemberTemp("Ryan","Wolfe"));
        userNames.add(new MemberTemp("Caitlyn","Clarke"));
        userNames.add(new MemberTemp("Caitlin","Lloyd"));
        userNames.add(new MemberTemp("Graham","Zimmerman"));
        userNames.add(new MemberTemp("Nathanael","Mclaughlin"));
        userNames.add(new MemberTemp("Dalton","Sanchez"));
        userNames.add(new MemberTemp("Esmeralda","Morales"));
        userNames.add(new MemberTemp("Blake","Lyons"));
        userNames.add(new MemberTemp("Vincent","Garcia"));

        return userNames;
    }

    public void setUserNames(ArrayList<MemberTemp> userNames) {
        this.userNames = userNames;
    }

}

