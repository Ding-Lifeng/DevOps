import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/todolist',
    name: 'todolist',
    component: () => import('../views/todoListView.vue')
  },
  {
    path: '/add',
    name: 'about',
    component: () => import('../views/AddView.vue')
  },
  {
    path: '/add_todo',
    name: 'add_todo',
    component: () => import('../views/AddtodoView.vue')
  },
  {
    path: '/edit_todo',
    name: 'edit_todo',
    component: () => import('../views/EdittodoView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
