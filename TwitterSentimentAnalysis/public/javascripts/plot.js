$(function draw() {
	var plotdata = [];
<<<<<<< Upstream, based on origin/master
	var timestamps= [];
	//The input is the sorted data collected from twitter about the terms in form of a json-file. 
	//These data will be represented within a graph in a clear way to enable a comparison between the terms.
=======
	var timestamps = [];
>>>>>>> 31fa381 final commit from my side...
	$.getJSON("/analysis/" + window.analysis_id + "/data", function(json) {
		for (var u = 0; u < json.terms.length; u++) {
			var jterm = json.terms[u];
			var termTimeSteps = [];
			for (var k = 0; k < jterm.results.length; k++) {
				var data = jterm.results[k];
				termTimeSteps.push(data.timestamp);
			}
			timestamps.push(termTimeSteps);
		}
		for (var i = 0; i < json.terms.length; i++) {
			var term = json.terms[i];
			var datapoints = []
			var datarow = {
				"label" : term.name,
				"data" : datapoints
			};
			var tempTimeStep = "";
			for (var j = 0; j < term.results.length; j++) {
				var data = term.results[j];
<<<<<<< Upstream, based on origin/master
=======
				// add the data only in case all data-rows contain the timestamp
>>>>>>> 31fa381 final commit from my side...
				var isContained = true;
				for (var t = 0; t < timestamps.length; t++) {
					if (timestamps[t].indexOf(data.timestamp) <= -1) {
						isContained = false;
					}
				}
				if (isContained) {
					// if there are more value for the same timestamp only the
					// first one is used
					if (tempTimeStep != data.timestamp) {
						var datapoint = [ data.timestamp, data.value ];
						datarow.data.push(datapoint);
						tempTimeStep = data.timestamp;
					}
				}

			}
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
	if (document.getElementsByClassName("flot-tick-label").length > 100000) {
		for (var i = 0; i < document.getElementsByClassName("flot-tick-label").length; i++) {
			document.getElementsByClassName("flot-tick-label")[i].style.visibility = "hidden";
		}
		for (var i = 0; i < document.getElementsByClassName("flot-tick-label").length; i = i + 10000) {
			document.getElementsByClassName("flot-tick-label")[i].style.visibility = "visible";
		}
	}
	// there should be a better way...to make the y tick label visible again
	for (var i = (document.getElementsByClassName("flot-tick-label").length - 12); i > (document.getElementsByClassName("flot-tick-label").length - 7); i--) {
		document.getElementsByClassName("flot-tick-label")[i].style.visibility = "visible";
	}
}
