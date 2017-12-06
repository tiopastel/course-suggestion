<template>
  <div>
    <b-card>
      <h6 align="center">Números de sugestões de conteúdos por disciplina</h6>
      <line-chart :labels="disciplineData.labels" :datasets="disciplineData.datasets" :options="disciplineData.options"></line-chart>
    </b-card>
    <br>
    <b-card>
      <h6 align="center">Números de sugestões de disciplinas e conteúdos por curso</h6>
      <bar-chart :labels="courseData.labels" :datasets="courseData.datasets" :options="courseData.options"></bar-chart>
    </b-card>
  </div>
</template>

<script>
  import LineChart from '../shared/line-chart/LineChart';
  import BarChart from '../shared/bar-chart/BarChart';
  import { constants } from '../../constants.js';

  export default {
    components: {
      LineChart,
      BarChart
    },
    data() {
      return {
        disciplineData: {
          labels: [],
          datasets: [
            {
              label: 'Número de conteúdos sugeridos',
              backgroundColor: '#FC2525',
              data: []
            } 
          ],
          options: {responsive: true, maintainAspectRatio: false}
        },
        courseData: {
          labels: [],
          datasets: [
            {
              label: 'Número de disciplinas sugeridas',
              backgroundColor: '#05CBE1',
              data: []
            },
            {
              label: 'Número de conteúdos sugeridos',
              backgroundColor: '#54AF6D',
              data: []
            } 
          ],
          options: {responsive: true, maintainAspectRatio: false}
        }
      }
    },
    mounted() {
      this.$http.get(constants.DEV_API_URL.concat('discipline/report'))
        .then(data => {
          let disciplines = data.body.disciplines;
          disciplines.forEach(element => {
            this.disciplineData.labels.push(element.disciplineName);
            this.disciplineData.datasets[0].data.push(element.contents);
          });
        }, error => console.log(error));

      this.$http.get(constants.DEV_API_URL.concat('course/report'))
        .then(data => {
          let courses = data.body.courses;
          courses.forEach(element => {
            this.courseData.labels.push(element.courseName);
            this.courseData.datasets[0].data.push(element.disciplines);
            this.courseData.datasets[1].data.push(element.contents);
          });
        }, error => console.log(error));
    }
  }
</script>

<style>
</style>