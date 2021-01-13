<template>
  <div>
    <header>
      <h1>Orders</h1>
    </header>
    <b-table class="table" id="ordersTable" striped hover bordered
             :items="result"
             :fields="fields">
      <template v-slot:cell(preview)="row">
        <router-link :to="{ name: 'ordersDetails', params: {order_id: row.item.order_id} }" class="btn-group">Open</router-link>
      </template>
      <template v-slot:cell(user_id)="row">
        {{ row.item.user_id.first_name }}
        {{ row.item.user_id.last_name }}
      </template>
      <template v-slot:cell(price)="row">
        {{ row.item.price }} лв.
      </template>
    </b-table>
  </div>
</template>

<script>
import OrdersService from '../services/orders-service.js'

export default {
  name: 'Orders',
  data () {
    return {
      result: [{ order_id: '', user_id: '', order_date: '', ship_date: '', status: '', preview: '', price: '' }],
      fields: [
        { key: 'order_id', label: 'Id' },
        { key: 'user_id', label: 'User' },
        { key: 'order_date', label: 'Order date' },
        { key: 'ship_date', label: 'Ship date' },
        { key: 'status', sortable: true, label: 'Status' },
        { key: 'preview', label: 'Details' },
        { key: 'price', label: 'Collect' }

      ]
    }
  },
  mounted () {
    OrdersService.getAllOrders().then(
      response => {
        this.result = response.data
      },
      error => {
        this.result =
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
