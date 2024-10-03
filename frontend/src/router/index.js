import { createRouter, createWebHistory } from 'vue-router'
import ClientSection from '../views/ClientSection.vue'
import OperatorSection from '../views/OperatorSection.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: OperatorSection
    },
    {
      path: '/client',
      name: 'client',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/ClientSection.vue')
    }
  ]
})

export default router
