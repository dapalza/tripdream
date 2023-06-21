import { createRouter, createWebHistory } from 'vue-router'
import DashBoardView from '../views/DashBoardView.vue'
import AboutView from '../views/AboutView.vue'
import LoginView from '../views/LoginView.vue'
import RegistView from '../views/RegistView.vue'

const routes = [
  {
    path: '/main',
    name: 'DashBoardView',
    component: DashBoardView
  },
  {
    path: '/',
    name: 'AboutView',
    component: AboutView
  },
  {
    path: '/login',
    name: 'LoginView',
    component: LoginView
  },
  {
    path: '/regist',
    name: 'RegistView',
    component: RegistView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export {router};