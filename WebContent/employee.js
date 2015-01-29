$(function () {
	$('#flash').hide();
	var eaccountOp = ["CreateEmployee", "ChangeEmployeePwd"];
	var caccountOp = ["CreateCustomer", "ResetPassword", "ViewAccount", "ViewTransaction","EmployeeViewTransHistory", "ResetCustomerPwd","DepositCheck"];
	var fundOp = ["CreateFund", "EmployeeResearchFund"];
	var active = false;
	$.each(eaccountOp, function(idx, s) {
		if (window.location.pathname.indexOf(s) != -1) {
			active = true;
			$('#account-op').addClass('active');
			$('#eaccount-operation').css('display', 'block');
			$('#caccount-operation').css('display', 'none');			
			$('#fund-operation').css('display', 'none');
		}
	})
	if (!active) {
		$('#account-op').removeClass('active');
	} else {
		addSub();
		return;
	}
	$.each(caccountOp, function(idx, s) {
		if (window.location.pathname.indexOf(s) != -1) {
			active = true;
			$('#account-op').addClass('active');
			$('#eaccount-operation').css('display', 'none');
			$('#caccount-operation').css('display', 'block');			
			$('#fund-operation').css('display', 'none');
		}
	})
	if (!active) {
		$('#fund-op').removeClass("active");
	} else {
		addSub();
		return;
	}
	
	active = false;
	$.each(fundOp, function(idx,s) {
		if (window.location.pathname.indexOf(s) != -1) {
			active = true;
			$('#fund-op').addClass('active');
			$('#eaccount-operation').css('display', 'none');
			$('#caccount-operation').css('display', 'none');			
			$('#fund-operation').css('display', 'block');	
		}
	})
	if (!active) {
		$('#transaction-day').removeClass('active');
	} else {
		addSub();
		return;
	}

	$('#eaccount-operation').css('display', 'none');
	$('#caccount-operation').css('display', 'none');			
	$('#fund-operation').css('display', 'none');
	$('#transaction-day').addClass('active');

})
$(function () {
  $('[data-toggle="popover"]').popover()
})
function addSub() {
	var listItems = $("#sub-operation li");
	listItems.each(function(idx, li) {
	    var product = $(li);
	    if (window.location.pathname.indexOf(product.attr("name")) != -1 
	    || window.location.pathname.indexOf("ResetCustomerPwd") != -1 && product.attr("name") == "ViewAccount") {
	    	product.addClass("active");
	    } else {
	    	product.removeClass("active");
	    }
	});	
}

