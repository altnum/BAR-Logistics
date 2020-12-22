import axios from 'axios'

const API_URL = 'http://localhost:8080/api/test/all/capitals'

class CapitalsService {
  getAllCapitals () {
    return axios.get(API_URL + '/all')
  }
}

export default new CapitalsService()
