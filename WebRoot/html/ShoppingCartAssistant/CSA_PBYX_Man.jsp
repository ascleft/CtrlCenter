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
			var url_addShoppingCart = "/CtrlCenter/LTYX/SCA/Main/SubmitCustomShopAidePBYX.action";
			var url_getPrice = "/CtrlCenter/LTYX/SCA/Main/GetPriceCustomShopAidePBYX.action";

			//提交到购物车
			function addShoppingCart() {
				state_upload_ing("正在提交订单信息，请稍候");

				checkLoginState();

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
				});
				$("div#section1 input").bind("change", function() {
					state_ready("n");
				});
				$("div#section1 select").bind("change", function() {
					state_ready("n");
				});
				state_loaded();
				state_ready("n");

				use_lzx11();

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
					<a id="logo-container " href="#" class="brand-logo white-text ">客户经理 优纤面料 男装</a>
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
								<!-- <ul class="collapsible popout"data-collapsible="accordion"> -->
								<ul class="collapsible teal lighten-5" data-collapsible="accordion">
									<li>
										<div class="collapsible-header">
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
														<label>穿衣人电话</label>
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
												<div class="col s12 m12 l12">
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
											<i class="material-icons">view_carousel</i>面料 主唛 包装
										</div>
										<div class="collapsible-body">
											<div class="row">

												<div class="col s12 m14 l4">
													<div class="input-field">
														<input type="text" class="validate" name="uskin_code" value=""> <label>面料编号（USKIN 编号）</label>
													</div>
												</div>
												<div class="input-field col s6 m6 l4">
													<select name="tailor_type">
														<option value="YX-00-02">长袖</option>
														<option value="YX-00-01">短袖</option>
													</select> <label>长袖/短袖</label>
												</div>
												<div class="input-field col s12 m6 l4">
													<select name="easy_type">
														<%=list_easytype%>
													</select> <label>宽松度</label>
												</div>
												<div class="input-field col s6 m6 l4">
													<select name="YX_08" id="YX_08">
														<%=list_LZX_08%>
														<option>客供</option>
													</select> <label>主唛</label>
												</div>
												<div class="input-field col s6 m6 l4" id="YX_08_div">
													<input id="YX_08_pbc" type="text" class="validate" name="YX_08" value="">
													<label>客供主唛说明</label>
												</div>
												<div class="input-field col s6 m6 l4">
													<select name="YX_09">
														<%=list_baozhuang%>
													</select> <label>包装</label>
												</div>
												<div class="input-field col s6 m6 l4">
													<select id="delivery_time_table_section_1" name="order_mtm_type">
													</select> <label>订单类型</label>
												</div>
												<div class="input-field col s6 m6 l4">
													<select id="delivery_time_table_section_2" name="order_production_count">
													</select> <label>起订量（件）</label>
												</div>
												<div class="input-field col s6 m6 l4">
													<select id="delivery_time_table_section_3" name="order_delivery_time">
													</select> <label>交期（工作日）（单量单裁DP衬衫加工费在原基础上加20元/件，交期顺延1个工作日）</label>
												</div>
												<div class="input-field col s6 m6 l4">
													<select id="delivery_time_table_section_4" name="order_processing_cost">
													</select> <label>工艺类型</label>
												</div>
												<div class="input-field col s12 m12 l12 red-text" >
													<p>注意：1.下单次日开始计算交期。 2.团单每加急一天加收相应加工费的5%。</p>
												</div>
											</div>
										</div>
									</li>

									<li>
										<div class="collapsible-header">
											<i class="material-icons">settings_overscan</i>成衣尺寸
										</div>
										<div class="collapsible-body">
											<div class="row">
												<div class="input-field col s12 m6 l4">
													<select id="measure_type" name="measure_type">
														<option value="成衣尺寸">成衣尺寸</option>
														<option value="号衣尺码">号衣尺码</option>
														<option value="量体尺寸">量体尺寸</option>
														<option value="needless">不新增尺寸信息</option>
													</select> <label>尺寸类型</label>
												</div>

												<div class="col s12 m12 l12">
													<div class="row" id="size_list">
														<div class="input-field col s12 m6 l4">
															<select name="size">
																<option value="2176">男式衬衫紧身版38 </option>
																<option value="2177">男式衬衫紧身版38.5</option>
																<option value="2178">男式衬衫紧身版39 </option>
																<option value="2179">男式衬衫紧身版39.5</option>
																<option value="2180">男式衬衫紧身版40 </option>
																<option value="2181">男式衬衫紧身版40.5</option>
																<option value="2182">男式衬衫紧身版41 </option>
																<option value="2183">男式衬衫紧身版41.5</option>
																<option value="2184">男式衬衫紧身版42 </option>
																<option value="2185">男式衬衫紧身版42.5</option>
																<option value="2186">男式衬衫紧身版43 </option>
																<option value="2187">男式衬衫紧身版43.5</option>
																<option value="2188">男式衬衫紧身版44 </option>
																<option value="2189">男式衬衫紧身版44.5</option>
																<option value="2190">男式衬衫紧身版45 </option>
																<option value="2191">男式衬衫紧身版45.5</option>
																<option value="2192">男式衬衫紧身版46 </option>
																<option value="2193">男式衬衫紧身版46.5</option>
																<option value="2194">男式衬衫紧身版47 </option>
																<option value="2276">男式衬衫修身版38 </option>
																<option value="2277">男式衬衫修身版38.5</option>
																<option value="2278">男式衬衫修身版39 </option>
																<option value="2279">男式衬衫修身版39.5</option>
																<option value="2280">男式衬衫修身版40 </option>
																<option value="2281">男式衬衫修身版40.5</option>
																<option value="2282">男式衬衫修身版41 </option>
																<option value="2283">男式衬衫修身版41.5</option>
																<option value="2284">男式衬衫修身版42 </option>
																<option value="2285">男式衬衫修身版42.5</option>
																<option value="2286">男式衬衫修身版43 </option>
																<option value="2287">男式衬衫修身版43.5</option>
																<option value="2288">男式衬衫修身版44 </option>
																<option value="2289">男式衬衫修身版44.5</option>
																<option value="2290">男式衬衫修身版45 </option>
																<option value="2291">男式衬衫修身版45.5</option>
																<option value="2292">男式衬衫修身版46 </option>
																<option value="2293">男式衬衫修身版46.5</option>
																<option value="2294">男式衬衫修身版47 </option>
																<option value="2376">男式衬衫合身版38 </option>
																<option value="2377">男式衬衫合身版38.5</option>
																<option value="2378">男式衬衫合身版39 </option>
																<option value="2379">男式衬衫合身版39.5</option>
																<option value="2380">男式衬衫合身版40 </option>
																<option value="2381">男式衬衫合身版40.5</option>
																<option value="2382">男式衬衫合身版41 </option>
																<option value="2383">男式衬衫合身版41.5</option>
																<option value="2384">男式衬衫合身版42 </option>
																<option value="2385">男式衬衫合身版42.5</option>
																<option value="2386">男式衬衫合身版43 </option>
																<option value="2387">男式衬衫合身版43.5</option>
																<option value="2388">男式衬衫合身版44 </option>
																<option value="2389">男式衬衫合身版44.5</option>
																<option value="2390">男式衬衫合身版45 </option>
																<option value="2391">男式衬衫合身版45.5</option>
																<option value="2392">男式衬衫合身版46 </option>
																<option value="2393">男式衬衫合身版46.5</option>
																<option value="2394">男式衬衫合身版47 </option>
																<option value="2476">男式衬衫宽松版38 </option>
																<option value="2477">男式衬衫宽松版38.5</option>
																<option value="2478">男式衬衫宽松版39 </option>
																<option value="2479">男式衬衫宽松版39.5</option>
																<option value="2480">男式衬衫宽松版40 </option>
																<option value="2481">男式衬衫宽松版40.5</option>
																<option value="2482">男式衬衫宽松版41 </option>
																<option value="2483">男式衬衫宽松版41.5</option>
																<option value="2484">男式衬衫宽松版42 </option>
																<option value="2485">男式衬衫宽松版42.5</option>
																<option value="2486">男式衬衫宽松版43 </option>
																<option value="2487">男式衬衫宽松版43.5</option>
																<option value="2488">男式衬衫宽松版44 </option>
																<option value="2489">男式衬衫宽松版44.5</option>
																<option value="2490">男式衬衫宽松版45 </option>
																<option value="2491">男式衬衫宽松版45.5</option>
																<option value="2492">男式衬衫宽松版46 </option>
																<option value="2493">男式衬衫宽松版46.5</option>
																<option value="2494">男式衬衫宽松版47 </option>
															</select> <label>号衣尺码</label>
														</div>
													</div>
													<div class="row" id="measure_list">
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="ling_wei" value="">
																<label>领围*</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="xiong_wei" value="">
																<label>胸围*</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="yao_wei" value="">
																<label>腰围（中腰围）*</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="du_wei" value="">
																<label>肚围</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="dibian" value="">
																<label>底边（臀围）*</label>
															</div>
														</div>

														<div class="col s6 m4 l3">
															<div class="input-field">
																<select name="neiwaichuan">
																	<option value="YX-10-01">内穿</option>
																	<option value="YX-10-02">外穿</option>
																</select> <label>内外穿</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="houshen_chang_nei" value="">
																<label>后身长（内穿）*</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="houshen_chang_wai" value="">
																<label>后身长（外穿）*</label>
															</div>
														</div>

														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="xiutouchang_zuo" value="">
																<label>左袖头长（左腕围）*（长袖时必填）</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="xiutouchang_you" value="">
																<label>右袖头长（右腕围）*（长袖时必填）</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="xiu_fei" value="">
																<label>袖肥（大臂围）*</label>
															</div>
														</div>

														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="xiuzhou_fei" value="">
																<label>袖肘肥（小臂围）</label>
															</div>
														</div>

														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="jian_kuan" value="">
																<label>肩宽*</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="xiu_chang" value="">
																<label>长袖长*（长袖时必填）</label>
															</div>
														</div>

														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="duanxiu_chang" value="">
																<label>短袖长*（短袖时必填）</label>
															</div>
														</div>

														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="duanxiu_kouwei" value="">
																<label>短袖口围*（短袖时必填）</label>
															</div>
														</div>

														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="chest_height" value="">
																<label>胸高</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="chest_distance" value="">
																<label>胸距</label>
															</div>
														</div>

														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="qianshen_chang" value="">
																<label>前身长*</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="qianxiong_kuan" value="">
																<label>前胸宽</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="houbei_kuan" value="">
																<label>后背宽</label>
															</div>
														</div>

														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="height" value="">
																<label>身高</label>
															</div>
														</div>
														<div class="col s6 m4 l3">
															<div class="input-field">
																<input type="number" class="validate" name="weight" value="">
																<label>体重(kg)</label>
															</div>
														</div>

													</div>
												</div>
											</div>
										</div>
									</li>

									<li>
										<div class="collapsible-header">
											<i class="material-icons">playlist_add</i>工艺
										</div>
										<div class="collapsible-body">
											<div class="row">
												<div class="input-field col s12 m8 l6">
													<div class="card-panel">
														<div class="row">
															<div class="col  s12 m12 l12 teal-text">
																<p>领型</p>
															</div>
															<div class="input-field col s8 m8 l8">
																<select name="LZX_01">
																	<%=list_LZX_01%>
																</select> <label>领型</label>
															</div>
															<div class="input-field col s4 m4 l4">
																<select name="lingcheng">
																	<%=list_lingcheng%>
																</select> <label>领插片</label>
															</div>
														</div>
													</div>
												</div>
												<div class="input-field col s6 m4 l3">
													<div class="card-panel">
														<div class="row">
															<div class="col  s12 m12 l12 teal-text">
																<p>袖头</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="LZX_02">
																	<%=list_LZX_02%>
																</select> <label>袖头</label>
															</div>
														</div>
													</div>
												</div>
												<div class="input-field col s6 m4 l3">
													<div class="card-panel">
														<div class="row">
															<div class="col  s12 m12 l12 teal-text">
																<p>门襟</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="LZX_03">
																	<%=list_LZX_03%>
																</select> <label>门襟</label>
															</div>
														</div>
													</div>
												</div>
												<div class="input-field col s6 m4 l3">
													<div class="card-panel">
														<div class="row">
															<div class="col  s12 m12 l12 teal-text">
																<p>口袋</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="LZX_04">
																	<%=list_LZX_04%>
																</select> <label>口袋</label>
															</div>
														</div>
													</div>
												</div>
												<div class="input-field col s6 m4 l3">
													<div class="card-panel">
														<div class="row">
															<div class="col  s12 m12 l12 teal-text">
																<p>袖褶</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="LZX_120">
																	<%=list_LZX_120%>
																</select> <label>袖褶</label>
															</div>
														</div>
													</div>
												</div>

												<div class="input-field col s6 m4 l3">
													<div class="card-panel">
														<div class="row">
															<div class="col  s12 m12 l12 teal-text">
																<p>后片</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="LZX_17">
																	<%=list_LZX_17%>
																</select> <label>后片款式</label>
															</div>
														</div>
													</div>
												</div>

												<div class="input-field col s6 m4 l3">
													<div class="card-panel">
														<div class="row">
															<div class="col  s12 m12 l12 teal-text">
																<p>下摆</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="LZX_06">
																	<%=list_LZX_06%>
																</select> <label>下摆弧度</label>
															</div>
														</div>
													</div>
												</div>

												<div class="input-field col s6 m4 l3">
													<div class="card-panel">
														<div class="row">
															<div class="col  s12 m12 l12 teal-text">
																<p>贴布</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="LZX_26">
																	<%=list_LZX_26%>
																</select> <label>侧缝底摆贴布</label>
															</div>
														</div>
													</div>
												</div>
												<div class="input-field col s6 m4 l3">
													<div class="card-panel">
														<div class="row">
															<div class="col  s12 m12 l12 teal-text">
																<p>明线</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="mingxian">
																	<%=list_mingxian%>
																</select> <label>翻领袖头明线</label>
															</div>
														</div>
													</div>
												</div>
												<div class="input-field col s6 m4 l3">
													<div class="card-panel">
														<div class="row">
															<div class="col  s12 m12 l12 teal-text">
																<p>侧缝工艺</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="cefeng">
																	<%=list_cefeng%>
																</select> <label>侧缝工艺</label>
															</div>
														</div>
													</div>
												</div>
												<div class="input-field col s6 m4 l3">
													<div class="card-panel">
														<div class="row">
															<div class="col  s12 m12 l12 teal-text">
																<p>特殊工艺</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="special_technics">
																	<option value="YX-ST-00">无特殊工艺</option>
																	<option value="YX-ST-01">DP成衣免烫</option>
																	<option value="YX-ST-02">水洗</option>
																</select> <label>DP或水洗</label>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</li>

									<li>
										<div class="collapsible-header">
											<i class="material-icons">dashboard</i>辅料
										</div>
										<div class="collapsible-body">
											<div class="row">

												<div class="col s12 m12 l12 teal-text">
													<div class="card-panel">
														<div class="row">
															<div class="col s12 m12 l12 teal-text">
																<p>纽扣</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select id="kouzi" name="kouzi">
																	<%=list_kouzi%>
																	<option value="">客供</option>
																</select> <label>纽扣类型</label>
															</div>
															<div class="input-field col s12 m12 l12" id="kouzi_div">
																<input id="kouzi_pbc" type="text" class="validate" name="kouzi" value="">
																<label>客供扣子</label>
															</div>
														</div>
													</div>
												</div>

												<div class="col s12 m12 l12 teal-text" style="display: none;">
													<div class="card-panel">
														<div class="row">
															<div class="col s12 m12 l12 teal-text">
																<p>线材</p>
															</div>
															<div class="input-field col s12 m6 l4">
																<select name="line_color_location_1">
																	<%=list_color%>
																</select> <label>缝制线</label>
															</div>
															<div class="input-field col s12 m6 l4">
																<select name="line_color_location_2">
																	<%=list_color%>
																</select> <label>装饰线</label>
															</div>
															<div class="input-field col s12 m6 l4">
																<select name="line_color_location_3">
																	<%=list_color%>
																</select> <label>钉扣线</label>
															</div>
															<div class="input-field col s12 m6 l4">
																<select name="line_color_location_4">
																	<%=list_color%>
																</select> <label>锁眼线</label>
															</div>
														</div>
													</div>
												</div>

												<div class="col s12 m12 l12 teal-text">
													<div class="card-panel">
														<div class="row">
															<div class="col s12 m12 l12 teal-text">
																<p>织带</p>
															</div>
															<div class="input-field col s12 m4 l3">
																<select name="zhidai">
																	<%=list_zhidai%>
																</select> <label>织带</label>
															</div>
															<div class="input-field col s12 m8 l9">
																<select name="weizhi_zhidai">
																	<%=list_weizhi_zhidai%>
																</select> <label>织带位置</label>
															</div>
														</div>
													</div>
												</div>

												<div class="col s12 m6 l4 teal-text" style="display: none;">
													<div class="card-panel">
														<div class="row">
															<div class="col s12 m12 l12 teal-text">
																<p>嵌条</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="qiantiao">
																	<%=list_qiantiao%>
																</select> <label>嵌条</label>
															</div>
														</div>
													</div>
												</div>

												<div class="col s12 m6 l4 teal-text">
													<div class="card-panel">
														<div class="row">
															<div class="col s12 m12 l12 teal-text">
																<p>衬布</p>
															</div>
															<div class="input-field col s12 m12 l12">
																<select name="chenbu">
																	<%=list_chenbu%>
																</select> <label>衬布</label>
															</div>
														</div>
													</div>
												</div>

											</div>
										</div>
									</li>

									<li>
										<div class="collapsible-header">
											<i class="material-icons">mode_edit</i>刺绣
										</div>
										<div class="collapsible-body">
											<div class="row">
												<div class="card-panel">
													<div class="row">
														<div class="col s12 m12 l12 teal-text">
															<p>刺绣文字</p>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="LZX_11_FOR_CHAR_SWITCH">
																<option value="0">不使用文字刺绣</option>
																<option value="1">使用文字刺绣</option>
															</select> <label>是否使用文字刺绣</label>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="LZX_13_FOR_CHAR">
																<%=list_LZX_13%>
															</select> <label>刺绣文字位置</label>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="LZX_11_CHAR_COLOR">
																<%=list_color%>
															</select> <label>刺绣文字颜色</label>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="LZX_11_CHAR_TYPE">
															</select> <label>刺绣文字字体</label>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="LZX_11_CHAR_SIZE">
															</select> <label>刺绣文字高度</label>
														</div>
														<div class="col s12 m6 l4">
															<div class="input-field">
																<input type="text" class="validate" name="LZX_11_CHAR_WORD" value="">
																<label>刺绣文字内容</label>
															</div>
														</div>
													</div>
												</div>
												<div class="card-panel">
													<div class="row">
														<div class="col s12 m12 l12 teal-text">
															<p>刺绣图案</p>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="LZX_11_FOR_PIC_SWITCH">
																<option value="0">不使用图案刺绣</option>
																<option value="1">使用图案刺绣</option>
															</select> <label>是否使用图案刺绣</label>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="LZX_13_FOR_PIC">
																<%=list_LZX_13%>
															</select> <label>刺绣图案位置</label>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="LZX_11_PIC_COLOR">
																<%=list_color%>
															</select> <label>刺绣图案颜色</label>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="LZX_11_PIC_TYPE">
															</select> <label>刺绣图案系列</label>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="LZX_11_PIC_SIZE">
															</select> <label>刺绣图案高度</label>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="LZX_11_PIC_NUM">
															</select> <label>刺绣图案编号</label>
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
									
									<li>
										<div class="collapsible-header">
											<i class="material-icons">tab</i>配色
										</div>
										<div class="collapsible-body">
											<div class="row">
												<div class="card-panel">
													<div class="row">
														<div class="col s12 m12 l12 teal-text">
															<p>配色</p>
														</div>
														<div class="col s12 m6 l4">
															<div class="input-field">
																<input type="text" class="validate" name="uskin_code_2" value="">
																<label>配色面料</label>
															</div>
														</div>
														<div class="input-field col s12 m6 l4">
															<select name="weizhi_peise" id="weizhi_peise">
																<%=list_weizhi_peise%>
																<option value="">手动填写</option>
															</select> <label>配色位置</label>
														</div>
														<div class="input-field col s12 m6 l4" id="weizhi_peise_div">
															<input type="text" class="validate" name="weizhi_peise" id="weizhi_peise_custom" value="">
															<label>手动填写配色位置</label>
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