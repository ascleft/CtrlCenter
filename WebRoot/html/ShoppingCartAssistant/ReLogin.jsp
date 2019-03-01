<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<!--
		
		作者:鸿安Adrian
		邮箱:ascleft@163.com
		时间:2019-01-16
		描述:购物车添加工具 SCA 3.0
		
		二次登录
		
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
			var url_login = "/CtrlCenter/LTYX/SCA/Login.action";

			var ajax_login = null;

			function login() {
				if($('#name').val().length == 0) {
					Materialize.toast('请输入账户', 1000);
				} else if($('#pwd').val().length == 0) {
					Materialize.toast('请输入密码', 1000);
				} else {
					submit();
				}
			}

			function submit() {

				state_now("checking");

				ajax_login = $.ajax({
					cache: true,
					type: "POST",
					url: url_login,
					data: $('#userform').serialize(),
					async: true,
					error: function(request) {
						state_now("fail");
						Materialize.toast('网络异常，无法连接到服务器', 1000);
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE) {
							if("succ" == resp.ERRDESC) {
								state_now("succ");
								Materialize.toast("登录成功", 1000);
								setTimeout("javascript:location.href='" + resp.data + "'", 1000);
							} else {
								state_now("fail");
								Materialize.toast(resp.data, 1000);
							}
						} else {
							state_now("fail");
							Materialize.toast("EC服务器通讯异常", 1000);
						}
					}
				});
			}

			function cancel() {
				if(ajax_login != null) {
					ajax_login.abort();
				}
			}

			function state_now(state) {
				if(state == "default") {
					$("#btn_login").show();
					$("#btn_cancel").hide();
					global_progress_loaded();
					global_notice_hide();
				}
				if(state == "checking") {
					$("#btn_login").hide();
					$("#btn_cancel").show();
					global_progress_loading();
					global_notice_show("云端比对中，请稍候");
				}
				if(state == "succ") {
					$("#btn_login").hide();
					$("#btn_cancel").hide();
					global_progress_loaded();
					global_notice_hide();
				}
				if(state == "fail") {
					$("#btn_login").show();
					$("#btn_cancel").hide();
					global_progress_loaded();
					global_notice_hide();
				}
			}

			$(document).ready(function() {
				state_now("default");
			});
		</script>

	</head>

	<body>

		<header>
			<nav class="top-nav teal">
				<div class="container">
					<div class="nav-wrapper">
						<a class="page-title">登录定制商品创建工具</a>
					</div>
				</div>
			</nav>
		</header>

		<main>
			<div class="container">
				<div class="section">
					<div class="card-panel">
						<div class="row">
							<div class=" col s12 m12 l12">
								<form method="post" id="userform">
									<div class="row">
										<div class="col s12 m12 l12">
											<h5 class="teal-text">
												系统检测到您的登录状态异常，请在本页重新登录。
											</h5>
											<h6 class="grey-text">
												登录成功后该页面将自动关闭，下单界面中已填写的信息不会丢失，您可以继续刚才的操作，<span style="color:red">核对无误</span>后即可提交。
											</h6>
										</div>
									</div>
									<div class="row">

										<div class="col s12 m4 l4">
											<div class="input-field">
												<input id="name" type="text" class="validate" name="name" value="">
												<label>USKIN企业会员手机号</label>
											</div>
										</div>
										<div class="col s12 m4 l4">
											<div class="input-field">
												<input id="pwd" type="password" class="validate" name="pwd" value="">
												<label>密码</label>
											</div>
										</div>
										<div class="input-field col s12 m4 l4">
											<select name="role">
												<option value="3">定制店</option>
												<option value="2">客户经理</option>
												<option value="1">定制顾问</option>
												<option value="4">临时账户登录</option>
											</select>
											<label>角色</label>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div class="row">
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

							<div class="col s12 m12 l12 center">
								<a class="col s12 m8 l6 offset-l3 offset-m2 btn" id="btn_login" onclick="login()" style="display: none;">登录</a>
							</div>
							<div class="col s12 m12 l12 center">
								<a class="col s12 m8 l6 offset-l3 offset-m2 btn" id="btn_cancel" onclick="cancel()" style="display: none;">取消</a>
							</div>

						</div>
					</div>
				</div>
			</div>
		</main>

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
				<div class="row" style="display:none; text-align:center">
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