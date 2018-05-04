function showDate() {
    var d = new Date();
    var day = d.getDate();
    if (day<10) {
        day = "0".concat(day.toString());
    }
    else {
        day = day.toString();
    }
    var month = (d.getMonth()+1).toString();
    if (month<10) {
        month = "0".concat(month.toString());
    }
    else {
        month = month.toString();
    }
    var year = d.getFullYear().toString();
    var today = day.concat(".", month, ".", year);
    // console.log(today);
    document.write(today);
}

function changeContainer(clickedOn) {
    var containerArrayAdd = ["addMember", "addTrainer"];
    var containerArrayUpdate = ["updateMember", "updateTrainer"];
    var containerArrayDelete = ["delete"];
    for (var i = 0; i< containerArrayAdd.length; i++){
        // console.log(containerArray[i]);
        document.getElementById(containerArrayAdd[i].concat("List")).classList.remove("active");
        document.getElementById(containerArrayAdd[i]).style.display = "none";
        document.getElementById(containerArrayUpdate[i].concat("List")).classList.remove("active");
        document.getElementById(containerArrayUpdate[i]).style.display = "none";
        // document.getElementById(containerArrayUpdate[i].concat("Form")).style.display = "none";
    }
    document.getElementById(containerArrayDelete[0].concat("List")).classList.remove("active");
    document.getElementById(containerArrayDelete[0]).style.display = "none";
    document.getElementById(clickedOn).style.display = "contents";
    if (clickedOn )
    document.getElementById(clickedOn.concat("List")).classList.add("active");
    resetForms();
    console.log(clickedOn);
}

function resetForms(){ 
    var formList = ["addMember", "updateMember1", "updateMember2", "addTrainer", "updateTrainer1", "updateTrainer2", "delete"]
    for (var i = 0; i<formList.length; i++){
        document.getElementById(formList[i].concat("Form")).reset();
    }
}

function updateSearch(idUpdate) {
    document.getElementById(idUpdate).style.display = "contents";
}

function showGraphAdd(clickedOn){
    var lis = document.getElementById("members").getElementsByTagName("li");

    for (var i=1; i<lis.length; i++){
        console.log(lis[i].firstChild.classList);
        lis[i].firstChild.classList.remove("active");
        var memberdivid = lis[i].firstChild.id;
        if (memberdivid==clickedOn){
            lis[i].firstChild.className = lis[i].firstChild.className.concat(" active");
        }
    }
}

function toggleTrainer(clickedOn) {
    var lis = document.getElementById("trainers").getElementsByTagName("li");
    for (var i=1; i<lis.length; i++){
        lis[i].firstChild.classList.remove("active");
        var trainerdivid = lis[i].firstChild.id;
        var trainerpid = trainerdivid.concat("p");
        document.getElementById(trainerpid).style.display = "none";
        if (trainerdivid==clickedOn){
            lis[i].firstChild.className = lis[i].firstChild.className.concat(" active");
            document.getElementById(trainerpid).style.display = "block";
            console.log(document.getElementById(trainerpid));
        }
    }
}

function changeContainerCourses(clickedOn) {
    var containerArrayCourses = ["addCourse", "updateCourse", "deleteCourse"];
    for (var i = 0; i< containerArrayCourses.length; i++){
        document.getElementById(containerArrayCourses[i].concat("List")).classList.remove("active");
        document.getElementById(containerArrayCourses[i]).style.display = "none";
    }
    document.getElementById(clickedOn).style.display = "contents";
    if (clickedOn )
    document.getElementById(clickedOn.concat("List")).classList.add("active");
    console.log(clickedOn);
}

function toggleCourse(clickedOn) {
    var lis = document.getElementById("courses").getElementsByTagName("li");
    for (var i=0; i<lis.length; i++){

        lis[i].firstChild.classList.remove("active");
        var coursedivid = lis[i].firstChild.id;
        var coursepid = coursedivid.concat("p");
        document.getElementById(coursepid).style.display = "none";
        if (coursedivid==clickedOn){
            lis[i].firstChild.className = lis[i].firstChild.className.concat(" active");
            document.getElementById(coursepid).style.display = "block";
            //document.getElementById("course-form").reset();
            document.getElementById("block-func").value = coursedivid;
            document.getElementById("block-func").textContent = document.getElementById("block-func").value;
            console.log(document.getElementById("block-func").value);
            document.getElementById("empty-container").style.display ="none";
            document.getElementById("addCourse").style.display ="contents";
        }
    }
}

