/**
 * 
 */
$(document).ready(function($){
	
	getData($("#countClientesUbicacion").attr("href"), null).done(function(data){
		
		console.log("la informacion se acaba de procesar!!!");
		console.log(data);
		
		var total = 0;
		
		$(data.content).each(function(i,item){
			
			total += item.cantidad;
			
		});
		
		var seriesData = [];
		
		$(data.content).each(function(i,item){
			
			var object = {
				"name" : item.distrito,
				"y" : (item.cantidad / total) * 100
			}
			
			seriesData.push(object);
			
		});
		
		console.log(seriesData);
		
		$("#clientesUbicacion .stat").highcharts({
			chart: {
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: true,
				type: 'pie',
				backgroundColor: null
			},
			title: {
				text: ' '
			},
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					dataLabels: {
						enabled: true
					}
				}
			},
			credits: {
				enabled: false
			},
			series: [{
				name: 'Ditrito',
				colorByPoint: true,
				data: seriesData
			}]
		});
		
	});

	var ahora = new Date();
	var parameters = cliente;
	parameters.resume = 4;
	parameters.fechaNacimiento = ahora.format("dd/mm/yyyy HH:MM:ss");
	
	getData($("#countClientesWeek").attr("href"), parameters).done(function(data){
		
		var seriesData = [0, 0, 0, 0, 0, 0, 0];
		
		$(data.content).each(function(i, item){
			
			var fechaEvaluada = item.registrado.split(" ")[0];
			fechaEvaluada = fechaEvaluada.split("/");
			fechaEvaluada = new Date(fechaEvaluada[2], fechaEvaluada[1] - 1, fechaEvaluada[0]);
			
			console.log(item.registrado + " : " + fechaEvaluada);
			
			var cantidad = seriesData[fechaEvaluada.getDay() - 1];
			seriesData[fechaEvaluada.getDay() - 1] = seriesData[fechaEvaluada.getDay() - 1] + 1;
			
		});
		
		console.log(seriesData);
		
		$("#clientesSemanal .stat").highcharts({
			chart: {
				type: 'area',
				spacingBottom: 30,
				backgroundColor: null,
				spacing: [48, 48, 48, 48]
			},
			title: {
				text: ' '
			},
			xAxis: {
				categories: ['Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo']
			},
			yAxis: {
				title: {
					text: ' '
				},
				labels: {
					formatter: function () {
						return this.value;
					}
				}
			},
			tooltip: {
				formatter: function () {
					return '<b>' + this.series.name + '</b><br/>' + this.x + ': ' + this.y;
				}
			},
			plotOptions: {
				area: {
					fillOpacity: 0.5
				}
			},
			legend: {
				enabled : false
			},
			credits: {
				enabled: false
			},
			series: [{
				name: 'Registrados',
				data: seriesData
			}]
		});
		
	});
	
	/*$("#clienteMensual .stat").highcharts({
		chart: {
			type: 'column',
			backgroundColor: null,
			spacing: [48, 48, 48, 48]
		},
		title: {
			text: ' '
		},
		subtitle: {
			text: ' '
		},
		xAxis: {
			categories: [
				'Jan',
				'Feb',
				'Mar',
				'Apr',
				'May',
				'Jun',
				'Jul',
				'Aug',
				'Sep',
				'Oct',
				'Nov',
				'Dec'
			],
			crosshair: true
		},
		yAxis: {
			min: 0,
			title: {
				text: 'Cantidad'
			}
		},
		tooltip: {
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			'<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
			footerFormat: '</table>',
			shared: true,
			useHTML: true
		},
		plotOptions: {
			column: {
				pointPadding: 0.2,
				borderWidth: 0
			}
		},
		credits: {
			enabled: false
		},
		series: [{
			name: 'Miraflores',
			data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
		}, {
			name: 'San borja',
			data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]
		}, {
			name: 'Santiago de surco',
			data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]
		}, {
			name: 'Ate vitarte',
			data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]
		}]
	});
	
	$("#clienteAnual .stat").highcharts({
		chart: {
			type: 'column',
			backgroundColor: null,
			spacing: [48, 48, 48, 48]
		},
		title: {
			text: ' '
		},
		xAxis: {
			categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
		},
		yAxis: {
			min: 0,
			title: {
				text: ' '
			}
		},
		tooltip: {
			pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
			shared: true
		},
		plotOptions: {
			column: {
				stacking: 'percent'
			}
		},
		credits: {
			enabled: false
		},
		series: [{
			name: 'John',
			data: [5, 3, 4, 7, 2]
		}, {
			name: 'Jane',
			data: [2, 2, 3, 2, 1]
		}, {
			name: 'Joe',
			data: [3, 4, 4, 2, 5]
		}]
	});*/

});