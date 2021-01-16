<template>
  <div>
    <header class="jumbotron">
      <h1 class="title">Product details</h1>
    </header>
    <img :src="image" width="450" alt="image"/>
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
    <template>
      <div class="pictureInputd">
        <picture-input
          ref="pictureInput"
          @change="onChange"
          width="200"
          height="200"
          margin="16"
          accept="image/jpeg,image/png"
          size="10"
          buttonClass="btn"
          :customStrings="{
        upload: '<h1>Bummer!</h1>',
        drag: 'Drag a :smiley_cat: GIF or GTFO'
      }">
        </picture-input>
      </div>
    </template>
  </div>
</template>

<script>
import PartsService from '../services/parts-service'
import basketParts from '../assets/basketParts.jpg'
import PictureInput from 'vue-picture-input'

export default {

  name: 'AdminEditPartDetails',
  data () {
    return {
      result: '',
      image: basketParts
    }
  },
  beforeRouteEnter (to, from, next) {
    PartsService.getPartsById(to.params.part_num).then(response => {
      next(vm =>
        vm.setData(response)
      )
    })
  },
  components: {
    PictureInput
  },
  methods: {
    setData (response) {
      this.result = response.data
    },
    onChange (image) {
      console.log('New picture selected!')
      if (image) {
        console.log('Picture loaded.')
        this.image = image
      } else {
        console.log('FileReader API not supported: use the <form>, Luke!')
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
.pictureInputd {
  margin-top: 200px;
}
</style>
