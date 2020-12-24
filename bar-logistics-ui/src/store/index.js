import Vue from 'vue'
import Vuex from 'vuex'

import { auth } from './auth.module'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    cart: []
  },
  getters: {
    countParts: state => {
      return state.cart.length
    }
  },
  mutations: {
    ADD_PART_TO_CART: (state, part) => {
      state.cart.push(part)
    },
    CLEAR_CART: (state) => {
      state.cart = []
    }
  },
  actions: {
  },
  modules: {
    auth
  }
})
