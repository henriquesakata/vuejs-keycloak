import * as types from './types'

export default {
  [types.SECURITY_AUTH] (state, keycloakAuth) {
    state.auth = keycloakAuth
  }
}
