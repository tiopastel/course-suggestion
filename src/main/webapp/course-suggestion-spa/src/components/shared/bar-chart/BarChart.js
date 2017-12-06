import { Bar } from 'vue-chartjs'

export default {
  extends: Bar,
  props: ['labels', 'datasets', 'options'],
  watch: { 
    labels: function(newVal, oldVal) {
      this.renderChart({
        labels: this.labels,
        datasets: this.datasets,
      }, this.options)
    }
  },
  mounted () {
    this.renderChart({
      labels: this.labels,
      datasets: this.datasets,
    }, this.options)
  }
}
