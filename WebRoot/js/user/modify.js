$(document)
		.ready(
				function() {
					$("#checkinguser").hide();
					$("#checkingpassword").hide();
					$("#checkingpassword2").hide();
					$("#checkingemail").hide();
					$("#checkingzipcode").hide();
					$("input[type='password']").css("width", "150px");
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
											{
											$("#checkemail").text("邮箱格式不正确");
											$("#checkingemail").attr("src",
											"img/user/uncheck.png");
											}
										else{
											$("#checkingemail").attr("src",
											"img/user/checkout.png");
										}
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
				});