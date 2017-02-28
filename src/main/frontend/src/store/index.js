import Vue from 'vue'
import Vuex from 'vuex'

import mutations from './mutations'
import actions from './actions'
import getters from './getters'

import security from './modules/security'

Vue.use(Vuex)

const state = {
}

export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  modules: {
    security
  }
})
