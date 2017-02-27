import * as Keycloak from 'keycloak-js'

let keycloak = Keycloak('/static/keycloak.json')
window['_keycloak'] = keycloak

export default {

  loadData () {
    console.log('[keycloak.loadData] ' + JSON.stringify(keycloak))
  },

  reloadData () {
    window['_keycloak'].updateToken(10)
      .success(this.loadData())
      .error(function () {
        console.log('Failed to load data. User is logged out.')
      })
  },

  init () {
    window['_keycloak'].init({ onLoad: 'login-required' })
      .success(this.reloadData())
      .error(function (errorData) {
        console.log('Failed to load data. Error: ' + JSON.stringify(errorData))
      })
  }

}
