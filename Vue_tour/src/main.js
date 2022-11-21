import { createApp } from 'vue'
import App from './App.vue'
import ScenicInfo from './views/ScenicInfo.vue'
import Login from './views/Login.vue'
import Sign from './views/Sign.vue'
import * as VueRouter from 'vue-router'
import eventBus from 'vue3-eventbus'

const app = createApp(App)

const routes = [
    {
        path: '/',
        component: App,
        meta: {
            show: true
        }
    },
    {
        path: '/login',
        component: Login,
        meta: {
            show: false
        }
    },
    {
        path: '/sign',
        component: Sign,
        meta: {
            show: false
        }
    },
    {
        name: 'info',
        path: '/info',
        component: ScenicInfo,
        meta: {
            show: false
        },
        props: true
    },
]

const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes,
})
app.use(router)
app.use(eventBus)
app.mount('#app')
