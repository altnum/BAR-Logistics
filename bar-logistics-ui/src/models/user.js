export default class User {
  // eslint-disable-next-line camelcase
  constructor (id, username, email, password, first_name, last_name, capitals, roles) {
    this.id = id
    this.username = username
    this.email = email
    this.password = password
    // eslint-disable-next-line camelcase
    this.first_name = first_name
    // eslint-disable-next-line camelcase
    this.last_name = last_name
    this.capitals = capitals
    this.roles = roles
  }
}
