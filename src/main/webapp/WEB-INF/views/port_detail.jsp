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
	
	
	#header_row, #contents_row
	{
		min-height: 200px;
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
		
		$("#back_btn").on("click", function()
		{
			history.back();
		});
		
	});
        
	onload = function()
	{
		
		
              
	};
        
</script>
</head>
<body>

	<div id="container" class="container">
	
		<div class="row my-5 align-items-center">
		
			<div class="col-12">
			
				<div id="header_row" class="row my-5">
				
					<div class="col-1">${ dto.p_seq }</div>
					
					<div class="col-6">${ dto.p_title }</div>
					
					<div class="col-3">${ dto.p_writer }</div>
					
					<div id="time_div" class="col-2">${ fn:substring(dto.p_write_time,0,19) }</div>
				
				</div>
				
				<div id="contents_row" class="row my-5">
				
					<div id="contents_div" class="col-12 text-center d-block my-5">
						${ dto.p_contents }
					</div>
				
				</div>
				<div id="btn_row" class="row my-5">
					
					<div id="btn_div" class="col-12 text-right">
						
						<input id="back_btn" class="btn btn-info" type="button" value="돌아가기">
						
						<c:if test="${ loginId == dto.p_writer }">
							
							<input id="update_btn" class="btn btn-info" type="button" value="수정하기">
							<input id="delete_btn" class="btn btn-danger"type="button" value="삭제하기">
							
						</c:if>
						
					</div>
					
				</div>
			</div>
			
			
			
		</div>
		
	</div>


</body>
<c:if test="${ loginId == dto.p_writer }">
	
	<script>
	
		$("#update_btn").on("click", function()
		{
			var form = $('<form></form>');
		    form.attr('action', 'port_update_page');
		    form.attr('method', 'POST');
		    form.appendTo('body');
		
		    var text = $("#contents_div").html();
		    
		    var seq = $('<input type="hidden" value="${ dto.p_seq }" name="p_seq">');
			var title = $('<input type="hidden" value="${ dto.p_title }" name="p_title">');
			var contents = $('<input type="hidden" id="contents_hidden" name="p_contents">');
			
		    form.append(title).append(contents).append(seq);
		    contents.val(text);
		    
		    form.submit();
		});
		
		$("#delete_btn").on("click", function()
		{
			location.href="port_delete_do?p_seq=${dto.p_seq}";
		});
	
	</script>
		
</c:if>
</html>