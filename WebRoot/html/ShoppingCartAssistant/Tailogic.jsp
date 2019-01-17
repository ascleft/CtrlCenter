<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
	
	String ec_user_id=(String) session.getAttribute("ec_user_id");
	String ec_user_name=(String) session.getAttribute("ec_user_name");
	String ec_user_rank=(String) session.getAttribute("ec_user_rank");
	
	String QRurl=(String) session.getAttribute("QRurl");

	String menulist=(String) session.getAttribute("menulist");
	
	String list_LZX_01=(String) session.getAttribute("list_LZX_01");
	String list_LZX_02=(String) session.getAttribute("list_LZX_02");
	String list_LZX_03=(String) session.getAttribute("list_LZX_03");
	String list_LZX_04=(String) session.getAttribute("list_LZX_04");
	String list_LZX_08=(String) session.getAttribute("list_LZX_08");
	String list_LZX_120=(String) session.getAttribute("list_LZX_120");
	String list_LZX_06=(String) session.getAttribute("list_LZX_06");
	String list_LZX_17=(String) session.getAttribute("list_LZX_17");
	String list_LZX_26=(String) session.getAttribute("list_LZX_26");
	String list_LZX_13=(String) session.getAttribute("list_LZX_13");
	String list_zhidai=(String) session.getAttribute("list_zhidai");
	String list_color=(String) session.getAttribute("list_color");
	String list_kouzi=(String) session.getAttribute("list_kouzi");
	String list_easytype=(String) session.getAttribute("list_easytype");
	String list_lingcheng=(String) session.getAttribute("list_lingcheng");
	String list_mingxian=(String) session.getAttribute("list_mingxian");
	String list_cefeng=(String) session.getAttribute("list_cefeng");
	String list_qiantiao=(String) session.getAttribute("list_qiantiao");
	String list_chenbu=(String) session.getAttribute("list_chenbu");
	String list_weizhi_zhidai=(String) session.getAttribute("list_weizhi_zhidai");
	String list_weizhi_peise=(String) session.getAttribute("list_weizhi_peise");
	String list_baozhuang=(String) session.getAttribute("list_baozhuang_shop");	
	
%>

