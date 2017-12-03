<template>
  <div>
    <h1>{{ title }}</h1>
    <img :src="institutionImage.src" :alt="institutionImage.alt"/>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        title: 'Sugestões para Cursos',
        institutionImage: {
          src: './src/assets/iftm-logo.png',
          alt: 'Logo do IFTM Uberaba'
        }
      }
    }
  }
</script>

<style>

</style>
