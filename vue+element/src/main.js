import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import VueRouter from 'vue-router'
import menus from '@/config/menu-config'
import router from './router/index.js'
import axios from 'axios'

Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.prototype.axios = axios

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})

