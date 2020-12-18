import axios from 'axios'

const API_URL = 'http://localhost:8080/parts'

class PartsService {
  getAllParts () {
    return axios.get(API_URL + '/all')
  }

  getPartsPage (filters, currentPage, perPage) {
    return axios.get(API_URL + '/search/pages', { params: { currentPage: currentPage, perPage: perPage, part_num: filters.part_num, part_name: filters.part_name } })
  }

  // eslint-disable-next-line camelcase
  getPartsById (part_num) {
    return axios.get(API_URL + '/search/part_num', { params: { part_num: part_num } })
  }

  getCart (shopList) {
    return axios.get(API_URL + '/cart', { params: { shopList: shopList } })
  }
}

export default new PartsService()
