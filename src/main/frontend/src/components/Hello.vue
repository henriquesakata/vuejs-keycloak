<template>
  <div>
    <p v-if="auth">{{ auth.idTokenParsed.name }}</p>
    <button @click="logout">Logout</button>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import * as securityTypes from '../store/modules/security/types'
import security from './security'

export default {
  name: 'hello',
  computed: {
    ...mapGetters({
      auth: securityTypes.SECURITY_AUTH
    })
  },
  methods: {
    logout () {
      security.logout()
    }
  }
}
</script>
