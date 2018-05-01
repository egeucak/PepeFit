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
    });
})(jQuery);


// (function($, undefined) {
//
//     "use strict";
//
//     // When ready.
//     $(function() {
//
//         // var $form = $( "#formCnT" );
//         // console.log($form);
//         // var $input = $form.find("#Time1");
//         // console.log($input);
//         // var $input = $(":input");
//         var $input = $("#formCnT\\:Time1");
//         console.log($input);
//
//         $input.on( "keyup", function( event ) {
//
//
//             // When user select text in the document, also abort.
//             var selection = window.getSelection().toString();
//             console.log(selection);
//             if ( selection !== '' ) {
//                 return;
//             }
//
//             // When the arrow keys are pressed, abort.
//             if ( $.inArray( event.keyCode, [38,40,37,39] ) !== -1 ) {
//                 return;
//             }
//
//
//             var $this = $( this );
//
//             // Get the value.
//             var input = $this.val();
//
//             // var input = input.replace(/[\D\s\._\-]+/g, "");
//             var input = input.replace(/^([0-9]|0[0-9]|1[0-9]|2[0-3]).[0-5][0-9]-([0-9]|0[0-9]|1[0-9]|2[0-3]).[0-5][0-9]$/, "");
//
//             // input = input ? parseInt( input, 10 ) : 0;
//
//             $this.val( function() {
//                 return ( input === 0 ) ? "" : input.toLocaleString( "en-US" );
//             } );
//         } );
//
//         /**
//          * ==================================
//          * When Form Submitted
//          * ==================================
//          */
//         // $form.on( "submit", function( event ) {
//         //
//         //     var $this = $( this );
//         //     var arr = $this.serializeArray();
//         //
//         //     for (var i = 0; i < arr.length; i++) {
//         //         arr[i].value = arr[i].value.replace(/[($)\s\._\-]+/g, ''); // Sanitize the values.
//         //     };
//         //
//         //     console.log( arr );
//         //
//         //     event.preventDefault();
//         // });
//
//     });
// })(jQuery);