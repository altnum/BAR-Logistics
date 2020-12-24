<template>
  <div>
    <div>
      <button class="btn" v-on:click="emptyCart">Изпразни количката</button>
    </div>
    <b-table striped hover
             :dark="true"
             :items="result"
             :fields="fields"
    >
      <template v-slot:cell(remove_element)="row">
        <button class="btn" v-on:click="removeParts(row.item.part_num)">Премахни</button>
      </template>
    </b-table>
  </div>
</template>

<script>
import PartsService from '../services/parts-service'
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'

export default {
  name: 'Cart',
  data () {
    return {
      result: [{ part_num: '', part_name: '', price: '', location: '', remove_element: '' }],
      fields: [
        { key: 'part_num', label: 'Част №' },
        { key: 'part_name', label: 'Част' },
        { key: 'price', label: 'Цена' },
        { key: 'location.name', label: 'На склад в:' },
        { key: 'remove_element', label: 'Премахни елемент' }
      ]
    }
  },
  mounted () {
    this.TRANSFORM_CART(JSON.parse(localStorage.getItem('cart')))
    this.loadCart(this.cart)
  },
  computed: {
    ...mapState([
      'cart'
    ]),
    ...mapGetters([
      'countParts'
    ])
  },
  methods: {
    ...mapMutations([
      'CLEAR_CART',
      'TRANSFORM_CART'
    ]),
    ...mapActions([
      'removePart'
    ]),
    removeParts: function (part) {
      if (this.countParts >= 1) {
        const initialSize = this.countParts
        const i = this.cart.indexOf(part)
        this.removePart(i)
        localStorage.setItem('cart', JSON.stringify(this.cart))
        if (initialSize > 1) {
          this.loadCart(this.cart)
        } else {
          this.result = []
        }
      }
    },
    emptyCart () {
      this.CLEAR_CART()
      localStorage.removeItem('cart')
      this.result = []
    },
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
