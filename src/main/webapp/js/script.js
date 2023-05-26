// Obtener todas las gráficas
fetch('/charts')
  .then(response => response.json())
  .then(charts => {
    // Generar gráficas con los datos obtenidos
    charts.forEach(chart => {
      var canvas = document.createElement('canvas');
      canvas.id = 'chart' + chart.id;
      document.getElementById('acciones').appendChild(canvas);
      var ctx = canvas.getContext('2d');
      createChart(ctx, chart.name);
    });
  })
  .catch(error => console.error('Error al obtener las gráficas:', error));

// Agregar una nueva gráfica
var newChartForm = document.getElementById('newChartForm');
newChartForm.addEventListener('submit', function(event) {
  event.preventDefault();
  var chartNameInput = document.getElementById('chartName');
  var chartName = chartNameInput.value;

  if (chartName !== '') {
    var chartData = {
      name: chartName
    };

    fetch('/charts', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(chartData)
    })
      .then(response => response.json())
      .then(createdChart => {
        var canvas = document.createElement('canvas');
        canvas.id = 'chart' + createdChart.id;
        document.getElementById('acciones').appendChild(canvas);
        var ctx = canvas.getContext('2d');
        createChart(ctx, createdChart.name);
      })
      .catch(error => console.error('Error al crear la gráfica:', error));
  }

  chartNameInput.value = '';
});
