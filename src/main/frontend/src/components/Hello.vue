<template>
  <div class="container">
    <h3><p v-if="auth">Hello {{ auth.idTokenParsed.name }}</p></h3>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import * as securityTypes from '../store/modules/security/types'

export default {
  name: 'hello',
  computed: {
    ...mapGetters({
      auth: securityTypes.SECURITY_AUTH
    })
  }
}
</script>
