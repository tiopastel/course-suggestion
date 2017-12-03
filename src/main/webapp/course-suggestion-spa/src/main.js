import Vue from 'vue'
import App from './App.vue'
import VeeValidate from 'vee-validate';
import VueRouter from 'vue-router';
import VueResource from 'vue-resource';
import BootstrapVue from 'bootstrap-vue'
import { messages } from '../node_modules/vee-validate/dist/locale/pt_BR.js';
import { routes } from './routes';
import '../node_modules/bootstrap/dist/css/bootstrap.css';
import '../node_modules/bootstrap-vue/dist/bootstrap-vue.css';

Vue.use(VeeValidate, {
  locale: 'pt_BR',
  dictionary: { 
    pt_BR: { messages }
  }
});
Vue.use(VueRouter);
Vue.use(VueResource);
Vue.use(BootstrapVue);

const router = new VueRouter({ routes, mode: 'history' });

new Vue({
  el: '#app',
  render: h => h(App),
  router
})
