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
        <p>Name of the part: <br/></p>
        <input
          v-model="newpart_name"
          v-validate="'required'"
          type="text"
          class="form-control"
          name="partName"
          :placeholder="this.result.part_name"
        />
      </div>
      <div class="info">
        <p>Location, from where the product can be distributed: <br/></p>
      </div>
      <select v-on:change="changeResultDistance" v-for="location in parts_locations" v-model="parts_locations.selectedOption" :key="location.options.name" class="select" name="locations">
        <option v-for="option in parts_locations.options" :value="option.name" :key="option.name " v-on:click="changeResultDistance(option.name)">
          {{ option.name }}
        </option>
      </select>
      <div class="info">
        <p>Distance from BAR Logistics center: <br/>
          <b>{{ result.location.distances_from_bar }} km.</b></p>
      </div>
      <div class="info">
        <p>Price of the product:</p>
      </div>
      <input
        v-model="newpart_price"
        v-validate="'required'"
        type="text"
        class="form-control"
        name="partName"
        :placeholder="this.result.price"
      />
      <div class="info">
        <p>Volume:</p>
      </div>
      <input
        v-model="newpart_volume"
        v-validate="'required'"
        type="text"
        class="form-control"
        name="partName"
        :placeholder="this.result.volume"
      />
    </div>
    <button class="back" v-on:click="$router.go(-1)">Back</button>
    <button class="upload" v-on:click="attemptUpload">Upload</button>
    <button class="save" v-on:click="editPart">Save</button>
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
        drag: 'Drag a GIF or GTFO'
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
import FormDataPost from '../services/upload-service'

export default {

  name: 'AdminEditPartDetails',
  data () {
    return {
      result: '',
      image: basketParts,
      parts_locations: [
        {
          options: [],
          selectedOption: '',
          selectedOptionIndex: ''
        }
      ],
      imageId: '',
      displayFile: [{
        id: '',
        path: '',
        type: '',
        img: null
      }],
      newpart_name: '',
      newpart_price: '',
      newpart_volume: ''
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
  mounted () {
  },
  methods: {
    async setData (response) {
      const response2 = await PartsService.getPartsLocation()
      this.parts_locations.options = response2.data
      this.parts_locations.selectedOption = response.data.location.name
      this.result = response.data
      this.newpart_name = this.result.part_name
      this.newpart_price = this.result.price
      this.newpart_volume = this.result.volume
      if (this.result.picture) {
        this.displayFile = this.result.picture
      } else {
        this.image = basketParts
      }
    },
    async onChange (image) {
      console.log('New picture selected!')
      if (image) {
        console.log('Picture loaded.')
        await this.changeImg(image)
      } else {
        console.log('FileReader API not supported: use the <form>, Luke!')
      }
    },
    changeImg (image) {
      this.image = image
    },
    changeResultDistance () {
      for (let i = 0; i < this.parts_locations.options.length; i++) {
        if (this.parts_locations.options[i].name === this.parts_locations.selectedOption) {
          this.result.location.distances_from_bar = this.parts_locations.options[i].distances_from_bar
          break
        }
      }
    },
    async attemptUpload () {
      if (this.image) {
        const base64Response = await fetch(this.image)
        const blob = await base64Response.blob()
        FormDataPost('http://localhost:8080/api/admin/parts/picture', blob, 'file')
          .then(response => {
            console.log(response.data)
            this.imageId = response.data
          })
          .catch(err => {
            console.error(err)
          })
      }
    },
    editPart () {
      PartsService.editParts(this.result.part_num, this.newpart_name, this.parts_locations.selectedOption, this.newpart_price, this.newpart_volume, this.imageId).then(response => {
        var temppartmessage = response.data.message
        setTimeout(function () { alert(temppartmessage) })
        this.$router.push('editparts')
      })
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
  margin-left: 800px;
}

.back {
  margin-left: 800px;
}

.upload {
  margin-left: 800px;
}
.save {
  top: 30%;
  margin-left: 200px;
}

</style>
