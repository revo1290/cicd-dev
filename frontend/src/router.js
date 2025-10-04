import { createRouter, createWebHistory } from 'vue-router'
import Login from './views/Login.vue'
import Register from './views/Register.vue'
import Home from './views/Home.vue'
import axios from 'axios'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    meta: { requiresAuth: true }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

/*
router.beforeEach(async (to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    try {
      await axios.get('/api/users/me', { withCredentials: true });
      next();
    } catch (error) {
      next({ name: 'Login' });
    }
  } else {
    next();
  }
});
*/

export default router
