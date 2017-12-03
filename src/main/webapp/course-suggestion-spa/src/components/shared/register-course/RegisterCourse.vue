<template>
  <form v-on:submit.prevent="onSubmit()">
    <input v-validate:name="'required'" v-model="name" type="text" name="nome" placeholder="Nome"><br>
    <span v-show="errors.has('nome')">{{ errors.first('nome') }}</span><br>

    <input v-validate:description="'required'" v-model="description" type="text" name="descricao" placeholder="Descrição"><br>
    <span v-show="errors.has('descricao')">{{ errors.first('descrição') }}</span><br>

    <input v-validate:justification="'required'" v-model="justification" type="text" name="justificativa" placeholder="Justificativa"><br>
    <span v-show="errors.has('justificativa')">{{ errors.first('justificativa') }}</span><br>

    <select v-validate:selectedLevel="'required'" v-model="selectedLevel" name="nível">
      <option disabled value="">Selecione o nível do curso</option>
      <option v-for="(level, index) in levels" :value="index">{{ level }}</option>
    </select><br>
    <span v-show="errors.has('nível')">{{ errors.first('nível') }}</span><br>

    <button type="submit">Avançar</button> <!-- v-bind:disabled="isValid" -->
  </form>
</template>

<script>
  import { constants } from '../../../constants.js';

  export default {
    data() {
      return {
        levels: {
          UPPER: 'Avançado',
          BASIC: 'Básico'
        },
        selectedLevel: 'UPPER',
        name: "JavaScript",
        description: 'Descrição do curso JavaScript',
        justification: 'Justificativa do curso JavaScript'
      }
    },
    created() {      
      let promise = this.$http.get(constants.DEV_API_URL.concat('course/levels'));
      promise
        .then(data => data.json())
        .then(levels => this.levels = levels.levels, error => console.log(error));
    },
    methods: {
      onSubmit() {
        let body = JSON.stringify({
          'name': this.name,
          'description': this.description,
          'justification': this.justification,
          'level': this.selectedLevel
        });
        console.log('BODY -> ', body);
        let options = {'Content-Type': 'application/json'};
        this.$http.post(constants.DEV_API_URL.concat('course'), body, options).then(response => {
          console.log('body -> ', response);
        }, error => {
          console.log('error -> ', error);
        });
      },
      teste() {
        console.log(this.selectedLevel);
        console.log(Object.keys(this.selectedLevel));
      }
    },
    computed: {
      isValid() {
        return Object.keys(this.fields).some(key => this.fields[key].pristine || this.fields[key].invalid);
      }
    }
  }
</script>

<style>

</style>