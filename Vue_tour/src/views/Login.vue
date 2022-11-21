<script setup>
import '../../public/css/bootstrap.min.css'
</script>

<template>
  <div class="container">
    <div class="row mt-5">
      <div class="col-md-4 offset-4">
        <div class="text-center w-25 h-25 m-lg-auto">
          <img src="/img/author.png" class="img-fluid" alt="">
        </div>
        <div class="mt-4 text-center">
          <h5>用户登陆</h5>
        </div>
        <div class="mt-3">
          <form id="from">
            <div class="input-group mb-3">
              <input type="text" class="form-control" placeholder="Username" name="username" v-model="username">
            </div>
            <div class="input-group mb-3">
              <input type="password" class="form-control" placeholder="Password" name="password" v-model="passwd">
            </div>
            <div class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="pwdCheck">
              <label class="form-check-label" for="pwdCheck">
                记住密码
              </label>
            </div>
            <div class="input-group mb-3 mt-3">
              <input type="button" class="btn btn-primary w-100" value="登录" @click="login">
            </div>
          </form>
          <div class="offset-10">
            <a href="#/sign">立即注册</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

axios.defaults.withCredentials=true
export default {
  data(){
    return{
      username: '',
      passwd: ''
    }
  },
  methods: {
    //登录
    login(){
      axios({
        method: 'post',
        url: 'http://localhost:8088/tour/login',
        params:{
          username: this.username,
          passwd: this.passwd
        }
      }).then(value => {
        const data = value.data
        console.log(data)
        //登录成功跳转到首页
        if(data.code === 100){
          window.location.href="/"
        }else if(data.code === 1002){
          alert(data.msg)
        }else {
          alert('请求错误，请重试')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>