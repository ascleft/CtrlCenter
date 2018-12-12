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
		
		作者：ascleft@163.com
		时间：2017-11-20
		描述：
		购物车添加工具 SCA 2.0
		
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

		<script src="../../js/init_sca.js"></script>

		<!--local jsp   -->
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
			var url_addShoppingCart = "/CtrlCenter/LTYX/SCA/Main/CustomShopRepairSubmit.action";
			var url_getPrice = "/CtrlCenter/LTYX/SCA/Main/CustomShopRepairGetPrice.action";

			//提交到购物车
			function addShoppingCart() {
				checkLoginState(
					function prepare() {
						state_upload_ing("正在建立安全连接");
					},
					function succ() {
						state_upload_ing("正在将商品放入购物车，请稍候");
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
						})
					},
					function fail() {
						state_upload_error("当前登录状态异常,请在自动弹出的登录界面中完成验证<br/>完成验证后，关闭该对话框即可继续操作");
						Materialize.toast("登录状态异常", 1000);
						Materialize.toast("即将自动跳转", 1000);
					}
				);

			}

			//获取系统报价
			function getPrice() {
				checkLoginState(
					function prepare() {
						state_loading("");
					},
					function succ() {
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
										if("<%=ec_user_rank%>" == "21") {
											$("#prices_system").attr("type", "password");
											$("#prices_now").attr("type", "password");
										} else {
											$("#prices_system").attr("type", "number");
											$("#prices_now").attr("type", "number");
										}
										$("#prices_system").val(+resp.data);
										$("#prices_now").val(+resp.data);
										$("#prices_desc").val('');
										state_ready("y");
										show_TIPS1('' + resp.TIP1);
									} else {
										state_loaded();
										$("#prices_system").val('999999999');
										$("#prices_now").val('999999999');
										$("#prices_desc").val('报价异常，请重试:' + resp.data);
										state_ready("n");
										show_TIPS1('' + resp.TIP1);
									}
								} else {
									state_loaded();
									$("#prices_system").val('999999999');
									$("#prices_now").val('999999999');
									$("#prices_desc").val('EC服务器通讯异常，请重新获取报价');
									state_ready("n");
								}
							}
						})
					},
					function fail() {
						state_loaded();
						$("#prices_system").val('999999999');
						$("#prices_now").val('999999999');
						$("#prices_desc").val('请重新获取报价');
						state_ready("n");
						Materialize.toast("登录状态异常", 1000);
						Materialize.toast("即将自动跳转", 1000);
					}
				)
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

			function show_TIPS1(msg) {
				if(msg == "" || msg == null) {
					$("#TIP1").html('');
					$("#TIP1").hide();
				} else {
					$("#TIP1").html('' + msg);
					$("#TIP1").show();
				}
			}

			$(document).ready(function() {
				$("div#section1 input").bind("keyup", function() {
					state_ready("n");
				});
				$("div#section1 input").bind("change", function() {
					state_ready("n");
				});
				$("div#section1 textarea").bind("keyup", function() {
					state_ready("n");
				});
				$("div#section1 textarea").bind("change", function() {
					state_ready("n");
				});
				$("div#section1 select").bind("change", function() {
					state_ready("n");
				});

				state_loaded();
				state_ready("n");

				use_SubcontractTable();

			})
		</script>

	</head>

	<body>

		<header>
			<nav class="teal" role="navigation">
				<div class="nav-wrapper container">
					<!-- 页面标题  -->
					<a id="logo-container " href="#" class="brand-logo white-text ">定制店 返修订单</a>
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
					<div class="section">
						<div class="row">

							<div class="col s12 m12 l12" id="section1">

								<div class="card-panel hoverable">
									<div class="card-content grey-text">
										<div class="row">

											<div class="input-field col s12 m12 l12">
												<select id="Type" name="Type" multiple>

												</select> <label>修改项目（取工厂报价文件选项）</label>
											</div>

											<div class="col s12 m12 l12" style="display: none;">
												<div class="card-panel hoverable">
													<div class="row">
														<div class="input-field col s4 m3 l2">
															<input type="text" class="validate" id="name_c" name="name_c" value="">
															<label>C端项目</label>
														</div>
														<div class="input-field col s4 m3 l2">
															<input type="text" class="validate" id="price_c" name="price_c" value="">
															<label>C端价格</label>
														</div>
														<div class="input-field col s4 m3 l2">
															<input type="text" class="validate" id="time_c" name="time_c" value="">
															<label>C端工期</label>
														</div>
														<div class="input-field col s4 m3 l2">
															<input type="text" class="validate" id="tips_c" name="tips_c" value="">
															<label>C端项目描述</label>
														</div>
														<div class="input-field col s4 m3 l2">
															<input type="text" class="validate" id="name_f" name="name_f" value="">
															<label>F端项目</label>
														</div>
														<div class="input-field col s4 m3 l2">
															<input type="text" class="validate" id="price_f" name="price_f" value="">
															<label>F端价格</label>
														</div>
														<div class="input-field col s4 m3 l2">
															<input type="text" class="validate" id="time_f" name="time_f" value="">
															<label>F端工期</label>
														</div>
														<div class="input-field col s4 m3 l2">
															<input type="text" class="validate" id="tips_f" name="tips_f" value="">
															<label>F端项目描述</label>
														</div>
														<div class="input-field col s4 m3 l2">
															<input type="text" class="validate" id="operator_id" name="operator_id" value="<%=ec_user_id%>">
															<label>操作人ID</label>
														</div>
													</div>
												</div>
											</div>

											<div class="col s12 m6 l6">
												<div class="input-field">
													<input type="text" class="validate" name="FactoryID" value="">
													<label>原订单号</label>
												</div>
											</div>
											<div class="col s12 m6 l6">
												<div class="input-field">
													<input type="text" class="validate" name="ExpressNO" value=""> <label>寄厂单号（修改衬衫物流单号）</label>
												</div>
											</div>
											<div class="col s12 m12 l12">
												<div class="input-field">
													<textarea type="text" class="materialize-textarea" name="Tips" value=""></textarea>
													<label>生产备注（工艺修改说明）</label>
												</div>
											</div>
										</div>

									</div>
								</div>
							</div>

							<div class="col s12 m12 l12" id="section2">
								<div class="card-panel hoverable">
									<div class="card-content grey-text">
										<div class="row">

											<div class="col s12 m12 l12">
												<div class="card-panel hoverable" id="TIP1" style="display: none;">
												</div>
											</div>

											<div class="col s12 m12 l4">
												<div class="input-field">
													<input type="number" class="validate" name="prices_system" value="0" id="prices_system" readonly="true">
													<label>系统报价</label>
												</div>
											</div>
											<div class="col s6 m6 l4" style="display: none;">
												<div class="input-field">
													<input type="number" class="validate" name="prices_now" value="0" id="prices_now" readonly="true">
													<label>自主报价</label>
												</div>
											</div>
											<div class="col s12 m12 l8">
												<div class="input-field">
													<input type="text" class="validate" name="prices_desc" value="点击获取报价" id="prices_desc" readonly="true">
													<label>报价说明</label>
												</div>
											</div>
											<div class="col s12 m12 l12">
												<div class="progress" id="load_state_progress_bar">
													<div class="indeterminate"></div>
												</div>
											</div>
											<div class="col s12 m12 l12">
												<a class="col s12 m12 l12 btn" onclick="getPrice()" id="addShoppingCart">获取报价</a>
											</div>
											<div class="col s12 m12 l12">
												<a class="col s12 m12 l12 btn" onclick="addShoppingCart()" id="getPrice">提交到USKIN购物车</a>
											</div>
										</div>
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