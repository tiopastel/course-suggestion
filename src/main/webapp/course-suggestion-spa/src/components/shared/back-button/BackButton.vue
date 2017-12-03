<template>
  <b-button type="button" variant="primary" @click="backHome()">Voltar</b-button>
</template>

<script>
  export default {
    methods: {
      backHome() {
        this.$router.replace('/home');
      }
    }
  }
</script>

<style scoped>

</style>
