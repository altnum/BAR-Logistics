<template>
  <div>
    <header class="jumbotron">
      <h1 class="title">Product details</h1>
    </header>

    <img :src="basketParts" width="450" alt="image"/>
    <div class="information1">
   <div>
     <p>Part number: <br/>
       <b>{{ result.part_num }}</b></p>
   </div>
    <div>
      <p>Name of the part: <br/>
        <b>{{ result.part_name }}</b></p>
    </div>
    <div>
    <p>Location, from where the product can be distributed: <br/>
      <b>{{ result.location.name }}</b></p>
    </div>
    <div>
      <p>Distance from BAR Logistics center: <br/>
        <b>{{ result.location.distances_from_bar }} km.</b></p>
    </div>
    <div>
      <p>Price of the product: <br/>
        <b>{{ result.price }}</b></p>
    </div>
    <div>
    <p>Volume: <br/>
      <b>{{ result.volume }}</b></p>
    </div>
    </div>
  </div>
</template>

<script>
import PartsService from '../services/parts-service'
import basketParts from '../assets/basketParts.jpg'
export default {

  name: 'PartDetails',
  data () {
    return {
      result: '',
      basketParts: basketParts
    }
  },
  beforeRouteEnter (to, from, next) {
    PartsService.getPartsById(to.params.part_num).then(response => {
      next(vm =>
        vm.setData(response)
      )
    })
  },
  methods: {
    setData (response) {
      this.result = response.data
      console.log(this.result)
    }
  }
}
</script>
<style>
.information1 {
  top: 50%;
  left: 55%;
  font-size: 18px;
  text-align: left;
  display: inline-block;
}

</style>
