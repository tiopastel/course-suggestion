<template>
  <div>
    <suggestion-menu :routes="routes"></suggestion-menu>
  </div>
</template>

<script>
  import SuggestionMenu from '../shared/suggestion-menu/SuggestionMenu.vue';
  import { routes } from '../../routes.js';

  export default {
    components: {
      'suggestion-menu': SuggestionMenu
    },
    data() {
      return {
        description: '',
        routes
      }
    }
  }
</script>

<style>

</style>