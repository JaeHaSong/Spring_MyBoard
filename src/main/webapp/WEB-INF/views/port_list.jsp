<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

.title_div {
	border: 1px solid black;
	box-sizing: border-box;
}

.title_div img {
/* 	width: 100%; */
/* 	height: auto; */
/* 	max-width: 379px; */
/* 	max-height: 210px; */
	width: 379px;
	height: 210px;
}

* {
	text-decoration: none !important;
}

a {
	color: black !important;
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
		    location.href = "port_detail_page?p_seq=" + $(this).attr("name");
	    });
	    
// 	    $(document).on("click", ".navi_btn", function()
// 	    {
// 		    location.href = "port_list_page?page=" + $(this).val();
// 	    });
	    $(document).on("click", "#prev_btn", function()
	    {
		    location.href = "port_list_page?page=${ startNavi-1 }"
		    {
			    startNavi - 1
		    };
	    });
	    $(document).on("click", "#next_btn", function()
	    {
		    location.href = "port_list_page?page=${ endNavi+1 }"
		    {
			    endNavi + 1
		    };
	    });
	    
	    if("${ currentPage }" % 5 == 0)
	    {
		    $($(".navi_btn")[4]).css("color", "#ffba00");
	    }
	    else
	    {
		    $($(".navi_btn")["${ currentPage % 5 - 1 }"]).css("color", "#ffba00");
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

				<c:forEach var="i" begin="1" end="${ listsize }" step="1" varStatus="status">

					<c:choose>

						<c:when test="${status.current==2}">

							<div class="title_div row my-5">

								<div class="col-4 p-0">
									<a class="detail_page_a" href="port_detail_page?p_seq=${ list[i-1].p_seq }"> 
										<img class="thumnail" src="${ list[i-1].p_thumnail }"> 
									</a>
								</div>

								<div class="col-8">

									<div class="row my-3 h-100">


										<div class="col-7 text-center my-auto">

											<h1>
												<a class="detail_page_a" href="port_detail_page?p_seq=${ list[i-1].p_seq }"> ${ list[i-1].p_title } </a>
											</h1>

										</div>


										<div class="col-5 my-auto">

											<div class="row my-3">

												<div class="col-12 text-center">

													<h3>${ list[i-1].p_writer }</h3>

												</div>

											</div>

											<div class="row my-3">

												<div id="time_div" class="col-12 text-center">${fn:substring(list[i-1].p_write_time,0,19) }</div>

											</div>

										</div>

									</div>

								</div>

							</div>
						</c:when>

						<c:otherwise>
							
							<div class="title_div row my-3">

								<div class="col-8">

									<div class="row my-3 h-100">

										<div class="col-5 my-auto">

											<div class="row my-3">

												<div class="col-12 text-center">

													<h3>${ list[i-1].p_writer }</h3>

												</div>

											</div>

											<div class="row my-3">

												<div id="time_div" class="col-12 text-center">
													${fn:substring(list[i-1].p_write_time,0,19) }
												</div>

											</div>

										</div>

										<div class="col-7 text-center my-auto">

											<h1>
												<a class="detail_page_a" href="port_detail_page?p_seq=${ list[i-1].p_seq }">
												 	${ list[i-1].p_title } 
												</a>
											</h1>

										</div>

									</div>

								</div>

								<div class="col-4 p-0">
									
									<a class="detail_page_a" href="port_detail_page?p_seq=${ list[i-1].p_seq }"> 
										<img class="thumnail" src="${ list[i-1].p_thumnail }"> 
									</a>
									
								</div>

							</div>
							
						</c:otherwise>

					</c:choose>



				</c:forEach>

			</div>


			<div id="navi_div" class="col-12 text-center">

				<c:if test="${ needPrev }">
					<input id="prev_btn" type="button" class="btn btn-link" value="<">
				</c:if>

				<c:forEach var="i" begin="${ startNavi }" end="${ endNavi }">
					<a href="http://localhost/port_list_page?page=${ i }"><input class="btn navi_btn" name="${ i }" type="button" value="${ i }"></a>
				</c:forEach>

				<c:if test="${ needNext }">
					<input id="next_btn" type="button" class="btn btn-link" value=">">
				</c:if>

			</div>


			<div class="col-12 text-right">

				<a href="port_write_page"><input id="port_write_btn" class="btn btn-info" type="button" value="글쓰기"></a> 
				<a href="/"><input id="back_btn" class="btn btn-info" type="button" value="돌아가기"></a>

			</div>

		</div>

	</div>

</body>
</html>