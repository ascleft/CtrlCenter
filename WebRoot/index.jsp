<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
%>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
		<title>衬衫信息录入平台</title>

		<!-- CDN  -->
		<!-- Google Icon Font -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<!-- JQuery  -->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<!--  Vue.js-->
		<!--<script src="https://unpkg.com/vue/dist/vue.js"></script>-->
		<!--  Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->

		<!-- local html  -->
		<link href="./img/CodeMartrix/main/weisuomeng.jpg" rel="shortcut icon" />

		<link href="./css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="./css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="./js/materialize.js"></script>
		<script src="./js/init.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/img/global/logo/icon_title_1.jpg" rel="shortcut icon">

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

		<script type="text/javascript">
			onload = function() {
				setTimeout(go, 3000);
			};

			function go() {
				location.href = "<%=path %>/LTYX/SCA/LoginPage.action";
			}
		</script>
	</head>

	<body>
		<div class="container">
			<div class="row">
				<div class="col s1 m2 l3">
				</div>
				<div class="card ">
					<div class="col s10 m8 l6">
						<div class="card-image">
							<img src="img/global/main/pre_gif_2.gif">
							<span class="card-title ">正在跳转，请稍候</span>
						</div>
						<div class="progress">
							<div class="indeterminate"></div>
						</div>
						<div class="card-action">
							<a href="<%=path %>/LTYX/SCA/LoginPage.action">立刻进入</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>