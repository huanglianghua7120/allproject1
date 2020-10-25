<template>
  <div class="main">    
    <div class="header">     
      <div class="star-panel">
        <div class="star-box">
        <div class="star">
        </div>
        <div class="logo">
        </div>
      </div>
      </div>
      <div class="user-info">
        <span class="user-name">sysadmin</span>
        <span class="iconfont icon-youjian">
          <b class="badge badge-info"></b>
        </span>
      </div>
    </div>
    <div class="menu">
      <div class="grid">
         <router-link to="/" class="grid-item">
          <span class="icon">
            <em class="iconfont icon-xiaoxi"></em>
          </span>
          <span class="title"> 我的任务</span>
        </router-link>
         <router-link to="/" class="grid-item">
          <span class="icon">
            <em class="iconfont icon-renwu"></em>
          </span>
          <span class="title">我的任务</span>
        </router-link>
         <router-link to="/" class="grid-item">
          <span class="icon">
            <em class="iconfont icon-renwu"></em>
          </span>
          <span class="title">我的任务</span>
        </router-link>
         <router-link to="/" class="grid-item">
          <span class="icon">
            <em class="iconfont icon-renwu"></em>
          </span>
          <span class="title">报表中心</span>
        </router-link>
        <router-link to="/" class="grid-item">
          <span class="icon icon-null">
            <em class="iconfont icon-jia"></em>
          </span>
          <span class="title" >添加菜单</span>
        </router-link>
      </div>

    </div>
  </div>

</template>
<style scoped lang="less">
@import '../styles/pages/home.less';
</style>
<script>
import store from '../store/index.js'
import * as mutation from '../store/mutation-types'
import { Grid, GridItem } from 'vux'
export default {
  data () {
    return {
      msg: 'hello vue'
    }
  },
  components: {
    Grid, GridItem
  },
  created () {

  },
  mounted () {
    store.dispatch(mutation.GET_NAVIGATION_LIST) // 分发action
  },
  methods: {

  }

}
</script>
