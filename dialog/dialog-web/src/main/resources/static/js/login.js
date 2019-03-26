function hidEmptyInfo1() {
	$("#empty_username").hide();
	$("#error_info").hide();
}

function hidEmptyInfo2() {
	$("#empty_password").hide();
	$("#error_info").hide();
}

function login() {
	var username = $("#login-name").val().trim();
	var password = $('#login-pass').val().trim();
	if (username == '') {
		$("#empty_username").show();
		return;
	}
	if (password == '') {
		$("#empty_password").show();
		return;
	}
	$.ajax({
		type : "POST",// 为post请求
		contentType : "application/json; charset=utf-8",
		url : "http://192.168.0.104:9010/dialog/user/login",
		data : JSON.stringify({
			"username" : username,
			"password" : password
		}),// 将该表单序列化
		error : function(request) {// 请求失败之后的操作
			return;
		},
		success : function(data) {// 请求成功之后的操作
			if(data.code=="0000"){
				window.location.href="http://localhost:9010/dialog/manager.html";
			}else {
				$("#error_info").show();
			}
		}
	});
}