<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>{{currentUser.first_name}} {{currentUser.last_name }}</strong>
      </h3>
    </header>
    <p>
      <strong>Username:</strong>
      {{currentUser.username }}
    </p>
    <p>
      <strong>Email:</strong>
      {{currentUser.email}}
    </p>
    <p>
      <strong>Address:</strong>
      {{currentUser.address}}
    </p>
    <ul>
      <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
    </ul>
  </div>
</template>

<script>
export default {
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
