import request from "@/utils/request";
// 开始新的销售
export function makeNewSale(){
  return request({
    url: '/sale/makeNewSale',
    method: 'get'
  })
}
// 录入订单明细
export function enterItem(params) {
  return request({
    url: '/sale/enterItem',
    method: 'get',
    params: params
  })
}
// 结束录入
export function endSale() {
  return request({
    url: '/sale/endSale',
    method: 'get'
  })
}

// 确认支付
export function makePayment(params) {
  return request({
    url: '/sale/makePayment',
    method: 'get',
    params: params
  })
}

// 修改一行订单明细数量，自行完成
export function changeQuantity(data){
    return request({
        url:'/sale/changeQuantity',
        method:'post',
        data:data
    })
}

// 删除一行订单明细，自行完成
export function deleteSaleItem(saleItemId){
    return request({
        url:'/sale/deleteSaleItem/'+saleItemId,
        method:'get'
    })
}
