<template>
  <div class="container">
    <h3>Request</h3>
    <br><br>
    <button class="btn btn-primary" @click="getMessage">Call Rest Service</button>
    <br><br><br>
    <p>{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'request',
  data () {
    return {
      message: ''
    }
  },
  methods: {
    getMessage () {
      axios.get('/api/services/v1/hello')
        .then(response => response.data)
        .then(data => {
          this.message = data.message
        })
    }
  }
}
</script>
