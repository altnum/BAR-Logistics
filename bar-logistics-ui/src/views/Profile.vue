<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>Welcome, {{currentUser.first_name}} {{currentUser.last_name }}!</strong>
      </h3>
    </header>
    <img :src="user" width="500" alt="image" align="center" class="imageUser"/>
    <h3 class="profileInfo">Your profile info:</h3>
    <div class="info">
    <p>
      <strong>Username:</strong>
    <br/>
      {{currentUser.username }}
    </p>
    <p>
      <strong>Email:</strong>
      <br/>
      {{currentUser.email}}
    </p>
    <p>
      <strong>Address:</strong>
      <br/>
      {{currentUser.address.name}}
    </p>
    <strong>User role:</strong>
    <ul>
      <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
    </ul>
  </div>
  </div>
</template>

<script>
import user from '../assets/avatar.jpg'
export default {
  data () {
    return {
      user: user
    }
  },
  name: 'Profile',
  computed: {
    currentUser () {
      return this.$store.state.auth.user
    }
  },
  mounted () {
    if (!this.currentUser) {
      this.$router.push('/login')
    }
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
</style>
