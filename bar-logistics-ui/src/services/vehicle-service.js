import axios from 'axios'

const API_URL = 'http://localhost:8080/api/test/user/vehicle'

class VehicleService {
  getExample (volume) {
    return axios.get(API_URL + '/example', { params: { volume: volume } })
  }
}

export default new VehicleService()
