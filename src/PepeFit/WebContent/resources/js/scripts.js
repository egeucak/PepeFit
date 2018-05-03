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
        courseContainerId = courseContainerId.concat("-Container");
        document.getElementById(courseContainerId).style.display = "none";
        if (coursedivid==clickedOn){
            lis[i].firstChild.className = lis[i].firstChild.className.concat(" active");
            document.getElementById(coursepid).style.display = "block";
            //document.getElementById("course-form").reset();
            document.getElementById("block-func").value = coursedivid;
            document.getElementById("block-func").textContent = document.getElementById("block-func").value;
            console.log(document.getElementById("block-func").value);
            document.getElementById("empty-container").style.display ="none";
            document.getElementById("addCourse").style.display ="contents";
            document.getElementById(courseContainerId).style.display = "block";
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

(function($, undefined) {
    "use strict";
    $(function() {

        var $input = $( "#addMemberForm\\:idNumber1" );

        $input.on( "keyup", function( event ) {


            // When user select text in the document, also abort.
            var selection = window.getSelection().toString();
            if ( selection !== '' ) {
                return;
            }

            // When the arrow keys are pressed, abort.
            if ( $.inArray( event.keyCode, [38,40,37,39] ) !== -1 ) {
                return;
            }
            var $this = $( this );
            // Get the value.
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt( input, 10 ) : "";
            $this.val(function() {
                console.log(input);
                console.log(checkTcNum(input));
                if (checkTcNum(input)) {
                    $('#idResultBox').text("Your ID is valid").attr('class', 'text-uppercase text-success');
                    $('#idResultBox').css({"display": "block", "class": "text-success"});
                    $('#addButton').css({"display": "block"});
                } else {
                        $('#idResultBox').text("Enter a valid ID").attr('class', 'text-uppercase text-danger');
                        $('#idResultBox').css({"display": "block", "class": "text-danger"});
                        $('#addButton').css({"display": "none"});
                }
                return ((input.toString().length > 11) ? input.toString().substring(0,11) : input);
            } );
        } );
    });
})(jQuery);

(function($, undefined) {
    "use strict";
    $(function() {

        var $input = $( "#searchMemberForm\\:idNumber2" );

        $input.on( "keyup", function( event ) {


            // When user select text in the document, also abort.
            var selection = window.getSelection().toString();
            if ( selection !== '' ) {
                return;
            }

            // When the arrow keys are pressed, abort.
            if ( $.inArray( event.keyCode, [38,40,37,39] ) !== -1 ) {
                return;
            }
            var $this = $( this );
            // Get the value.
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt( input, 10 ) : "";
            $this.val(function() {
                console.log(input);
                console.log(checkTcNum(input));
                if (checkTcNum(input)) {
                    $('#idResultBox2').text("Your ID is valid").attr('class', 'text-uppercase text-success');
                    $('#idResultBox2').css({"display": "block", "class": "text-success"});
                    $('#searchButton1').css({"display": "block"});
                } else {
                        $('#idResultBox2').text("Enter a valid ID").attr('class', 'text-uppercase text-danger');
                        $('#idResultBox2').css({"display": "block", "class": "text-danger"});
                        $('#searchButton1').css({"display": "none"});
                }
                return ((input.toString().length > 11) ? input.toString().substring(0,11) : input);
            } );
        } );
    });
})(jQuery);

(function($, undefined) {
    "use strict";
    $(function() {

        var $input = $( "#updateMemberForm\\:idNumber_2" );

        $input.on( "keyup", function( event ) {


            // When user select text in the document, also abort.
            var selection = window.getSelection().toString();
            if ( selection !== '' ) {
                return;
            }

            // When the arrow keys are pressed, abort.
            if ( $.inArray( event.keyCode, [38,40,37,39] ) !== -1 ) {
                return;
            }
            var $this = $( this );
            // Get the value.
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt( input, 10 ) : "";
            $this.val(function() {
                console.log(input);
                console.log(checkTcNum(input));
                if (checkTcNum(input)) {
                    $('#idResultBox3').text("Your ID is valid").attr('class', 'text-uppercase text-success');
                    $('#idResultBox3').css({"display": "block", "class": "text-success"});
                    $('#updateButton1').css({"display": "block"});
                } else {
                        $('#idResultBox3').text("Enter a valid ID").attr('class', 'text-uppercase text-danger');
                        $('#idResultBox3').css({"display": "block", "class": "text-danger"});
                        $('#updateButton1').css({"display": "none"});
                }
                return ((input.toString().length > 11) ? input.toString().substring(0,11) : input);
            } );
        } );
    });
})(jQuery);

var checkTcNum = function(value) {
    value = value.toString();
    var isEleven = /^[0-9]{11}$/.test(value);
    var totalX = 0;
    for (var i = 0; i < 10; i++) {
        totalX += Number(value.substr(i, 1));
    }
    var isRuleX = totalX % 10 == value.substr(10,1);
    var totalY1 = 0;
    var totalY2 = 0;
    for (var i = 0; i < 10; i+=2) {
        totalY1 += Number(value.substr(i, 1));
    }
    for (var i = 1; i < 10; i+=2) {
        totalY2 += Number(value.substr(i, 1));
    }
    var isRuleY = ((totalY1 * 7) - totalY2) % 10 == value.substr(9,0);
    return isEleven && isRuleX && isRuleY;
};
