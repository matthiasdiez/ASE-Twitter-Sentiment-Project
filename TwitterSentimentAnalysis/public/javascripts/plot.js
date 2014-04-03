$(function() {
	var plotdata = [];
	$.getJSON( "/analysis/" + window.analysis_id + "/data", function( json ) {			
		for (var i = 0; i < json.terms.length; i++) {
			var term = json.terms[i];
			var datapoints = []
			var datarow = {"label" : term.name, "data" : datapoints};

			for (var j = 0; j < term.results.length; j++) {
				var data = term.results[j];
				var datapoint = [data.timestamp, data.value];
				datarow.data.push(datapoint);
			}
			plotdata.push(datarow);
		}

		$.plot(
			// target
			"#plot", 

			// data
			plotdata,

			// options
			{
				xaxis: {
					mode: "categories",
        			ticks: 0
		  		},
		  		yaxis: {
					min:0,
					max: 1
			  	},
				lines: { show: true, fill: true },
				points: { show: true},
		      	grid: { hoverable: true },
		      	legend: { show: true },
		      	tooltip: true,
		      	tooltipOpts: {
		    	content: "%x : %y"
		      	}
	  		}
		);

		// Add the Flot version string to the footer
	});
});