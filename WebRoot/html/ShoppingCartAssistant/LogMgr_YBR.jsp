<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
	
	String ec_user_id=(String) session.getAttribute("ec_user_id");
	String ec_user_name=(String) session.getAttribute("ec_user_name");
	String ec_user_rank=(String) session.getAttribute("ec_user_rank");
	
	String QRurl=(String) session.getAttribute("QRurl");

	String menulist=(String) session.getAttribute("menulist");
	
%>

<html>
	<!--
		
		作者：ascleft@163.com
		时间：2017-11-20
		描述：
		购物车添加工具 SCA 2.0
		
		客户经理 优纤面料
		
	-->

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
		<title>定制商品创建工具</title>

		<!-- CDN -->
		<!-- Google Icon Font -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<!-- JQuery -->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<!-- Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->

		<!-- local html -->
		<link href="../../img/global/logo/icon_title_1.jpg" rel="shortcut icon" />

		<link href="../../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="../../js/materialize.js"></script>
		<script src="../../js/init.js"></script>

		<script src="../../js/vue.min.js"></script>

		<script src="../../js/init_sca.js"></script>

		<!--local jsp -->
		<link href="<%=path %>/img/global/logo/icon_title_1.jpg" rel="shortcut icon">

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

		<script src="<%=path %>/js/vue.min.js"></script>

		<script src="<%=path %>/js/init_sca.js"></script>

		<script type="application/javascript">
			//开启菜单
			function OpenMenu() {
				$('#nav_menu').sideNav('show');
			}
			//url定义
			//			var url_search = "http://localhost:8080/CtrlCenter/LTYX/Core/FindLog.action";
			var url_search = "/CtrlCenter/LTYX/Core/FindLog.action";
			var searchAjax = null;

			//提交到购物车
			function logSearchStart() {

				logSearchStop();
				state_loading();
				searchAjax = $.ajax({
					cache: true,
					type: "POST",
					url: url_search,
					data: $('#mianForm').serialize(),
					async: true,
					error: function(request) {
						state_upload_error("无法连接到服务器");
						state_loaded();
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE) {
							if("succ" == resp.ERRDESC) {
								makeTable(resp.data);
							} else {
								var desc = "查询失败<br/>智能错误分析：" + resp.data;
								state_upload_error(desc);
							}
						} else {
							state_upload_error("EC服务器通讯异常");
						}
						state_loaded();
					}
				});
			}

			//提交到购物车
			function logSearchStop() {
				if(searchAjax != null) {
					searchAjax.abort();
				}
				state_loaded();
			}

			function show_msg_new_page(msg, title, type) {
				var newwindow = window.open('_black', '', '', '');
				html_material = "";
				if(type == "link") {
					html_material += "<p>" + unescape(msg) + "</p>";
					html_material += "<br/>";
					html_material += "<br/>";
					html_material += "<a href=" + unescape(msg) + " target=\"目标\" title=\"EC请求连接\">再次提交</a>";
				} else {
					html_material += unescape(msg);
				}
				newwindow.document.write(html_material);
				newwindow.document.close();
			}

			function make_button_from_json(obj, type, name, color) {
				var tempHtml = "";
				if(JSON.stringify(obj).length > 2) {
					tempHtml += "<a class=\"" + color + "\" onclick=\"show_msg_new_page(\'";
					tempHtml += escape(JSON.stringify(obj));
					tempHtml += "\', \'" + name + "\', \'" + type + "\')\">" + name + "</a>";
				} else {
					tempHtml += "<br />";
				}
				return tempHtml;
			}

			function makeTable(logList) {

				//				var newwindow = window.open('', "_blank",'');
				//				newwindow.document.write("Hello World!");

				//window.open('', "_blank",'').document.write("");

				logList = jsonSort(logList, 'sortKeyWord', false);

				String_html = "";

				if(0 == logList.length) {

					String_html += "<p>当前条件无数据</p>";

				} else {

					String_html += "<table class=\"striped\">";
					String_html += "<thead>";
					String_html += "<tr>";
					String_html += "<th data-field=\"\">时间</th>";
					String_html += "<th data-field=\"\">帐号</th>";
					String_html += "<th data-field=\"\">关键词</th>";
					String_html += "<th data-field=\"\">快照</th>";
					String_html += "<th data-field=\"\">流转</th>";
					String_html += "<th data-field=\"\">进度</th>";

					String_html += "</tr>";
					String_html += "</thead>";
					String_html += "<tbody>";

					for(i = 0; i < logList.length; i++) {

						String_html += "<tr>";
						//时间
						String_html += "<td>"
						String_html += "第" + (i + 1) + "条";
						String_html += "<br /><br />";
						String_html += logList[i].m_time.replace(/ /g, "<br />");
						String_html += "<br /><br />";
						String_html += "<a class=\"grey-text\">" + logList[i].m_snapshot.time_total + "</a>";
						String_html += "</td>";

						//帐号
						String_html += "<td>店·" + logList[i].u_name + "<br />号·" + logList[i].u_tel + "<br />ID·" + logList[i].u_ecid + "</td>";
						//关键词
						String_html += "<td><a>" + logList[i].m_keys.replace(/, /g, "<br /><br />") + "</a></td>";
						//快照
						String_html += "<td>";
						String_html += make_button_from_json(logList[i].m_snapshot.src_req, "string", "入站", "teal-text")

						String_html += "<br/><br/><br/>";
						String_html += make_button_from_json(logList[i].m_snapshot.src_resp, "string", "出站", "blue-text")
						String_html += "</td>";
						//流转
						String_html += "<td>";
						String_html += make_button_from_json(logList[i].e_snapshot.src_req, "link", "请求", "teal-text");
						String_html += "<br/><br/><br/>";
						String_html += make_button_from_json(logList[i].e_snapshot.src_resp, "string", "响应", "blue-text");
						String_html += "</td>";
						//进度
						String_html += "<td><a>" + logList[i].m_tags.replace(/, /g, "<br />") + "</a></td>";

						String_html += "</tr>";

					}

					String_html += "</tbody>";
					String_html += "</table>";
				}

				$("#table_log_list").html(String_html);

			}

			function stopAddShoppingCart() {
				ajax_addShopingCart.abort();
			}

			function state_upload_ing(displaywords) {
				$("#modal_state").modal('open');
				$("#modal_state_title").html(displaywords);
				$("#modal_state_progress_bar").show();
				$("#btn_finish").hide();
				$("#btn_cancel").hide();
				$("#btn_stop").hide();

				setTimeout(function() {
					$("#btn_stop").show()
				}, 20000);
			}

			function state_upload_finish(displaywords) {
				$("#modal_state_title").html(displaywords);
				$("#modal_state_progress_bar").hide();
				$("#btn_finish").show();
				$("#btn_cancel").hide();
				$("#btn_stop").hide();
			}

			function state_upload_error(displaywords) {
				$("#modal_state_title").html(displaywords);
				$("#modal_state_progress_bar").hide();
				$("#btn_finish").hide();
				$("#btn_cancel").show();
				$("#btn_stop").hide();
			}

			function state_loading(displaywords) {
				$("#load_state_progress_bar").show();
			}

			function state_loaded(displaywords) {
				$("#load_state_progress_bar").hide();
			}

			function state_ready(state) {
				if(state == "y") {
					$("#getPrice").show();
					$("#addShoppingCart").hide();
				} else {
					$("#getPrice").hide();
					$("#addShoppingCart").show();
				}
			}

			$(document).ready(function() {
				$("div#section1 input").bind("keyup", function() {
					state_ready("n");
					logSearchStart();
				});
				$("div#section1 input").bind("change", function() {
					state_ready("n");
					logSearchStart();
				});
				$("div#section1 select").bind("change", function() {
					state_ready("n");
					logSearchStart();
				});
				state_loaded();
				state_ready("n");

			})
		</script>

	</head>

	<body>

		<header>
			<nav class="teal" role="navigation">
				<div class="nav-wrapper container">
					<!-- 页面标题 -->
					<a id="logo-container" href="#" class="brand-logo white-text ">日志管理
					</a>
					<!-- 导航菜单键（运动移动设备） -->
					<a href="#" data-activates="nav_menu_list " class="button-collapse ">
						<i class="material-icons white-text">menu</i>
					</a>
					<!-- 页面右上角菜单 （桌面设备）-->
					<ul id="nav_menu_mobile" class="right hide-on-med-and-down white-text">
						<li>
							<a class="white-text">当前用户:
								<%=ec_user_name%>
							</a>
						</li>
						<li>
							<a class="white-text" onclick="OpenMenu()">操作</a>
						</li>
					</ul>
					<!-- 菜单触发-->
					<a href="#" class="button-collapse" data-activates="nav_menu_list" id="nav_menu"></a>
					<!-- 菜单列表-->
					<ul id="nav_menu_list" class="side-nav teal-text">
						<div class="teal">
							<li>
								<a class=" white-text">您好:
									<%=ec_user_name%>
								</a>
							</li>
							<li>
								<a class=" white-text" onclick="logout() ">注销 </a>
							</li>
						</div>
						<%=menulist%>
					</ul>
				</div>
			</nav>
		</header>

		<main>

			<div class="container">
				<a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons white-text">menu</i></a>
				<form method="post" id="mianForm">
					<div class="section" id="section1">
						<div class="row">

							<div class="card-panel">
								<div class="row">
									<div class="col s12 m12 l12 teal-text">
										<p>分类</p>
									</div>
									<div class="input-field col s4 m4 l4" style="display: none;">
										<select name="tag_0">
											<option value="衣邦人">衣邦人</option>
										</select> <label>根</label>
									</div>
									<div class="input-field col s4 m4 l4">
										<select name="tag_1">
											<option value="all">全模块</option>
											<option value="优纤">优纤</option>
											<option value="客供">客供</option>
											<option value="返修">返修</option>
										</select> <label>功能模块</label>
									</div>

									<div class="input-field col s4 m4 l4">
										<select name="tag_2">
											<option value="all">全操作</option>
										</select> <label>操作步骤</label>
									</div>

									<div class="input-field col s4 m4 l4">
										<select name="tag_3">
											<option value="all">全状态</option>
											<option value="成功. ">成功</option>
											<option value="失败. ">失败</option>
										</select> <label>动作状态</label>
									</div>

									<div class="col s12 m12 l12 teal-text" style="display: none;">
										<p>ID依据</p>
									</div>
									<div class="col s3 m3 l3" style="display: none;">
										<div class="input-field">
											<select name="id_type">
												<option value="tel">手机号</option>
												<option value="ecid">EC用户ID</option>
												<option value="name">EC用户名</option>
												<option value="id">ID</option>
											</select> <label>ID源</label>
										</div>
									</div>
									<div class="col s9 m9 l9" style="display: none;">
										<div class="input-field">
											<input type="text" class="validate" name="id_code" value="">
											<label>ID</label>
										</div>
									</div>

									<div class="col s12 m12 l12 teal-text">
										<p>辅助</p>
									</div>

									<div class="input-field col s6 m4 l4">
										<input type="text" class="validate" name="key_1" value="">
										<label>主面料</label>
									</div>
									<div class="input-field col s6 m4 l4">
										<input type="text" class="validate" name="key_2" value="">
										<label>穿衣人</label>
									</div>
									<div class="input-field col s12 m4 l4">
										<input type="text" class="validate" name="key_3" value="">
										<label>刺绣</label>
									</div>

									<div class="input-field col s12 m12 l12" name="lines">
										<select name="lines">
											<option value="50">50</option>
											<option value="100">100</option>
											<option value="150">150</option>
											<option value="200">200</option>
											<option value="500">500</option>
										</select> <label>长度</label>
									</div>

									<div class="col s12 m12 l12" style="display: none;">
										<a class="col s12 m12 l12 btn" onclick="logSearchStart()">搜索</a>
									</div>
									<div class="col s12 m12 l12">
										<div class="progress" id="load_state_progress_bar">
											<div class="indeterminate"></div>
										</div>
									</div>

								</div>

							</div>

							<div class="card-panel">
								<div class="row">
									<div class="input-field col s12 m12 l12" id="table_log_list">
									</div>
								</div>
							</div>

						</div>
					</div>
				</form>
			</div>

			<div class="fixed-action-btn" onclick="logSearchStart()">
				<a class="btn-floating btn-large teal">
					<i class="large material-icons">refresh</i>
				</a>
			</div>

			<!-- 模态框 -->
			<div id="modal_state" class="modal">
				<div class="modal-content">
					<h4 id="modal_state_title">信息</h4>
					<div class="progress" id="modal_state_progress_bar">
						<div class="indeterminate"></div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="btn_finish" href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">确定</a>
					<a id="btn_cancel" href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">取消</a>
					<a id="btn_stop" onclick="stopAddShoppingCart()" class="modal-action modal-close waves-effect waves-green btn-flat">停止</a>
				</div>
			</div>

		</main>

		<footer class="page-footer teal">
			<div class="container">
				<div class="row" style="display:; text-align:center">
					<div class="col s6 m6 l6">
						<h6><a href="http://www.uskin.net.cn/index.php/wap/cart.html" target="_blank" class="white-text">进入USKIN购物车结算(手机版)</a></h6>
					</div>
					<div class="col s6 m6 l6">
						<h6><a href="http://www.uskin.net.cn/index.php/cart.html" target="_blank" class="white-text">进入USKIN购物车结算(电脑版)</a></h6>
					</div>
				</div>
			</div>
			<div class="footer-copyright">
				<div class="container">Made By ZhangChi 2018</div>
			</div>
		</footer>

	</body>

</html>