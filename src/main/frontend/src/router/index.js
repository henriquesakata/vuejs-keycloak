import Vue from 'vue'
import Router from 'vue-router'
import { sync } from 'vuex-router-sync'

import store from '../store'

import Hello from '../components/Hello'
import Request from '../components/Request'
import Admin from '../components/Admin'
import User from '../components/User'
import NotSecure from '../components/NotSecure'

import security from '../components/security'

Vue.use(Router)

const routes = [
  { path: '/', name: 'Hello', component: Hello, meta: { requiresAuth: true } },
  { path: '/request', name: 'Request', component: Request, meta: { requiresAuth: true } },
  { path: '/admin', name: 'Admin', component: Admin, meta: { requiresAuth: true } },
  { path: '/user', name: 'User', component: User, meta: { requiresAuth: true } },
  { path: '/notsecure', name: 'NotSecure', component: NotSecure }
]

const router = new Router({
  routes,
  mode: 'history'
})

sync(store, router)

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    const auth = store.state.security.auth
    if (!auth.authenticated) {
      security.init()
    }
  }
  next()
})

export default router
