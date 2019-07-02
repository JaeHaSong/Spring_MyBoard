<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    
<style>
	
	html, body, #container
	{
		height: 100%;
	}
	
	#login_div
	{
		background-color: #f5f5dc90;
	}
	
</style>
    
<script src="https://code.jquery.com/jquery-3.4.0.min.js">
</script>
    
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
<script>
        
	$(function()
	{
		
		
		        
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
					
				<form action="signup_do" method="POST">
				
					<div class="row my-3">
					
						<div class="col-4 my-auto"><h4>아이디</h4></div>
						<div class="col-8 my-auto"><input id="id_text" class="form-control m-0" name="id" type="text"></div>
					
					</div>
					
					<div class="row my-3">
					
						<div class="col-12 my-auto">
							<label id="id_check_label"></label>
						</div>
					
					</div>
					
					<div class="row my-3">
					
						<div class="col-4 my-auto"><h4>비밀번호</h4></div>
						<div class="col-8 my-auto"><input id="id_text" class="form-control m-0" name="pw" type="password"></div>
					
					</div>
					
					<div class="row my-3">
					
						<div class="col-4 my-auto"><h4>비밀번호 확인</h4></div>
						<div class="col-8 my-auto"><input id="pwc_text" class="form-control m-0" name="pwc" type="password"></div>
					
					</div>
					
					<div class="row my-3">
					
						<div class="col-12 my-auto">
							<label id="pw_check_label"></label>
						</div>
					
					</div>
					
					<div class="row mb-5 my-3">
					
						<div class="col-4 my-auto"><h4>닉네임</h4></div>
						<div class="col-8 my-auto"><input id="nickname_text" class="form-control m-0" name="nickname" type="text"></div>
					
					</div>
					
					<div class="row mt-5 my-3">
					
						<div class="col-12 my-auto">
						
							<input id="signup_btn" class="btn btn-info" value="회원가입" type="submit">
						
						</div>
					
					</div>
				
				</form>
					
			</div>
					
			<div class="col-3"></div>
					
		</div>
			
	</div>


</body>
</html>