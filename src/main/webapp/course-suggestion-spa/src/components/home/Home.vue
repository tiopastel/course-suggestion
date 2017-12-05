<template>
  <line-chart :labels="labels" :datasets="datasets" :options="options"></line-chart>
</template>

<script>
  import LineChart from '../shared/line-chart/LineChart';
  import { constants } from '../../constants.js';

  export default {
    components: {
      LineChart
    },
    data() {
      return {
        labels: [],
        datasets: [
          {
            label: 'Número de conteúdos',
            backgroundColor: '#FC2525',
            data: []
          }
          /*{
            label: 'Data One',
            backgroundColor: '#FC2525',
            data: [40, 39, 10, 40, 39, 80, 40]
          },{
            label: 'Data Two',
            backgroundColor: '#05CBE1',
            data: [60, 55, 32, 10, 2, 12, 53]
          }*/
        ],
        options: {responsive: true, maintainAspectRatio: false} 
      }
    },
    mounted() {
      this.$http.get(constants.DEV_API_URL.concat('discipline/report'))
        .then(data => {
          console.log(data);
          let disciplines = data.body.disciplines;
          disciplines.forEach(element => {
            this.labels.push(element.disciplineName);
            this.datasets[0].data.push(element.contents)
          });
          console.log('datasets ->', this.datasets)
          console.log('labels ->', this.labels)
        }, error => console.log(error));
    }
  }
</script>

<style>

</style>