<template>
  <div>
    {{ result }}
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
