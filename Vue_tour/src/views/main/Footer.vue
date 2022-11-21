<script setup>
defineProps(['page','sumPage','flashList'])
import '../../../public/css/bootstrap.min.css'
</script>
<template>
  <!-- 翻页组件 -->
<div class="row page_btn">
  <div class="col-md-3 offset-1">
    <button type="button" class="btn btn-outline-secondary btn-sm m-3" :disabled="page === 1" @click="firstPage">首 页</button>
    <button type="button" class="btn btn-outline-primary btn-sm m-3" :disabled="page === 1" @click="pageBack">上一页</button>
  </div>
  <div class="col-md-2 mt-4 page_text">
    <span>第{{page}}页,共{{sumPage}}页</span>
  </div>
  <div class="col-md-3">
    <button type="button" class="btn btn-outline-primary btn-sm m-3" :disabled="page === sumPage" @click="pageNext">下一页</button>
    <button type="button" class="btn btn-outline-secondary btn-sm m-3" :disabled="page === sumPage" @click="lastPage">尾 页</button>
  </div>
</div>
</template>

<script>
export default {
  methods:{
    //下一页
    pageNext(){
      this.flashList(this.page + 1)
    },
    //上一页
    pageBack(){
      this.flashList(this.page - 1)
    },
    //首页
    firstPage(){
      this.flashList(1)
    },
    //尾页
    lastPage(){
      this.flashList(this.sumPage)
    }
  }
}
</script>

<style scoped>
.page_btn{
  margin-bottom: 36px;
}
.page_text{
  align-content: center;
  font-size: 12px;
}
</style>