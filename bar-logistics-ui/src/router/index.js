import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import PartDetails from '../views/PartDetails.vue'
import Cart from '../views/Cart.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import OrdersDetails from '../views/OrdersDetails'
import EditParts from '../views/EditParts'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path: '/orders',
    name: 'Orders',
    component: () => import('../views/Orders.vue')
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/parts',
    name: 'Parts',
    component: () => import(/* webpackChunkName: "about" */ '../views/Parts.vue')
  },
  {
    path: '/parts_details',
    name: 'partsDetails',
    component: PartDetails
  },
  {
    path: '/orders_details',
    name: 'ordersDetails',
    component: OrdersDetails
  },
  {
    path: '/cart',
    name: 'cart',
    component: Cart
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/register',
    component: Register
  },
  {
    path: '/profile',
    name: 'profile',
    // lazy-loaded
    component: () => import('../views/Profile.vue')
  },
  {
    path: '/admin',
    name: 'admin',
    // lazy-loaded
    component: () => import('../views/BoardAdmin.vue')
  },
  {
    path: '/user',
    name: 'user',
    // lazy-loaded
    component: () => import('../views/BoardUser.vue')
  },
  {
    path: '/editparts',
    name: 'editparts',
    component: EditParts
  }
]

const router = new VueRouter({
  routes
})

export default router

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register', '/home', '/about', '/']
  const authRequired = !publicPages.includes(to.path)
  // eslint-disable-next-line no-unused-vars
  const adminPages = ['/orders', '/editparts', '/admin']
  const loggedIn = localStorage.getItem('user')

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next('/login')
  } else if (adminPages.includes(to.path) && loggedIn && JSON.parse(localStorage.getItem('user')).roles[0] !== 'ROLE_ADMIN') {
    next('/home')
  } else {
    next()
  }
})
