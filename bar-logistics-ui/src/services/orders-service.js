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
}

export default new OrdersService()
