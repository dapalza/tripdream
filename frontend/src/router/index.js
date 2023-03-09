import { createRouter, createWebHistory } from "vue-router";
// import { getCurrentInstance,computed } from 'vue';

import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();

import MainView from "../components/HelloWorld";
import JoinView from "../views/JoinView";
import LoginView from "../views/LoginView";

const routes = [
  { path: "/", name: "main", component: MainView },
  { path: "/join", name: "join", component: JoinView },
  { path: "/login", name: "login", component: LoginView },
  {
    path: "/sign-up",
    name: "sign-up",
    component: () => import("@/views/SignUpView.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 라우터에 들어가기 전에 실행되는 가드
router.beforeEach((to, from, next) => {
  //경로가 같으면 실행?(새로고침 or 링크를 재입력?)
  if (to.path == from.path) {
    next();
  } else if (to.path == "/login" || to.path == "/join") {
    if (cookies.isKey("login.refreshToken")) {
      alert(from.path + "이미 로그인 하셨습니다.");
      window.localStorage.setItem("loginRefresh", from.path);
      next();
    } else {
      next();
    }
  } else {
    next();
  }
});

// router.beforeResolve((to,from,next)=>{
// 컴포넌트 가드가 끝난 후에 실행
// });

// 라우터 이동이 해결된 후에 실행되는 가드
router.afterEach((to, from) => {
  //현재 로그인/로그아웃을 상단 헤드에 놓아서 router영역의 밖에 존재
  //로그인시 토큰과 회원 정보는 저장이 되지만 반영이 안됨
  //새로고침 한번을 해주거나 헤드 부분을 re-render를 해주어야하지만
  //re-render방식을 몰라서 해당 부분이 로그인 후 처음 발생하는 페이지
  //이동일 경우, 새로고침을 강제로 한번 실행해 준다.
  const tmp = window.localStorage.getItem("loginRefresh");
  //true일 경우 로그인 후 해당 router로 처음 이동했으므로 새로고침
  if (tmp == "true") {
    window.localStorage.removeItem("loginRefresh");
    window.location.reload();
    //해당 부분은 만약 로그인을 했는데 로그인/회원가입에 강제적으로
    //접근을 시도할 경우 이동전 링크를 tmp에 담아주고 이전 경로를 없애고
    //tmp에 저장된 경로로 보내준다. main => login 일경우 main으로
  } else if (tmp !== null) {
    window.localStorage.removeItem("loginRefresh");

    router.push(tmp);
  }
  console.log(to.path);
  console.log(from.path);
});

export { router };
