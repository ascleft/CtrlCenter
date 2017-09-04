<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
	
	String ec_user_rank =(String) session.getAttribute("ec_user_rank");
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

		<script type="application/javascript">
			var url_get_page_count = "<%=path %>/LTYX/Tailor/List/GetPageCount.action";
			var url_get_list = "<%=path %>/LTYX/Tailor/List/GetList.action";

			var ucs_tip_card = document.getElementById("ucs_tip_card");
			var ucs_tip = document.getElementById("ucs_tip");
			var ucs_progress_bar = document.getElementById("ucs_progress_bar");

			var ucs_table_card = document.getElementById("ucs_table_card");
			var ucs_table = document.getElementById("ucs_table");

			var ec_user_rank = parseInt("<%=ec_user_rank%>");

			$().ready(function() {
				ucs_tip_card = document.getElementById("ucs_tip_card");
				ucs_tip = document.getElementById("ucs_tip");
				ucs_progress_bar = document.getElementById("ucs_progress_bar");

				ucs_table_card = document.getElementById("ucs_table_card");
				ucs_table = document.getElementById("ucs_table");

				state_defult();
			})

			function getList() {

				state_loading();

				$.ajax({
					cache: true,
					type: "POST",
					url: url_get_list,
					data: $('#talkForm').serialize(),
					async: true,
					error: function(request) {
						state_error("无法连接服务器");

					},

					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE && "succ" == resp.ERRDESC) {
							state_answer();
							var String_html = "";
							for(var i = 0; i < resp.data.length; i++) {
								String_html += "<div class=\"col s6 m4 l3\">";
								String_html += "<div class=\"card blue-grey darken-1\">";
								String_html += "<div class=\"card-content white-text\">";
								String_html += "<span class=\"card-title\">";
								String_html += resp.data[i].operator_name;
								String_html += "</span>";
								String_html += "<p>";
								String_html += resp.data[i].operator_name;
								String_html += "</p>";
								String_html += "</div>";
								String_html += "</div>";
								String_html += "</div>";
							}

							String_html += "</ul>";

							//							ucs_table.html(String_html)  ;
							//							set_innerHTML('ucs_table', String_html);
							$("#ucs_table").html(String_html);

						} else {
							state_error(resp.data);
						}
					}
				});
			}

			function state_defult() {
				ucs_tip_card.style.display = "none";
				ucs_tip.textContent = "";
				ucs_progress_bar.style.display = "none";

				ucs_table_card.style.display = "none";
				ucs_table.innerHTML = "";
			}

			function state_loading() {
				ucs_tip_card.style.display = "";
				ucs_tip.textContent = "查询中，请稍候...";
				ucs_progress_bar.style.display = "";

				ucs_table_card.style.display = "none";
				ucs_table.innerHTML = "";
			}

			function state_answer() {
				ucs_tip_card.style.display = "none";
				ucs_tip.textContent = "";
				ucs_progress_bar.style.display = "none";

				ucs_table_card.style.display = "";
				ucs_table.innerHTML = "";
			}

			function state_error(string_desc) {
				ucs_tip_card.style.display = "";
				ucs_tip.textContent = string_desc;
				ucs_progress_bar.style.display = "none";

				ucs_table_card.style.display = "none";
				ucs_table.innerHTML = "";
			}
		</script>

	</head>

	<body>
		<!-- Dropdown Structure -->
		<ul id="dropdown1" class="dropdown-content">
			<li>
				<a href="http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/TailorForm/Pro.action">极简模式</a>
			</li>
			<li>
				<a href="http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/TailorForm/Pro.action">高级模式</a>
			</li>
			<li>
				<a href="http://61.50.122.58:8029/CtrlCenter/UcsPlus/GetSearchPage.action">库存查询</a>
			</li>
		</ul>
		<nav class="teal" role="navigation">
			<div class="nav-wrapper container">
				<a id="logo-container" href="#" class="brand-logo white-text">衬衫信息录入平台</a>
				<ul class="right hide-on-med-and-down">
					<li>
						<a class="white-text" href="#">您好:
							<%=(String) session.getAttribute("ec_user_name")%>
						</a>
					</li>
					<li>
						<a class="white-text" onclick="logout()">注销 </a>
					</li>
					<li>
						<a class="white-text dropdown-button" data-activates="dropdown1">操作选择</a>
					</li>
				</ul>
				<ul id="nav-mobile" class="side-nav">
					<li class="teal">
						<a class="white-text" href="#">您好:
							<%=(String) session.getAttribute("ec_user_name")%>
						</a>
						<a class="white-text" onclick="logout()">注销 </a>
					</li>
					<li>
						<a href="http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/TailorForm/Pro.action">极简模式</a>
					</li>
					<li>
						<a href="http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/TailorForm/Pro.action">高级模式</a>
					</li>
					<li>
						<a href="http://61.50.122.58:8029/CtrlCenter/UcsPlus/GetSearchPage.action">库存查询</a>
					</li>
				</ul>
				<a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons white-text">menu</i> </a>
			</div>
		</nav>
		<div class="container">
			<div class="section">
				<!--状态板-->
				<div class="card-panel" id="ucs_tip_card">
					<div class="card-content grey-text">
						<div class="row">
							<p class="col s12 m12 l12" id="ucs_tip">tips</p>
							<div class="progress" id="ucs_progress_bar">
								<div class="indeterminate"></div>
							</div>
						</div>
					</div>
				</div>

				<!--展示表-->
				<div class="col s12 m12 l12">
					<div id="ucs_table_card">
						<div class="card-content grey-text">
							<div class="row">
								<div id="ucs_table">
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--控制器-->
				<div class="col s12 m12 l12">
					<div class="card-panel">
						<form method="post" id="talkForm" onkeydown="if(event.keyCode==13)return false;">
							<div class="row">
								<div style="display: none;">
									<input name="rank">
									<%=ec_user_rank%>
									</input>
								</div>
								<div class="col s2 m2 l2 offset-s3 offset-m3 offset-l3">
									<input type="number" class="validate teal-text center-align" name="page_target" value="1">
								</div>

								<div class="col s2 m2 l2">
									<h5 class=" center-align">/100</h5>
								</div>

								<div class="col s4 m4 l4">
									<h6 class=" waves-effect waves-light white-text btn " onclick="getList()">GO</h6>
								</div>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
		<footer class="page-footer teal">
			<div class="container">
				<div class="row">
					<h5 class="center-align">					
						<img class="hoverable" src="http://pan.baidu.com/share/qrcode?w=150&h=150&url=http://61.50.122.58:8029/CtrlCenter/UcsPlus/GetSearchPage.action"/>
						</h5>
				</div>
			</div>
			<div class="footer-copyright">
				<div class="container">Made By ZhangChi 2017</div>
			</div>
		</footer>
	</body>

</html>