<template>
    <div>
        <div style="margin-bottom: 20px;">
        <el-button
            size="small"
            @click="addTab(editableTabsValue2)"
        >
            add tab
        </el-button>
        </div>
        <el-tabs v-model="editableTabsValue2" type="card" closable @tab-click="goToComponent" @tab-remove="removeTab">
        <!-- 去掉了v-for参数index -->
        <el-tab-pane
            v-for="(item) in editableTabs2"
            :key="item.name"
            :label="item.title"
            :name="item.name"
        >
            {{item.content}}
        </el-tab-pane>
        </el-tabs>
    </div>
</template>
<script>
  export default {
    data() {
      return {
        editableTabsValue2: '2',
        editableTabs2: [],
        tabIndex: 2
      }
    },
    methods: {
        //创建标签页
        addTab(targetName) {
            let newTabName = ++this.tabIndex + '';
            this.editableTabs2.push({
            title: 'New Tab',
            name: 'list',
            });
            this.editableTabsValue2 = newTabName;
        },
        //移除标签页
        removeTab(targetName) {
            let tabs = this.editableTabs2;
            let activeName = this.editableTabsValue2;
            if (activeName === targetName) {
            tabs.forEach((tab, index) => {
                if (tab.name === targetName) {
                let nextTab = tabs[index + 1] || tabs[index - 1];
                if (nextTab) {
                    activeName = nextTab.name;
                }
                }
            });
            }
            
            this.editableTabsValue2 = activeName;
            this.editableTabs2 = tabs.filter(tab => tab.name !== targetName);
        },

        goToComponent(editableTabs2){
            this.$router.push('/' + editableTabs2.name)
        }
    }
  }
</script>