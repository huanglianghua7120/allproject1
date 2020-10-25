<template>
    <div class="navigation" :class="{'open-menu': menuOpen}" @click="openToggel">
      <div class="main-menu">
        <a href="javascript:">
          <span class="iconfont icon-caidan"></span>
        </a>
      </div>
      <scroller lock-x scrollbar-y height="100%" ref="scroller">
        <ul>
          <li v-for="li in menuList" :key="li.menuId">
          <a href="javascript:"> <em class="iconfont icon-renwu"></em> {{li.prompt}}</a>
          <ul>
            <li v-for="subli in menuList" v-if="subli.menuParentId === li.menuId" :key="subli.menuId">
              <a href="javascript:">{{subli.prompt}}</a>
            </li>            
          </ul>
          </li>        
        </ul>
      </scroller>
      
    </div>
</template>
<script type="text/ecmascript-6">
import { Scroller } from 'vux'
export default {
  data () {
    return {
      menuOpen: false,
      menuList: []
    }
  },
  components: {
    Scroller
  },
  created () {

  },
  mounted () {
    // this.getNav()
  },
  methods: {
    openToggel () {
      this.menuOpen = !this.menuOpen
      console.log('animated')
      this.menuList = this.$store.state.navigationList
    }
  }
}
</script>
<style lang="less" scoped>
 @import '../styles/base/_init.less';

  .navigation{
    transition: left .5s ease;
    background-color:#333;
    color:#ccc;
    min-width: 60%;
    height: 100%;
    position: absolute;
    top:0;
    left: -60%;
    z-index: 9999;
    box-sizing: border-box;
    padding: 40px;
    ul {
      margin: 0;
      margin: 0 ;
      display: block;
      height: 100%; 
    
      overflow: hidden;
    }
    li {
        font-size: 48px;
        line-height: 72px;
        .iconfont {
          width: 50px;
          overflow:show;
        }
        ul {
          padding: 0 0 0 50px;
          overflow: initial;
        }
    }
    li a {
        color:@brand-info;
       
      }
    li li a {
      color:#ccc;
    }
    
  }
 
 .open-menu {
   left: 0;
   box-shadow: 0.2rem 0 0.6rem #333;
 }
 
.main-menu {
  position: absolute; 
  
  right: -100px;
  top: 20px;
  z-index: 999;
  a {
    display: inline-block;

    font-size: 64px;
    color: @brand-info;
  }
}

  /* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.myslide-enter-active {
  transition: all 1s ease;

}
.myslide-leave-active {
  transition: all 1s ease;
}
.myslide-enter{
 left: -45%;
}
.myslide-leave-to {
 left: -45%;
}

</style>
