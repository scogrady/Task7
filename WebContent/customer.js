$(document).ready(function() {
	var accountOp = ["AccountInfo", "ChangePwd", "TransHistory", "RequestCheck"];
	var fundOp = ["BuyFund", "SellFund", "ResearchFund"];
	var active = false;
	$.each(accountOp, function(idx, s) {
		if (window.location.pathname.indexOf(s) != -1) {
			$('#view-account').addClass("active");
			active = true;
			$('#account-operation').css('display', 'block');
			$('#fund-operation').css('display', 'none');
		}
	})
	if (!active) {
		$('#view-account').removeClass("active");
	}
	
	active = false;
	$.each(fundOp, function(idx,s) {
		if (window.location.pathname.indexOf(s) != -1) {
			$('#fund-manage').addClass("active");
			active = true;
			$('#account-operation').css('display', 'none');
			$('#fund-operation').css('display', 'block');	
		}
	})
	if (!active) {
		$('#fund-manage').removeClass("active");
	}
	
	$('#view-account').click(function() {

	})
	
	$('#fund-manage').click(function() {
	
	})
	
	var listItems = $("#sub-operation li");
	listItems.each(function(idx, li) {
	    var product = $(li);
	    if (window.location.pathname.indexOf(product.attr("name")) == -1) {
	    	product.removeClass("active");
	    } else {
	    	product.addClass("active");
	    }
	});
}) 