<template>
  <div>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="http://www.rockt.com.br" target="_blank">
            <img alt="Brand" src="../assets/rockt_color.png">
          </a>
        </div>
        <ul class="nav navbar-nav">
          <router-link to="/" tag="li" active-class="active" exact><a>Home</a></router-link>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#"><span class="glyphicon glyphicon-user"></span> {{ auth.idTokenParsed.name }}</a></li>
          <li><a @click="logout" onmouseover="" style="cursor: pointer;"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
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
