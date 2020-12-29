<template>
  <div>
    <div v-if="this.cart.length === 0 && submittedOrder === false">
      <h3>Количката е празна!</h3>
    </div>
    <div v-else-if="submittedOrder === true">
      <h3>Вашата поръчка е приета!</h3>
    </div>
    <router-link to="/parts" tag="button">Към продуктите</router-link>
    <div v-if="this.cart.length > 0 && submittedOrder === false">
      <button class="btn" v-on:click="emptyCart">Изпразни количката</button>
      <button class="btn" v-on:click="submitOrders">Потвърди поръчката</button>
    <b-table striped hover
             :dark="true"
             :items="result"
             :fields="fields"
    >
      <template v-slot:cell(remove_element)="row">
        <button class="btn" v-on:click="removeParts(row.item.part_num)">Премахни</button>
      </template>
    </b-table>
      <div v-if="this.cart.length !== 0 && !submittedOrder">
        Цена на частите: {{ this.partsPrice.toPrecision(3) }}
        +
        Цена на доставка: {{ this.deliveryPrice.toFixed(2) }}
        =
        Крайна цена: {{ this.overallPrice.toFixed(2) }}
      </div>
    </div>
  </div>
</template>

<script>
import PartsService from '../services/parts-service'
import OrdersService from '../services/orders-service'
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import VehicleService from '../services/vehicle-service'
import CapitalsService from '../services/capitals-service'

export default {
  name: 'Cart',
  data () {
    return {
      result: [{ part_num: '', part_name: '', price: 0.0, location: { name: '', distances_from_bar: '' }, remove_element: '', volume: 0.0 }],
      fields: [
        { key: 'part_num', label: 'Част №' },
        { key: 'part_name', label: 'Част' },
        { key: 'price', label: 'Цена' },
        { key: 'location.name', label: 'На склад в' },
        { key: 'remove_element', label: 'Премахни елемент' }
      ],
      submittedOrder: false,
      partsPrice: 0.0,
      deliveryPrice: 0.0,
      volume: 0.0,
      vehicle: [{ type: { type: '', fuel_consumption: 0.0 } }],
      userDistance: [{ import_tax: '', distance_from_bar: 0.0 }],
      overallPrice: 0.0
    }
  },
  mounted () {
    this.partsPrice = 0.0
    this.TRANSFORM_CART(JSON.parse(localStorage.getItem('cart')))
    if (this.cart.length > 0) {
      this.loadCart(this.cart)
    }
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
    addPrice: function () {
      PartsService.getCart(this.cart.toString()).then(response => {
        this.result = response.data
        this.partsPrice = 0.0
        this.volume = 0.0
        this.deliveryPrice = 0.0
        const setOfWarehouses = new Set()
        for (let i = 0; i < this.result.length; i++) {
          this.partsPrice = this.partsPrice + this.result[i].price
          this.volume = this.volume + this.result[i].volume
          setOfWarehouses.add(this.result[i].location.name)
        }
        VehicleService.getExample(this.volume).then(response => {
          this.vehicle = response.data
          CapitalsService.getCapitalByName(JSON.parse(localStorage.getItem('user')).address.name.toString()).then(response => {
            this.userDistance = response.data
            this.deliveryPrice = this.userDistance.distance_from_bar / this.vehicle.type.fuel_consumption * 2 + this.userDistance.import_tax + 50
            for (let i = 0; i < this.result.length; i++) {
              if (setOfWarehouses.has(this.result[i].location.name)) {
                this.deliveryPrice = this.deliveryPrice + this.result[i].location.distances_from_bar / this.vehicle.type.fuel_consumption * 2
                setOfWarehouses.delete(this.result[i].location.name)
              }
            }
            this.overallPrice = this.partsPrice + this.deliveryPrice
          })
        })
      })
    },
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
      this.addPrice()
    },
    submitOrders () {
      OrdersService.submitOrder(JSON.parse(localStorage.getItem('user')).username.toString(), this.cart.toString(), this.overallPrice.toFixed(2)).then(response => {
      })
      localStorage.removeItem('cart')
      this.result = []
      this.submittedOrder = true
    }
  }
}
</script>

<style scoped>

</style>
