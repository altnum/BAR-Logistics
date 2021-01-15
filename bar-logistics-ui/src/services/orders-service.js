import axios from 'axios'

const API_URL = 'http://localhost:8080/api/test'

class OrdersService {
  getAllOrders () {
    return axios.get(API_URL + '/admin/orders/all')
  }

  // eslint-disable-next-line camelcase
  getOrderIdDetails (order_id) {
    return axios.get(API_URL + '/admin/orders/all/detailed', {
      params: {
        order_id
      }
    })
  }

  // eslint-disable-next-line camelcase
  getOrderVolume (order_id) {
    return axios.get(API_URL + '/admin/orders/getordervolume', {
      params: {
        order_id
      },
      headers: { 'Access-Control-Allow-Origin': '*' }
    })
  }

  // eslint-disable-next-line camelcase
  getOrderPartsPrice (part_id, quantity) {
    return axios.get(API_URL + '/user/orders/getorderprice', {
      params: {
        part_id: part_id, quantity: quantity
      },
      headers: { 'Access-Control-Allow-Origin': '*' }
    })
  }

  // eslint-disable-next-line camelcase
  getCurrUserOrders (userId) {
    return axios.get(API_URL + '/user/orders/myorders', {
      params: {
        userId
      }
    })
  }

  submitOrder (username, parts, price) {
    return axios.post(API_URL + '/user/orders/save', null, {
      params: {
        username,
        parts,
        price
      }
    })
  }

  orderInProcess (orderId, vehicleId) {
    return axios.post(API_URL + '/admin/orders/adminSend', null, { params: { orderId, vehicleId } })
  }

  orderDelivered (orderId) {
    return axios.post(API_URL + '/admin/orders/delivered', null, { params: { orderId } })
  }

  getOrdersPage (currentPage, perPage) {
    return axios.get(API_URL + '/admin/orders/search/pages', { params: { currentPage: currentPage, perPage: perPage }, headers: { 'Access-Control-Allow-Origin': '*' } })
  }
}

export default new OrdersService()
