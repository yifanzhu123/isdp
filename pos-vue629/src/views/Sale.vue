<template>
  <div class="app-container">
      <el-row :gutter="20">
        <!-- 左侧商品、支付信息录入表单 -->
        <el-col :span="6" :xs="24">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>商品录入</span>
            </div>
            <el-form :model="enterItemForm"  ref="enterItemForm" size="small" label-width="68px">
                <el-form-item label="商品编码" prop="itemSn">
                    <el-input v-model="enterItemForm.itemSn" placeholder="请输入条码" clearable @keyup.enter.native="enterItem"/>
                </el-form-item>
                <el-form-item label="订购数量" prop="quantity">
                    <el-input v-model="enterItemForm.quantity" clearable @keyup.enter.native="enterItem"/>
                </el-form-item>
                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-button type="primary" size="mini" :disabled="step!==2" @click="enterItem">ENTER ITEM</el-button>
                    </el-col>
                    <el-col :span="12">
                        <el-button type="warning" size="mini" :disabled="step !==2 || saleItemList.length == 0" @click="endSale">END SALE</el-button>
                    </el-col>
                </el-row>
            </el-form>
          </el-card>

          <el-card class="box-card"> 
            <div slot="header" class="clearfix">
              <span>订单支付</span>
            </div>
            <el-form :model="makePaymentForm" ref="makePaymentForm" size="small" label-width="68px">
                <el-form-item label="付款金额" prop="cashTendered">
                    <el-input v-model="makePaymentForm.cashTendered"></el-input>
                </el-form-item>
                <el-form-item label="找零">
                    <el-input v-model="changeDue" disabled></el-input>
                </el-form-item>
                  <el-form-item>
                    <el-button type="danger" size="mini" :disabled="step!==4" @click="makePayment">MAKE PAYMENT</el-button>
                </el-form-item>
            </el-form>
          </el-card>
        </el-col>

        <!-- 左侧商品、支付信息录入表单 -->
        <el-col :span="18" :xs="24">
           <el-descriptions border>
              <template slot="extra">
                  <el-button type="success" size="small" round :disabled="step!==1" @click="makeNewSale">Make NEW SALE</el-button>
              </template>
              <el-descriptions-item label="会员">张三</el-descriptions-item>
              <el-descriptions-item label="订单号">{{orderInfo.saleNo}}</el-descriptions-item>
              <el-descriptions-item label="总金额">{{orderInfo.total}}</el-descriptions-item>
              <el-descriptions-item label="总件数">{{orderInfo.quantity}}</el-descriptions-item>
              <el-descriptions-item label="状态"><el-tag size="small">{{orderInfo.status}}</el-tag></el-descriptions-item>
              <el-descriptions-item label="备注">暂无</el-descriptions-item>
          </el-descriptions>
          <br>
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>订单明细</span>
            </div>
            <el-table :data="saleItemList" :row-class-name="rowSaleItemIndex">
              <el-table-column label="序号" align="center" prop="index" width="55" />
              <el-table-column label="商品编号" align="center" prop="itemSn" />
              <el-table-column label="商品名称" align="center" prop="productName" />
              <el-table-column label="价格" align="center" prop="price" />
              <el-table-column label="数量" align="center" prop="quantity">
                  <template slot-scope="scope">
                      <el-input-number size="mini"  :disabled="step!==2"  v-model="scope.row.quantity" @change="handleChangeQuantity(scope.row)" :min="1" :max="10" ></el-input-number>
                  </template>
              </el-table-column>
              <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                  <template slot-scope="scope">
                  <el-button size="small" type="text" :disabled="step!==2" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
                  </template>
              </el-table-column>
          </el-table>
          </el-card>
        </el-col>
      </el-row>
  </div>
</template>

<script>

import {makeNewSale,enterItem,endSale,makePayment,changeQuantity,deleteSaleItem} from '@/api/sale'
export default {
  name: "Sale",
  // 定义属性
  data() {
    return {
      // 当前步骤，用于控制按钮是否可用
      step: 1,
      //商品录入表单
      enterItemForm: {
          itemSn:null,
          quantity:null
      },
      //订单支付表单
      makePaymentForm: {},
      //订单详情
      orderInfo: {
          saleNo:null,
          quantity:null,
          status:null,
          total:null,
      },
      //找零
      changeDue: 0,
      //订单明细列表
      saleItemList: [],
    };
  },
  // 计算属性
  computed: {},
  // 监控data变化
  watch: {

  },
  // 生命周期-创建完成
  created() {},
  // 生命周期-挂载完成
  mounted() {},
  
  // 方法集合
  methods: {
    //步骤1：创建订单
    makeNewSale(){
      this.clear();
      this.step = 2;
      makeNewSale().then(response=>{
          this.orderInfo=response.data
         
      })
    },
    // 步骤2：提交输入商品
    enterItem(){
      let index = this.saleItemList.findIndex((item) => item.itemSn == this.enterItemForm.itemSn)
          console.log(index);
        enterItem(this.enterItemForm).then(response=>{
          if(response.data&&index==-1){
            this.saleItemList.push(response.data)
          }else if(response.data&&index!=-1){
            this.saleItemList[index].quantity=this.saleItemList[index].quantity+this.enterItemForm.quantity
            changeQuantity(this.saleItemList[index])
          }
          else {
            this.$message.error(response.msg)
          }
          
            
        })
    },
    // 步骤3：结束输入
    endSale(){
      this.step = 4;
      endSale().then(response=>{
          this.orderInfo.total=response.data
      })
      //求商品总件数
      var totalQuantity=0
      this.saleItemList.forEach((item)=>{
          totalQuantity=totalQuantity+item.quantity
      })
      this.orderInfo.quantity=totalQuantity
      
    },
    
    // 步骤4：提交支付
    makePayment(){
      
      if(this.makePaymentForm.cashTendered>=this.orderInfo.total){
         this.step = 1;
         makePayment(this.makePaymentForm).then(response=>{
            this.$message.success(response.msg)
           this.changeDue=response.data
        })
        this.orderInfo.status='已付款'
      }else{
        this.$message.error("付款金额必须大于等于总金额！")
      }
       
    },
    // 修改一行订单明细数量
    handleChangeQuantity(row){
      
      changeQuantity(row).then(response=>{
        this.$message.success(response.msg)
      })
    },
    // 删除一行订单明细
    handleDelete(row){
      deleteSaleItem(row.itemSn).then(response=>{
        this.$message.success(response.msg)
      })
      this.saleItemList.splice(row.index-1,1)

    },
    // 清空界面数据
    clear(){
        this.orderInfo = {};
        this.saleItemList = [];
        this.enterItemForm = {
            itemSn: '',
            quantity: 1
        };
        this.makePaymentForm = {
            cashTendered: 0
        };
        this.changeDue = 0;
        this.step = 1;
    },
    //为table订单明细添加序号
    rowSaleItemIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    }

  },
};
</script>

<style scoped>
</style>