import Keycloak from 'keycloak-js'
import store from '../../store'

let keycloakAuth = new Keycloak('/static/keycloak.json')

const loadData = () => {
  store.dispatch('authLogin', keycloakAuth)
}

const reloadData = () => {
  keycloakAuth.updateToken(10)
    .success(loadData)
    .error(() => {
      console.log('Failed to load data')
    })
}

export default () => {
  keycloakAuth.init({ onLoad: 'login-required' })
    .success(reloadData)
    .error(() => {
      console.log('failed to login')
    })
}
