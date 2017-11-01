<template>
  <div>
    <h1>{{ title }}</h1>
    <p>Dê sugestões nos cursos do IFTM de Uberaba. Para começar insira seu nome e-mail abaixo:</p>
    <form>
      <input v-model="name" placeholder="Nome"><br>
      <input v-model="email" placeholder="E-mail"><br>
      <button>Avançar</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: 'Sugestões para Cursos',
      institutionImage: {
        src: '',
        alt: ''
      }
    }
  }
}
</script>

<style>

</style>
