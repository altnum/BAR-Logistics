<template>
  <div>
    <header class="jumbotron">
      <h1 class="title">Product details</h1>
    </header>
    <div v-if="displayFile.img == null">
      <img :src="image" width="450" alt="image"/>
    </div>
    <div v-else>
      <img v-bind:src="'data:image/jpeg;base64, ' + displayFile.img" width="450" alt="image"/>
    </div>
    <div class="information1">
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
        <b>{{ result.location.distances_from_bar }} km.</b></p>
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
    <button class="btn" v-on:click="$router.go(-1)">Back</button>
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
      image: basketParts,
      displayFile: [{
        id: '',
        path: '',
        type: '',
        img: null
      }]
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
      if (this.result.picture) {
        this.displayFile = this.result.picture
      } else {
        this.image = basketParts
      }
    }
  }
}
</script>
<style>

.information1 {
  position: absolute;
  top: 50%;
  left: 55%;
  transform: translate(-50%, -50%);
  margin-top: 200px;
  font-size: 18px;
  text-align: left;
}

</style>
