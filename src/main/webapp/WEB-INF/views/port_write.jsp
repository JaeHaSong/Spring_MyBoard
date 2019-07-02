<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Noto+Sans+KR|Acme&display=swap"
	rel="stylesheet">
<title>게시글 작성</title>
    
<style>
.container {
	text-align: center;
	margin-top: 5%;
}

img {
	width: 500px !important;
	height: auto;
}        
</style>
    
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>

    
<script>
	$(function()
	{
		//summerNote 기본 설정
		$("#summernote").summernote
		({
			height : 400,
		        
			minHeight : null,
		        
			maxHeight : null,
		        
			focus : true,
		        
			lang : 'ko-KR',
		        
			callbacks :
			{
				onImageUpload : function(files, editor, welEditable)
				{
					for(var i = files.length - 1 ; i >= 0 ; i--)
					{
						sendFile(files[i], this);
					}
				}
			}
		});
	    
	    //summerNote에 이미지 업로드 시 바로 보일 수 있게 해주는 AJAX
	    function sendFile(file, editor)
	    {
		    var data = new FormData();
		    
		    data.append('file', file);
		    
		    $.ajax(
		    {
		        data : data,
		        
		        type : "POST",
		        
		        url : 'board_img_ajax',
		        
		        cache : false,
		        
		        contentType : false,
		        
		        enctype : 'multipart/form-data',
		        
		        processData : false
		    
		    }).done(function(data)
		    {
			    $(editor).summernote('editor.insertImage', "/resources/${loginId}/"+data);
			    
		    }).fail(function(data)
		    {
			    alert("error");
		    });
		    
	    }
	  	
	    
	    
	    
	    
  });
  
  onload = function()
  {
	    
  };   
</script>
</head>
<body>

	<div class="container">

		<div class="row">
		
			<div class="col-12">
			
				<label>제목</label><br> 
				<input type="text" id="title_text" class="form-control" name="title" required><br>
			
			</div>
		
		</div>
		
		<div class="row">
		
			<div class="col-12">
			
				<label>내용</label><br>
				<div id="summernote" contenteditable="true"></div><br>
			
			</div>
		
		</div> 
		
		<div class="row">
			
			<div class="col-9 text-left">
			
				<input id="file" class="btn" name="img_file" type="file"  accept=".jpg"> 
			
			</div> 
			
			<div class="col-3 text-right">
			
				<input id="regNotice" class="btn btn-info" type="button" value="작성 완료">
			
			</div>
		
		</div>
		 
		
		

	</div>
	
</body>
<c:if test="${ rewriting == null }">

<script>
		
	$(function()
	{
		//글 작성 완료  버튼 누를 시 form 생성 후 POST 방식으로 보낸다.
	    $('#regNotice').on("click", function(e)
	    {
	    	var form = $('<form></form>');
	    	form.attr('action', 'port_write_do');
	    	form.attr('method', 'POST');
	    	form.attr('enctype', 'multipart/form-data');
	    	form.appendTo('body');
	               		
	    	var text = $(".note-editable").html();
	    	
		    if((text != "<br>") && ($("#title_text").val() != ""))
		    {
		    	var title = $('<input type="hidden" name="title">');
		    	var contents = $('<input type="hidden" id="contents_hidden" name="contents">');
		    	var writer = $('<input type="hidden" value='+"${ loginId }"+' name="writer">');
		    	
		   		form.append($("#file")).append(title).append(contents).append(writer);
		    	$("#contents_hidden").val(text);
		    	title.val($("#title_text").val());
		               			    
		    	form.submit();
		    }
		    else
		    {
		   		alert("작성 내용을 확인하세요.");
		    }
	    });
	});
		
</script>

</c:if>
<c:if test="${ rewriting == 'Y' }">

<script>
		
	$(function()
	{
		$("#title_text").val("${ title }");
		$(".note-editable").html(`${ contents }`);
		
		//글 작성 완료  버튼 누를 시 form 생성 후 POST 방식으로 보낸다.
	    $('#regNotice').on("click", function(e)
	    {
	    	var form = $('<form></form>');
	    	form.attr('action', 'port_update_do');
	    	form.attr('method', 'POST');
	    	form.attr('enctype', 'multipart/form-data');
	    	form.appendTo('body');
	               		
	    	var text = $(".note-editable").html();
	    	
		    if((text != "<br>") && ($("#title_text").val() != ""))
		    {
		    	var seq = $('<input type="hidden" value="${seq}" name="seq">');
		    	var writer = $('<input type="hidden" value='+"${ loginId }"+' name="writer">');
		    	var title = $('<input type="hidden" name="title">');
		    	var contents = $('<input type="hidden" id="contents_hidden" name="contents">');
		    	
		   		form.append($("#file")).append(seq).append(writer).append(title).append(contents);
		    	$("#contents_hidden").val(text);
		    	title.val($("#title_text").val());
		               			    
		    	form.submit();
		    }
		    else
		    {
		   		alert("작성 내용을 확인하세요.");
		    }
	    });
	});
		
</script>

</c:if>
</html>