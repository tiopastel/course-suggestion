<template>
  <div>
    <h1>{{ title }}</h1>
    <p>Dê sugestões nos cursos do IFTM de Uberaba. Para começar insira seu nome e e-mail abaixo:</p>
    <form action="?" method="POST">
      <input v-model="name" placeholder="Nome"><br><br>
      <input v-model="email" placeholder="E-mail"><br><br>
      <div class="g-recaptcha" :data-sitekey="recaptcha.sig_key"></div><br><br>
      <input type="submit" value="Avançar">
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      recaptcha: {
        sig_key: '6LfbITcUAAAAAJ07pND9XkGBEBCdstWCdcpVGNGx',
        id: 0
      },
      title: 'Sugestões para Cursos',
      institutionImage: {
        src: '',
        alt: ''
      },
      name: '',
      email: ''
    }
  },
  mounted() {
    if (window.grecaptcha) {
      this.recaptcha.id = grecaptcha.render( $('.g-recaptcha')[0], { sitekey : this.recaptcha.sig_key });
      console.log('this.recaptcha.id -> '+this.recaptcha.id);
    }
  }
}
</script>

<style>

</style>
