import axios from 'axios'
import authHeader from '../services/auth-header'

const API_URL = 'http://localhost:8080/api'

class PartsService {
  getAllParts () {
    return axios.get(API_URL + '/user/parts/all',
      { headers: authHeader() })
  }

  getPartsPage (filters, currentPage, perPage) {
    return axios.get(API_URL + '/user/parts/search/pages',
      { params: { currentPage: currentPage, perPage: perPage, part_num: filters.part_num, part_name: filters.part_name }, headers: authHeader() })
  }

  // eslint-disable-next-line camelcase
  getPartsById (part_num) {
    return axios.get(API_URL + '/user/parts/search/part_num', { params: { part_num: part_num }, headers: authHeader() })
  }

  getCart (shopList) {
    return axios.get(API_URL + '/user/parts/cart', { params: { shopList: shopList }, headers: authHeader() })
  }

  // eslint-disable-next-line camelcase
  removePartsById (part_num) {
    return axios.post(API_URL + '/admin/parts/remove', null, { params: { part_num: part_num }, headers: authHeader() })
  }

  getPartsLocation () {
    return axios.get(API_URL + '/partslocation/all', { headers: authHeader() })
  }

  // eslint-disable-next-line camelcase
  addPart (part_name, locations, part_price, part_volume) {
    return axios.post(API_URL + '/admin/parts/save', null, { params: { part_name: part_name, location: locations, price: part_price, volume: part_volume }, headers: authHeader() })
  }
}

export default new PartsService()
