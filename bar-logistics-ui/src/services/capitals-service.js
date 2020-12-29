import axios from 'axios'

const API_URL = 'http://localhost:8080/api/test'

class CapitalsService {
  getAllCapitals () {
    return axios.get(API_URL + '/all/capitals/all')
  }

  getCapitalByName (name) {
    return axios.get(API_URL + '/user/capitals/search', { params: { name: name } })
  }
}

export default new CapitalsService()
