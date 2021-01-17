<template>
  <div>
  <div>
    <header>
      <br>

      <header class="jumbotron">
        <div class="title">
          <h1>Admin Board</h1>
        </div>
        <div class="title2">
          <h2 class="s"><i>AUTOMOTIVE PARTS SHIPPING & LOGISTICS</i></h2>
        </div>

      </header>
        <span class="title1">View current orders:   </span><br/>
        <router-link to="/orders" tag="button" class="button1">Confirm Orders</router-link>
    </header>
      <header>
        <span class="title1">Add or Remove parts:   </span><br/>
        <router-link to="/editparts" tag="button" class="button1">Edit Parts</router-link>
      </header>
  </div>
  </div>
</template>

<script>
import OrdersService from '../services/orders-service.js'

export default {
  name: 'Orders',
  data () {
    return {
      content: ''
    }
  },
  mounted () {
    OrdersService.getAllOrders().then(
      response => {
        this.content = response.data
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString()
      }
    )
  }
}
</script>

<script>
import emailjs from 'emailjs-com';

export default {
  name: 'ContactUs',
  data() {
    return {
      name: '',
      email: '',
      message: ''
    }
  },
  methods: {
    sendEmail(e) {
      try {
        emailjs.sendForm('service_bar', 'template_yt6rx0v',
          'user_KCM8XuWUwpKj18QxPwDec', {
            name: this.name,
            email: this.email,
            message: this.message
          })

      } catch(error) {
        console.log({error})
      }
      // Reset form field
      this.name = ''
      this.email = ''
      this.message = ''
    },
  }
}
</script>

<style>
.title1 {
  font-size: 30px;
  padding-bottom: 7px;
}
.button1 {
  background-color: grey; /* Green */
  border: none;
  color: white;
  padding: 20px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  width: 20%;
}
.s {
  font-size: 20px;
}
</style>
