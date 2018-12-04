<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
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

		<!-- CDN  -->
		<!-- Google Icon Font -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<!-- JQuery  -->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<!--  Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->

		<!-- local html  -->
		<link href="../../../img/global/logo/icon_title_1.jpg" rel="shortcut icon" />

		<link href="../../../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../../../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="../../../js/materialize.js"></script>
		<script src="../../../js/init.js"></script>

		<script src="../../../js/vue.min.js"></script>

		<script src="../../../js/init_sca.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/img/global/logo/icon_title_1.jpg" rel="shortcut icon">

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

		<script src="<%=path %>/js/vue.min.js"></script>

		<script src="<%=path %>/js/init_sca.js"></script>

		<script type="application/javascript">
			function checkLoginState(funPrepare, funSucc, funFail) {
				funPrepare();
				$.ajax({
					cache: true,
					type: "POST",
					url: url_checklogin,
					data: "",
					async: true,
					error: function(request) {},
					success: function(data) {
						var resp = JSON.parse(data);
						if("1" == resp.ERRCODE && "fail" == resp.ERRDESC) {
							funFail();
							setTimeout(function() {
								window.open("/CtrlCenter/LTYX/SCA/ReLoginPage.action")
							}, 2000);
						} else {
							funSucc();
						}
					}
				});
			}
		</script>

	</head>

	<body>

		<header>

		</header>

		<main>
			<div class="row">

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
		</main>

		<footer class="page-footer teal" id="global-frame-foot">
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