<template>
  <div class="bg">
    <div class="app-name">
      <div class="logo"></div>
      <h1>赛意SAAF3.6研发平台</h1>
    </div>
    <form class="login-form">
      <div class="inputItem">
        <span class="iconfont icon-zhanghao"></span>
        <input type="text" placeholder="请输入您的账号">
      </div>
      <div class="inputItem">
        <span class="iconfont icon-mima"></span>
        <input :type="inputType"  placeholder="请输入您的密码">
        <span class="iconfont  fr" :class="{'icon-artboard79': !eye, 'icon-yanjing1': eye}" @click="look"></span>
      </div>
    </form>
    <div class="btn">
      <x-button type="primary" @click.native="login">登录</x-button>
    </div>
    <div class="tr">
      <a href="javascript:">忘记密码?</a>
    </div>
    <div class="copyright">
      Copyright © 2017 SIE All Rights Reserved
    </div>
  </div>
</template>
<style scoped lang="less">
@import '../styles/pages/login.less';
</style>
<script>
import { XButton } from 'vux'

export default {
  data () {
    return {
      eye: true,
      inputType: 'password'
    }
  },
  components: {
    XButton
  },
  created () {

  },
  mounted () {

  },
  methods: {
    login () {
      this.$router.push({name: 'home'})
    },
    look () {
      this.eye = !this.eye
      this.inputType = this.eye ? 'password' : 'text'
    }
  }

}
</script>
