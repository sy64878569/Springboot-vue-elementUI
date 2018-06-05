<template>
  <el-row class="tac">
  <el-col :span="24">
    <el-menu
      class="el-menu-vertical-demo"
      mode="vertical"
      router
      unique-opened
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      style="height:700px">

      <el-submenu v-for="item in menu" :index="item.id" :key="item.id">
        <template slot="title">
            <span class="el-icon-menu"></span>
            <span v-text="item.name"></span>
        </template>
        <el-menu-item-group class="over-hide" v-for="sub in item.sub" :key="sub.componentName">
          <el-menu-item :index="sub.componentName" v-text="sub.name" @click.native="openTags(item)">
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>

    </el-menu>
  </el-col>
</el-row>
</template>

<style scoped>
  .over-hide{
    overflow: hidden;
  }
</style>

<script>
  import menu from '@/config/menu-config'
  import TagsView from './TagsView'
  export default {
    data () {
      return {
        menu: menu
      }
    },
    components:{
      'tags-view':TagsView
    },

    methods: {
      handleOpen (key, keyPath) {
        console.log(key, keyPath)
      },
      handleClose (key, keyPath) {
        console.log(key, keyPath)
      },

      //点击子菜单菜单事件
      openTags(item){
        // console.log(item)
        // alert(this.sub.componentName)

        // let tagsName = this.sub.name
        // let tagsPath = this.sub.componentName

        // this.$store.commit('setTagsName',tagsName)
        // this.$store.commit('setTagsPath',tagsPath)
      }
    }
  }
</script>