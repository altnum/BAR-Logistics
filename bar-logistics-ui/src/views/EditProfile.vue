<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>Welcome, {{currentUser.first_name}} {{currentUser.last_name }}!</strong>
      </h3>
    </header>
    <img :src="user" width="500" alt="image" align="center" class="imageUser"/>
    <h3 class="profileInfo">Your profile info:</h3> <br/>

    <form name="editProfile" @submit.prevent="saveProfile">
    <div v-if="!successful">
    <div class="info">
        <b><label for="currentUser">Edit current username:</label></b><br/>
        <input type="text"
          v-model="newUsername"
          v-validate="'required|min:3|max:40'"
          class="form-control"
          name="currentUser"
          :placeholder="this.currentUsername"
        /><br/>
      <div v-if="submitted && errors.has('currentUser')" class="alert-danger">{{errors.first('currentUser')}}</div>
      <b><label>Edit current email:</label></b><br/>
      <input
        type="email"
        v-model="newEmail"
        v-validate="'required|min:3|max:40'"
        class="form-control"
        name="currentEmail"
        :placeholder="this.currentEmail"
      /><br/>
      <div v-if="submitted && errors.has('currentEmail')" class="alert-danger">{{errors.first('currentEmail')}}</div>
      <b><label for="address">Edit current address:</label></b><br/>
      <select v-for="capital in capitals" v-model="capitals.selectedOption" :key="capital.options.name" class="form-control" name="address">
        <option v-for="option in capitals.options" :value="option.name" :key="option.name" class="form-control" name="selectedCapital">
          {{option.name}}
        </option>
      </select>
      <b><label for="currentUser">Edit current first name:</label></b><br/>
      <input type="text"
             v-model="newFirstName"
             v-validate="'required|min:3|max:40'"
             class="form-control"
             name="currentFirstName"
             :placeholder="this.currentFirstName"
      /><br/>
      <div v-if="submitted && errors.has('currentFirstName')" class="alert-danger">{{errors.first('currentFirstName')}}</div>
      <b><label for="currentUser">Edit current last name:</label></b><br/>
      <input type="text"
             v-model="newLastName"
             v-validate="'required|min:3|max:40'"
             class="form-control"
             name="currentLastName"
             :placeholder="this.currentLastName"
      /><br/>
      <div v-if="submitted && errors.has('currentLastName')" class="alert-danger">{{errors.first('currentLastName')}}</div>
      <button class="btn btn-success" v-on:click="saveProfile">Save</button>
    </div>
    </div>
    </form>
<div v-if="message" class="alert" :class="successful ? 'alert-success': 'alert-danger'">{{message}}</div>
  </div>
</template>

<script>
import user from '../assets/avatar.jpg'
import CapitalsService from '../services/capitals-service'
import EditUser from '../models/editUser'
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
      currentLastName: '',
      currentFirstName: '',
      capitals: [
        {
          options: [],
          selectedOption: ''
        }
      ],
      newUsername: '',
      newEmail: '',
      newAddress: '',
      newFirstName: '',
      newLastName: '',
      message: '',
      submitted: false,
      successful: false,
      user1: new EditUser('', '', '', '', '', '')
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
      this.newUsername = JSON.parse(localStorage.getItem('user')).username
      this.newEmail = JSON.parse(localStorage.getItem('user')).email
      this.newFirstName = JSON.parse(localStorage.getItem('user')).first_name
      this.newLastName = JSON.parse(localStorage.getItem('user')).last_name
    },
    saveProfile () {
      this.message = ''
      this.submitted = true
      this.$validator.validate().then(isValid => {
        if (isValid) {
          var id = JSON.parse(localStorage.getItem('user')).id
          this.user1.id = id.toString()
          this.user1.username = this.newUsername
          this.user1.email = this.newEmail
          this.user1.address = this.capitals.selectedOption
          this.user1.first_name = this.newFirstName
          this.user1.last_name = this.newLastName

          this.$store.dispatch('auth/editProfile', this.user1).then(
            data => {
              setTimeout(function () { alert(data) }, 800)
              this.successful = true
              this.$store.dispatch('auth/logout')
              this.$router.push('/').catch(() => {})
            },
            error => {
              this.message =
                (error.response && error.response.data) ||
              error.message ||
              error.toString()
              this.successful = false
            })
        }
      })
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
