<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
%>
<html>
	<!--
		作者：ascleft@163.com
		 时间：2017-04-29
		 描述：
	-->

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
		<title>Code Martrix</title>

		<!-- CDN  -->
		<!-- Google Icon Font -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<!-- JQuery  -->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<!--  Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->

		<!-- local html  -->
		<link href="../../img/global/logo/icon_title_1.jpg" rel="shortcut icon" />

		<link href="../../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="../../js/materialize.js"></script>
		<script src="../../js/init.js"></script>

		<script src="../../js/vue.min.js"></script>

		<script src="../../js/init_tailorinfo.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/img/global/logo/icon_title_1.jpg" rel="shortcut icon">

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

		<script src="<%=path %>/js/vue.min.js"></script>

		<script src="<%=path %>/js/init_tailorinfo.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/img/CodeMartrix/main/weisuomeng.jpg" rel="shortcut icon" />

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

		<script type="application/javascript">
			var url_talk = "<%=path %>/Talk/Talk.action";

			var string_talk = "";

			function talk() {
				$.ajax({
					cache: true,
					type: "POST",
					url: url_talk,
					data: $('#talkForm').serialize(),
					async: false,
					error: function(request) {
						string_talk += "<p>";
						string_talk += "我：";
						string_talk += document.getElementById("tell").value;
						string_talk += "<br/>";
						string_talk += "服务器：";
						string_talk += "Connection error";
						string_talk += "<br/>";
						string_talk += "</p>";
						update();
						document.getElementById("tell").value = "";
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("succ" == resp.ERRDESC) {
							string_talk += "<p>";
							string_talk += "我：";
							string_talk += document.getElementById("tell").value;
							string_talk += "<br/>";
							string_talk += "服务器：";
							string_talk += resp.data;
							string_talk += "<br/>";
							string_talk += "</p>";
						} else {
							string_talk += "<p>";
							string_talk += "我：";
							string_talk += document.getElementById("tell").value;
							string_talk += "<br/>";
							string_talk += "服务器：";
							string_talk += resp.data;
							string_talk += "<br/>";
							string_talk += "</p>";
						}
						update();
						document.getElementById("tell").value = "";
					}
				});
			}

			function clr() {
				string_talk = "";
				$('#talk').children().remove();
				$('#talk').value = "";
			}

			function update() {
				var talk = document.getElementById("talk");
				talk.innerHTML = string_talk;
			}
		</script>

	</head>

	<body>
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="card ">
							<div class="card-content grey-text">
								<div id="talk">内容</div>
							</div>
							<div class="card-content grey-text">
								<form method="post" id="talkForm" onkeydown="if(event.keyCode==13)return false;">
									<div class="input-field">
										<input type="text" class="validate" name="tell" id="tell" value="">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col s6 m6 l6 center">
						<a class="waves-effect waves-light btn" onclick="clr()">清屏</a>
					</div>
					<div class="col s6 m6 l6 center">
						<a class="swaves-effect waves-light btn" onclick="talk()">提交</a>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>