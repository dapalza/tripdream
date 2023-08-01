import {createStore} from "vuex";

import {customer} from "@/store/modules/customer";

const store = createStore({
    modules:{customer},
    
});

export default store;