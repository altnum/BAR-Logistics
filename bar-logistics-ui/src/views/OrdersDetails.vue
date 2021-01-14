<template>
  <div>
    {{ result }}
  </div>
</template>

<template>
  <div>
    <header>
      <h1>Orders details</h1>
    </header>
    <b-table class="table" id="ordersTable" striped hover boarded
             :items="result">
    </b-table>
  </div>
</template>
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

<script>
import OrdersService from '@/services/orders-service'

export default {
  name: 'OrdersDetails',
  data () {
    return {
      result:
        {
          order_id: '',
          part_id: '',
          quantity: ''
        }
    }
  },
  beforeRouteEnter (to, from, next) {
    OrdersService.getOrderIdDetails(to.params.order_id).then(response => {
      next(vm =>
        vm.setData(response)
      )
    })
  },
  methods: {
    setData (response) {
      this.result = response.data
    }
  }
}
</script>

<style scoped>

</style>
