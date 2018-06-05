import Vue from 'vue'
import Vuex from 'vuex'
import * as types from './mutation-types'
import { asyncRouterMap, constantRouterMap } from '@/router'
var cookie = require('cookies-js')
// import * as actions from './actions'

Vue.use(Vuex)

// 应用初始状态
const state = {
  token: cookie.get('x-lt-token'),
  expires: cookie.get('expires'),
  roles: [],
  routers: constantRouterMap,
  addRouters: [],
  sidebar: {
    opened: !+cookie.get('sidebarStatus')
  },
  visitedViews: [],
  name: '',
  avatar: ''
}

// actions
const actions = {
  isLogin ({ commit, state }, isLogin) {
    state.isLogin = isLogin
  },
  ToggleSideBar ({ commit })  {
    commit('TOGGLE_SIDEBAR')
  },
  addVisitedViews ({ commit }, view) {
    commit('ADD_VISITED_VIEWS', view)
  },
  delVisitedViews ({ commit }, view) {
    commit('DEL_VISITED_VIEWS', view)
  }
}

// 定义所需的 mutations
const mutations = {
    // INCREMENT(state) {
    //     state.count++
    // },
    // DECREMENT(state) {
    //     state.count--
    // }
  [types.SET_TOKEN] (state, token) {
    state.token = token
  },
  [types.SET_EXPIRES] (state, expires) {
    state.expires = expires
  },
  [types.SET_ROLES] (state, roles) {
    state.roles = roles
  },
  [types.SET_ROUTERS] (state, routers) {
    state.addRouters = routers
    state.routers = constantRouterMap.concat(routers)
  },
  [types.SET_NAME] (state, name) {
    state.name = name
  },
  [types.SET_AVATAR] (state, avatar) {
    state.avatar = avatar
  },
  [types.TOGGLE_SIDEBAR] (state) { 
    if (state.sidebar.opened) {
      cookie.set('sidebarStatus', 1)
    } else {
      cookie.set('sidebarStatus', 0)
    }
    state.sidebar.opened = !state.sidebar.opened
  },
  [types.ADD_VISITED_VIEWS] (state, view) {
    if (state.visitedViews.some(v => v.path === view.path)) return
    state.visitedViews.push({ name: view.name, path: view.path, componentName:view.componentName })
  },
  [types.DEL_VISITED_VIEWS] (state, view) {
    let index
    for (const [i, v] of state.visitedViews.entries()) {
      if (v.path === view.path) {
        index = i
        break
      }
    }
    state.visitedViews.splice(index, 1)
  }
}

// 创建 store 实例
export default new Vuex.Store({
    actions,
    state,
    mutations
})