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
    console.log(clickedOn);
}

function updateSearch(idUpdate) {
    document.getElementById(idUpdate).style.display = "contents";
}

function showGraphAdd(clickedOn){
    var lis = document.getElementById("members").getElementsByTagName("li");

    for (var i=1; i<lis.length; i++){
        lis[i].firstChild.classList.remove("active");
        var memberdivid = lis[i].firstChild.id;
        if (memberdivid==clickedOn){
            lis[i].firstChild.className = lis[i].firstChild.className.concat(" active");
        }
        // if (lis[i].firstChild.id){
        //     lis[i].firstChild.classList.add("active");
        // }
    }
    // console.log(lis[1]);
}