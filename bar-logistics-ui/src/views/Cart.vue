<head>
<h1>Количка</h1>
</head>

<body>
<template>
<div>
    <b-table striped hover
             :dark="true"
             :items="result"
             :fields="fields"
    ></b-table>
  </div>
</template>
</body>

<script>
import PartsService from '../services/parts-service'

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
      ],
      content: { shopList: [] }
    }
  },
  beforeRouteEnter (to, from, next) {
    PartsService.getCart(to.params.shopList.toString()).then(response => {
      next(vm =>
        vm.setData(response)
      )
    })
  },
  methods: {
    setData (response) {
      this.result = response.data
    }
  }
}
</script>

<style scoped>

</style>
