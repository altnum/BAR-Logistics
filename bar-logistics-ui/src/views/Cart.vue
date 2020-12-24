<template>
  <div>
    <b-table striped hover
             :dark="true"
             :items="result"
             :fields="fields"
    ></b-table>
  </div>
</template>

<script>
import PartsService from '../services/parts-service'
import { mapState } from 'vuex'

export default {
  name: 'Cart',
  data () {
    return {
      result: [{ part_num: '', part_name: '', price: '', location: '' }],
      fields: [
        { key: 'part_num', label: 'Част №' },
        { key: 'part_name', label: 'Част' },
        { key: 'price', label: 'Цена' },
        { key: 'location.name', label: 'На склад в:' }
      ]
    }
  },
  mounted () {
    this.loadCart(this.cart)
  },
  computed: {
    ...mapState([
      'cart'
    ])
  },
  methods: {
    loadCart (cart) {
      PartsService.getCart(cart.toString()).then(response => {
        this.result = response.data
      })
    }
  }
}
</script>

<style scoped>

</style>
