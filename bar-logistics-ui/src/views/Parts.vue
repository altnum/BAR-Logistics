<template>
  <div>
    <header>
      <h1>Parts</h1>
      <h3>{{ content }}</h3>
    </header>
  </div>
</template>

<script>
import PartsService from '@/services/parts-service.js'

export default {
  name: 'Parts',
  data () {
    return {
      content: ''
    }
  },
  mounted () {
    PartsService.getAllParts().then(
      response => {
        console.log(response)
        this.content = response.data
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString()
      }
    )
  }
}
</script>

<style scoped>

</style>
