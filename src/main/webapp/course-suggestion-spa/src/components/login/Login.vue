<template>
  <div>
    <p>{{ description }}</p>
    <form>
      <input v-validate:name="'required'" v-model="name" type="text" name="nome" placeholder="Nome"><br>
      <span v-show="errors.has('nome')">{{ errors.first('nome') }}</span><br>
      <input v-validate:email="'required|email'" v-model="email" type="text" name="e-mail" placeholder="E-mail"><br>
      <span v-show="errors.has('e-mail')">{{ errors.first('e-mail') }}</span><br>
      <vue-recaptcha ref="recaptcha" 
        @verify="onVerify" 
        @expired="onExpired" 
        :sitekey="recaptchaSiteKey">
      </vue-recaptcha>
      <br>
      <b-button type="button" @click="login()">Entrar</b-button>
    </form>
  </div>
</template>

<script>
  import VueRecaptcha from 'vue-recaptcha';

  export default {
    components: { 'vue-recaptcha': VueRecaptcha },
    data() {
      return {
        recaptchaSiteKey: '6LfbITcUAAAAAJ07pND9XkGBEBCdstWCdcpVGNGx',
        description: 'Dê sugestões nos cursos do IFTM de Uberaba. Para começar insira seu nome e e-mail abaixo:',
        name: '',
        email: ''
      }
    },
    methods: {
      onVerify: function (response) {
        console.log('Verify: ' + response)
      },
      onExpired: function () {
        console.log('Expired')
      },
      resetRecaptcha () {
        this.$refs.recaptcha.reset() // Direct call reset method
      },
      login() {
        this.$router.push('home');
      }
    }
  }
</script>

<style>

</style>
