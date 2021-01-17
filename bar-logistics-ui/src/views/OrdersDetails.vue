<template>
  <div>
    <header>
      <h1>Orders details</h1>
    </header>
    <b-table class="table" id="ordersTable" striped hover boarded
             :items="result">
    </b-table>
    <button class="btn" v-on:click="$router.go(-1)">Back</button>
  </div>
</template>
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
          quantity: '',
          price: ''
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
    async setData (response) {
      var currResult = response.data
      for (let index = 0; index < currResult.length; index++) {
        var currentPrice = await OrdersService.getOrderPartsPrice(currResult[index].part_id, currResult[index].quantity)
        currResult[index].price = currentPrice.data
      }
      this.result = currResult
    }
  }
}
</script>

<style scoped>
.btn
{
  background-color: lavender;
}
</style>
