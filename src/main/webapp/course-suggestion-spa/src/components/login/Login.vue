<template>
  <b-card>
    <b-row>
      <b-col cols="9" align-self="center">
        <h2>{{ title }}</h2>
        <p>{{ subtitle }}</p>
      </b-col>
      <b-col cols="3">
        <b-img :src="institutionImage.src" fluid :alt="institutionImage.alt" />
      </b-col>
    </b-row>
    <b-form>
      <b-form-group label="Nome:">
        <b-form-input v-validate:name="'required'" v-model="name" type="text" name="nome"></b-form-input>
        <b-alert variant="danger" :show="errors.has('nome')">
          {{ errors.first('nome') }}
        </b-alert>
      </b-form-group>

      <b-form-group label="E-mail:">
        <b-form-input v-validate:email="'required|email'" v-model="email" type="text" name="e-mail"></b-form-input>
        <b-alert variant="danger" :show="errors.has('e-mail')">
          {{ errors.first('e-mail') }}
        </b-alert>
      </b-form-group>

      <b-form-row>
        <b-col>
          <vue-recaptcha ref="recaptcha" 
            @verify="onVerify" 
            @expired="onExpired" 
            :sitekey="recaptchaSiteKey">
          </vue-recaptcha>
        </b-col>
        <b-col align-self="center" style="text-align: right;">
          <b-button type="button" variant="primary" @click="login()">Entrar</b-button>
        </b-col>
      </b-form-row>
    </b-form>
  </b-card>
</template>

<script>
  import VueRecaptcha from 'vue-recaptcha';

  export default {
    components: { 'vue-recaptcha': VueRecaptcha },
    data() {
      return {
        recaptchaSiteKey: '6LfbITcUAAAAAJ07pND9XkGBEBCdstWCdcpVGNGx',
        subtitle: 'Dê sugestões nos cursos do IFTM de Uberaba. Para começar insira seu nome e e-mail abaixo:',
        name: '',
        email: '',
        title: 'Sugestões para Cursos',
        institutionImage: {
          src: './src/assets/iftm-logo.png',
          alt: 'Logo do IFTM Uberaba'
        }
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

<style scoped>
  .alert-danger {
    margin-top: 10px;
    margin-bottom: 0;
  }
</style>
