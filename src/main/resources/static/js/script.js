/**
 * 
 */



function hover(){
	//document.getElementById("msg").innerHTML = 'Enter Time Please';
	
	var today = new Date();
	var timeHour = today.getHours() + ':' + today.getMinutes();
	document.getElementById('timeMessage').value = timeHour;
};

function hoverDate(){
	var today = new Date();
	var todayDate = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
	document.getElementById('currentDate').value = todayDate;
}


function welcome(){
	document.getElementById("welcome").style.color = 'red';
	};
