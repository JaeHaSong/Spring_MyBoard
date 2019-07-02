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
	
    #profile_img
    {
        width: 150px;
        height: 100px;
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
		var time = new Date().getTime();
		var source = "/resources/${ loginId }/${ loginId }_profile_img.jpg?time="+time;
		$("#profile_img").attr("src", source);        
	});
        
	onload = function()
	{
		
		
              
	};
        
</script>
</head>
<c:choose>

	<c:when test="${ loginId != null }">
	
		<body>

			<div id="container" class="container">
			
				<div class="row h-100 align-items-center">
				
					<div class="col-3 text-center">
						
						<img id="profile_img" alt="없다">
					
					</div>
				
					<div class="col-4 text-center">
					
						${ loginId } 님 환영합니다! <br>
					
					</div>
					
					<div class="col-5 text-center">
						
						<a href="logout_do"><input id="logout_btn" class="btn btn-danger" value="로그아웃" type="button"></a>
						<a href="board_list_page?page=1"><input id="board_btn" class="btn btn-info" value="게시판" type="button"></a>
						<a href="port_list_page?page=1"><input id="board_btn" class="btn btn-info" value="또 다른 게시판" type="button"></a>
					</div>
					
				</div>
			
			</div>

		</body>
		<script>
		
			$("#profile_img").on("click", function()
            {
				var popOption = "width=500, height=300, resizable=no, scrollbars=no, status=no top=200, left=200;";
			    
			    open("change_profile_page", "프로필 이미지 변경", popOption);
            });
		
		</script>
		
	</c:when>
	
	<c:otherwise>
	
		<body>

			<div id="container" class="container">
			
				<div class="row h-100 align-items-center">
				
					<div class="col-3"></div>
				
					<div class="col-6 text-center">
					
						<a href="login_page"><input id="login_btn" class="btn btn-primary" name="" value="로그인" type="button"></a>
						<a href="signup_page"><input id="signup_btn" class="btn btn-primary" name="" value="회원가입" type="button"></a>
					
					</div>
					
					<div class="col-3"></div>
					
				</div>
			
			</div>

		</body>
		<script>
		
			
		
		</script>
		
	</c:otherwise>
	
</c:choose>
</html>