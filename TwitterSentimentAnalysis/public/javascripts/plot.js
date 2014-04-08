$(function draw() {
	var plotdata = [];
	$.getJSON("/analysis/" + window.analysis_id + "/data", function(json) {
		for (var i = 0; i < json.terms.length; i++) {
			var term = json.terms[i];
			var datapoints = []
			var datarow = {
				"label" : term.name,
				"data" : datapoints
			};

			for (var j = 0; j < term.results.length; j++) {
				var data = term.results[j];
				var datapoint = [ data.timestamp, data.value ];
				datarow.data.push(datapoint);
			}
			// alert("plotdatachange");
			plotdata.push(datarow);
		}

		var plot = $.plot(
		// target
		"#plot",

		// data
		plotdata,

		// options
		{
			xaxis : {
				mode : "categories",
				ticks : 0
			},
			yaxis : {
				min : 0,
				max : 1
			},
			series : {
				shadowSize : 0
			}, // Drawing is faster without shadows
			lines : {
				show : true,
				fill : false
			},
			points : {
				show : false
			},
			grid : {
				hoverable : true
			},
			legend : {
				show : true
			},
			tooltip : true,
			tooltipOpts : {
				content : "%x : %y"
			}
		});

		setVisibility();

		var updateInterval = 3000;

		function update() {

			setTimeout(draw, updateInterval);
		}

		update();

	});
});

function setVisibility() {
	if (document.getElementsByClassName("flot-tick-label").length > 20) {
		for (var i = 0; i < document.getElementsByClassName("flot-tick-label").length; i++) {
			document.getElementsByClassName("flot-tick-label")[i].style.visibility = "hidden";
		}
		for (var i = 0; i < document.getElementsByClassName("flot-tick-label").length; i = i + 5) {
			document.getElementsByClassName("flot-tick-label")[i].style.visibility = "visible";
		}
	}
	if (document.getElementsByClassName("flot-tick-label").length > 100) {
		for (var i = 0; i < document.getElementsByClassName("flot-tick-label").length; i++) {
			document.getElementsByClassName("flot-tick-label")[i].style.visibility = "hidden";
		}
		for (var i = 0; i < document.getElementsByClassName("flot-tick-label").length; i = i + 10) {
			document.getElementsByClassName("flot-tick-label")[i].style.visibility = "visible";
		}
	}
	if (document.getElementsByClassName("flot-tick-label").length > 1000) {
		for (var i = 0; i < document.getElementsByClassName("flot-tick-label").length; i++) {
			document.getElementsByClassName("flot-tick-label")[i].style.visibility = "hidden";
		}
		for (var i = 0; i < document.getElementsByClassName("flot-tick-label").length; i = i + 100) {
			document.getElementsByClassName("flot-tick-label")[i].style.visibility = "visible";
		}
	}
	if (document.getElementsByClassName("flot-tick-label").length > 10000) {
		for (var i = 0; i < document.getElementsByClassName("flot-tick-label").length; i++) {
			document.getElementsByClassName("flot-tick-label")[i].style.visibility = "hidden";
		}
		for (var i = 0; i < document.getElementsByClassName("flot-tick-label").length; i = i + 1000) {
			document.getElementsByClassName("flot-tick-label")[i].style.visibility = "visible";
		}
	}
	// there should be a better way...to make the y tick label visible again
	for (var i = (document.getElementsByClassName("flot-tick-label").length - 1); i > (document.getElementsByClassName("flot-tick-label").length - 7); i--) {
		document.getElementsByClassName("flot-tick-label")[i].style.visibility = "visible";
	}
}