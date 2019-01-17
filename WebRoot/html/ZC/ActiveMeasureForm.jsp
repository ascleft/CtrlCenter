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
		<title>喵喵喵？？？</title>

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
			var url_getActionForm = "/CtrlCenter/LTYX/Simulator/GetActionForm.action";
			var url_submitActionForm = "";

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
					data: $('#nothing').serialize(),
					async: true,
					error: function(request) {
						state_upload_error("无法连接到服务器");
						tinybug.message = request;

						//						tablelist = JSON.parse("[{\"NAME\":\"男士量体表\",\"LIST\":[{\"KEY\":\"1\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"胸围\"},{\"KEY\":\"2\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"腰围\"},{\"KEY\":\"2\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"臂长\"},{\"KEY\":\"2\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"臀围\"}]},{\"NAME\":\"女士量体表\",\"LIST\":[{\"KEY\":\"3\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"胸围\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"臀围\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"领围\"}]},{\"NAME\":\"童装体表\",\"LIST\":[{\"KEY\":\"5\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"胸高\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"身高\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"腰围\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"领围\"}]}]");
						//						state_upload_finish();
						//						insertSelector(tablelist);
						//						insertItems(tablelist);
					},
					success: function(data) {
						if(data.length < 2000) {
							var resp = JSON.parse(data);
							if("0" == resp.ERRCODE) {
								if("succ" == resp.ERRDESC) {
									tablelist = resp.data.TABLES;
									state_upload_finish();
									insertSelector(tablelist);
									insertItems(tablelist);
								} else {
									var desc = "提交失败<br/>智能错误分析：" + resp.data;
									state_upload_error(desc);
								}
							} else {
								state_upload_error("EC服务器通讯异常");
							}
						} else {
							state_upload_error("操作超时，请重新登录");
						}
						tinybug.message = data;
					}
				});
			}

			function insertSelector(tablelist_in) {
				var items = "";
				for(i = 0; i < tablelist_in.length; i++) {
					items += "<option>" + tablelist_in[i].NAME + "</option>";
				}

				$("#tableSelector").html(items);

				$('select').material_select();
			}

			function insertItems(tablelist_in) {

				var tableName = document.getElementById("tableSelector").value;

				var table;

				for(i = 0; i < tablelist_in.length; i++) {

					if(tableName == tablelist_in[i].NAME) {
						table = tablelist_in[i];
					}

				}
				var items = "";

				for(i = 0; i < table.LIST.length; i++) {

					items += "<div class=\"col s6 m4 l3\">";
					items += "<div class=\"input-field\">";
					items += "<input type=\"number\" class=\"validate\" name=\"height\" value=\"\">";
					items += "<label>" + table.LIST[i].NAME + "</label>";
					items += "</div>";
					items += "</div>";

				}

				var actionform = document.getElementById("actionform");

				actionform.innerHTML = items;

			};

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
					//					update();
				});
				$("input").bind("change", function() {
					//					update();
				});
				$("select").bind("change", function() {
					//					update();
					insertItems(tablelist);
				});

				modal_state_title = document.getElementById("modal_state_title");
				modal_state_progress_bar = document.getElementById("modal_state_progress_bar");
				btn_finish = document.getElementById("btn_finish");
				btn_download = document.getElementById("btn_download");

				tinybug = new Vue({
					el: '#tinybug',
					data: {
						seen: false
					}
				})

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
				<a id="logo-container" href="#" class="brand-logo white-text">专业版</a>
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
									<div class="input-field col s6 m6 l6">
										<select id="tableSelector">
										</select> <label>量体表</label>
										<div id="tableSelectors"></div>
									</div>
									<a class="col s6 m6 l6 waves-effect waves-light btn input-field" onclick="getActionForm()">获取表单列表</a>

								</div>
							</div>
						</div>
					</div>
					<form method="post" id="clothform">
						<div class="col s12 m12 l12">
							<div class="card-panel hoverable">
								<div class="card-content grey-text">
									<div class="row">
										<div id="actionform">
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>

					<div class="col s12 m12 l12">
						<div class="card-panel hoverable">
							<div class="card-content grey-text">
								<div class="row">
									<div class="col s6">
										<a class="col s12 m12 l12 waves-effect waves-light btn input-field">上一位</a>
									</div>
									<div class="col s6">
										<a class="col s12 m12 l12 waves-effect waves-light btn input-field">下一位</a>
									</div>
								</div>
							</div>
						</div>
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