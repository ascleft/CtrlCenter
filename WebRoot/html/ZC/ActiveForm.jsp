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
		<title>接口联通性测试</title>

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
			var url_getActionForm = "/CtrlCenter/LTYX/OpenAPI/InterfaceTest.action";

			var modal_state_title;
			var modal_state_progress_bar;
			var btn_finish;
			var btn_download;

			var tinybug;

			var tablelist = "";

			function getActionForm() {
				state_upload_ing("正在提交订单信息，请稍候");

				$.ajax({
					cache: true,
					type: "POST",
					url: url_getActionForm,
					data: $('#paramform').serialize(),
					async: true,
					error: function(request) {
						state_upload_error("无法连接到服务器");
						tinybug.message = request;
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE) {
							if("succ" == resp.ERRDESC) {
								state_upload_finish();
								$('#resp').val(""+resp.data);
								$('#resp').trigger('autoresize');
							} else {
								var desc = "提交失败<br/>智能错误分析：" + resp.data;
								state_upload_error(desc);
							}
						} else {
							state_upload_error("EC服务器通讯异常");
						}
						tinybug.message = data;
					}
				});
			}

			function check_param_list() {

				var param_list = $("input[name^='param_key_']"); //选择所有的name属性以'param_key_'开头的input元素 
				var param_num = 0;

				for(i = 0; i < param_list.length; i++) {
					if(param_list[i].value.length > 0) {
						param_num++;
					}
				}

				if(param_list.length > 0) {
					if(param_list.length == param_num) {
						add_param_group("param_key_" + param_list.length, "param_value_" + param_list.length);
					}
				} else {
					add_param_group("param_key_" + "0", "param_value_" + "0");
				}

			}

			function add_param_group(param_key_name, param_value_name) {

				var param_group = "";

				param_group += "<div class=\"input-field col s12 m6 l4\">";
				param_group += "	<div class=\"card-panel hoverable\">";
				param_group += "		<div class=\"row\" >";
				param_group += "			<div class=\"input-field col s6\">";
				param_group += "				<input name=\"" + param_key_name + "\" type=\"text\" class=\"validate\" value=\"\">";
				param_group += "				<label>Key</label>";
				param_group += "			</div>";
				param_group += "			<div class=\"input-field col s6\">";
				param_group += "				<input name=\"" + param_value_name + "\" type=\"text\" class=\"validate\" value=\"\">";
				param_group += "				<label>Value</label>";
				param_group += "			</div>";
				param_group += "		</div>";
				param_group += "	</div>";
				param_group += "</div>";

				var param_list = $("#param_list").append(param_group);

				Materialize.updateTextFields();

				$("input").bind("keyup", function() {
					check_param_list();
				});
				$("input").bind("change", function() {
					check_param_list();
				});
				$("select").bind("change", function() {
					check_param_list();
				});

			}

			function state_upload_ing(displaywords) {
				$("#modal_state").modal('open');
				modal_state_title.innerHTML = displaywords;
				modal_state_progress_bar.style.display = "";
				btn_finish.style.display = "none";
				btn_download.style.display = "none";
			}

			function state_upload_finish(html_desc) {
				modal_state_title.innerHTML = html_desc;
				modal_state_progress_bar.style.display = "none";
				btn_finish.style.display = "none";
				btn_download.style.display = "";
			}

			function state_upload_finish() {
				$("#modal_state").modal('close');
				modal_state_title.innerHTML = "";
				modal_state_progress_bar.style.display = "none";
				btn_finish.style.display = "none";
				btn_download.style.display = "";
			}

			function state_upload_error(html_desc) {
				modal_state_title.innerHTML = html_desc;
				modal_state_progress_bar.style.display = "none";
				btn_finish.style.display = "";
				btn_download.style.display = "none";
			}

			$(document).ready(function() {
				$("input").bind("keyup", function() {
					check_param_list();
				});
				$("input").bind("change", function() {
					check_param_list();
				});
				//				$("select").bind("change", function() {
				//					check_param_list();
				//				});

				modal_state_title = document.getElementById("modal_state_title");
				modal_state_progress_bar = document.getElementById("modal_state_progress_bar");
				btn_finish = document.getElementById("btn_finish");
				btn_download = document.getElementById("btn_download");

				tinybug = new Vue({
					el: '#tinybug',
					data: {
						seen: false
					}
				});

				check_param_list();

			})
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
				<a id="logo-container" href="#" class="brand-logo white-text">接口联通性测试</a>
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
			<a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons white-text">menu</i></a>
			<div class="section">
				<div class="row">

					<div class="col s12 m12 l12">

						<div class="card-panel hoverable">
							<div class="card-content grey-text">
								<div class="row">

									<form method="post" id="paramform">

										<div class="input-field col l8 m8 s12">
											<div class="input-field col s12">
												<input name="target_url" type="text" class="validate" value="https://">
												<label>接口地址</label>
											</div>
										</div>
										<div class="input-field col l2 m2 s6">
											<div class="input-field col s12">
												<select name="http_type">
													<option value="GET">GET</option>
													<option value="POST">POST</option>
												</select>
												<label>提交类型</label>
											</div>
										</div>
										<div class="input-field col l2 m2 s6">
											<div class="input-field col s12">
												<select name="terminal_type">
													<option value="PC">PC</option>
													<option value="Android">Android</option>
													<option value="iPhone">iPhone</option>
													<option value="WeChat">WeChat</option>
												</select>
												<label>设备类型</label>
											</div>
										</div>

										<div id="param_list"></div>

									</form>
									<div class="input-field col s12">
										<div class="card-panel hoverable ">
											<textarea id="resp" type="text" class="materialize-textarea"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

					<div class="col s12 m12 l12">
						<a class="col col s12 m12 l12waves-effect waves-light btn input-field" onclick="getActionForm()">get提交</a>
					</div>

				</div>
			</div>

		</div>

		<!-- Modal Structure -->
		<div id="modal_state" class="modal">
			<div class="modal-content">
				<h4 id="modal_state_title">信息</h4>
				<div class="progress" id="modal_state_progress_bar">
					<div class="indeterminate"></div>
				</div>
			</div>
			<div class="modal-footer">
				<a id="btn_finish" href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">确定</a>
				<a id="btn_download" href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">下载</a>
			</div>
		</div>

		<div id="tinybug">
			<p v-if="seen">{{ message }}</p>
		</div>

		<footer class="page-footer teal">
			<div class="container">
				<div class="row" style="display: none;">
					<h5 class="center-align">					
						<img class="hoverable"
						src="http://pan.baidu.com/share/qrcode?w=150&h=150&url=http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/TailorForm/Pro.action"/>
					</h5>
				</div>
			</div>
			<div class="footer-copyright">
				<div class="container">Made By ZhangChi 2017</div>
			</div>
		</footer>
	</body>

</html>