<html>
	<!--
		
		作者:鸿安Adrian
		邮箱:ascleft@163.com
		时间:2019-01-16
		描述:购物车添加工具 SCA 3.0
		
		定制店 优纤面料
		
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
			var url_addShoppingCart = "/CtrlCenter/LTYX/SCA/Main/SubmitCustomShopWoman.action";
			var url_getPrice = "/CtrlCenter/LTYX/SCA/Main/GetPriceCustomShopWoman.action";

			//提交到购物车
			function addShoppingCart() {
				state_upload_ing("正在提交订单信息，请稍候");
				$.ajax({
					cache: true,
					type: "POST",
					url: url_addShoppingCart,
					data: $('#mianForm').serialize(),
					async: true,
					error: function(request) {
						state_upload_error("无法连接到服务器");
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE) {
							if("succ" == resp.ERRDESC) {
								state_upload_finish("提交成功");
							} else {
								var desc = "提交失败<br/>智能错误分析：" + resp.data;
								state_upload_error(desc);
							}
						} else {
							state_upload_error("EC服务器通讯异常");
						}
					}
				});
			}

			//获取系统报价
			function getPrice() {
				state_loading("");
				$.ajax({
					cache: true,
					type: "POST",
					url: url_getPrice,
					data: $('#mianForm').serialize(),
					async: true,
					error: function(request) {
						state_loaded();
						$("#prices_system").val('999999999');
						$("#prices_now").val('999999999');
						$("#prices_desc").val('网络异常，请重新获取报价');
						state_ready("n");
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE) {
							if("succ" == resp.ERRDESC) {
								state_loaded();
								$("#prices_system").val(+resp.data);
								$("#prices_now").val(+resp.data);
								$("#prices_desc").val('');
								state_ready("y");
							} else {
								state_loaded();
								$("#prices_system").val('999999999');
								$("#prices_now").val('999999999');
								$("#prices_desc").val('报价异常，请重试:' + resp.data);
								state_ready("n");
							}
						} else {
							state_loaded();
							$("#prices_system").val('999999999');
							$("#prices_now").val('999999999');
							$("#prices_desc").val('EC服务器通讯异常，请重新获取报价');
							state_ready("n");
						}
					}
				});
			}

			function state_upload_ing(displaywords) {
				$("#modal_state").modal('open');
				$("#modal_state_title").html(displaywords);
				$("#modal_state_progress_bar").show();
				$("#btn_finish").hide();
				$("#btn_cancel").hide();
				$("#btn_download").hide();
			}

			function state_upload_finish(displaywords) {
				$("#modal_state_title").html(displaywords);
				$("#modal_state_progress_bar").hide();
				$("#btn_finish").show();
				$("#btn_cancel").hide();
				$("#btn_download").hide();
			}

			function state_upload_error(displaywords) {
				$("#modal_state_title").html(displaywords);
				$("#modal_state_progress_bar").hide();
				$("#btn_finish").hide();
				$("#btn_cancel").show();
				$("#btn_download").hide();
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
				});
				$("div#section1 input").bind("change", function() {
					state_ready("n");
				});
				$("div#section1 select").bind("change", function() {
					state_ready("n");
				});
				state_loaded();
				state_ready("n");


				use_DeliveryTime();
				use_stylebase_check();
				use_size();

				use_pbc_YX_08();
				use_pbc_kouzi();

				use_custom_weizhi_peise();

			})
		</script>

	</head>

	<body>

		<header>
			<nav class="teal" role="navigation">
				<div class="nav-wrapper container">
					<!-- 页面标题  -->
					<a id="logo-container " href="#" class="brand-logo white-text ">MTM测试</a>
					<!-- 导航菜单键（运动移动设备） -->
					<a href="#" data-activates="nav_menu_list " class="button-collapse ">
						<i class="material-icons white-text">menu</i>
					</a>
					<!-- 页面右上角菜单  （桌面设备）-->
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
					<div class="col s12 m12 l12" id="section2">
						<div class="card-panel hoverable">
							<div class="card-content grey-text">
								<div class="row">
									<div class="col  s12 m12 l12 teal-text">
										<p>Fabric</p>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
											<option value="DP02-02">DP02-02 </option>
											<option value="DP03-06">DP03-06 </option>
											<option value="DP04-02">DP04-02 </option>
											<option value="DP04-06">DP04-06 </option>
											<option value="DP05-02">DP05-02 </option>
											<option value="DP05-05">DP05-05 </option>
											<option value="DP06-01">DP06-01 </option>
											<option value="FB06-01">FB06-01 </option>
											<option value="FB06-02">FB06-02 </option>
											<option value="FB07-02">FB07-02 </option>
											<option value="L03-06">L03-06 </option>
											<option value="MC05-11">MC05-11 </option>
											<option value="SS12-06">SS12-06 </option>
											<option value="SS12-12">SS12-12 </option>
											<option value="ST02-01">ST02-01 </option>
											<option value="U03-1003">U03-1003 </option>
											<option value="U05-2002">U05-2002 </option>
											<option value="U06-2002">U06-2002 </option>
											<option value="U08-3006">U08-3006 </option>
											<option value="U08-4003">U08-4003 </option>
											<option value="U13-3003">U13-3003 </option>
											<option value="U13-4005">U13-4005 </option>
											<option value="U13-5006">U13-5006 </option>
										</select><label>Main Fabric</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="">未选择</option>
											<option value="CS02-04">CS02-04 </option>
											<option value="DP02-02">DP02-02 </option>
											<option value="DP03-06">DP03-06 </option>
										</select><label>Contrast fabric</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="">未选择</option>
											<option value="CS02-04">CS02-04 </option>
											<option value="DP02-02">DP02-02 </option>
											<option value="DP03-06">DP03-06 </option>
										</select><label>Patches fabric</label>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12" id="section2">
						<div class="card-panel hoverable">
							<div class="card-content grey-text">
								<div class="row">
									<div class="col  s12 m12 l12 teal-text">
										<p>Buttons&Threads</p>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Button thread</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Button hole</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Button</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Side Stitch Type</label>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12" id="section2">
						<div class="card-panel hoverable">
							<div class="card-content grey-text">
								<div class="row">
									<div class="col  s12 m12 l12 teal-text">
										<p>Body</p>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Fit type</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Yoke type</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Back style</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Shirt hem side cover</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Shirt hem side</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col s12 m12 l12" id="section2">
						<div class="card-panel hoverable">
							<div class="card-content grey-text">
								<div class="row">
									<div class="col  s12 m12 l12 teal-text">
										<p>Collar</p>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Collar ribbon color</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Collar style</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Collar patches fabric</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Collar stays choice</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col s12 m12 l12" id="section2">
						<div class="card-panel hoverable">
							<div class="card-content grey-text">
								<div class="row">
									<div class="col  s12 m12 l12 teal-text">
										<p>Cuff&Sleeve</p>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Sleeve style</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Cuff style</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Cuff slit type</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Sleeve Pleat choice</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Sleeve patches</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Cuff patches color</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Sleeve rolling strap choice</label>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12" id="section2">
						<div class="card-panel hoverable">
							<div class="card-content grey-text">
								<div class="row">
									<div class="col  s12 m12 l12 teal-text">
										<p>Front part</p>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Front edge extra</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>patches color</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Front edge style</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col s12 m12 l12" id="section2">
						<div class="card-panel hoverable">
							<div class="card-content grey-text">
								<div class="row">
									<div class="col  s12 m12 l12 teal-text">
										<p>Pocket</p>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Pocket placement</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Pocket style</label>
									</div>

								</div>
							</div>
						</div>
					</div>
					<div class="col s12 m12 l12" id="section2">
						<div class="card-panel hoverable">
							<div class="card-content grey-text">
								<div class="row">
									<div class="col  s12 m12 l12 teal-text">
										<p>Embroidery</p>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Monogram thread</label>
									</div>
									<div class="input-field col s12 m6 l4">
										<select name="LZX_120">
											<option value="CS02-04">CS02-04 </option>
										</select><label>Monogram font/type</label>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12" id="section3">
						<div class="card-panel hoverable">
							<div class="card-content grey-text">
								<div class="row">
									
									<div class="col s12 m12 l12">
										<div class="progress" id="load_state_progress_bar">
											<div class="indeterminate"></div>
										</div>
									</div>
									<div class="col s12 m12 l12">
										<a class="col s12 m12 l12 btn" onclick="getPrice()" id="addShoppingCart">提交MTM报价</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>

			<!--  模态框 -->
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
					<a id="btn_download" href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">下载</a>
				</div>
			</div>

		</main>

		<footer class="page-footer teal">
			<div class="container">
				<div class="row" style="display: none; ">
					<h5 class="center-align">					
						<img class="hoverable" src="<%=QRurl%>"/>
					</h5>
				</div>
			</div>
			<div class="footer-copyright">
				<div class="container">Made By ZhangChi 2017</div>
			</div>
		</footer>
	</body>

</html>