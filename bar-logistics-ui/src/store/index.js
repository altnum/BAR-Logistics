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
    },
    REMOVE_PART: (state, part) => {
      state.cart.splice(part, 1)
    },
    TRANSFORM_CART: (state, newCart) => {
      if (newCart) {
        state.cart = newCart
      } else {
        state.cart = []
      }
    }
  },
  actions: {
    removePart: (context, part) => {
      context.commit('REMOVE_PART', part)
    }
  },
  modules: {
    auth
  }
})
