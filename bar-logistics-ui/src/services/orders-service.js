import axios from 'axios'
import authHeader from '../services/auth-header'

const API_URL = 'http://localhost:8080/api'

class OrdersService {
  getAllOrders () {
    return axios.get(API_URL + '/admin/orders/all', {
      headers: authHeader()
    })
  }

  // eslint-disable-next-line camelcase
  getOrderIdDetails (order_id) {
    return axios.get(API_URL + '/admin/orders/all/detailed', {
      params: {
        order_id
      },
      headers: authHeader()
    })
  }

  // eslint-disable-next-line camelcase
  getOrderVolume (order_id) {
    return axios.get(API_URL + '/admin/orders/getordervolume', {
      params: {
        order_id
      },
      headers: authHeader()
    })
  }

  // eslint-disable-next-line camelcase
  getOrderPartsPrice (part_id, quantity) {
    return axios.get(API_URL + '/user/orders/getorderprice', {
      params: {
        part_id: part_id, quantity: quantity
      },
      headers: authHeader()
    })
  }

  // eslint-disable-next-line camelcase
  getCurrUserOrders (userId) {
    return axios.get(API_URL + '/user/orders/myyorders', {
      params: {
        userId
      },
      headers: authHeader()
    })
  }

  paginateCurrUserOrders (currentPage, perPage, userId) {
    return axios.get(API_URL + '/user/orders/myorders', {
      params: {
        currentPage: currentPage,
        perPage: perPage,
        userId: userId
      },
      headers: authHeader()
    })
  }

  submitOrder (username, parts, price) {
    return axios.post(API_URL + '/user/orders/save', null, {
      params: {
        username,
        parts,
        price
      },
      headers: authHeader()
    })
  }

  orderInProcess (orderId, vehicleId) {
    return axios.post(API_URL + '/admin/orders/adminSend', null, {
      params: { orderId, vehicleId },
      headers: authHeader()
    })
  }

  orderDelivered (orderId) {
    return axios.post(API_URL + '/admin/orders/delivered', null, {
      params: { orderId },
      headers: authHeader()
    })
  }

  getOrdersPage (currentPage, perPage) {
    return axios.get(API_URL + '/admin/orders/search/pages', {
      params: { currentPage: currentPage, perPage: perPage },
      headers: authHeader()
    })
  }

  getMyOrdersPage (currentPage, perPage) {
    return axios.get(API_URL + '/admin/orders/search/pages', { params: { currentPage: currentPage, perPage: perPage }, headers: { 'Access-Control-Allow-Origin': '*' } })
  }
}

export default new OrdersService()
