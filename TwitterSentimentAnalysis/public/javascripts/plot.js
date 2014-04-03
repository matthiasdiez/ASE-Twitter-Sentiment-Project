$(function() {

		var labels = ["datum1", "datum2", "datum3", "datum4", "datum5", "datum6"];

		var values = [[0, 0.3], [1, 0.5], [2,0.8], [3,0.2], [4,0.6], [5,0.4]];

		function TickGenerator(axis) {
	        var res = [],
				j=0,
	            i = Math.floor(axis.min);
	        do {
	            res.push([i, labels[j]]);
				j++;
				++i;
	        } while (i < axis.max);
	        return res;
    	}

		$.plot(
			// target
			"#plot", 

			// data
			[{
				data: values,
				lines: { show: true, fill: true }
			}		
			],

			// options
			{
				xaxis: {
        			ticks: TickGenerator
		  		},
		  		yaxis: {
					min:0,
					max: 1
			  	},
		      	grid: { hoverable: true },
		      	legend: { show: false },
		      	tooltip: true,
		      	tooltipOpts: {
		    		content: "%x : %y"
		      	}
	  		}
		);

		// Add the Flot version string to the footer

		
	});