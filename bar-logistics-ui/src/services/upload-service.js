import axios from 'axios'

export default function (url, file, name) {
  const formData = new FormData()
  formData.append(name, file, 'photo')
  return axios.post(url, formData)
}
