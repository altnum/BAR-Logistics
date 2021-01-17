import axios from 'axios'
import authHeader from './auth-header'

const API_URL = 'http://localhost:8080/api/test/'

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

  saveProfile (username, email, address) {
    return axios.post(API_URL + 'user/editProfile/save', null, { params: { username: username, email: email, address: address, headers: authHeader() } })
  }
}

export default new UserService()
