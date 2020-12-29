<template>
  <div>
    {{ result }}
  </div>
</template>

<script>
import PartsService from '../services/parts-service'

export default {
  name: 'PartDetails',
  data () {
    return {
      result: ''
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

<style scoped>

</style>
