import axios from 'axios'

import security from '../../../components/security'

import * as types from './types'

export default {
  [types.SECURITY_AUTH] (state, keycloakAuth) {
    state.auth = keycloakAuth
    axios.defaults.headers.common = security.header()
  }
}
