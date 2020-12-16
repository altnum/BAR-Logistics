import axios from 'axios'

const API_URL = 'http://localhost:8080/parts'

class PartsService {
  getAllParts () {
    return axios.get(API_URL + '/all')
  }
}

export default new PartsService()
