<script setup>
defineProps(['user'])
import '../../../public/css/bootstrap.min.css'
</script>

<template>
  <!-- 用户信息组件 -->
  <div class="user_info" v-if="user == null">
    <img src="/img/author.png" width="200" height="200">
    <h3>请先登录</h3>
  </div>
  <div class="user_info" v-else="user == null">
    <img :src="user.authorImg" width="200" height="200">
    <h3>{{user.username}}</h3>
  </div>
</template>
<style>
.user_info{
  margin-top: 18px;
  text-align: center;
  border: 1px solid #adabab;
  border-radius: 8px;
  height: 300px;
}
</style>