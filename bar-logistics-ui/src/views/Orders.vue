<template>
  <div>
    <header>
      <h1>Orders</h1>
    </header>

    <b-table stripped hover
             :items="content"
             id="item-table" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
      <thead>
      <tr>
        <th class="mdl-data-table__cell--non-numeric">Id</th>
        <th class="mdl-data-table__cell--non-numeric">User</th>
        <th class="mdl-data-table__cell--non-numeric">Order date</th>
        <th class="mdl-data-table__cell">Ship date</th>
        <th class="mdl-data-table__cell">Status</th>
        <th class="mdl-data-table__cell">Part name</th>
        <th class="mdl-data-table__cell">Details</th>
        <th class="mdl-data-table__cell--non-numeric">
          <button class="mdl-button mdl-js-button mdl-button--icon">
            <i class="material-icons">delete</i>
          </button>
          <v-btn icon class="mx-0" @click="editItem(props.item)">
            <v-icon color="teal">edit</v-icon>
          </v-btn>
        </th>
      </tr>
      </thead>
      <tbody>

      </tbody>
    </b-table>
  </div>
</template>

<script>
import OrdersService from '../services/orders-service.js'

export default {
  name: 'Orders',
  data () {
    return {
      result: [{ order_id: '', user_id: '', order_date: '', ship_date: '', status: '', shopList: '' }],
      fields: [
        { key: 'order_id', label: 'Id' },
        { key: 'user_id.username', label: 'User' },
        { key: 'order_date', label: 'Order date' },
        { key: 'ship_date', label: 'Ship date' },
        { key: 'status', sortable: true, label: 'Status' },
        { key: 'shopList', label: 'Part name' }

      ],
      content: { shopList: [] }
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

<style scoped>

</style>
