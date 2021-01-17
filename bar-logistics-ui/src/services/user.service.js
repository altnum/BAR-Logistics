import axios from 'axios'
import authHeader from './auth-header'

const API_URL = 'http://localhost:8080/api/'

class UserService {
  getPublicContent () {
    return axios.get(API_URL + 'all')
  }

  getUserBoard () {
    return axios.get(API_URL + 'user', { headers: authHeader() })
  }

  getAdminBoard () {
    return axios.get(API_URL + 'admin', { headers: authHeader() })
  }

  saveProfile (user) {
    return axios.post(API_URL + 'user/editprofile/save', { id: user.id, username: user.username, email: user.email, address: user.address, first_name: user.first_name, last_name: user.last_name }, { headers: authHeader() })
  }
}

export default new UserService()
