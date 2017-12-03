<template>
  <div>
    <b-card>
      <p>Para sugerir um curso informe um nome, uma descrição, dê uma justificativa para a criação do curso, 
      selecione o nível deste curso sugerido e clique em cadastrar.</p>
      
      <b-form v-on:submit.prevent="onSubmit()">
        <b-form-group label="Nome:">
          <b-input v-validate="'required'" v-model="form.name" type="text" name="nome"></b-input>
          <b-alert variant="danger" :show="errors.has('nome')">
            {{ errors.first('nome') }}
          </b-alert>
        </b-form-group>
        
        <b-form-group label="Descrição:">
          <b-input v-validate="'required'" v-model="form.description" type="text" name="descrição"></b-input>
          <b-alert variant="danger" :show="errors.has('descrição')">
            {{ errors.first('descrição') }}
          </b-alert>
        </b-form-group>

        <b-form-group label="Justificativa:">
          <b-form-textarea v-validate="'required'" 
            v-model="form.justification" 
            name="justificativa"
            :rows="3"
            :max-rows="6">
          </b-form-textarea>
          <b-alert variant="danger" :show="errors.has('justificativa')">
            {{ errors.first('justificativa') }}
          </b-alert>
        </b-form-group>

        <b-form-group label="Selecione o nível do curso:">
          <b-form-select v-validate="'required'" v-model="form.level" class="mb-3">
            <option disabled value="-1">Selecione um nível</option>
            <option v-for="(level, index) in levels" :value="index" :key="index">{{ level }}</option>
          </b-form-select>
        </b-form-group>

        <b-form-row>
          <b-col>
            <back-button></back-button>
          </b-col>
          <b-col style="text-align: right;">
            <b-button type="submit" variant="success" :disabled="isValid">Cadastrar</b-button>
          </b-col>
        </b-form-row>
      </b-form>
    </b-card>

    <b-modal ref="modal" ok-only hide-header @ok="handleOk()">
      <div class="d-block">{{ message }}</div>
    </b-modal>
  </div>
</template>

<script>
  import BackButton from '../shared/back-button/BackButton.vue';
  import { constants } from '../../constants.js';

  export default {
    components: {
      'back-button': BackButton 
    },
    data() {
      return {
        levels: '',
        form: {
          level: -1,
          name: '',
          description: '',
          justification: ''
        },
        success: false,
        message: 'Ocorreu um erro ao cadastrar o curso, tente novamente'
      }
    },
    created() {
      this.$http.get(constants.DEV_API_URL.concat('course/levels'))
        .then(data => { 
          this.levels = data.body.levels;
        }, error => console.log(error));
    },
    methods: {
      onSubmit() {
        let body = JSON.stringify(this.form);
        let options = {'Content-Type': 'application/json'};
        this.$http.post(constants.DEV_API_URL.concat('course'), body, options)
          .then(data => {
            this.success = true;
            this.message = 'Curso cadastrado com sucesso';
            this.$refs.modal.show();
          }, error => {
            console.log(error);
            this.$refs.modal.show();
          });
      },
      handleOk() {
        if (this.success) {
          this.$router.replace('/home');
        }
      }
    },
    computed: {
      isValid() {
        return Object.keys(this.fields).some(key => this.fields[key].pristine || this.fields[key].invalid);
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