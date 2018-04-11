function showDate() {
    var d = new Date();
    var day = d.getDay();
    if (day<10) {
        day = "0".concat(day.toString());
    }
    else {
        day = day.toString();
    }
    var month = d.getMonth().toString();
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
    for (var i = 0; i< containerArrayAdd.length; i++){
        // console.log(containerArray[i]);
        document.getElementById(containerArrayAdd[i].concat("List")).classList.remove("active");
        document.getElementById(containerArrayAdd[i]).style.display = "none";
        document.getElementById(containerArrayUpdate[i].concat("List")).classList.remove("active");
        document.getElementById(containerArrayUpdate[i]).style.display = "none";
        document.getElementById(containerArrayUpdate[i].concat("Form")).style.display = "none";
    }
    document.getElementById(clickedOn).style.display = "contents";
    if (clickedOn )
    document.getElementById(clickedOn.concat("List")).classList.add("active");
    console.log(clickedOn);
}

function updateSearch(idUpdate) {
    document.getElementById(idUpdate).style.display = "contents";
}