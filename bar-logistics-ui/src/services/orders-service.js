import axios from 'axios'

const API_URL = 'http://localhost:8080/orders'

class OrdersService {
  getAllOrders () {
    return axios.get(API_URL + '/all')
  }
}

export default new OrdersService()
