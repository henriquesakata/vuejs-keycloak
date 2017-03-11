<template>
  <div>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="http://www.rockt.com.br" target="_blank">
            <img alt="Brand" src="../assets/rockt_color.png">
          </a>
        </div>
        <ul class="nav navbar-nav" v-if="auth.authenticated">
          <router-link to="/" tag="li" active-class="active" exact><a>Home</a></router-link>
          <router-link to="/request" tag="li" active-class="active" exact><a>Request</a></router-link>
          <router-link to="/user" tag="li" active-class="active" exact v-if="hasRole('user')"><a>User</a></router-link>
          <router-link to="/admin" tag="li" active-class="active" exact v-if="hasRole('admin')"><a>Admin</a></router-link>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li v-if="auth.authenticated"><a href="#"><span class="glyphicon glyphicon-user"></span> {{ auth.idTokenParsed.name }}</a></li>
          <li v-if="auth.authenticated"><a @click="logout" onmouseover="" style="cursor: pointer;"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import * as securityTypes from '../store/modules/security/types'
import security from './security'

export default {
  name: 'header',
  computed: {
    ...mapGetters({
      auth: securityTypes.SECURITY_AUTH
    })
  },
  methods: {
    hasRole (role) {
      return security.roles(role)
    },
    logout () {
      security.logout()
    }
  }
}
</script>
