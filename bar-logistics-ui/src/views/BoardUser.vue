<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>Welcome, {{currentUser.first_name}} {{currentUser.last_name }}!</strong>
      </h3>
    </header>
    <div class="imageUser">
    <img :src="user" width="500" alt="image" align="center"/>
    </div>
    <h3 class="profileInfo">Your profile info:</h3>
    <div class="info">
      <p>
        <strong>Username:</strong>
        <br/>
        {{currentUser.username }}
      </p>
      <p>
        <strong>Email:</strong>
        <br/>
        {{currentUser.email}}
      </p>
      <p>
        <strong>Address:</strong>
        <br/>
        {{currentUser.address.name}}
      </p>
      <strong>User role:</strong>
      <ul>
        <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
      </ul>
    </div>
    <div>
      <strong>My orders:</strong>
      <b-table class="table" id="myOrdersTable" striped hover bordered :items="result" :fields="fields" :current-page="currentPage">
        <template v-slot:cell(ship_date)="row">
          <div v-if="row.item.status !==  'pending'">
            {{ row.item.ship_date }}
          </div>
          <div v-else>-----</div>
        </template>
        <template v-slot:cell(preview)="row">
          <router-link :to="{ name: 'ordersDetails', params: {order_id: row.item.order_id} }" class="btn-group">Details</router-link>
        </template>
        <template v-slot:cell(progress)="row">
          <progress-bar
            :options="options"
            :value="row.item.value"
          />
        </template>
      </b-table>
      <b-pagination
        v-model="currentPage"
        pills
        :total-rows="rows"
        :per-page="perPage"
        @input="loadOrders"
        aria-controls="myOrdersTable">
      </b-pagination>
    </div>
  </div>
</template>

<script>
import user from '../assets/avatar.jpg'
import OrdersService from '../services/orders-service.js'

export default {
  name: 'Profile',
  data () {
    return {
      options: {
        text: {
          color: '#FFFFFF',
          shadowEnable: true,
          shadowColor: '#000000',
          fontSize: 14,
          fontFamily: 'Helvetica',
          dynamicPosition: false,
          hideText: false
        },
        progress: {
          color: '#2dbd2d',
          backgroundColor: '#C0C0C0'
        },
        layout: {
          height: 35,
          width: 140,
          verticalTextAlign: 61,
          horizontalTextAlign: 43,
          zeroOffset: 0,
          strokeWidth: 30,
          progressPadding: 0,
          type: 'line'
        }
      },
      result: [{ order_id: '', status: '', order_date: '', ship_date: '', price: '', preview: '', progress: '', value: 0 }],
      fields: [{ key: 'order_id', label: 'Order №' }, { key: 'ship_date', label: 'Delivery Date' }, { key: 'price', sortable: true, label: 'Price in BGN' }, { key: 'status', sortable: true, label: 'Status' }, { key: 'preview', label: '' }, { key: 'progress', label: 'Progress (%):' }],
      user: user,
      currentPage: 1,
      perPage: 5,
      totalItems: '',
      rows: ''
    }
  },
  computed: {
    currentUser () {
      return this.$store.state.auth.user
    }
  },
  mounted () {
    if (!this.currentUser) {
      this.$router.push('/login')
    }
    this.loadOrders()
  },
  methods: {
    async loadOrders () {
      const searchOrdersResponse = await OrdersService.paginateCurrUserOrders(this.currentPage, this.perPage, JSON.parse(localStorage.getItem('user')).id)
      var result = searchOrdersResponse.data.result
      this.totalItems = searchOrdersResponse.data.totalItems
      this.rows = this.totalItems
      for (let i = 0; i < result.length; i++) {
        result[i].value = this.getPercentageCompletion(result[i].order_date, result[i].ship_date, result[i].order_id, result[i].status)
      }
      this.result = result
    },
    getPercentageCompletion (orderDate, shipDate, orderId, status) {
      const currentDate = new Date()
      shipDate = shipDate.replace(' ', 'T')
      const shipD = new Date(shipDate)

      orderDate = orderDate.replace(' ', 'T')
      const orderD = new Date(orderDate)

      var subs = 0
      var orderDateMs = orderD.getTime()
      var currDateMs = currentDate.getTime()
      var shipDateMs = shipD.getTime()

      if ((shipDateMs - currDateMs) >= 0 || shipDateMs === orderDateMs) {
        if (status === 'processing') {
          if ((shipDateMs - orderDateMs) > 0) {
            subs = ((currDateMs - orderDateMs) * 100) / (shipDateMs - orderDateMs)
          } else if ((shipDateMs - orderDateMs) === 0) {
            OrdersService.orderDelivered(orderId)
            return 100
          }
        } else if (status === 'pending') {
          if ((shipDateMs - orderDateMs) > 0) {
            subs = ((currDateMs - orderDateMs) * 100) / (shipDateMs - orderDateMs)
          } else if ((shipDateMs - orderDateMs) === 0) {
            return 0
          }
        } else if (status === 'delivered') {
          return 100
        }
      } else {
        OrdersService.orderDelivered(orderId)
        return 100
      }

      return Math.round(subs)
    }
  }
}
</script>
<style>
</style>
