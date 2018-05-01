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
            document.getElementById("block-func").textContent = coursedivid;
            console.log(document.getElementById(coursepid));
            document.getElementById("empty-container").style.display ="none";
            document.getElementById("addCourse").style.display ="contents";
        }
    }
}

(function($, undefined) {
    "use strict";
    // When ready.
    $(function() {
        $.fn.formatter.addInptType('H', /[0-2]/);
        $.fn.formatter.addInptType('M', /[0-5]/);
        $("#formCnT\\:Time1").formatter({
            'pattern': '{{H9}}:{{M9}}-{{H9}}:{{M9}}',
            'persistent': false
        });
        $("#formCnT\\:Capacity1").formatter({
            'pattern': '{{99}}',
            'persistent': false
        });
    });
})(jQuery);