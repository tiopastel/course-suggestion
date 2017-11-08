<template>
  <div>
    <p>{{ description }}</p>
    <select v-model="selected">
      <option disabled value="">Selecione um curso</option>
      <option v-for="course of courses" :value="course.value">{{ course.name }}</option>
    </select>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        description: `Selecione o curso no qual você deseja adicionar uma sugestão 
        ou use o formulário abaixo para cadastrar um novo curso.`,
        courses: [
          { value: 'ENG_COMP', name: 'Engenharia da Computação' }, 
          { value: 'ADS', name: 'Análise e Desenvolvimento de Sistemas' }
        ],
        selected: ''
      }
    }
  }
</script>

<style>

</style>