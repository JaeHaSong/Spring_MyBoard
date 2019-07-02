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
	.seq_div, .title_div, .writer_div, .time_div
	{
		text-align: center;
		border-bottom: 1px black solid;
		border-top: 1px black solid;
	}
	#board_div > div
	{
		text-align: center;
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
		
		$(document).on("click", ".detail_class_btn", function()
		{
			location.href = "board_detail_page?b_seq=" + $(this).attr("name");
		});
		       	    
		$(document).on("click", ".navi_btn", function()
		{
			location.href = "board_list_page?page=" + $(this).val();
		});
		$(document).on("click","#prev_btn",function()
		{
			location.href = "board_list_page?page="+${ startNavi - 1};
		});
		$(document).on("click","#next_btn",function()
		{
			location.href = "board_list_page?page="+${ endNavi + 1};
		});
		       	   	
		       	   	
		if("${ currentPage }" % 10 == 0)
		{
			$($(".navi_btn")[9]).css("color", "#ffba00");
		}
		else
		{
			$($(".navi_btn")["${ currentPage % 10 - 1 }"]).css("color", "#ffba00");
		}
		        
	});
        
	onload = function()
	{
		
		
              
	};
        
</script>
</head>
<body>
	
	<div id="container" class="container">
			
		<div class="row h-100 align-items-center">
				
			<div class="col-12">
			
				<div class="row my-3">
				
					<div class="col-1 seq_div">글 번호</div>
					<div class="col-5 title_div">제목</div>
					<div class="col-3 writer_div">작성자</div>
					<div class="col-3 time_div">시간</div>
				
				</div>
				
				<c:forEach var="i" begin="1" end="${ listsize }" step="1">

					<div class="row my-3">

						<div class="col-12">

							<div id="board_div" class="row">

								<div class="col-lg-1 my-auto">${ list[i-1].b_seq }</div>

								<div id="title_div" class="col-lg-5 my-auto">

									<a class="btn btn-link" style="color:black" href="board_detail_page?b_seq=${ list[i-1].b_seq }">${ list[i-1].b_title }</a>

								</div>

								<div class="col-lg-3 my-auto">${ list[i-1].b_writer }</div>

								<div class="col-lg-3 my-auto">${ list[i-1].b_write_date }</div>

							</div>

						</div>

					</div>

				</c:forEach>
			
			</div>
			

			<div id="navi_div" class="col-12 text-center">

				<c:if test="${ needPrev }">
					<input id="prev_btn" type="button" class="btn btn-link" value="<">
				</c:if>
					
				<c:forEach var="i" begin="${ startNavi }" end="${ endNavi }">
					<input class="btn navi_btn" name="${ i }" type="button" value="${ i }">
				</c:forEach>
					
				<c:if test="${ needNext }">
					<input id="next_btn" type="button" class="btn btn-link" value=">">
				</c:if>

			</div>

			
			<div class="col-12 text-right">
			
				<a href="board_write_page"><input id="board_write_btn" class="btn btn-info" type="button" value="글쓰기"></a>
				<a href="/"><input id="back_btn" class="btn btn-info" type="button" value="돌아가기"></a>
				
			</div>
			
		</div>
			
	</div>

</body>
</html>