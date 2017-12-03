<template>
  <b-navbar toggleable type="light" variant="light">
    <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
    <b-navbar-brand href="#">Sugestões para Cursos</b-navbar-brand>
    <b-collapse is-nav id="nav_collapse">
      <b-navbar-nav>
        <b-nav-item v-for="route of routes" v-if="route.show" :key="route.path">
          <router-link :to="route.path ? route.path : '/'" replace>{{ route.title }}</router-link>
        </b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script>
  export default {
    props: {
      routes: {
        type: Array,
        required: true
      }
    },
    data() {
      return {
      }
    }
  }
</script>

<style>

</style>