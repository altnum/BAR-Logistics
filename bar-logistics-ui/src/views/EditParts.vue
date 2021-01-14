<template>
  <div>
    <br/>
    <div>
      <input type="text"  v-model="newpart_name" v-validate="'required|min:2|max:30'" name="pName"  placeholder="part_name">
      <select v-for="location in parts_locations" v-model="parts_locations.selectedOption" :key="location.options.name" class="form-control" name="locations">
        <option v-for="option in parts_locations.options" :value="option.name" :key="option.name">
          {{ option.name }}
        </option>
      </select>
      <input type="text" v-model="newpart_price" v-validate="'required|min:1|max:30'" name="pPrice" placeholder="price">
      <input type="text" v-model="newpart_volume" v-validate="'required|min:1|max:30'" name="pVolume" placeholder="volume">
      <button class="btn btn-success" v-on:click="addParts">Add</button>
    </div>
    <button class="btn" v-on:click="searchParts">Search by filter</button>
    <b-table class="table" id="partsTable" striped hover bordered
             :items="result"
             :fields="fields"
             :current-page="currentPage">

      <template slot="top-row" slot-scope="{ fields }">
        <td v-for="field in fields" :key="field.name">
          <div v-if="field.key.toString() === 'price' || field.key.toString() === 'preview' || field.key.toString() === 'remove'">
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
      <template v-slot:cell(remove)="row">
        <button class="btn btn-danger"
                v-on:click="removeParts(row.item.part_num)">Remove</button>
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
  name: 'EditParts.vue',
  data () {
    return {
      selected: '',
      currentPage: 1,
      rows: '',
      perPage: 10,
      result: [{ part_num: '', part_name: '', price: '', preview: '', remove: '' }],
      fields: [{ key: 'part_num', sortable: true, label: 'Product №' }, { key: 'part_name', sortable: true, label: 'Product' }, { key: 'price', sortable: true, label: 'Price in BGN' }, { key: 'preview', label: 'Details' }, { key: 'price', sortable: true, label: 'Price in BGN' }, { key: 'remove', label: 'Remove' }],
      filters: {
        part_num: '',
        part_name: ''
      },
      newpart_name: '',
      parts_locations: [
        {
          options: [],
          selectedOption: ''
        }
      ],
      newpart_price: '',
      newpart_volume: ''
    }
  },
  mounted () {
    PartsService.getPartsLocation().then(
      response => {
        this.parts_locations.options = response.data
        this.parts_locations.selectedOption = this.parts_locations.options[0].name
      }
    )
    this.searchParts()
  },
  methods: {
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
    },
    // eslint-disable-next-line camelcase
    removeParts (part_num) {
      PartsService.removePartsById(part_num).then(response => {
        this.searchParts()
      })
    },
    // eslint-disable-next-line camelcase
    addParts () {
      if (this.newpart_name !== '' && this.newpart_price !== '' && this.newpart_volume !== '') {
        PartsService.addPart(this.newpart_name, this.parts_locations.selectedOption, this.newpart_price, this.newpart_volume)
      }
      this.newpart_name = ''
      this.parts_locations.selectedOption = this.parts_locations.options[0]
      this.newpart_price = ''
      this.newpart_volume = ''
      this.searchParts()
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

</style>
