// import {store} from "../store"

export default{
  install(Vue){
    // Vue.config.globalProperties.$store = store;
    //서버 주소
    Vue.config.globalProperties.$getUrl = () =>{
      return "http://localhost:8082";
    },
    Vue.config.globalProperties.$getUrlFront = () =>{
      return "http://localhost:8084";
    },
    //토큰 시간 최소 단위
    Vue.config.globalProperties.$getTokenTime = () =>{
      return 110;
    },
    Vue.config.globalProperties.$setLocalStorage = (key,value)=>{
      localStorage.setItem(key,value);
    }
    Vue.config.globalProperties.$getLocalStorage = (key) =>{
      return localStorage.getItem(key);
    }; // 데이터 조회
    Vue.config.globalProperties.$removeLocalStorage =(key)=>{
      localStorage.removeItem(key);
    }; // 키에 해당되는 데이터 삭제
    Vue.config.globalProperties.$clearLocalStorage =() =>{
      localStorage.clear();
    }; // 저장소 데이터 전체 삭제
    //유효성 검사 규칙
    Vue.config.globalProperties.$getRule = (key) =>{
      if(key == "email"){
        return /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
      }
      else if(key == "password"){
        return /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/; 
      }
      else if(key == "nickname"){
        return /^([a-zA-z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,9}$/;
      }
      return 10
    };
  }
}
