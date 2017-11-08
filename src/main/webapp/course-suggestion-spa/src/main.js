import Vue from 'vue'
import App from './App.vue'
import VeeValidate from 'vee-validate';
import VueRouter from 'vue-router';
import { messages } from '../node_modules/vee-validate/dist/locale/pt_BR.js';
import { routes } from './routes';

Vue.use(VeeValidate, {
  locale: 'pt_BR',
  dictionary: { 
    pt_BR: { messages }
  }
});
Vue.use(VueRouter);

const router = new VueRouter({ routes, mode: 'history' });

new Vue({
  el: '#app',
  render: h => h(App),
  router
})
