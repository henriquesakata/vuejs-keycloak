import mutations from './mutations'
import actions from './actions'
import getters from './getters'

const state = {
  auth: {
    authenticated: false
  }
}

export default {
  state,
  mutations,
  actions,
  getters
}
