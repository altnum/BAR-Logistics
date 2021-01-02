<template>
  <div>
    <header class="jumbotron">
      <h1 class="title">Product details</h1>
    </header>
    <img :src="basketParts" width="450" alt="image"/>
    <div class="information">
   <div class="info">
     <p>Part number: <br/>
       <b>{{ result.part_num }}</b></p>
   </div>
    <div class="info">
      <p>Name of the part: <br/>
        <b>{{ result.part_name }}</b></p>
    </div>
    <div class="info">
    <p>Location, from where the product can be distributed: <br/>
      <b>{{ result.location.name }}</b></p>
    </div>
    <div class="info">
      <p>Distance from BAR Logistics center: <br/>
        <b>{{ result.distance_from_bar }}</b></p>
    </div>
    <div class="info">
      <p>Price of the product: <br/>
        <b>{{ result.price }}</b></p>
    </div>
    <div class="info">
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
    }
  }
}
</script>
<style>
.information {
  position: absolute;
  top: 50%;
  left: 55%;
  transform: translate(-50%, -50%);
  margin-top: 200px;
  font-size: 18px;
  text-align: left;
}

.info {
  margin-bottom: 20px;
}
</style>
