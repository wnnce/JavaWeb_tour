<script setup>
defineProps(['scenicTop'])
import '../../../public/css/bootstrap.min.css'
let num = 1;
</script>
<template>
  <!-- 景点排行榜组件 -->
  <div class="mt-5">
    <fieldset>
      <legend>阅读排行榜</legend>
      <ul class="list-group">
        <li class="list-group-item" v-for="li in scenicTop">{{num ++}}、{{li.title}}({{li.clickNum}})</li>
      </ul>
    </fieldset>
  </div>
</template>