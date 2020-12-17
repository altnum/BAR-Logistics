<template>
  <div>
    <button class="btn" v-on:click="searchParts">Търси</button>
    <b-table class="table" id="partsTable" striped hover bordered :items="result" :fields="fields" :current-page="currentPage">
      <template slot="top-row" slot-scope="{ fields }">
        <td v-for="field in fields" :key="field.name">
          <div v-if="field.key.toString() === 'price' || field.key.toString() === 'preview'">
          </div>
          <div v-else>
            <input v-model="filters[field.key]" :placeholder="field.label">
          </div>
        </td>
      </template>
      <template v-slot:cell(preview)="row">
        <router-link :to="{ name: 'partsDetails', params: {part_num: row.item.part_num} }" class="btn-group">Отвори</router-link>
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
      result: [{ part_num: '', part_name: '', price: '', preview: '' }],
      fields: [{ key: 'part_num', label: 'Част №' }, { key: 'part_name', label: 'Част' }, { key: 'price', label: 'Цена' }, { key: 'preview', label: 'Детайли' }],
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
    searchParts () {
      PartsService.getPartsPage(this.filters, this.currentPage, this.perPage).then(
        response => {
          this.result = response.data.result
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
