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
    var containerArray = ["addMember", "updateMember", "addTrainer", "updateTrainer"];
    for (var i = 0; i< containerArray.length; i++){
        console.log(containerArray[i]);
        document.getElementById(containerArray[i].concat("List")).classList.remove("active");
        document.getElementById(containerArray[i]).style.display = "none";
    }
    document.getElementById(clickedOn).style.display = "contents";
    document.getElementById(clickedOn.concat("List")).classList.add("active");
    console.log(clickedOn);
}