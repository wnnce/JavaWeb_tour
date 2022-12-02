<script setup>
import '../../public/css/bootstrap.min.css'
import Footer from "@/views/main/Footer.vue";
</script>

<template>
  <div class="info">
    <div class="row mt-5">
      <div class="col-md-6 offset-3">
        <h2>{{scenic.title}}</h2>
        <p>发布于&nbsp;{{scenic.scenicDate}}</p>
        <div>
          {{scenic.content}}
        </div>
      </div>
    </div>
    <div class="row pt-3 mt-5 comment">
      <div class="col-md-6 offset-3">
        <!-- 判断当前是否是登录用户 -->
        <p v-if="isUser == 0">想要评论，请先<a href="#/login">登录</a>！</p>
        <div class="mb-3">
          <label for="exampleFormControlTextarea1" class="form-label">留下你的评论</label>
          <textarea class="form-control" id="exampleFormControlTextarea1" placeholder="评论不能为空" rows="4" v-model="text"></textarea>
          <!-- 不是登录用户不能提交评论 -->
          <button class="submit_btn btn btn-primary mt-3 offset-10" value="" :disabled="isUser == 0" @click="addComment">提 交</button>
        </div>
        <div v-if="commentList == '' " class="text-center">
          <h6>还没有评论哦</h6>
        </div>
        <div v-else="commentList == '' " >
          <div class="comment_list col-md-12" v-for="comment in commentList">
            <img :src="comment.user.authorImg" width="36" height="36"><span>&nbsp;&nbsp;&nbsp;</span>
            <span>{{comment.user.username}}</span>
            <div class="comment_text">{{comment.content}}</div>
            <div class="comment_date">发布于: {{comment.commentDate}}</div>
          </div>
          <Footer :page="page" :sum-page="sumPage" :flash-list="getCommentList"></Footer>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import bus from 'vue3-eventbus'
export default {
  data(){
    return{
      isUser: 0,
      scenicId: null,
      page: 1,
      sumPage: null,
      scenic: '',
      commentList: '',
      text: ''
    }
  },
  methods:{
    //通过路由跳转的参数获取景点详情
    getScenic(){
      axios({
        method: 'post',
        url: 'http://localhost:8088/tour/info',
        params:{
          id: this.scenicId
        }
      }).then(value => {
        const data = value.data
        if(data.code === 100){
          this.scenic = data.data
          bus.emit('flashTop','100')
        }
      })
    },
    //获取评论列表
    getCommentList(page){
      axios({
        method: 'post',
        url: 'http://localhost:8088/tour/comments',
        params:{
          scenicId: this.scenicId,
          page: page
        }
      }).then(value => {
        const data = value.data
        if(data.code === 100){
          this.commentList = data.data.commentList
          this.sumPage = data.data.sumPage
          this.page = data.data.currPage
        }
      })
    },
    //添加评论
    addComment(){
      if(this.text === ''){
        alert('评论不能为空')
      }else{
        axios({
          method: 'post',
          url: 'http://localhost:8088/tour/addcomment',
          params: {
            scenicId: this.scenic.id,
            content: this.text
          },
        }).then(value => {
          const data = value.data
          if(data.code === 100){
            this.getCommentList(1)
            this.text = ''
          }else if (data.code === 102){
            alert('未登录')
          }
        })
      }
    }
  },
  mounted() {
    //获取路由参数
    this.isUser = this.$route.query.isUser
    this.scenicId = this.$route.query.id
    this.getScenic()
    this.getCommentList(this.page)
  }
}
</script>
<style>
.comment{
  background-color: #f3f2f2;
  font-size: 14px;
  color: #171717;
  height: 720px;
}
.comment_list{
  height: 100px;
  border-radius: 6px;
  background-color: white;
  padding: 10px;
  margin: 16px 0;
}
.submit_btn{
  width: 120px;
}
.comment_text{
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
  margin: 4px 8px
}
.comment_date{
  margin: 4px 8px;
  font-size: 12px;
}
</style>