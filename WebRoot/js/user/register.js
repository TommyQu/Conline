$(document)
		.ready(
				function() {
					$("#checkinguser").hide();
					$("#checkingpassword").hide();
					$("#checkingpassword2").hide();
					$("#checkingemail").hide();
					$("#checkingzipcode").hide();
					$("#checkingicode").hide();
					$("input[type='password']").css("width", "150px");
					$("tr:eq(7),tr:eq(8),tr:eq(9),tr:eq(10),tr:eq(11),tr:eq(12),tr:eq(13),tr:eq(14)").hide();
					$("#username").blur(
							function() {
								$("#checkinguser").show();
								$("#checkinguser").attr("src",
										"img/user/checking.gif");
								/*
								 * checking user name in database
								 */
								if (!((/^[A-Za-z_]+[A-Za-z0-9_]{5,7}$/).test($(
										"#username").val()))
										|| $("#username").val() == "") {
									$("#checkuser").text("用户名格式不正确");
									$("#checkinguser").attr("src",
									"img/user/uncheck.png");
								} else {
									$("#checkuser").text("");
									$("#checkinguser").attr("src",
											"img/user/checkout.png");
								}
							});
					$("#password").blur(
							function() {
								$("#checkingpassword2").hide();
								$("#checkingpassword").show();
								$("#checkingpassword").attr("src",
										"img/user/checking.gif");
								if (!((/^[A-Za-z0-9_]{6,18}$/).test($(
										"#password").val()))
										|| $("#password").val() == "") {
									$("#checkpassword").text("密码长度不正确");
									$("#checkingpassword").attr("src",
									"img/user/uncheck.png");
								} else {
									$("#checkpassword").text("");
									$("#checkingpassword").attr("src",
											"img/user/checkout.png");
								}
								
							});
					$("#passwordConfirm").blur(
							function() {
								$("#checkingpassword2").show();
								$("#checkingpassword2").attr("src",
										"img/user/checking.gif");
								if ($("#passwordConfirm").val()!=$("#password").val()){
									$("#checkpassword2").text("两次密码不相等");
									$("#checkingpassword2").attr("src",
									"img/user/uncheck.png");
								} else {
									$("#checkpassword2").text("");
									$("#checkingpassword2").attr("src",
											"img/user/checkout.png");
								}
							});

					$("#email")
							.blur(
									function() {
										$("#checkingemail").show();
										$("#checkemail").text("");
										$("#checkingemail").attr("src",
												"img/user/checking.gif");
										if (!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)
												.test($("#email").val()))
											$("#checkemail").text("邮箱格式不正确");
										/*
										 * checking user name in database
										 */
									});
					$("#zipCode").blur(
							function() {
								$("#checkingzipcode").show();
								$("#checkzipcode").text("");
								$("#checkingzipcode").attr("src",
										"img/user/checking.gif");
								if (!(/^[0-9]{6}$/).test($("#zipCode").val())){
									$("#checkzipcode").text("邮编格式不正确");
								$("#checkingzipcode").attr("src",
								"img/user/uncheck.png");
								}
								else{
									$("#checkingzipcode").attr("src",
									"img/user/checkout.png");
								}
							});
					$("#icode").blur(
							function() {
								$("#checkingicode").show();
								$("#checkicode").text("");
								$("#checkingicode").attr("src",
										"img/user/checking.gif");
								if ($("#icode").val().length!=4){
									$("#checkicode").text("验证码不正确");
								$("#checkingicode").attr("src",
								"img/user/uncheck.png");
								}
								else{
									$("#checkingicode").attr("src",
									"img/user/checkout.png");
								}
							});
				});

$(function($) {
	$("input[value='更多信息']")
			.click(
					function() {
						$(
								"tr:eq(7),tr:eq(8),tr:eq(9),tr:eq(10),tr:eq(11),tr:eq(12),tr:eq(13),tr:eq(14)")
								.show();
						$("tr:eq(6)").hide();
						$("#regist").css("margin-top", "2%");
						$("td").css("padding-top", "0px");
					});
});
$(function($) {
	$("input[value='收起信息']")
			.click(
					function() {
						$(
								"tr:eq(7),tr:eq(8),tr:eq(9),tr:eq(10),tr:eq(11),tr:eq(12),tr:eq(13),tr:eq(14)")
								.hide();
						$("tr:eq(6)").show();
						$("#regist").css("margin-top", "6%");
						$("td").css("padding-top", "10px");
					});
});
