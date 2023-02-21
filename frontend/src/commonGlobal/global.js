// import {store} from "../store"

export default{
    install(Vue){
        // Vue.config.globalProperties.$store = store;
        //서버 주소
        Vue.config.globalProperties.$getUrl = () =>{
            return "http://localhost:8088"
        },
        //토큰 시간 최소 단위
        Vue.config.globalProperties.$getTokenTime = () =>{
            return 10
        },
        Vue.config.globalProperties.$setLocalStoage = (key,value)=>{
            localStorage.setItem(key,value);
        }
        Vue.config.globalProperties.$getLocalStoage = (key) =>{
            return localStorage.getItem(key);
        }; // 데이터 조회
        Vue.config.globalProperties.$removeLocalStoage =(key)=>{
            localStorage.removeItem(key);
        }; // 키에 해당되는 데이터 삭제
        Vue.config.globalProperties.$clearLocalStoage =() =>{
            localStorage.clear();
        }; // 저장소 데이터 전체 삭제
    }
}
