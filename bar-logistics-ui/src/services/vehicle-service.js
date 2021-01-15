import axios from 'axios'
import authHeader from '../services/auth-header'

const API_URL = 'http://localhost:8080/api/test/user/vehicle'

class VehicleService {
  getExample (volume) {
    return axios.get(API_URL + '/example', { params: { volume: volume }, headers: authHeader() })
  }

  getAvailableVehicles () {
    return axios.get(API_URL + '/free', { headers: authHeader() })
  }
}

export default new VehicleService()
