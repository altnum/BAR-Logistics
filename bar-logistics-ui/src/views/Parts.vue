<template>
  <div>
    <br/>
    <h3>Cart: {{ countParts }} products added to cart</h3>
    <div class="goToCart">
    <div v-if="countParts !== 0">
      <router-link :to="{ name: 'cart' }" class="buttonCart" type="button">Go to cart</router-link>
    </div>
    </div>
    <button class="btn" v-on:click="searchParts">Search by filter</button>
    <b-table class="table" id="partsTable" striped hover bordered
             :items="result"
             :fields="fields"
             :current-page="currentPage">

      <template slot="top-row" slot-scope="{ fields }">
        <td v-for="field in fields" :key="field.name">
          <div v-if="field.key.toString() === 'price' || field.key.toString() === 'preview' || field.key.toString() === 'checkVal' || field.key.toString() === 'quantity' ">
          </div>
          <div v-else>
            <input v-model="filters[field.key]" :placeholder="field.label">
          </div>
        </td>
      </template>
      <template v-slot:cell(price)="row">
        {{ row.item.price }}
      </template>
      <template v-slot:cell(preview)="row">
        <router-link :to="{ name: 'partsDetails', params: {part_num: row.item.part_num} }" class="btn-group">Open</router-link>
      </template>
      <template v-slot:cell(quantity)="row">
        <input v-model="row.item.quantity" type="number" min="1" placeholder="0">
      </template>
      <template v-slot:cell(checkVal)="row">
        <b-checkbox v-on:input="addPartToCart(row.item.part_num, row.item.checkVal, row.item.quantity)" v-model="row.item.checkVal"></b-checkbox>
      </template>
    </b-table>
    <b-pagination
      v-model="currentPage"
      pills
      :total-rows="rows"
      :per-page="perPage"
      @input="searchParts"
      aria-controls="partsTable">
    </b-pagination>
  </div>
</template>

<script>
import PartsService from '../services/parts-service'
import { mapState, mapMutations, mapGetters } from 'vuex'

export default {
  name: 'Parts.vue',
  data () {
    return {
      currentPage: 1,
      rows: '',
      perPage: 10,
      result: [{ part_num: '', part_name: '', price: '', preview: '', checkVal: false, quantity: '' }],
      fields: [{ key: 'part_num', sortable: true, label: 'Product №' }, { key: 'part_name', sortable: true, label: 'Product' }, { key: 'price', sortable: true, label: 'Price in BGN' }, { key: 'preview', label: 'Details' }, { key: 'quantity', label: 'Quantity' }, { key: 'checkVal', label: 'Add to cart' }],
      filters: {
        part_num: '',
        part_name: ''
      },
      totalItems: ''
    }
  },
  mounted () {
    this.searchParts()
    this.TRANSFORM_CART(JSON.parse(localStorage.getItem('cart')))
  },
  computed: {
    ...mapGetters([
      'countParts'
    ]),
    ...mapState([
      'cart'
    ])
  },
  methods: {
    ...mapMutations([
      'ADD_PART_TO_CART',
      'TRANSFORM_CART'
    ]),
    addPartToCart (partNum, checkVal, times) {
      if (checkVal && times >= 1) {
        for (let i = 0; i < times; i++) {
          this.ADD_PART_TO_CART(partNum)
        }
        localStorage.setItem('cart', JSON.stringify(this.cart))
      }
      this.searchParts()
    },
    searchParts () {
      PartsService.getPartsPage(this.filters, this.currentPage, this.perPage).then(
        response => {
          this.result = response.data.result
          this.result.checkVal = false
          this.result.quantity = 1
          this.totalItems = response.data.totalItems
          this.rows = this.totalItems
        },
        error => {
          this.result = (error.response && error.response.data) || error.message || error.toString()
        }
      )
    }
  }
}
</script>

<style scoped>
.buttonCart {
  background-color: mediumseagreen;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  width: 30%;
}

.btn {
  background-color: slategrey;
  color: white;
  text-align: center;
  display: inline-block;
  padding: 5px 10px;
  margin-right: 5px;
  margin-bottom: 10px;
}

.goToCart{
  margin-bottom: 10px;
  margin-top: 10px;
}
</style>
