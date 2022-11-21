<script setup>
defineProps(['scenicList','user'])
import '../../../public/css/bootstrap.min.css'
import {useRoute} from "vue-router";
</script>
<template>
  <!-- 景点列表组件 -->
  <div>
    <ul>
      <li class="attr_item" v-for="scenic in scenicList">
        <div class="item">
          <div class="item_img">
            <img :src="scenic.imgUrl" width="244" height="164">
          </div>
          <div class="item_text">
            <p><span>默认分类</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{scenic.scenicDate}}</span></p>
            <!-- 路由跳转 params参数被弃用了 所以使用query显示路由 -->
            <h5><router-link :to="{name: 'info',query:{id: scenic.id,isUser: user == null ? 0:1}}">{{scenic.title}}</router-link></h5>
            <div class="item_text_info">
              {{scenic.content}}
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>
<style>
.item{
  width: 600px;
  height: 200px;
  border: 1px solid #c8c3c3;
  border-radius: 8px;
  margin: 20px;
}
.item_img{
  margin: 16px;
  float: left;
}
.item_text{
  margin: 16px;
  float: left;
  font-size: 14px;
}
.item_text h5 a{
  text-decoration: none;
}
.item_text_info{
  width: 260px;
  height: 96px;
  overflow: hidden;
  text-overflow: ellipsis;
}
.attr_item{
  list-style: none;
}
</style>