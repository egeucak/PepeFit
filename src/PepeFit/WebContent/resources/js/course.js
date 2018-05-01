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
            document.getElementById("block-func").textContent = coursedivid;
            console.log(document.getElementById(coursepid));
            document.getElementById("empty-container").style.display ="none";
            document.getElementById("addCourse").style.display ="contents";
        }
    }
}