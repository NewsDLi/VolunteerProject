$(function() {

	init();
	
	$(".iam-input-container").on("focus", "input", function() {
		$(this).parent().addClass("focus");
	})
	$(".iam-input-container").on("blur", "input", function() {
		$(this).parent().removeClass("focus");
		$(this).parent().removeClass("active");
		if (!$(this).val() == "") {
			$(this).parent().addClass('active');
		}
	})
	$('.icon-checked').click(function() {
		if ($('.icon-checked').hasClass('is-active')) {
			$('.icon-checked').removeClass('is-active');
			$('#isRemember').val('false');
			return;
		}
		$('.icon-checked').addClass('is-active');
		$('#isRemember').val('true');
	});
});

// 初始化
function init(){
	var pw = getCookie("p_w");
	var un = getCookie("u_n");
	if ('' != pw && pw.length > 0 && ''!=un && un.length>0){
		$('.user').val(un);
		$('.pass').val(pw);
	}
}

function getCookie(c_name) {
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=");
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1;
			c_end = document.cookie.indexOf(";", c_start);
			if (c_end == -1)
				c_end = document.cookie.length;
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
	return "";
}
