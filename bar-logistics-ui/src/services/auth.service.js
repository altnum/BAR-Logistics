import axios from 'axios'

const API_URL = 'http://localhost:8080/api/auth/'

class AuthService {
  login (user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data))
        }
        return response.data
      })
  }

  logout () {
    localStorage.removeItem('user')
    localStorage.removeItem('cart')
  }

  register (user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password,
      first_name: user.first_name,
      last_name: user.last_name,
      capitals: user.capitals
    })
  }
}

export default new AuthService()
