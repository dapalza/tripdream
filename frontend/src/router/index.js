import {createRouter, createWebHistory} from 'vue-router'

import FirstView from '../views/FirstView'
import MainView from '../components/HelloWorld'
import JoinView from '../views/JoinView'
import LoginView from '../views/LoginView'

const routes = [
  {path:'/1',name:"test", component:FirstView},
  {path:'/',name:"main", component:MainView},
  {path:'/join',name:"join", component:JoinView},
  {path:'/login',name:"login", component:LoginView}
]

const router = createRouter({
  history: createWebHistory(),
  routes
});

export {router}