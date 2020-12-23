<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent="handleRegister">
        <div v-if="!successful">
          <div class="form-group">
            <label for="first_name">First name</label>
            <input
              v-model="user.first_name"
              v-validate="'required|min:3|max:30'"
              type="text"
              class="form-control"
              name="first_name"
            />
            <div
              v-if="submitted && errors.has('first_name')"
              class="alert-danger"
            >{{errors.first('first_name')}}</div>
          </div>
          <div class="form-group">
            <label for="last_name">Last name</label>
            <input
              v-model="user.last_name"
              v-validate="'required|min:3|max:30'"
              type="text"
              class="form-control"
              name="last_name"
            />
            <div
              v-if="submitted && errors.has('last_name')"
              class="alert-danger"
            >{{errors.first('last_name')}}</div>
          </div>
          <label for="address">Address</label>
          <select v-for="capital in capitals" v-model="capitals.selectedOption" :key="capital.options.name" class="form-control" name="address">
            <option v-for="option in capitals.options" :value="option.name" :key="option.name" class="form-control" name="selectedCapital">
              {{option.name}}
            </option>
          </select>
          <div class="form-group">
            <label for="username">Username</label>
            <input
              v-model="user.username"
              v-validate="'required|min:3|max:20'"
              type="text"
              class="form-control"
              name="username"
            />
            <div
              v-if="submitted && errors.has('username')"
              class="alert-danger"
            >{{errors.first('username')}}</div>
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input
              v-model="user.email"
              v-validate="'required|email|max:50'"
              type="email"
              class="form-control"
              name="email"
            />
            <div
              v-if="submitted && errors.has('email')"
              class="alert-danger"
            >{{errors.first('email')}}</div>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input
              v-model="user.password"
              v-validate="'required|min:6|max:40'"
              type="password"
              class="form-control"
              name="password"
            />
            <div
              v-if="submitted && errors.has('password')"
              class="alert-danger"
            >{{errors.first('password')}}</div>
          </div>
          <div class="form-group">
            <label for="password1">Confirm password</label>
            <input
              v-model="password1"
              v-validate="'required'"
              type="password"
              class="form-control"
              name="password1"
            />
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block">Sign Up</button>
          </div>
        </div>
      </form>

      <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >{{message}}</div>
    </div>
  </div>
</template>

<script>
import User from '../models/user'
import CapitalsService from '../services/capitals-service.js'

export default {
  name: 'Register',
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
      capitals: [
        {
          options: [],
          selectedOption: ''
        }
      ],
      user: new User('', '', '', '', '', ''),
      password1: '',
      submitted: false,
      successful: false,
      message: ''
    }
  },
  computed: {
    loggedIn () {
      return this.$store.state.auth.status.loggedIn
    }
  },
  mounted () {
    if (this.loggedIn) {
      this.$router.push('/profile')
    }
  },
  methods: {
    setData (response) {
      this.capitals.options = response.data
      this.capitals.selectedOption = this.capitals.options[0].name
    },
    checkPass (current) {
      return this.user.password === current
    },
    handleRegister () {
      this.message = ''
      this.submitted = true
      this.$validator.validate().then(isValid => {
        if (isValid) {
          if (this.checkPass(this.password1)) {
            this.user.capitals = this.capitals.selectedOption
            this.$store.dispatch('auth/register', this.user).then(
              data => {
                this.message = data.message
                this.successful = true
              },
              error => {
                this.message =
                  (error.response && error.response.data) ||
                  error.message ||
                  error.toString()
                this.successful = false
              }
            )
          } else {
            this.message = 'The passwords do not match!'
            this.successful = false
          }
        }
      })
    }
  }
}
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>
