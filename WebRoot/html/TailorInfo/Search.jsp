<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
	
	String ec_user_id=(String) session.getAttribute("ec_user_id");
	String ec_user_name=(String) session.getAttribute("ec_user_name");
	String ec_user_rank=(String) session.getAttribute("ec_user_rank");
	
	String menulist=(String) session.getAttribute("menulist");
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
		<title>即时库存查询</title>

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
			var url_talk = "<%=path %>/UcsPlus/Search.action";

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

			function submitfilterform() {

				state_loading();

				$.ajax({
					cache: true,
					type: "GET",
					url: url_talk,
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

							String_html += "<table class=\"striped\">";
							String_html += "<thead>";
							String_html += "<tr>";
							String_html += "<th>USKIN编码 </th>";
							if(ec_user_rank < 10) {
								String_html += "<th class=\"hide-on-small-only\">物料编码 </th>";
							}
							String_html += "<th>库存 </th>";
							String_html += "<th>可用库存 </th>";
							String_html += "</tr>";
							String_html += "</thead>";
							String_html += "<tbody>";

							for(var i = 0; i < resp.data.length; i++) {
								String_html += "<tr>";
								String_html += "<td>";
								if("MLCK040" == resp.data[i].FSTOCKNUMBER) {
									String_html += "<a href=\"/CtrlCenter/LTYX/Tailor/TailorForm/ProPBC.action?code=";
									String_html += resp.data[i].FMATERIALNUMBER;
									String_html += "\">";
									String_html += resp.data[i].FMATERIALNUMBER;
									String_html += "</a>";
								} else {
									String_html += resp.data[i].FUSKIN;
								}
								String_html += "</td>";
								if(ec_user_rank < 10) {
									String_html += "<td class=\"hide-on-small-only\">";
									String_html += resp.data[i].FMATERIALNUMBER;
									String_html += "</td>";
								}
								String_html += "<td>";
								String_html += resp.data[i].FBASEQTY;
								String_html += "</td>";
								String_html += "<td>";
								String_html += resp.data[i].FBASEAVBQTY;
								String_html += "</td>";
								String_html += "</tr>";
							}

							String_html += "</tbody>";
							String_html += "</table>";

							ucs_table.innerHTML = String_html;

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
		<!--宽屏模式下拉框菜单-->
		<ul id="dropdown1" class="dropdown-content">
			<%= menulist %>
		</ul>
		<nav class="teal" role="navigation">
			<div class="nav-wrapper container">
				<a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons white-text">menu</i></a>
				<a id="logo-container" href="#" class="brand-logo white-text">即时库存</a>
				<ul class="right hide-on-med-and-down">
					<li>
						<a class="white-text" href="#">您好:
							<%=ec_user_name%>
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
							<%=ec_user_name%>
						</a>
					</li>
					<li class="teal lighten-1">
						<a class="white-text" onclick="logout()">注销 </a>
					</li>
					<%= menulist %>
				</ul>
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
					<div class="card-panel" id="ucs_table_card">
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
								<div class="col s12 m12 l12 teal-text">
									<p>输入USKIN编码或物料编码</p>

								</div>
								<div style="display: none;">
									<input name="rank">
									<%=ec_user_rank%>
									</input>
								</div>
								<div class="col s12 m6 l6 red-text">
									<div class="input-field">
										<input type="text" class="validate" name="name" value="MC01-01">
									</div>

								</div>
								<div class="col s7 m3 l3 teal-text">
									<select class="input-field" name="storeroom">
										<option value="0">鲁泰仓库</option>
										<option value="1">智能制造</option>
										<option value="2">客供仓库</option>
									</select>
								</div>
								<div class="col s5 m3 l3  white-text center">
									<div class="input-field">
										<a class=" waves-effect waves-light btn" onclick="submitfilterform()">确定</a>
									</div>
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
					<h5 class="center-align" style="display: none;">					
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