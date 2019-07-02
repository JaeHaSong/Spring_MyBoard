<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
html, body, #container {
	height: 100%;
}

#login_div {
	background-color: #f5f5dc90;
}
</style>

<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<script>
	$(function()
    {
		$("#kakao-login-btn").on("click", function() 
		{
			Kakao.init('13fe5c08665b4e8a48dc83219f00ee79');
			Kakao.Auth.loginForm
			({
				success : function(authObj) 
				{
					var obj = JSON.stringify(authObj);
					Kakao.API.request
					({
						url : '/v2/user/me',
						success : function(res) 
						{
							var resobj = JSON.stringify(res);

							var $form = $('<form></form>');
							$form.attr('action', 'kakao_login_do');
							$form.attr('method', 'POST');
							$form.appendTo('body');

							var json = $('<input type="hidden" name="json">');
							json.val(resobj);
							
							$form.append(json);
							$form.submit();
						},
						fail : function(error) 
						{
						alert(JSON
						.stringify(error));
						}
					})
				}
			});
		})
    });
    
    onload = function()
    {

    };
</script>
</head>
<body>

	<div id="container" class="container">

		<div class="row h-100 align-items-center">

			<div class="col-3"></div>

			<div id="login_div" class="col-6 p-5 text-center">

				<form action="login_do" method="POST">

					<div class="row my-3">

						<div class="col-4 my-auto">
							<h3>아이디</h3>
						</div>
						<div class="col-8 my-auto">
							<input id="id_text" class="form-control m-0" name="id"
								type="text">
						</div>

					</div>

					<div class="row mb-5 my-3">

						<div class="col-4 my-auto">
							<h3>비밀번호</h3>
						</div>
						<div class="col-8 my-auto">
							<input id="pw_text" class="form-control m-0" name="pw"
								type="password">
						</div>

					</div>

					<div class="row my-5">
						<div class="col-12 my-auto">
							<c:if test="${ loginFalse != null }">
								<label id="login_check_label">로그인 실패</label>
							</c:if>
						</div>
					</div>

					<div class="row mt-5 my-3">

						<div class="col-12 my-auto">

							<input id="login_btn" class="btn btn-info" value="로그인" type="submit"> 
							<a href="signup_page"><input id="signup_btn" class="btn btn-info" value="회원가입" type="button"></a>

						</div>

					</div>

				</form>
				
				<div class="row mt-5 my-3">

					<div class="col-12 my-auto">

						<a id="kakao-login-btn"> <img id="kakao-login-btn"
							src="https://kauth.kakao.com/public/widget/login/kr/kr_02_medium.png"
							style="cursor: pointer"
							onmouseover="this.src='https://kauth.kakao.com/public/widget/login/kr/kr_02_medium_press.png'"
							onmouseout="this.src='https://kauth.kakao.com/public/widget/login/kr/kr_02_medium.png'">
						</a>

					</div>

				</div>
				
			</div>

			<div class="col-3"></div>

		</div>

	</div>

</body>
</html>