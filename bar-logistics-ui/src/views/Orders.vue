<template>
  <div>
    <header>
      <h1>Orders</h1>
    </header>
    <b-table class="table" id="ordersTable" striped hover bordered
             :items="result"
             :fields="fields"
             :current-page="currentPage">
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
      <template v-slot:cell(volume)="row">
        {{ row.item.volume }}
      </template>
    </b-table>
    <b-pagination
      v-model="currentPage"
      pills
      :total-rows="rows"
      :per-page="perPage"
      @input="searchOrders"
      aria-controls="ordersTable">
    </b-pagination>
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
      result: [{ order_id: '', user_id: '', order_date: '', ship_date: '', status: '', preview: '', price: '', volume: '' }],
      fields: [
        { key: 'order_id', label: 'Id' },
        { key: 'user_id', label: 'User' },
        { key: 'order_date', label: 'Order date' },
        { key: 'ship_date', label: 'Ship date' },
        { key: 'status', sortable: true, label: 'Status' },
        { key: 'preview', label: 'Details' },
        { key: 'price', label: 'Collect' },
        { key: 'volume', label: 'Volume' }

      ],
      vehicleTable: [{ id: '', type: '', order_id1: '', confirm: '' }],
      vehicleTableFields: [
        { key: 'type.type', sortable: true, label: 'Vehicle:' },
        { key: 'order_id1', label: 'Order ID:' },
        { key: 'confirm', label: 'Confirm' }
      ],

      inputHolder: 'Input ID',
      perPage: 10,
      currentPage: 1,
      rows: '',
      totalItems: ''
    }
  },
  mounted () {
    this.searchOrders()
    this.loadAvailableVehicles()
  },
  methods: {
    async loadOrders () {
      const ordersResponse = await OrdersService.getAllOrders()
      var result = ordersResponse.data
      for (let index = 0; index < result.length; index++) {
        const volumeResponse = await OrdersService.getOrderVolume(result[index].order_id)
        result[index].volume = volumeResponse.data
      }
      this.result = result
    },
    loadAvailableVehicles () {
      VehicleService.getAvailableVehicles().then(
        response => {
          var vehicleTable = response.data
          for (let index = 0; index < vehicleTable.length; index++) {
            if (vehicleTable[index].type.type === 'Van') {
              vehicleTable[index].type.type = 'Van (Volume: 200)'
            } else if (vehicleTable[index].type.type === 'MicroBus') {
              vehicleTable[index].type.type = 'MicroBus (Volume: 500)'
            } else if (vehicleTable[index].type.type === 'TIR') {
              vehicleTable[index].type.type = 'TIR (Volume: 2500)'
            }
          }
          this.vehicleTable = vehicleTable
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
        this.searchOrders()
        this.loadAvailableVehicles()
      }
      )
    },
    async searchOrders () {
      const searchOrdersResponse = await OrdersService.getOrdersPage(this.currentPage, this.perPage)
      var result = searchOrdersResponse.data.result
      this.result.checkVal = false
      this.result.quantity = 1
      this.totalItems = searchOrdersResponse.data.totalItems
      this.rows = this.totalItems
      console.log(this.result)

      for (let index = 0; index < result.length; index++) {
        const volumeResponse = await OrdersService.getOrderVolume(result[index].order_id)
        result[index].volume = volumeResponse.data
      }
      this.result = result
    }
  }
}
</script>

<style scoped>

</style>
