<template>
  <div class="container">
    <h3><p v-if="auth">Hello {{ auth.idTokenParsed.name }}</p></h3>
    <br><br>
    <table class="table table-striped">
      <thead>
      </thead>
      <tbody>
        <tr>
          <td><b>Username:</b></td>
          <td>{{ auth.idTokenParsed.preferred_username }}</td>
        </tr>
        <tr>
          <td><b>Given Name:</b></td>
          <td>{{ auth.idTokenParsed.given_name }}</td>
        </tr>
        <tr>
          <td><b>Family Name:</b></td>
          <td>{{ auth.idTokenParsed.family_name }}</td>
        </tr>
        <tr>
          <td><b>E-mail:</b></td>
          <td>{{ auth.idTokenParsed.email }}</td>
        </tr>
      </tbody>
    </table>
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
