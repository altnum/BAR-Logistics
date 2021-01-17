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

  saveProfile (id, username, email, address, firstname, lastname) {
    return axios.post(API_URL + 'user/editProfile/save', { id: id, username: username, email: email, address: address, firstname: firstname, lastname: lastname }, { headers: authHeader() })
  }
}

export default new UserService()
