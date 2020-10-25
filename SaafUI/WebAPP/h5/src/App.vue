<template>
  <div id="app" class="view-box">
    <navigation v-show="navShow"></navigation>
    <transition :name="transitionName">
      <router-view class="router-view"></router-view>
    </transition>
  </div>
</template>
<script>
import Navigation from './components/navigation'
export default {
  name: 'app',
  data () {
    return {
      transitionName: 'slide-left',
      navShow: false
    }
  },
  components: {
    Navigation
  },
  watch: {
    '$route' (to, from) {
      const toDepth = to.path.split('/').length
      const fromDepth = from.path.split('/').length
      this.transitionName = toDepth <= fromDepth ? 'slide-left' : 'slide-right'
      if (to.name !== 'login') {
        if (!this.navShow) {
          setTimeout(() => {
            this.navShow = true
          }, 600)
        }
      } else {
        this.navShow = false
      }
    }
  }
}
</script>

<style lang="less">
@import './styles/style.less';

body {
  background-color: #fbf9fe;
}

.router-view {
  position: absolute !important;
  top: 0;
  width: 100%;
  box-sizing: border-box;
  overflow-x: hidden;
}
</style>
