<template>
  <div>
    {{ result }}
  </div>
</template>

<script>
import PartsService from '../services/parts-service'

export default {
  name: 'Cart',
  data () {
    return {
      result:
        {
          shopList: []
        }
    }
  },
  beforeRouteEnter (to, from, next) {
    PartsService.getCart(to.params.shopList.toString()).then(response => {
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
