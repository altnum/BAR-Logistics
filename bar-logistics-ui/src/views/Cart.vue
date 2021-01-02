<template>
  <div>
    <div v-if="this.cart.length === 0 && submittedOrder === false">
      <h3>Your cart is empty!</h3>
    </div>
    <div v-else-if="submittedOrder === true">
      <h2 class="text">The order is confirmed!</h2>
      <router-link to="/parts" tag="button" class="buttonCart" style="background-color: #4CAF50" margin="20px">Back to parts inventory</router-link>
      <template>
        <div class="container">
          <h3>Send email to the customer:</h3>
          <form>
            <label>Name</label>
            <input
              type="text"
              v-model="name"
              name="name"
              placeholder="Customer name"
            >
            <label>Email</label>
            <input
              type="email"
              v-model="email"
              name="email"
              placeholder="Customer email"
            >
            <label>Message</label>
            <textarea
              name="message"
              v-model="message"
              cols="30" rows="5"
              placeholder="Message">
          </textarea>

            <input type="submit" value="Send">
          </form>
        </div>
      </template>
    </div>

    <div v-if="this.cart.length > 0 && submittedOrder === false">
      <br/>
      <button class="btn" v-on:click="emptyCart" type="button">Empty cart</button>
      <button class="btn" v-on:click="submitOrders">Confirm order</button>
    <b-table striped hover
             :items="result"
             :fields="fields"
    >
      <template v-slot:cell(remove_element)="row">
        <button class="btn" v-on:click="removeParts(row.item.part_num)">Remove</button>
      </template>
    </b-table>
      <div class="totalPrice">
      <div v-if="this.cart.length !== 0 && !submittedOrder">
        Total price of the parts: <b>{{ this.partsPrice.toPrecision(3) }}</b> <br/>
        + <br/>
        Price for delivery: <b>{{ this.deliveryPrice.toFixed(2) }}</b> <br/>
        = <br/>
        Total price: <b>{{ this.overallPrice.toFixed(2) }}</b>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
import emailjs from 'emailjs-com'
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
        { key: 'part_num', label: 'Product №' },
        { key: 'part_name', label: 'Product' },
        { key: 'price', label: 'Price' },
        { key: 'location.name', label: 'In stock in' },
        { key: 'remove_element', label: 'Remove product' }
      ],
      submittedOrder: false,
      partsPrice: 0.0,
      deliveryPrice: 0.0,
      volume: 0.0,
      vehicle: [{ type: { type: '', fuel_consumption: 0.0 } }],
      userDistance: [{ import_tax: '', distance_from_bar: 0.0 }],
      overallPrice: 0.0,
      name: '',
      email: '',
      message: ''
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
    sendEmail (e) {
      try {
        emailjs.sendForm('YOUR_SERVICE_ID', 'YOUR_TEMPLATE_ID', e.target,
          'YOUR_USER_ID', {
            name: this.name,
            email: this.email,
            message: this.message
          })
      } catch (error) {
        console.log({ error })
      }
      // Reset form field
      this.name = ''
      this.email = ''
      this.message = ''
    },
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
.totalPrice {
  font-size: 18px;
}
.buttonCart {
  background-color: slategrey;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  width: 30%;
  margin-top: 40px;
}

.btn {
  background-color: slategrey;
  color: white;
  text-align: center;
  display: inline-block;
  padding: 5px 10px;
  margin-right: 5px;
  margin-bottom: 5px;

}

.totalPrice {
  margin-left: 500px;
  text-align: center;
  margin-bottom: 50px;
  margin-top: 50px;
  font-size: 20px;
}

.container {
  display: block;
  margin:30px;
  text-align: center;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  width: 50%;
}

label {
  float: left;
}

input[type=text], [type=email], textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}
.container {
  float: right;
}

.text {
  margin-top: 70px;
  text-align: center;
  font-style: italic;
  font-weight: 400;
}
</style>
