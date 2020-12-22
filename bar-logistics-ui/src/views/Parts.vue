<template>
  <div>
    <h2>Cart: {{ cart.shopList.length }}</h2>
    <div v-if="cart.shopList.length !== 0">
      <router-link :to="{ name: 'cart', params: { shopList: cart.shopList } }" class="btn-group">Към Kоличка</router-link>
    </div>
    <button class="btn" v-on:click="searchParts">Търси</button>
    <b-table class="table" id="partsTable" striped hover bordered
             :dark="true"
             :items="result"
             :fields="fields"
             :current-page="currentPage">

      <template slot="top-row" slot-scope="{ fields }">
        <td v-for="field in fields" :key="field.name">
          <div v-if="field.key.toString() === 'price' || field.key.toString() === 'preview' || field.key.toString() === 'checkVal'">
          </div>
          <div v-else>
            <input v-model="filters[field.key]" :placeholder="field.label">
          </div>
        </td>
      </template>
      <template v-slot:cell(preview)="row">
        <router-link :to="{ name: 'partsDetails', params: {part_num: row.item.part_num} }" class="btn-group">Отвори</router-link>
      </template>
      <template v-slot:cell(checkVal)="row">
        <b-checkbox v-on:input="addPartToCart(row.item.part_num, row.item.checkVal)" v-model="row.item.checkVal"></b-checkbox>
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

export default {
  name: 'Parts.vue',
  data () {
    return {
      currentPage: 1,
      rows: '',
      perPage: 10,
      cart: {
        shopList: []
      },
      result: [{ part_num: '', part_name: '', price: '', preview: '', checkVal: false }],
      fields: [{ key: 'part_num', sortable: true, label: 'Част №' }, { key: 'part_name', sortable: true, label: 'Част' }, { key: 'price', sortable: true, label: 'Цена за Брой' }, { key: 'preview', label: 'Детайли' }, { key: 'checkVal', label: 'Добави в Количка' }],
      filters: {
        part_num: '',
        part_name: ''
      },
      totalItems: ''
    }
  },
  mounted () {
    this.searchParts()
  },
  methods: {
    addPartToCart (partNum, checkVal) {
      if (checkVal) {
        this.cart.shopList.push(partNum)
      }
      this.searchParts()
    },
    searchParts () {
      PartsService.getPartsPage(this.filters, this.currentPage, this.perPage).then(
        response => {
          this.result = response.data.result
          this.result.checkVal = false
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

</style>
