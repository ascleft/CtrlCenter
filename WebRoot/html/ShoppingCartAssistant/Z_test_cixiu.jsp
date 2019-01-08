<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
	
	String ec_user_id=(String) session.getAttribute("ec_user_id");
	String ec_user_name=(String) session.getAttribute("ec_user_name");
	String ec_user_rank=(String) session.getAttribute("ec_user_rank");
	

	
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
		<!--<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>-->
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
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
			var url_price = "/CtrlCenter/LTYX/SCA/Main/CustomShopPBYXManGetPrice.action";
			var url_check = "/CtrlCenter/LTYX/SCA/Main/CustomShopPBYXManCheck.action";
			var url_addsc = "/CtrlCenter/LTYX/SCA/Main/CustomShopPBYXManSubmit.action";

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
						global_notice_loading("正在建立安全连接");
					},
					function succ() {
						global_notice_loading("云端数据分析中");
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
			};

			$(document).ready(function() {

				state_now("default");

				use_embroidery_new();

				$("div#section1 input").bind("keyup", function() {
					state_now("lost_price");
				});
				$("div#section1 input").bind("change", function() {
					state_now("lost_price");
				});
				$("div#section1 select").bind("change", function() {
					state_now("lost_price");
				});

				state_now("default");

			});
		</script>

	</head>

	<body>

		<header>
			<nav class="teal" role="navigation">
				<div class="nav-wrapper container">
					<!-- 页面标题  -->
					<a id="logo-container " href="#" class="brand-logo">定制店 优纤面料 男装</a>
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
								<!-- <ul class="collapsible popout"data-collapsible="expandable"> -->
								<ul class="collapsible teal lighten-5" data-collapsible="accordion ">
									<li>
										<div class="collapsible-header active">
											<i class="material-icons">mode_edit</i>刺绣
										</div>
										<div class="collapsible-body">
											<div class="row">
												<div class="card-panel">
													<div class="row">
														<div class="input-field col s12">
															<select name="embroidery_section_1_type">
																<option value="needless">不使用刺绣</option>
																<option value="char">文字刺绣</option>
																<option value="pic">图案刺绣</option>
															</select> <label>刺绣位置1</label>
														</div>
														<div class="col s12 m12 l12" id="embroidery_section_1_char_div">
															<div class="row">

																<div class="col s12 teal-text">
																	<p>刺绣文字</p>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_1_char_font">
																	</select> <label>刺绣文字字体</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_1_char_size">
																	</select> <label>刺绣文字高度</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_1_char_location">
																	</select> <label>刺绣文字位置</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_1_char_color">
																	</select> <label>刺绣文字颜色</label>
																</div>
																<div class="col s12 m6 l4">
																	<div class="input-field validate">
																		<input type="text" name="embroidery_section_1_char_content" value="">
																		<label>刺绣文字内容</label>
																	</div>
																</div>
															</div>
														</div>
														<div class="col s12 m12 l12" id="embroidery_section_1_pic_div">
															<div class="row">
																<div class="col s12 m12 l12 teal-text">
																	<p>刺绣图案</p>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_1_pic_font">
																	</select> <label>刺绣图案系列</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_1_pic_size">
																	</select> <label>刺绣图案高度</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_1_pic_location">
																	</select> <label>刺绣图案位置</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_1_pic_color">
																	</select> <label>刺绣图案颜色</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_1_pic_content">
																	</select> <label>刺绣图案编号</label>
																</div>
															</div>
														</div>

													</div>
												</div>
												<div class="card-panel">
													<div class="row">
														<div class="input-field col s12">
															<select name="embroidery_section_2_type">
																<option value="needless">不使用刺绣</option>
																<option value="char">文字刺绣</option>
																<option value="pic">图案刺绣</option>
															</select> <label>刺绣位置2</label>
														</div>
														<div class="col s12 m12 l12" id="embroidery_section_2_char_div">
															<div class="row">

																<div class="col s12 teal-text">
																	<p>刺绣文字</p>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_2_char_font">
																	</select> <label>刺绣文字字体</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_2_char_size">
																	</select> <label>刺绣文字高度</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_2_char_location">
																	</select> <label>刺绣文字位置</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_2_char_color">
																	</select> <label>刺绣文字颜色</label>
																</div>
																<div class="col s12 m6 l4">
																	<div class="input-field validate">
																		<input type="text" name="embroidery_section_2_char_content" value="">
																		<label>刺绣文字内容</label>
																	</div>
																</div>
															</div>
														</div>
														<div class="col s12 m12 l12" id="embroidery_section_2_pic_div">
															<div class="row">
																<div class="col s12 m12 l12 teal-text">
																	<p>刺绣图案</p>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_2_pic_font">
																	</select> <label>刺绣图案系列</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_2_pic_size">
																	</select> <label>刺绣图案高度</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_2_pic_location">
																	</select> <label>刺绣图案位置</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_2_pic_color">
																	</select> <label>刺绣图案颜色</label>
																</div>
																<div class="input-field col s12 m6 l4">
																	<select name="embroidery_section_2_pic_content">
																	</select> <label>刺绣图案编号</label>
																</div>
															</div>
														</div>

													</div>
												</div>
												<div class="card-panel">
													<div class="row">
														<div class="col s12 m12 l12 teal-text">
															<p>客供刺绣</p>
														</div>
														<div class="input-field col s12 m12 l12">
															<select name="cixiu_kegong_num">
																<option value="0">0个</option>
																<option value="1">1个</option>
																<option value="2">2个</option>
																<option value="3">3个</option>
															</select> <label>客供图案刺绣数量</label>
														</div>
													</div>
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