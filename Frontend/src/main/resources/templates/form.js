function allcheck(isChecked) {
    var Timetable = document.getElementsByClassName("timetable"); // チェックボックスの数
    var TimetableCount = Timetable.length; // チェックボックスの数
    
    for (i = 1; i < TimetableCount; i++) {
        Timetable.elements[i].checked = isChecked; // ON・OFFを切り替え
        
    }
}