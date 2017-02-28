import * as types from './types'

export default {
  authLogin ({ commit }, keycloakAuth) {
    commit(types.SECURITY_AUTH, keycloakAuth)
  },
  authLogout ({ commit }) {
    commit(types.SECURITY_AUTH)
  }
}