function toggleCourseSplit(clickedOn) {
    var lis = document.getElementById("courses").getElementsByTagName("li");
    for (var i=0; i<lis.length; i++){
        lis[i].firstChild.classList.remove("active");
        var coursedivid = lis[i].firstChild.id;
        var coursepid = coursedivid.concat("p");
        document.getElementById(coursepid).style.display = "none";

        courseContainerId = coursedivid; //for different containers
        if(courseContainerId.includes(" ")){
            courseContainerId = coursedivid.replace(" ","-");
        }
        courseFormId = courseContainerId.concat("-Form");
        courseListId = courseContainerId.concat("-List");
        courseContainerId = courseContainerId.concat("-Container");

        document.getElementById(courseContainerId).style.display = "none";
        if (coursedivid==clickedOn){
            lis[i].firstChild.className = lis[i].firstChild.className.concat(" active");
            document.getElementById(coursepid).style.display = "block";
            if(document.getElementById(courseFormId)){
                document.getElementById(courseFormId).reset();
            }
            document.getElementById("block-func").value = coursedivid;
            document.getElementById("block-func").textContent = document.getElementById("block-func").value;
            console.log(document.getElementById("block-func").value);
            document.getElementById("empty-container").style.display ="none";
            document.getElementById("addCourse").style.display ="contents";
            document.getElementById(courseContainerId).style.display = "block";
            syncRadios(courseListId,"pickatime");
        }
    }
}

function syncRadios(courseListIdv,radioId){
    var courseButtonslis = document.getElementById(courseListIdv).getElementsByTagName("input");
    var courseTableEllis= document.getElementById(courseListIdv).getElementsByTagName("td");
    for(var i=0; i<courseButtonslis.length; i++){
        if(courseButtonslis[i].getAttribute('type')=='radio'){
            courseButtonslis[i].name = "radioId";
            courseTableEllis[i].style.display= "inline-block";
            courseTableEllis[i].style.width= "25%";    
            courseTableEllis[i].parentElement.parentElement.parentElement.style.width="100%";
        }
    }
}

function toggleCourseSplitDeneme(clickedOn) { // Don't use this function
    var lis = document.getElementById("courses").getElementsByTagName("li");
    var firstName;
    var numberXD = 0;
    for (var i=0; i<lis.length; i++){
        lis[i].firstChild.classList.remove("active");
        var coursedivid = lis[i].firstChild.id;
        var coursepid = coursedivid.concat("p");
        document.getElementById(coursepid).style.display = "none";

        courseContainerId = coursedivid; //for different containers
        if(courseContainerId.includes(" ")){
            courseContainerId = coursedivid.replace(" ","-");
        }
        courseFormId = courseContainerId.concat("-Form");
        courseListId = courseContainerId.concat("-List");
        courseContainerId = courseContainerId.concat("-Container");
        if (i==0){
            firstName = document.getElementById(courseListId).getElementsByTagName("input")[0].name;
        }
        document.getElementById(courseContainerId).style.display = "none";
        if (coursedivid==clickedOn){
            lis[i].firstChild.className = lis[i].firstChild.className.concat(" active");
            document.getElementById(coursepid).style.display = "block";
            if(document.getElementById(courseFormId)){
                document.getElementById(courseFormId).reset();
            }
            document.getElementById("block-func").value = coursedivid;
            document.getElementById("block-func").textContent = document.getElementById("block-func").value;
            console.log(document.getElementById("block-func").value);
            document.getElementById("empty-container").style.display ="none";
            document.getElementById("addCourse").style.display ="contents";
            document.getElementById(courseContainerId).style.display = "block";
            syncRadios(courseListId,firstName,numberXD);
            numberXD=numberXD+document.getElementById(courseListId).getElementsByTagName("input").length;

        }
    }
}

function syncRadiosDeneme(courseListIdv,firstName,xd){ // Don't use this function
    var courseButtonslis = document.getElementById(courseListIdv).getElementsByTagName("input");
    var courseTableEllis= document.getElementById(courseListIdv).getElementsByTagName("td");
    for(var i=0; i<courseButtonslis.length; i++){
        if(courseButtonslis[i].getAttribute('type')=='radio'){
            courseButtonslis[i].name = firstName;
            courseButtonslis[i].id = firstName.concat(":").concat(i+xd);
            courseButtonslis[i].parentElement.getElementsByTagName("label")[0].for = firstName.concat(":").concat(i+xd);
            courseTableEllis[i].style.display= "inline-block";
            courseTableEllis[i].style.width= "25%";    
            courseTableEllis[i].parentElement.parentElement.parentElement.style.width="100%";
        }
    }
}

(function($, undefined) {

    "use strict";

    // When ready.
    $(function() {
        $.fn.formatter.addInptType('H', /[0-2]/);
        $.fn.formatter.addInptType('M', /[0-5]/);
        $("#course-form\\:Time1").formatter({
            'pattern': '{{H9}}:{{M9}}-{{H9}}:{{M9}}',
            'persistent': false
        });
    });
})(jQuery);
