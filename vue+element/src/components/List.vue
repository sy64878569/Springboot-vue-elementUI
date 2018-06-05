<template>
  <div>
    <div style="margin-top:10px">
      <el-input class="search" v-model="search_id" placeholder="根据ID查询"></el-input>
      <el-input class="search" v-model="search_username" placeholder="根据姓名查询"></el-input>
      <el-button type="primary" @click="getList()">查询</el-button>
    </div>
      <el-table
        stripe
        :data="userlist.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        :border = true
        style="margin-top:15px"
        @selection-change="handleSelectionChange"
        >
        <el-table-column type="selection" width="40px"></el-table-column>
        <el-table-column prop="id" label="ID"></el-table-column>
        <el-table-column prop="userName" label="姓名"></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column prop="actions" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="warning" @click="setCurrentUser(scope.row.id)">修改</el-button>
            <el-button slot="reference" size="mini" type="info" @click="deleteConfirm(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    <!-- 新增弹出框 -->
    <el-dialog
      title="新增用户"
      :visible.sync="dialogVisible"
      width="30%"
      style="">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户ID" prop="id">
          <el-input style="width:200px" v-model.number="ruleForm.id"></el-input>
        </el-form-item>
        <el-form-item label="用户姓名" prop="username">
          <el-input style="width:200px" v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" prop="password">
          <el-input style="width:200px" v-model="ruleForm.password"></el-input>
        </el-form-item>
        <div style="text-align:center">
          <el-button style="margin-top:20px" type="primary" @click="saveUser()">保存</el-button>
          <span><el-button style="margin-top:20px" type="success" @click="resetForm('ruleForm')">重置</el-button></span>
        </div>
      </el-form>
    </el-dialog> 

    <!-- 修改弹出框 -->
    <el-dialog
      title="修改用户"
      :visible.sync="dialogVisibleEdit"
      width="30%"
      style="">
      <el-form :model="ruleForm2" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户ID" prop="id">
          <el-input style="width:200px" v-model.number="ruleForm2.id"></el-input>
        </el-form-item>
        <el-form-item label="用户姓名" prop="username">
          <el-input style="width:200px" v-model="ruleForm2.username"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" prop="password">
          <el-input style="width:200px" v-model="ruleForm2.password"></el-input>
        </el-form-item>
        <div style="text-align:center">
          <el-button style="margin-top:20px" type="primary" @click="updUser()">修改</el-button>
          <span><el-button style="margin-top:20px" type="success" @click="resetForm('ruleForm')">重置</el-button></span>
        </div>
      </el-form>
    </el-dialog>

    <!-- 删除确认弹出框 -->
    <el-dialog
      title="删除用户"
      :visible.sync="dialogVisibleDelete"
      width="30%"
      style="">
      <span>删了就没了，确定吗?</span>
      <ul>
        <li style="list-style-type:none;text-align:center">
          <el-button style="margin-top:20px" type="primary" @click="dialogVisibleDelete = false">取消</el-button>
          <span><el-button style="margin-top:20px" prop="deleteId" type="success" @click="deleteUser(deleteId)">确定</el-button></span>
        </li>
      </ul>
    </el-dialog> 

    <div class="" style="text-align:center;margin-top:20px">
      <el-button style="float:left" size="mini" type="success" @click="addUser()">新增</el-button>
      <el-button style="float:left" size="mini" type="danger" @click="batchDelete()">批量删除</el-button>
      <el-pagination
        style="float:right"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10]"
        :page-size="pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total=parseInt(total)>
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {

    data() {
      return {
        //定义查询条件
        search_id:'',
        search_username:'',
        //定义查询结果数组
        userlist:[],
        total:0,
        currentPage: 1,
        pagesize: 10,
        //弹出框初始显示
        dialogVisible: false,
        dialogVisibleEdit: false,
        dialogVisibleDelete: false,
        add_id:'',
        add_username:'',
        add_password:'',
        currentId:'',
        upd_id:'',
        upd_username:'',
        upd_password:'',
        deleteId:'',
        activeName: 'second',
        multipleSelection: [],
        //表单
        ruleForm:{
          id:'',
          username:'',
          password:''
        },
        ruleForm2:{
          id:'',
          username:'',
          password:''
        },
        //表单合法性验证
        rules:{
          id:[
            { required: true, message: '用户ID不能为空！', trigger: 'blur' },
            { type: 'number', message: '用户ID必须为数字！', trigger: 'blur'}
          ],
          username:[
            { required: true, message: '姓名不能为空！', trigger: 'blur' }
          ],
          password:[
             { required: true, message: '用户ID不能为空！', trigger: 'blur' },
             { min: 6, message: '密码不能小于6位！', trigger: 'blur' }
          ]
        }
      }
    },

    created(){
        this.getList()
    },

    methods:{
        getList(){
          const params = {
              search_id : this.search_id,
              search_username : this.search_username,
          }
          this.axios.post('/api/search',params).then(response =>{
            this.userlist = response.data
            this.total = this.userlist.length
          })
        },
        addUser(){
          this.dialogVisible = true
          this.$refs['ruleForm'].resetFields()
        },

        setCurrentUser(id){
          this.dialogVisibleEdit = true
          const params = {
            currentId : id
          }
          this.axios.post('/api/edit',params).then(response =>{
            this.ruleForm2.id = response.data.id
            this.ruleForm2.username = response.data.userName
            this.ruleForm2.password = response.data.password
          })
        },

        saveUser(){
          this.$refs['ruleForm'].validate((valid) =>{
            if (valid) {
              const params = {
                add_id : this.ruleForm.id,
                add_username : this.ruleForm.username,
                add_password : this.ruleForm.password
          }
              this.axios.post('/api/add',params).then(response =>{
                this.$message('保存成功')
                this.dialogVisible = false
                this.getList()
              }).catch(err =>{
                this.$message('保存失败')
              })
            }else{
                this.$message('信息输入有误！')
                return false;
            }
          }
        )},

        updUser(){
          this.$refs['ruleForm'].validate((valid) =>{
            if (valid) {
              const params = {
                upd_id : this.ruleForm2.id,
                upd_username : this.ruleForm2.username,
                upd_password : this.ruleForm2.password
              }
              this.axios.post('/api/update',params).then(response =>{
              this.$message('修改成功')
              this.dialogVisibleEdit = false
              this.getList()
            }).catch(err =>{
              this.$message('修改失败')
            })
            }else{
              this.$message('信息输入有误！')
              return false;
            }
          }
        )},

        deleteConfirm(id){
          this.dialogVisibleDelete = true
          this.deleteId = id
        },

        deleteUser(deleteId){
          const params = {
            deleteId
          }
          this.axios.post('/api/delete',params).then(response =>{
            this.$message('删除成功')
            this.dialogVisibleDelete = false
            this.getList()
          }).catch(err =>{
          this.$message('删除失败')
          })
        },

        handleSelectionChange(val){
          this.multipleSelection = val
        },

        // 批量删除
        batchDelete(){
          if(this.multipleSelection == ''){
            this.$message('至少选择一条！')
          }else{
            this.multipleSelection.forEach(selectedItem => {
            var deleteId = selectedItem.id
            const params = {
              deleteId
          }
            this.axios.post('/api/delete',params).then(response =>{
              this.$message('删除成功')
              this.dialogVisibleDelete = false
              this.getList()
            }).catch(err =>{
              this.$message('删除失败')
            })
          })
          }
        },

        resetForm(formName) {
        this.$refs[formName].resetFields();
      },

　　　  handleSizeChange(val) {
        this.pagesize = val;
      },
        handleCurrentChange(val) {
        this.currentPage = val;
      },
        handleClose(done) {
          this.$confirm('确认关闭？')
            .then(_ => {
              done();
            })
            .catch(_ => {});
      },
        handleClick(tab, event) {
          //这里是点击标签页事件
      }
    },
  }
</script>

<style>
.search{
    width:200px
}
.el-table th{
  background-color: #A6B3C1;
  color: aliceblue
}
</style>