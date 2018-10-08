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
	
	String code=(String) session.getAttribute("code");
	
%>

<html>
	<!--
		
		作者：ascleft@163.com
		时间：2017-11-20
		描述：
		购物车添加工具 SCA 2.0
		
		客户经理 客供面料
		
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
			var url_addShoppingCart = "/CtrlCenter/LTYX/SCA/Main/SubmitCustomShopAidePBC.action";
			var url_getPrice = "/CtrlCenter/LTYX/SCA/Main/GetPriceCustomShopAidePBC.action";

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
		</div>
		
	</body>
	
</html>