<template>
  <b-container>
    <suggestion-menu :routes="routes" v-if="showMenu"></suggestion-menu>
    <br>
    <router-view></router-view>
  </b-container>
</template>

<script>
  import Menu from './components/shared/menu/Menu.vue';
  import { routes } from './routes.js';

  export default {
    components: {
      'suggestion-menu': Menu
    },
    data() {
      return {
        routes
      }
    },
    computed: {
      showMenu() {
        return this.$route.path != '/';
      }
    }
  }
</script>

<style scoped>
  .container {
    margin-top: 25px;
  }
</style>
