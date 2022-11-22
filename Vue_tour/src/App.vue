<script setup>
import '../public/css/bootstrap.min.css';
import Header from "@/views/main/Header.vue";
import Item from "@/views/main/Item.vue";
import User from "@/views/main/User.vue";
import Top from "@/views/main/Top.vue";
import Footer from "@/views/main/Footer.vue";
</script>
<template>
<main v-if="$route.meta.show">
  <Header :user="user" :sign-out="signOut" :key-word="keyWord"></Header>
  <div class="container mt-5">
    <div class="row">
      <div class="col-md-10 offset-1">
        <div class="row">
          <div class="col-md-6">
            <Item :scenic-list="scenicList" :user="user"></Item>
          </div>
          <div class="col-md-4 offset-2">
            <User :user="user"></User>
            <Top :scenic-top="scenicTop"></Top>
          </div>
        </div>
        <Footer :page="page" :sum-page="sumPage" :flash-list="getScenicList"></Footer>
      </div>
    </div>
  </div>
</main>
  <router-view v-if="!$route.meta.show"></router-view>
</template>
<script>
import axios from "axios";
import bus from 'vue3-eventbus'
axios.defaults.withCredentials=true
export default {
  data(){
    return{
      //景点列表
      scenicList: '',
      //景点排行榜
      scenicTop: '',
      //用户信息
      user: null,
      //当前页码
      page: null,
      //总页数
      sumPage: null,
      //搜索关键字
      keyWord: undefined
    }
  },
  methods:{
    //退出登录
    signOut(){
      axios.get("http://localhost:8088/tour/signout")
          .then(value => {
            if(value.data.code === 100){
              alert(value.data.msg)
              //清空用户信息
              this.user = null;
            }
          })
    },
    //根据页码获取景点列表
    getScenicList(page){
      let params = '';
      let url = '';
      //判断搜索词是不是为空 为空则访问item接口 否则访问search接口
      if(this.keyWord === undefined){
        params = {page: page}
        url = 'http://localhost:8088/tour/item'
      }else{
        params = {page: page, keyWord: this.keyWord}
        url = 'http://localhost:8088/tour/search'
      }
      axios({
        method: 'post',
        url: url,
        params: params,
      }).then(value => {
        const data = value.data
        if(data.code === 100){
          //赋值
          this.scenicList = data.data.scenicList
          this.sumPage = data.data.sumPage
          this.page = data.data.currPage
        }
      })

    },
    //获取景点排行榜
    getScenicTop(){
      axios({
        method: 'get',
        url: 'http://localhost:8088/tour/top',
      }).then(value => {
        const data = value.data
        if(data.code === 100){
          this.scenicTop = data.data
        }
      })
    },
    //获取用户信息
    getUser(){
      axios({
        method: 'get',
        url: 'http://localhost:8088/tour/user',
      }).then(value => {
        const data = value.data
        if(data.code === 100){
          this.user = data.data
        }
      })
    }
  },
  //页面加载时执行
  mounted:function (){
    this.getScenicList(1)
    this.getScenicTop()
    this.getUser()
    //监听事件 鼠标点击景点标题时刷新景点排行榜
    bus.on('flashTop', e =>{
      this.getScenicTop()
    })
    //监听事件 点击搜索按钮或者清空搜索框时触发
    bus.on('search', e =>{
      this.keyWord = e;
      this.getScenicList(1)
    })
  }
}
</script>