<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OpenAPI - 대기오염지수</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous">
</script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous">
	
</script>
</head>
<body>
	<div class="p-5" align="center">
		<h2>대피소 정보</h2>
		<br>
		<br>
		<div class="input-group">
		<!--  
			<span class="input-group-text">지역</span> <select id="location"
				class="form-select w-25">
				<option>경상북도</option>
				<option>경상남도</option>
				<option>울산광역시</option>
				<option>부산광역시</option>
				<option>서울특별시</option>
			</select>
			-->
			<button id="btn1" class="btn btn-success">지진해일 대피소 정보 보기</button>
		</div>
		<br>
		<br>
		<table class="table table-hover table-striped text-center">
			<thead>
				<tr class="table-success">
					<th>시군구 이름</th>
					<th>지구명</th>
					<th>대피소 이름</th>
					<th>주소</th>
					<th>전화번호</th>
					<th>관리청</th>
					<th>수용인원</th>
				</tr>
			</thead>
			<tbody id="tbod"></tbody>
		</table>
		 <div id="pagingArea">
         </div>
	</div>
	<script>
		$(function() {
			let pageBtn = ""
			for(let i=0; i<=10; i++) {
			pageBtn += "<ul class='pagination'>" +
						"<li class='page-item' style='cursor: pointer' id='pBtn'+'"+i+"'>"+ i + "</li>" + "</ul>"
			}
			$("#pagingArea").html(pageBtn)
			// JSON 형식에 대한 데이터 처리
			/*
			$("#btn1").click(function() {
				
				$.ajax({
					url: 'air.do',
					data: {
						location: $("#location").val()
					},
					success : function(result) {
						let answer = "";
						for(let i=0; i<result.length; i++) {
									  answer += "<tr>"
											 +"<td>"+result[i].stationName+"</td>"
											 +"<td>"+result[i].dataTime+"</td>"
											 +"<td>"+result[i].khaiValue+"</td>"
											 +"<td>"+result[i].pm10Value+"</td>"
											 +"<td>"+result[i].so2Value+"</td>"
											 +"<td>"+result[i].coValue+"</td>"
											 +"<td>"+result[i].no2Value+"</td>"
											 +"<td>"+result[i].o3Value+"</td>"
											 +"</tr>"
						
						}
						$("#tbod").html(answer); 
						
					},
					error : function() {
						console.log("대기오염 조회 통신 실패!")
					}
				})
			})
		})
		*/
		/* XML 형식에 대한 데이터 처리 
		$("#btn1").click(function() {
				
				$.ajax({
					url: 'air.do',
					data: {
						location: $("#location").val()
					},
					success : function(result) {
						// console.log(result);
						// xml 데이터 처리
						// 1) 응답 데이터 내의 실제 데이터가 담겨진 요소 선택
						let itemArr = $(result).find("item");
						// console.log(itemArr);
						
						// 2) 반복문을 통해 실제 데이터가 담긴 요소들에 접근하여 동적으로 생성
						let answer = "";
						itemArr.each(function(i, item) {
							//console.log(item);
							//console.log($(item).find("stationName"));			// <stationName>강남구</stationName>
							//console.log($(item).find("stationName").text()); // 강남구
							answer += "<tr>"
								 +"<td>"+$(item).find("stationName").text()+"</td>"
								 +"<td>"+$(item).find("dataTime").text()+"</td>"
								 +"<td>"+$(item).find("khaiValue").text()+"</td>"
								 +"<td>"+$(item).find("pm10Value").text()+"</td>"
								 +"<td>"+$(item).find("coValue").text()+"</td>"
								 +"<td>"+$(item).find("no2Value").text()+"</td>"
								 +"<td>"+$(item).find("so2Value").text()+"</td>"
								 +"<td>"+$(item).find("o3Value").text()+"</td>"
								 +"</tr>"
						})
						
						// 3) 동적으로 만든 요소를 화면에 출력
						$("#tbod").html(answer);
						
					},
					error : function() {
						console.log("대기오염 조회 통신 실패!")
					}
				})
			})
		})
		*/
		$("#btn1").click(function() {
			
			$.ajax({
				url: 'shelter.do',
				data: {
					
				},
				success : function(result) {
					
					let list = result.TsunamiShelter[1].row;
					console.log(list[0])
					let answer = "";
					for(let i=0; i<list.length; i++) {
								  
								answer += "<tr>"
										 +"<td>"+list[i].sigungu_name+"</td>"
										 +"<td>"+list[i].remarks+"</td>"
										 +"<td>"+list[i].shel_nm+"</td>"
										 +"<td>"+list[i].address+"</td>"
										 +"<td>"+list[i].tel+"</td>"
										 +"<td>"+list[i].manage_gov_nm+"</td>"
										 +"<td>"+list[i].shel_av+"</td>"
										 +"</tr>"
					
					}
					$("#tbod").html(answer); 
					
				},
				error : function() {
					console.log("대피소 조회 통신 실패!")
				}
			})
		})
	})
	</script>
</body>
</html>