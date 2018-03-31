$(function(){
	alert("ok");
	$(".increment").click(function(){//＋
		alert("increment");
		var _thisInput = $(this).siblings("input");
		alert(_thisInput.val());
		_thisInput.val(eval(_thisInput.val()) + 1);
		var num=_thisInput.val();
		$.post("/cart/update/num/"+_thisInput.attr("itemId")+".html?num="+num ,function(data){
			refreshTotalPrice();
		});
		alert(_thisInput.val());
	});
	$(".decrement").click(function(){//-
		alert("decrement");
		var _thisInput = $(this).siblings("input");
		if(eval(_thisInput.val()) == 1){
			return ;
		}
		_thisInput.val(eval(_thisInput.val()) - 1);
		var num=_thisInput.val();
		$.post("/cart/update/num/"+_thisInput.attr("itemId")+".html?num="+num ,function(data){
			refreshTotalPrice();
		});
	});
	$(".quantity-form .quantity-text").rnumber(1);//限制只能输入数字
	$(".quantity-form .quantity-text").change(function(){
		alert("change");
		var _thisInput = $(this);
		var num=_thisInput.val();
		$.post("/service/cart/update/num/"+_thisInput.attr("itemId")+".html?num="+num,function(data){
			refreshTotalPrice();
		});
	});
	function refreshTotalPrice(){ //重新计算总价
		var total = 0;
		$(".quantity-form .quantity-text").each(function(i,e){
			var _this = $(e);
			total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
		});
		$(".totalSkuPrice").html(new Number(total/100).toFixed(2)).priceFormat({ //价格格式化插件
			 prefix: '￥',
			 thousandsSeparator: ',',
			 centsLimit: 2
		});
	 }
});