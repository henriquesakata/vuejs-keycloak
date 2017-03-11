import Keycloak from 'keycloak-js'
import store from '../../store'

let keycloakAuth = new Keycloak('/static/keycloak.json')

export default (next, roles) => {
  keycloakAuth.init({ onLoad: 'login-required' })
    .success(() => {
      keycloakAuth.updateToken(10)
        .success(() => {
          store.dispatch('authLogin', keycloakAuth)
          if (roles) {
            if (keycloakAuth.hasRealmRole(roles[0])) {
              next()
            } else {
              next({ name: 'Unauthorized' })
            }
          } else {
            next()
          }
        })
    })
    .error(() => {
      console.log('failed to login')
    })
}
