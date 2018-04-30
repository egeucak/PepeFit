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

public class ListTrainerBean {
    private ArrayList<TrainerTemp> trainerNames = new ArrayList<TrainerTemp>();

    public ArrayList<TrainerTemp> getTrainerNames() {
        trainerNames.add(new TrainerTemp("Ege Uçak", "egeu", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Bahadır Adak", "baho", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Eyüpcan Bodur", "konyalı", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Berk Can Özen", "brkczn", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Serhat Sağlık", "mavi", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Sean Green", "seangreen", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Keegan Alvarado", "keeganalvarado", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Nguyen","oscarnguyen", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Carrillo","reginaldcarrillo", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Isabelle","Strickland", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Alaina","Willis", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Tiffani","Phillips", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Eugene","Miranda", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Brenna","Chandler", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Ryan","Wolfe", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Caitlyn","Clarke", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Caitlin","Lloyd", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Graham","Zimmerman", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Nathanael","Mclaughlin", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Dalton","Sanchez", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Esmeralda","Morales", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Blake","Lyons", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));
        trainerNames.add(new TrainerTemp("Vincent","Garcia", "Lorem ipsum dolor sit amet, cetero commodo cum et, nam elit gubergren ex, cetero euripidis definitiones has"));

        return trainerNames;
    }

    public void setTrainerNames(ArrayList<TrainerTemp> trainerNames) {
        this.trainerNames = trainerNames;
    }

}

