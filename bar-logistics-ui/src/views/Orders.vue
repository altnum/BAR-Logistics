<template>
  <div>
    <header>
      <h1>Orders</h1>
    </header>
    <b-table class="table" id="ordersTable" striped hover bordered
             :items="result"
             :fields="fields">
      <template v-slot:cell(ship_date)="row">
        <div v-if="row.item.status !==  'pending'">
          {{ row.item.ship_date }}
        </div>
        <div v-else>-----</div>
      </template>
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
    <b-table class="table" id="vehiclesTable" striped hover bordered
             :items="vehicleTable"
             :fields="vehicleTableFields">
      <template v-slot:cell(order_id1)="row">
        <input v-model="row.item.order_id1" :placeholder="inputHolder">
      </template>
      <template v-slot:cell(confirm)="row">
        <button class="btn" v-on:click="sendCurrOrder(row.item.order_id1, row.item.id)">Send</button>
      </template>
    </b-table>
  </div>
</template>

<script>
import OrdersService from '../services/orders-service.js'
import VehicleService from '../services/vehicle-service.js'

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

      ],
      vehicleTable: [{ id: '', type: '', order_id1: '', confirm: '' }],
      vehicleTableFields: [
        { key: 'type.type', label: 'Вид:' },
        { key: 'order_id1', label: 'Поръчка ID:' },
        { key: 'confirm', label: 'Потвърди' }
      ],
      inputHolder: 'Въведи ID'
    }
  },
  mounted () {
    this.loadOrders()
    this.loadAvailableVehicles()
  },
  methods: {
    loadOrders () {
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
    },
    loadAvailableVehicles () {
      VehicleService.getAvailableVehicles().then(
        response => {
          this.vehicleTable = response.data
        },
        error => {
          this.vehicleTable =
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        }
      )
    },
    sendCurrOrder (orderId, vehicleId) {
      OrdersService.orderInProcess(orderId, vehicleId).then(response => {
        this.loadOrders()
        this.loadAvailableVehicles()
      }
      )
    }
  }
}
</script>

<style scoped>

</style>
