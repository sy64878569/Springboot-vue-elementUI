<template>
  <div id="app">
    <el-container style="font-family:Microsoft YaHei">
      <el-header style="background-color: #474E55;color: #fff;line-height: 80px;height:80px;">
        <!-- 页面头部组件 -->
        <vheader />
      </el-header>
      <el-container>
        <el-aside width="200px">
          <!-- 侧边菜单栏组件 -->
          <navmenu></navmenu>
        </el-aside>
        <el-main>
          <div style="padding:0px">
            <tags-view></tags-view>
          </div>
          <div>
            <router-view></router-view>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import NavMenu from '@/components/NavMenu'
import Header from '@/components/Header'
import Tabs from '@/components/Tabs'
import TagsView from '@/components/TagsView'

export default {
  name: 'app',
  components: {
    'navmenu': NavMenu,
    'vheader': Header,
    'tabs':Tabs,
    'tags-view':TagsView
  }
}

</script>

<style>
body{
  padding:0px;
  margin:0px;
}
</style>    