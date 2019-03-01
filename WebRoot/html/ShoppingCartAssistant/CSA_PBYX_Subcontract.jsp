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
	String list_baozhuang=(String) session.getAttribute("list_baozhuang_aide");	
	
%>

<html>
	<!--
		
		作者：ascleft@163.com
		时间：2017-11-20
		描述：
		购物车添加工具 SCA 2.0
		
		定制顾问 其他商品
		
	-->

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
		<title>优纤下单工具</title>

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
			var url_price = "/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYXSubcontractGetPrice.action";
			var url_check = "/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYXSubcontractCheck.action";
			var url_addsc = "/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYXSubcontractSubmit.action";

			var ajax_price = null;
			var ajax_check = null;
			var ajax_addsc = null;

			//获取系统报价
			function getPrice() {
				checkLoginState(
					function prepare() {
						global_progress_loading("");
					},
					function succ() {
						global_progress_loading("");
						ajax_price = $.ajax({
							cache: true,
							type: "POST",
							url: url_price,
							data: $('#mianForm').serialize(),
							async: true,
							error: function(request) {
								global_progress_loaded();
								$("#prices_system").val('999999999');
								$("#prices_now").val('999999999');
								$("#prices_desc").val('网络异常，请重新获取报价');
								state_now("lost_price")

							},
							success: function(data) {
								var resp = JSON.parse(data);
								if("0" == resp.ERRCODE) {
									if("succ" == resp.ERRDESC) {
										global_progress_loaded();
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
										state_now("got_price")

									} else {
										global_progress_loaded();
										$("#prices_system").val('999999999');
										$("#prices_now").val('999999999');
										$("#prices_desc").val('报价异常，请重试:' + resp.data);
										state_now("lost_price")

									}
								} else {
									global_progress_loaded();
									$("#prices_system").val('999999999');
									$("#prices_now").val('999999999');
									$("#prices_desc").val('EC服务器通讯异常，请重新获取报价');
									state_now("lost_price")

								}
							}
						})
					},
					function fail() {
						global_progress_loaded();
						$("#prices_system").val('999999999');
						$("#prices_now").val('999999999');
						$("#prices_desc").val('请重新获取报价');
						state_now("lost_price")

						Materialize.toast("登录状态异常", 1000);
						Materialize.toast("即将自动跳转", 1000);
					}
				)
			}

			//表单内容校验
			function check() {
				if(ajax_check != null) {
					ajax_check.abort();
				}
				checkLoginState(
					function prepare() {
						global_notice_show("正在建立安全连接");
					},
					function succ() {
						global_notice_show("云端数据分析中");
						ajax_check = $.ajax({
							cache: true,
							type: "POST",
							url: url_check,
							data: $('#mianForm').serialize(),
							async: true,
							error: function(request) {
								global_notice_show("无法连接到服务器");
							},
							success: function(data) {
								var resp = JSON.parse(data);
								if("0" == resp.ERRCODE) {
									if("succ" == resp.ERRDESC) {
										global_notice_hide();
									} else {
										var desc = "智能分析:<br/>" + resp.data;
										global_notice_show(desc);
										console.log("else");
									}
									console.log("0");
								} else {
									global_notice_show("EC服务器通讯异常");
								}
							}
						})
					},
					function fail() {
						global_notice_show("当前登录状态异常,请在自动弹出的登录界面中完成验证<br/>完成验证后，关闭该对话框即可继续操作");
						Materialize.toast("登录状态异常", 1000);
						Materialize.toast("即将自动跳转", 1000);
					}
				);
			}

			//提交到购物车
			function addShoppingCart() {
				checkLoginState(
					function prepare() {
						global_modal_upload_start("正在建立安全连接");
					},
					function succ() {
						global_modal_upload_start("正在将商品放入购物车，请稍候");
						ajax_addsc = $.ajax({
							cache: true,
							type: "POST",
							url: url_addsc,
							data: $('#mianForm').serialize(),
							async: true,
							error: function(request) {
								global_modal_upload_error("无法连接到服务器");
							},
							success: function(data) {
								var resp = JSON.parse(data);
								if("0" == resp.ERRCODE) {
									if("succ" == resp.ERRDESC) {
										global_modal_upload_finish("提交成功");
									} else {
										var desc = "提交失败<br/>智能错误分析：" + resp.data;
										global_modal_upload_error(desc);
									}
								} else {
									global_modal_upload_error("EC服务器通讯异常");
								}
							}
						})
					},
					function fail() {
						global_modal_upload_error("当前登录状态异常,请在自动弹出的登录界面中完成验证<br/>完成验证后，关闭该对话框即可继续操作");
						Materialize.toast("登录状态异常", 1000);
						Materialize.toast("即将自动跳转", 1000);
					}
				);
			}

			function stopAddShoppingCart() {
				if(ajax_addsc != null) {
					ajax_addsc.abort();
				}
			}

			function state_now(state) {
				if(state == "default") {
					$("#getPrice").show();
					$("#addShoppingCart").hide();
					global_progress_loaded();
					global_notice_hide();
				}
				if(state == "lost_price") {
					$("#getPrice").show();
					$("#addShoppingCart").hide();
					check();
				}
				if(state == "got_price") {
					$("#getPrice").hide();
					$("#addShoppingCart").show();
				}
			}

			$(document).ready(function() {
				package_page();
			});

			function package_page() {
				page_build_timer = setInterval(page_build, 200);
				page_flush_timer = setInterval(page_flush, 200);
			}

			var page_build_timer;
			var page_flush_timer;
			var page_loader_func_list = [];
			var page_loader_func_state = [0, "开始构建页面", 0, "done"];

			function page_flush() {
				global_page_loading_determinate(page_loader_func_state[0], page_loader_func_state[1]);
				if(page_loader_func_state[0] > 1) {
					clearInterval(page_build_timer);
					clearInterval(page_flush_timer);
					state_now("default");
					global_page_loaded();
				}
			}

			function page_build() {
				if(page_loader_func_list.length == 0) {
					page_loader_func_list.push(use_table_subcontract);
					page_loader_func_list.push(page_input_binder);
				}
				if(page_loader_func_state[3] == "done") {
					page_loader_func_state[3] = "doing";
					var fn = page_loader_func_list[page_loader_func_state[2]];
					page_loader_func_state[1] = fn() + "加载完成";
					page_loader_func_state[2]++;
					page_loader_func_state[3] = "done";
				}
				page_loader_func_state[0] = (page_loader_func_state[2] + 1) / page_loader_func_list.length;
			}

			function page_input_binder() {
				$("div#section1 input").bind("keyup", function() {
					state_now("lost_price");
				});
				$("div#section1 input").bind("change", function() {
					state_now("lost_price");
				});
				$("div#section1 select").bind("change", function() {
					state_now("lost_price");
				});

				console.log("finish bind");

				return "绑定页面输入事件";
			};
		</script>

	</head>

	<body>

		<header>
			<nav class="teal" role="navigation">
				<div class="nav-wrapper container">
					<!-- 页面标题  -->
					<a id="logo-container " href="#" class="brand-logo white-text ">客户经理 其他商品</a>
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

					<div class="section" id="section_loading">
						<div class="row">

							<div class="col s12">
								<div class="progress">
									<div id="section_loading_determinate" class="determinate" style="width: 0%"></div>
								</div>
							</div>

							<div class="col s12 center">
								<h6 id="section_loading_title">页面构建中,请稍候...</h6>
							</div>

							<div class="col s12 center">
								<div class="preloader-wrapper small active" style="display: none;">
									<div class="spinner-layer spinner-green-only">
										<div class="circle-clipper left">
											<div class="circle"></div>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>

					<div class="section" id="section_content" style="opacity: 0;">
						<div class="row">
							<div class="col s12 m12 l12" id="section1">
								<!-- <ul class="collapsible popout"data-collapsible="accordion"> -->
								<ul class="collapsible teal lighten-5" data-collapsible="accordion">
									<li>
										<div class="collapsible-header active">
											<i class="material-icons">perm_identity</i>用户信息
										</div>
										<div class="collapsible-body">
											<div class="row">
												<div class="col s12 m6 l4">
													<div class="input-field">
														<input type="text" class="validate" name="customer_name" value="">
														<label>穿衣人姓名（必填）</label>
													</div>
												</div>
												<div class="col s12 m6 l4">
													<div class="input-field">
														<input type="text" class="validate" name="customer_tel" value="">
														<label>穿衣人电话（必填）</label>
													</div>
												</div>
												<div class="col s12 m6 l4" style="display: none;">
													<div class="input-field">
														<input type="text" class="validate" name="customer_address" value="">
														<label>收货地址</label>
													</div>
												</div>
												<div class="col s12 m6 l4">
													<div class="input-field">
														<input type="text" class="validate" name="customer_tel_target" value="">
														<label>会员帐号（电话）（必填）</label>
													</div>
												</div>
												<div class="col s12 m12 l4">
													<div class="input-field">
														<input type="text" class="validate" name="customer_tips" value="">
														<label>生产备注</label>
													</div>
												</div>
											</div>
											<div style="display: none;">
												<input type="text" class="validate" name="operator_id" value="<%=ec_user_id%>"><label>操作人ID</label>
												<input type="text" class="validate" name="operator_name" value="<%=ec_user_name%>"><label>操作人名称</label>
											</div>
										</div>
									</li>
									<li>
										<div class="collapsible-header">
											<i class="material-icons">view_carousel</i>商品类型
										</div>
										<div class="collapsible-body">
											<div class="row">
												<div class="input-field col s12 m6 l4" id="subcontract_design_code_div">
													<select name="design_code" id="subcontract_design_code">
													</select> <label>商品编号</label>
												</div>
												<div class="input-field col s12 m6 l4">
													<input name="uskin_code" type="text" class="validate">
													<label>面料编号</label>
												</div>
												<div class="input-field col s12 m6 l4" id="subcontract_price_type_div">
													<select name="subcontract_price_type" id="subcontract_price_type">
													</select> <label>价格类型</label>
												</div>
												<div class="input-field col s12 m6 l4" id="subcontract_peice_final_div">
													<select name="subcontract_peice_final" id="subcontract_peice_final">
													</select> <label>价格</label>
												</div>
												<div class="input-field col s12 m6 l4" id="subcontract_fabric_unit_cost_div">
													<select name="subcontract_fabric_unit_cost" id="subcontract_fabric_unit_cost">
													</select> <label>面料价位</label>
												</div>
												<div class="input-field col s12 m6 l4" id="subcontract_process_cost_div">
													<select name="subcontract_process_cost" id="subcontract_process_cost">
													</select> <label>工艺类型</label>
												</div>
												<div class="input-field col s6 m3 l2" id="subcontract_fabric_length_div">
													<input name="subcontract_fabric_length" id="subcontract_fabric_length" type="number" class="validate" value="0" readonly="true">
													<label>面料用量</label>
												</div>
												<div class="input-field col s6 m3 l2" id="subcontract_ratio_div">
													<input name="subcontract_ratio" id="subcontract_ratio" type="number" class="validate" value="0" readonly="true">
													<label>辅助参数</label>
												</div>
											</div>
										</div>
									</li>
								</ul>
							</div>

							<div class="col s12 m12 l12" id="section2">
								<div class="card-panel hoverable">
									<div class="card-content grey-text">
										<div class="row">
											<div class="col s6 m6 l4">
												<div class="input-field">
													<input type="number" class="validate" name="prices_system" value="0" id="prices_system" readonly="true">
													<label>系统报价</label>
												</div>
											</div>
											<div class="col s6 m6 l4">
												<div class="input-field">
													<input type="number" class="validate" name="prices_now" value="0" id="prices_now">
													<label>自主报价</label>
												</div>
											</div>
											<div class="col s12 m12 l4">
												<div class="input-field">
													<input type="text" class="validate" name="prices_desc" value="点击获取报价" id="prices_desc">
													<label>差价说明</label>
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

											<div class="col s12 m12 l12">
												<a class="col s12 m12 l12 btn" onclick="getPrice()" id="getPrice">获取报价</a>
											</div>
											<div class="col s12 m12 l12">
												<a class="col s12 m12 l12 btn" onclick="addShoppingCart()" id="addShoppingCart">提交到USKIN购物车</a>
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
				</div>
			</div>
			<div class="footer-copyright">
				<div class="container">Powered by ZhangChi 2019</div>
			</div>
			<div class="fixed-action-btn toolbar">
				<a class="btn-floating  teal darken-1">
					<i class="large material-icons">shopping_cart</i>
				</a>
				<ul>
					<li class="waves-effect waves-light">
						<a href="http://www.uskin.net.cn/index.php/cart.html">USKIN电脑版</a>
					</li>
					<li class="waves-effect waves-light">
						<a href="http://www.uskin.net.cn/index.php/wap/cart.html">USKIN手机版 </a>
					</li>
					<li class="waves-effect waves-light">
						<a href="http://www.utailor.com.cn/index.php/cart.html">君奕电脑版</a>
					</li>
					<li class="waves-effect waves-light">
						<a href="http://www.utailor.com.cn/index.php/wap/cart.html">君奕手机版</a>
					</li>
				</ul>
			</div>

		</footer>

	</body>