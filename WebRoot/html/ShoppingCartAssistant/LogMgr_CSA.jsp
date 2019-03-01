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
		
		作者:鸿安Adrian
		邮箱:ascleft@163.com
		时间:2019-01-16
		描述:购物车添加工具 SCA 3.0
		
		客户经理 日志管理
		
	-->

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
		<title>定制商品创建工具</title>

		<!-- CDN  -->
		<!-- Google Icon Font -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<!-- JQuery  -->
		<!--<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>-->
		<!--  Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->
		<!-- Vue.js  -->
		<!--<script src="../../js/vue.min.js"></script>-->

		<!-- YXN  -->
		<script src="../../js/jquery-3.2.1.min.js"></script>
		<script src="<%=path %>/js/jquery-3.2.1.min.js"></script>

		<!-- local html  -->
		<link href="../../css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<script src="../../js/materialize.js"></script>
		<script src="../../js/init.js"></script>
		
		<script src="../../js/init_sca.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>
		
		<script src="<%=path %>/js/init_sca.js"></script>

		<script type="application/javascript">
			//开启菜单
			function OpenMenu() {
				$('#nav_menu').sideNav('show');
			}
			//url定义
			//			var url_search = "http://www.uskin.net.cn:8080/CtrlCenter/LTYX/Core/FindLog.action";
			var url_search = "/CtrlCenter/LTYX/Core/FindLog.action";
			var ajax_search = null;
			var ajax_resubmit = null;

			var logList = null;

			//搜索日志
			function searchLog() {

				if(ajax_search != null) {
					ajax_search.abort();
				}

				global_progress_loading();
				global_notice_show("查询中,请稍候");

				ajax_search = $.ajax({
					cache: true,
					type: "POST",
					url: url_search,
					data: $('#mianForm').serialize(),
					async: true,
					error: function(request) {
						global_notice_show("无法连接到服务器");
						global_progress_loaded();
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE) {
							if("succ" == resp.ERRDESC) {
								logList = resp.data;
								makeTable();
								global_notice_hide();
							} else {
								var desc = "查询失败<br/>智能错误分析：" + resp.data;
								global_notice_show(desc);
							}
						} else {
							global_notice_show("EC服务器通讯异常");
						}
						global_progress_loaded();
					}
				});
			}

			function stopSearchLog() {
				if(ajax_search != null) {
					ajax_search.abort();
				}
			}

			function button_packager(list_index, button_name, function_name, color) {
				var btn_html = "";
				if(function_name == "请求") {
					if(logList[list_index].e_snapshot.src_req.length > 0) {
						return btn_html;
					}
				}
				if(function_name == "响应") {
					if(logList[list_index].e_snapshot.src_resp.length > 0) {
						return btn_html;
					}
				}
				btn_html += "<a class=\"" + color + "\" onclick=\"" + function_name + "(\'";
				btn_html += list_index;
				btn_html += "\', \'";
				btn_html += button_name;
				btn_html += "\')\">" + button_name + "</a>";
				return btn_html;
			}

			function active_button_json_2_page_show(list_index, perception) {
				var tempHtml = "";
				if(perception == "入站") {
					tempHtml += logList[list_index].m_snapshot.src_req;
				}
				if(perception == "出站") {
					tempHtml += JSON.stringify(logList[list_index].m_snapshot.src_resp);
				}
				if(perception == "请求") {
					tempHtml += "<p>";
					tempHtml += logList[list_index].e_snapshot.src_req;
					tempHtml += "</p>";
					tempHtml += "<br/><br/>";
					tempHtml += logList[list_index].m_time;
					tempHtml += "<br/><br/>";
					tempHtml += logList[list_index].m_keys;
					tempHtml += "<br/><br/>";
					tempHtml += "<a href=" + logList[list_index].e_snapshot.src_req + " target=\"目标\" title=\"EC请求连接\">再次提交" + logList[list_index].m_time + "</a>";
				}
				if(perception == "响应") {
					tempHtml += JSON.stringify(logList[list_index].e_snapshot.src_resp);
				}
				var newwindow = window.open('_black', '', '', '');
				newwindow.document.write(tempHtml);
				newwindow.document.close();
			}

			function active_button_json_2_action_resubmit(list_index) {
				var tmp_url = logList[list_index].e_snapshot.src_req;
				//				resubmit_ajax(tmp_url);
				window.open(tmp_url, '', 'width=300,height=400');
			}

			//再次提交到购物车
			function resubmit_ajax(tmp_url) {

			}

			function makeTable() {

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
						String_html += button_packager(i, "入站", "active_button_json_2_page_show", "teal-text");

						String_html += "<br/><br/><br/>";
						String_html += button_packager(i, "出站", "active_button_json_2_page_show", "blue-text")
						String_html += "</td>";
						//流转
						String_html += "<td>";

						var temp_tips = "";
						temp_tips += logList[i].m_time;

						String_html += button_packager(i, "请求", "active_button_json_2_page_show", "teal-text");
						String_html += "<br/><br/><br/>";
						String_html += button_packager(i, "响应", "active_button_json_2_page_show", "blue-text");

						if($("#quick_resubmit").val() == "Y") {
							String_html += "<br/><br/><br/>";
							String_html += button_packager(i, "快速提交", "active_button_json_2_action_resubmit", "red-text");
							String_html += "<br/>";
						}

						String_html += "</td>";

						//进度
						String_html += "<td><a>" + logList[i].m_tags.replace(/, /g, "<br />") + "</a></td>";

						String_html += "</tr>";

					}

					String_html += "</tbody>";
					String_html += "</table>";
				}

				$("#table_log_list").html(String_html);

			};

			$(document).ready(function() {
				$("div#section1 input").bind("keyup", function() {
					searchLog();
				});
				$("div#section1 input").bind("change", function() {
					searchLog();
				});
				$("div#section1 select").bind("change", function() {
					searchLog();
				});

				global_progress_loaded();

				searchLog();

			});
		</script>

	</head>

	<body>

		<header>
			<nav class="teal" role="navigation">
				<div class="nav-wrapper container">
					<!-- 页面标题 -->
					<a id="logo-container" href="#" class="brand-logo white-text ">日志管理 客户经理
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
											<option value="客户经理">客户经理</option>
										</select> <label>根</label>
									</div>
									<div class="input-field col s4 m4 l4">
										<select name="tag_1">
											<option value="all">全模块</option>
											<option value="优纤">优纤</option>
											<option value="男装">男装</option>
											<option value="女装">女装</option>
											<option value="设计师">设计师款</option>
											<option value="客供">客供</option>
										</select> <label>功能模块</label>
									</div>

									<div class="input-field col s4 m4 l4">
										<select name="tag_2">
											<option value="all">全操作</option>
											<option value="提交购物车-">提交</option>
											<option value="报价-">报价</option>
										</select> <label>操作步骤</label>
									</div>

									<div class="input-field col s4 m4 l4">
										<select name="tag_3">
											<option value="all">全状态</option>
											<option value="成功. ">成功</option>
											<option value="失败. ">失败</option>
										</select> <label>动作状态</label>
									</div>

									<div class="col s12 m12 l12 teal-text">
										<p>ID依据</p>
									</div>
									<div class="col s3 m3 l3">
										<div class="input-field">
											<select name="id_type">
												<option value="tel">手机号</option>
												<option value="ecid">EC用户ID</option>
												<option value="name">EC用户名</option>
												<option value="id">ID</option>
											</select> <label>ID源</label>
										</div>
									</div>
									<div class="col s9 m9 l9">
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

									<div class="input-field col s10 m10 l10" name="lines">
										<select name="lines">
											<option value="50">50</option>
											<option value="100">100</option>
											<option value="150">150</option>
											<option value="200">200</option>
											<option value="500">500</option>
										</select> <label>长度</label>
									</div>
									<div class="input-field col s2 m2 l2">
										<select id="quick_resubmit">
											<option value="N">关闭</option>
											<option value="Y" disabled="disabled">开启</option>
										</select> <label>快速提交</label>
									</div>

									<!--进度条-->
									<div id="global_frame_progress_div" class="col s12 m12 l12">
										<div class="progress" id="global_frame_progress_loading_bar">
											<div class="indeterminate"></div>
										</div>
									</div>
									<!--提示-->
									<div id="global_frame_notice_div" class="col s12 m12 l12">
										<p id="global_frame_notice_board" class="grey-text">
										</p>
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

			<div class="fixed-action-btn" onclick="searchLog()">
				<a class="btn-floating btn-large teal">
					<i class="large material-icons">refresh</i>
				</a>
			</div>

			<!--  模态框 -->
			<div id="global_frame_modal_div" class="modal">
				<div class="modal-content">
					<h4 id="global_frame_modal_state_title">信息</h4>
					<div class="progress" id="global_frame_modal_progress_bar">
						<div class="indeterminate"></div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="global_frame_modal_btn_finish" onclick="modal_fun_finish()" class="modal-action modal-close waves-effect waves-green btn-flat">确定</a>
					<a id="global_frame_modal_btn_cancel" onclick="modal_fun_cancel()" class="modal-action modal-close waves-effect waves-green btn-flat">取消</a>
					<a id="global_frame_modal_btn_terminate" onclick="modal_fun_terminate()" class="modal-action modal-close waves-effect waves-green btn-flat">终止</a>
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
				<div class="container">Powered by ZhangChi 2019</div>
			</div>
		</footer>

	</body>

</html>