
module.exports = {
  //卡券类型
  couponType: [{ type: 1, name: "优惠券" }, { type: 2, name: "折扣券" }, { type: 3, name: "体验券" }],
  //退款类型
  refundType: [{ type: 1, name: "我要退款（无需退货）" }, { type: 2, name: "我要退货" }],
  //收货状态
  deliveryStatus: [{ type: 1, name: "未收到货" }, { type: 2, name: "已收到货" }],
  //退款原因
  refundReason: [{ delivery: 1, reason: ["不喜欢/不想要", "空包裹", "未按约定时间发货", "快递/物流一直未送到", "快递/物流无跟踪记录"] }, { delivery: 2, reason: ["商品瑕疵","质量问题","颜色/尺寸/参数不符","少件/漏发","收到商品时有划痕或破损","未按约定时间发货"] }],
  //取消订单原因
  cancelReason: ["不想买了", "拍错了","不是我买的","其他"]
}