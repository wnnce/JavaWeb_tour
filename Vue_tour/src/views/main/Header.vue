<script setup>
import '../../../public/css/bootstrap.min.css'
defineProps(['user','signOut','keyWord'])
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid container">
      <a class="navbar-brand" href="/">旅游网</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav offset-6" v-if="user == null">
          <a class="nav-link" aria-current="page" href="#/login">登录</a>
          <a class="nav-link" href="#/sign">注册</a>
        </div>
        <div class="navbar-nav offset-6" v-else="user == null">
          <span class="nav-link" aria-current="page">欢迎：{{ user.username }}</span>
          <span class="nav-link sign_out" @click="signOut">退出</span>
        </div>
      </div>
      <div class="d-flex">
        <input class="form-control me-2" placeholder="搜 索" v-model="value" @keyup="clear">
        <button class="btn btn-outline-success w-25" @click="search" :disabled="value === ''">搜 索</button>
      </div>
    </div>
  </nav>
</template>
<script>
import bus from 'vue3-eventbus'
export default {
  data(){
    return{
      value: ''
    }
  },
  methods:{
    //向事件总线传入事件 参数为搜索的值
    search(){
      bus.emit('search', this.value)
    },
    //传入事件 清空搜索值
    clear(){
      if (this.value === ''){
        bus.emit('search', undefined)
      }
    }
  },
  mounted() {
    if(this.keyWord !== undefined){
      this.value = this.keyWord
    }
  }
}
</script>
<style>
.sign_out:hover{
  cursor: pointer;
}
</style>