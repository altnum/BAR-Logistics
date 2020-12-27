import axios from 'axios'

const API_URL = 'http://localhost:8080/api/test'

class OrdersService {
  getAllOrders () {
    return axios.get(API_URL + '/admin/orders/all')
  }

  // eslint-disable-next-line camelcase
  getOrderIdDetails (order_id) {
    return axios.get(API_URL + '/admin/orders/all/detailed', null, {
      params: {
        order_id: order_id
      }
    })
  }

  submitOrder (username, parts) {
    return axios.post(API_URL + '/user/orders/save', null, {
      params: {
        username,
        parts
      }
    })
  }
}

export default new OrdersService()
