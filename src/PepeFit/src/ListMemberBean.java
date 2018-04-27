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
        userNames.add(new MemberTemp("Serhat Sağlık", "konyalı"));
        return userNames;
    }

    public void setUserNames(ArrayList<MemberTemp> userNames) {
        this.userNames = userNames;
    }

}

