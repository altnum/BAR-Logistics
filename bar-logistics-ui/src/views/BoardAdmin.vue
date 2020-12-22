<template>
  <div>
    <header>
      <br>
        <span class="title" font-size="16px">Преглед на настоящите поръчки:   </span>
        <router-link to="/orders" tag="button">Поръчки</router-link>
    </header>
  </div>
</template>

<script>
import OrdersService from '../services/orders-service.js'

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
