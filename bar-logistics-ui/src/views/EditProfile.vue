<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>Welcome, {{currentUser.first_name}} {{currentUser.last_name }}!</strong>
      </h3>
    </header>
    <img :src="user" width="500" alt="image" align="center" class="imageUser"/>
    <h3 class="profileInfo">Your profile info:</h3> <br/>
    <div class="info">
        <b><label for="currentUser">Current username:</label></b><br/>
        <input type="text"
          v-model="newUsername"
          v-validate="'required|min:6|max:40'"
          class="form-control"
          name="currentUser"
          :placeholder="this.currentUsername"
        /><br/>
      <b><label>Current email:</label></b><br/>
      <input type="text"
        v-model="newEmail"
        v-validate="'required|min:6|max:40'"
        class="form-control"
        name="currentEmail"
        :placeholder="this.currentEmail"
      /><br/>
      <b><label for="address">Current address:</label></b><br/>
      <select v-for="capital in capitals" v-model="newAddress" :key="capital.options.name" class="form-control" name="address">
        <option v-for="option in capitals.options" :value="option.name" :key="option.name" class="form-control" name="selectedCapital">
          {{option.name}}
        </option>
      </select>
      <button class="btn btn-success" v-on:click="saveProfile">Save</button>
    </div>
  </div>
</template>

<script>
import user from '../assets/avatar.jpg'
import CapitalsService from '../services/capitals-service'
import UserService from '../services/user.service'
export default {
  beforeRouteEnter (to, from, next) {
    CapitalsService.getAllCapitals(to.params.name).then(
      response => {
        next(vm =>
          vm.setData(response)
        )
      }
    )
  },
  data () {
    return {
      user: user,
      currentUsername: '',
      currentEmail: '',
      currentAddress: '',
      capitals: [
        {
          options: [],
          selectedOption: ''
        }
      ],
      newUsername: '',
      newEmail: '',
      newAddress: ''
    }
  },
  name: 'EditProfile',
  computed: {
    currentUser () {
      return this.$store.state.auth.user
    }
  },
  methods: {
    setData (response) {
      this.capitals.options = response.data
      this.capitals.selectedOption = JSON.parse(localStorage.getItem('user')).address.name
    },
    saveProfile () {
      console.log(user.id)
      console.log('bbb' + this.newUsername)
      console.log('aaaaa' + this.newAddress)
      if (this.newUsername !== '' || this.newUsername !== '') {
        UserService.saveProfile(this.newUsername,
          this.newEmail, this.newAddress).then(
          response => {
            this.newUsername = ''
            this.newEmail = ''
            this.newAddress = ''
            var message = response.data.message
            console.log('aaaaa' + response)
            setTimeout(function () { alert(message) }, 200)
            this.getUserBoard()
          })
      }
    }
  },
  mounted () {
    if (!this.currentUser) {
      this.$router.push('/login')
    }
    this.currentUsername = JSON.parse(localStorage.getItem('user')).username
    this.currentEmail = JSON.parse(localStorage.getItem('user')).email
  }
}
</script>

<style>
.profileInfo{
  text-align: center;
}
.info {
  margin-left: 300px;
  margin-bottom: 200px;
}
.imageUser {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
  margin-bottom: 30px;
}

.btn {
  background-color: green;
  margin-top: 20px;
}

input[type=text]{
  width: 100%;
  padding: 15px 15px;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 20px 30px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.info {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  margin-bottom: 50px;
  width: 500px;
  align-content: center;
}
</style>
