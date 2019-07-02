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
.box {
	text-align: center;
}

#btn_div {
	text-align: center;
}

.row {
	justify-content: center;
}
</style>

<script src="https://code.jquery.com/jquery-3.4.0.min.js">
	
</script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
	$(function()
    {
	    
	    $("#input_file").on("change", function()
	    {
		    var obj = this;
		    var file_kind = obj.value.lastIndexOf('.');
		    var file_name = obj.value.substring(file_kind + 1, obj.length);
		    var file_type = file_name.toLowerCase();
		    
		    check_file_type =
		    [
		        'jpg',
		        'gif',
		        'png',
		        'jpeg',
		        'bmp'
		    ];
		    
		    if(check_file_type.indexOf(file_type) == -1)
		    {
			    alert('이미지 파일만 선택할 수 있습니다.');
			    $("#input_file").val("");
			    $(".custom-file-label").text("");
		    }
		    else
		    {
			    var fileName = $(this).val().split("\\").pop();
			    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
		    }
	    });
	    
    });
    
    onload = function()
    {

    };
</script>
</head>
<body>

	<div class="container mt-5">
	
		<form action="change_profile_do" method="POST" enctype="multipart/form-data">
	
			<div class="custom-file mb-3">
				
				<div class="row">
				
					<div class="col-10">
					
						<input type="file" class="custom-file-input" id="input_file" name="img_file" accept="image/gif, image/jpeg, image/png">
						<label class="custom-file-label" for="customFile">Choose file</label>
						
					</div>
					
				</div>
				
				<div class="row">
				
					<div id="btn_div" class="col-10 mt-5">
					
						<input class="btn btn-warning" type="submit" value="수정">
						
					</div>
				
				</div>
				
			</div>
			
		</form>
	
	</div>


</body>
</html>