import {createRouter, createWebHistory} from 'vue-router'
import {useCookies} from "vue3-cookies";
const {cookies} = useCookies();

import FirstView from '../views/FirstView'
import MainView from '../components/HelloWorld'
import JoinView from '../views/JoinView'
import LoginView from '../views/LoginView'

const routes = [
  {path:'/1',name:"test", component:FirstView},
  {path:'/',name:"main",component:MainView},
  {path:'/join',name:"join", component:JoinView},
  {path:'/login',name:"login", component:LoginView}
]

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach(function(to,from,next){
  const token = cookies.get("login.accessToken");
  if(to.path=='/login' || to.path=='/join'){
    //로그인 했는데 로그인 페이지나 회원가입 페이지에 접근하려는 시도가 있을 경우 잘못된 접근표시
    if(token != null){
      alert("잘못된 접근입니다.");
      // 경고후 이동 페이지\
      //지금은 홈이지만 경고 페이지나 이전 페이지로 돌아게 할 수 있다.
      // 이전 페이지는 from.path로 교체해주면 해결 될듯?
      //next('/'); 
      next(from.path);
    }
    else{
      next();
    }
  }
  else {
    next();
  }
});

export {router}