<script setup>
import '../../public/css/bootstrap.min.css'
</script>
<template>
  <div class="container">
    <div class="row mt-5">
      <div class="col-md-4 offset-4">
        <div class="mt-4 text-center">
          <h4>新用户注册</h4>
        </div>
        <div class="mt-4" id="main">
          <div class="input-group mb-3 mt-2">
            <input type="text" class="form-control" placeholder="Username" v-model="username">
          </div>
          <div class="input-group mb-3 mt-2">
            <input type="password" class="form-control" placeholder="Password" v-model="passwd">
          </div>
          <p class="text-warning" v-if="bl">两次密码不一致</p>
          <div class="input-group mb-3 mt-2">
            <input type="password" class="form-control" placeholder="RePassword" v-model="rePasswd" @keyup="isPasswd">
          </div>
          <div class="input-group mb-3 mt-3">
            <input class="btn btn-success w-100" type="button" value="立即注册" @click="sign" :disabled="btn">
          </div>
          <div class="offset-8">
            <a href="#/sign" >已有账号?去登录</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

//允许axios请求携带Cookie
axios.defaults.withCredentials=true

export default {
  data() {
    return {
      username: '',
      passwd: '',
      rePasswd: '',
      bl: false,
      btn: true
    }
  },
  methods: {
    //判断
    isPasswd() {
      this.bl = this.rePasswd !== this.passwd;
      if (!this.bl) {
        this.btn = false
      }
    },
    sign() {
      axios({
        method: 'post',
        url: 'http://localhost:8088/tour/sign',
        params: {
          username: this.username,
          passwd: this.passwd
        },
      }).then(value => {
        const data = value.data
        //注册成功跳转到首页
        if (data.code === 100) {
          alert(data.msg)
          window.location.href = "/"
        } else {
          alert('请求错误，请重试')
        }
      })
    }
  }
}
</script>