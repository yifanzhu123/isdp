<template>
  <div>

      <div class="left">
      <el-card class="left-card">
  <div slot="header" >
    <span>商品录入</span>
  </div>
      <el-form class="left1">
        <el-form-item label="商品编码"> <el-input v-model="input1"></el-input></el-form-item>
         <el-form-item label="订购数量"> <el-input v-model="input2"></el-input></el-form-item>
      </el-form>
      <el-button size="small" type="primary" @click="enterItem" :disabled="enterItemB">ENTER ITEM</el-button>
    <el-button size="small" type="warning" @click="endSale" :disabled="endSaleB">END SALE</el-button>
    </el-card>

     <el-card class="left-card">
  <div slot="header" >
    <span>订单支付</span>
  </div>
      <el-form class="left1">
        <el-form-item label="付款金额"> <el-input v-model="input3"></el-input></el-form-item>
         <el-form-item label="找零"> <el-input v-model="input4" disabled></el-input></el-form-item>
      </el-form>
      <el-button size="small" type="danger" @click="makePayment" :disabled="makePaymentB">MAKE PAYMENT</el-button>
    
    </el-card>
</div>



<div class="right">
    <div class="description">
   <el-descriptions   :column="3"  border>
    <template slot="extra">
      <el-button type="success" round size="small" @click="makeNewSale" >Make NEW SALE</el-button>
    </template>
    <el-descriptions-item>
      <template slot="label">
        会员
      </template>
      张三
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        订单号
      </template>
      so-65432165413213
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        总金额
      </template>
      112
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
       总件数
      </template>
     3
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        状态
      </template>
       <el-tag size="small">已预定</el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        备注
      </template>
      无
    </el-descriptions-item>
  </el-descriptions>
</div>

     <el-card class="right-card">
  <div slot="header" class="clearfix">
    <span>订单明细</span>
  </div>
      <el-table :data="saleItemList" >
        <el-table-column type="index" label="序号"></el-table-column>
        <el-table-column prop="itemSn" label="商品编号"></el-table-column>
        <el-table-column prop="productName" label="商品名称"></el-table-column>
        <el-table-column prop="price" label="价格"></el-table-column>
        <el-table-column prop="quantity" label="数量">
            <template slot-scope="scope">
            <el-input-number size="small"  v-model="scope.row.quantity" :min="1" :max="10" >
                </el-input-number>
                </template>
                </el-table-column>
        <el-table-column  label="操作"><el-button size="small" type="primary">删除</el-button></el-table-column>
      </el-table> 
    </el-card>
</div>
    


  </div>
</template>

<script>
export default {
    data(){
        return{
            input1:'',
            input2:'',
            input3:'',
            input4:'',
            saleItemList:[    
            ],
            
            enterItemB:true,
            endSaleB:true,
            makePaymentB:true,
        }
    },
    methods:{
        makeNewSale(){
            this.enterItemB=false
        },
        
        endSale(){
            this.makePaymentB=false
        },
        makePayment(){
            var change=parseInt(this.input3)-parseInt(this.saleItemList[0].price)
            this.input4=change
            this.enterItemB=true
            this.endSaleB=true
            this.makePaymentB=true
        },
          enterItem(){
             this.endSaleB=false
    // 仅用于测试
             this.saleItemList.push({itemSn: 1001, productName: '钢笔', price: '60.00', quantity: 2});
        },
}
}
</script>

<style>
.left{
    
    float: left;
}
.right{
  
    width:1050px;
    float: right;
}
  .left-card {
    width: 300px;
   
  }
  .description{
     padding-bottom: 20px;
  }
  
  
</style>