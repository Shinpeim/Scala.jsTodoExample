import Vue from 'vue'

import Application from './components/Application.vue'

const opts = {
    template: "<application></application>",
    components:{
        Application
    }
};

const app = new Vue(opts);
app.$mount("#main");
