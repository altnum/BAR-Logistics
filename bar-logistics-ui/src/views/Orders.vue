<template>
  <div>
    <header>
      <h1>Поръчки</h1>
      <h3>{{ content }}</h3>
    </header>
  </div>
</template>

<script>
import OrdersService from '@/services/orders-service.js'

export default {
  name: 'Orders',
  data () {
    return {
      content: ''
    }
  },
  mounted () {
    OrdersService.getAllOrders().then(
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
