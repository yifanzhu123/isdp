import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Category from '@/views/Category'
import Product from '@/views/Product'
import Layout from '@/layout'
import Login from '@/views/Login'
import Test from '@/views/Test'
Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/test',
    name: 'test',
    component: Test
  },
  {
    path: '/',
    component: Layout,
    redirect:'/home',
    children:[
      {
        path: '/home',
        name: 'home',
        component: HomeView,
        meta: { title: '首页', access: 0, affix: true }
      },
    ] 
  },
  {
        path: '/about',
       component:Layout,
       children:[
         {
           path:"/about",
           name:"about",
           component: () => import('../views/AboutView.vue'),
           meta: { title: '关于', access: 0 }
         }
       ]
        
      },
     /*{
        path: 'category',
        name: 'Category',
        component: Category,
        meta: { title: '类别'}
      },*/
  {
    path: '/product',
    component: Layout,
    
    meta: { title: '产品管理' },
    children: [{
      path: '/product/category',
      name: 'category',
      component:Category,
      meta: { title: '类别', access: 0, affix: true }
    },
    {
      path:'/product/item',
      name:'product',
      component:Product,
      meta: { title: '产品', access: 0, affix: true }
    }

   ]
  },
  {
    path: '/sale',
    component: Layout,
    redirect: '/sale/order',
    meta: { title: '销售管理' },
    children: [{
      path: '/sale/order',
      name: 'Sale',
      component: () => import('@/views/Sale.vue'),
      meta: { title: '订单', access: 0 }
    }]
  },


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
