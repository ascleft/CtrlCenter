<%@ page language="java"import="java.util.*"pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
	
	String ec_user_id=(String) session.getAttribute("ec_user_id");
	String ec_user_name=(String) session.getAttribute("ec_user_name");
	String ec_user_rank=(String) session.getAttribute("ec_user_rank");
	
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
	String list_shenxing=(String) session.getAttribute("list_shenxing");
	String list_lingcheng=(String) session.getAttribute("list_lingcheng");
	String list_mingxian=(String) session.getAttribute("list_mingxian");
	String list_cefeng=(String) session.getAttribute("list_cefeng");
	String list_qiantiao=(String) session.getAttribute("list_qiantiao");
	String list_chenbu=(String) session.getAttribute("list_chenbu");
	String list_weizhi_zhidai=(String) session.getAttribute("list_weizhi_zhidai");
	String list_weizhi_peise=(String) session.getAttribute("list_weizhi_peise");
	
	String code=(String) session.getAttribute("code");
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
		<title>客供面料</title>

		<!-- CDN  -->
		<!-- Google Icon Font -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<!-- JQuery  -->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<!--  Vue.js-->
		<script src="https://unpkg.com/vue/dist/vue.js"></script>
		<!--  Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->

		<!-- local html  -->
		<link href="../../img/CodeMartrix/main/weisuomeng.jpg" rel="shortcut icon" />

		<link href="../../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="../../js/materialize.js"></script>
		<script src="../../js/init.js"></script>

		<script src="../../js/init_tailorinfo.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/img/global/logo/icon_title_1.jpg" rel="shortcut icon">

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

		<script src="<%=path %>/js/init_tailorinfo.js"></script>

		<script type="application/javascript">
			var url_submittailorform = "/CtrlCenter/LTYX/Tailor/TailorForm/SubmitTailorForm.action";

			var modal_state_title;
			var modal_state_progress_bar;
			var btn_finish;
			var btn_download;

			var tinybug;

			var csv_string;

			function submitclothform() {
				state_upload_ing("正在提交订单信息，请稍候");

				$.ajax({
					cache: true,
					type: "POST",
					url: url_submittailorform,
					data: $('#clothform').serialize(),
					async: true,
					error: function(request) {
						state_upload_error("无法连接到服务器");
						//Materialize.toast('无法连接到服务器', 1000);
						tinybug.message = request;
					},
					success: function(data) {
						if(data.length < 1000) {
							var resp = JSON.parse(data);
							if("0" == resp.ERRCODE) {
								if("succ" == resp.ERRDESC) {
									state_upload_finish("提交成功");
									csv_string = resp.data.CONTENT;
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

			function update() {
				var elems = $("input");
				var labels = $("label");
				//				console.log(labels[2]);

				var formMsgs = new Array(elems.length);

				for(i = 0; i < elems.length; i++) {
					formMsgs[i] = labels[i].innerText + ":" + elems[i].value + "&nbsp;&nbsp;";

				}

				var preview_1 = document.getElementById("preview_1");
				var preview_2 = document.getElementById("preview_2");
				var preview_3 = document.getElementById("preview_3");
				var preview_4 = document.getElementById("preview_4");
				var preview_5 = document.getElementById("preview_5");
				var preview_6 = document.getElementById("preview_6");
				var preview_7 = document.getElementById("preview_7");
				var preview_8 = document.getElementById("preview_8");

				//				preview_1.innerHTML = formMsgs.slice(0, 2);
				//				用户
				preview_2.innerHTML = formMsgs.slice(0, 5);
				//				面料领标
				preview_3.innerHTML = formMsgs.slice(10, 16);
				//				尺寸
				preview_4.innerHTML = formMsgs.slice(20, 46);
				//				工艺
				preview_5.innerHTML = formMsgs.slice(50, 61);
				//				辅料
				//				preview_6.innerHTML = formMsgs.slice(70, 79);
				//				配色
				//				preview_7.innerHTML = formMsgs.slice(90, 92);
				//				刺绣
				preview_8.innerHTML = formMsgs.slice(110, 122);

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

			function state_upload_error(html_desc) {
				modal_state_title.innerHTML = html_desc;
				modal_state_progress_bar.style.display = "none";
				btn_finish.style.display = "";
				btn_download.style.display = "none";
			}

			$(document).ready(function() {
				$("input").bind("keyup", function() {
					update();
				});
				$("input").bind("change", function() {
					update();
				});
				$("select").bind("change", function() {
					update();
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

			function downloadcsv(aLink) {
				var str = csv_string;
				str = encodeURIComponent(str);
				aLink.href = "data:text/csv;charset=utf-8," + str;
				//				aLink.click();
			}
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
				<a id="logo-container" href="#" class="brand-logo white-text">客供版</a>
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
			<div class="section">
				<form method="post" id="clothform">
					<div class="row">
						<div class="col s12 m12 l12">
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
													<label>姓名（必填）</label>
												</div>
											</div>
											<div class="col s12 m6 l4">
												<div class="input-field">
													<input type="text" class="validate" name="customer_tel_target" value=""> <label>量体人电话（必填）</label>
												</div>
											</div>
											<div class="col s12 m6 l4">
												<div class="input-field">
													<input type="text" class="validate" name="customer_tel" value=""> <label>账户电话（必填）</label>
												</div>
											</div>
											<div class="col s12 m6 l6">
												<div class="input-field">
													<input type="text" class="validate" name="customer_address" value=""> <label>收货地址</label>
												</div>
											</div>
											<div class="col s12 m12 l6">
												<div class="input-field">
													<input type="text" class="validate" name="customer_tips" value=""> <label>备注</label>
												</div>
											</div>
										</div>
										<div style="display: none;">
											<input type="text" class="validate" name="operator_id" value="<%=ec_user_id%>"><label>操作人ID</label>
											<input type="text" class="validate" name="operator_name" value="<%=ec_user_name%>"><label>操作人名称</label>
											<input type="text"><label>补位</label>
											<input type="text"><label>补位</label>
											<input type="text"><label>补位</label>
										</div>
									</div>
								</li>
								<li>
									<div class="collapsible-header">
										<i class="material-icons">view_carousel</i>面料 领标 包装
									</div>
									<div class="collapsible-body">
										<div class="row">
											<div class="col s12 m12 l12">
												<div class="input-field">
													<input type="text" class="validate" name="uskin_code" readonly="true" value="<%=code%>">
													<label>客供面料单号（必填）</label>
												</div>
											</div>
											<div class="col s6 m4 l4">
												<div class="input-field"> <input type="number" class="validate" name="uskin_code_length" value="">
													<label>面料用量（必填）</label>
												</div>
											</div>
											<div class="col s6 m4 l4">
												<div class="input-field"><input type="text" class="validate" name="uskin_code_color" value="">
													<label>颜色描述（必填）</label>
												</div>
											</div>
											<div class="col s6 m4 l4">
												<div class="input-field">
													<input type="text" class="validate" name="uskin_code_pattern" value="">
													<label>花型描述</label>
												</div>
											</div>
											<div class="input-field col s5 m5 l4">
												<select name="YX_08">
													<%=list_LZX_08%>
												</select> <label>主唛</label>
											</div>
											<div class="input-field col s12 m6 l6">
												<select name="YX_09">
													<option value="YX-09-00">无包装（透明胶袋）</option>
													<option value="YX-09-01">礼盒装（优纤包装盒）</option>
													<option value="YX-09-02">环保装（简易包装）</option>
												</select> <label>包装</label>
											</div>
										</div>
										<div style="display: none;">
											<input type="text" class="validate" name="uskin_code_type" value="PBC"><label>USKIN编码类型</label>
											<input type="text" class="validate" name="style_type" value="2"><label>1推荐款、2自由搭配</label>
											<input type="text"><label>补位</label>
											<input type="text"><label>补位</label>
										</div>
									</div>
								</li>
								<li>
									<div class="collapsible-header">
										<i class="material-icons">settings_overscan</i>尺寸
									</div>
									<div class="collapsible-body">
										<div class="row">
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="height" value="">
													<label>身高</label>
												</div>
											</div>
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="weight" value="">
													<label>体重</label>
												</div>
											</div>

											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="xiong_wei" value="">
													<label>胸围</label>
												</div>
											</div>
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="yao_wei" value="">
													<label>腰围</label>
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
													<label>底边（臀围）</label>
												</div>
											</div>
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="ling_wei" value="">
													<label>领围</label>
												</div>
											</div>
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="houshen_chang" value="">
													<label>后身长</label>
												</div>
											</div>
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="jian_kuan" value="">
													<label>肩宽</label>
												</div>
											</div>
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="jian_kuan_qian" value="">
													<label>前肩宽</label>
												</div>
											</div>
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="xiu_chang_zuo" value="">
													<label>袖长左</label>
												</div>
											</div>
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="xiu_chang_you" value="">
													<label>袖长右</label>
												</div>
											</div>

											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="duanxiu_chang" value="">
													<label>短袖长</label>
												</div>
											</div>

											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="xiu_fei" value="">
													<label>袖肥（大臂围）</label>
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
													<input type="number" class="validate" name="duanxiu_kouwei_zuo" value="">
													<label>短袖口围左</label>
												</div>
											</div>
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="duanxiu_kouwei_you" value="">
													<label>短袖口围右</label>
												</div>
											</div>
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="xiutouchang_zuo" value="">
													<label>左袖头长（左腕围）</label>
												</div>
											</div>
											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="xiutouchang_you" value="">
													<label>右袖头长（右腕围）</label>
												</div>
											</div>

											<div class="col s6 m4 l3">
												<div class="input-field">
													<input type="number" class="validate" name="qianshen_chang" value="">
													<label>前身长</label>
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
											<div class="input-field col s6 m4 l3">
												<select name="shenxing">
													<%=list_shenxing%>
												</select> <label>身型</label>
											</div>
											<div class="input-field col s6 m4 l3">
												<select name="measure_type">
													<option value="patternform">净尺寸</option>
													<option value="clotheval">成衣尺寸</option>
													<option value="needless">不使用尺寸</option>
												</select> <label>尺寸类型</label>
											</div>

											<div style="display: none;">
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
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
															</select> <label>领撑</label>
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
															<p>下摆弧度</p>
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
															<p>后片款式</p>
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
															<p>侧缝底摆贴布</p>
														</div>
														<div class="input-field col s12 m12 l12">
															<select name="LZX-26">
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
															</select> <label>明线宽</label>
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
											<div style="display: none;">
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
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
											<div class="card-panel">
												<div class="row">
													<div class="col s12 m12 l12 teal-text">
														<p>织带（此处暂未提供织带编号归属及位置编号）</p>
													</div>
													<div class="input-field col s12 m4 l3">
														<select name="zhidai">
															<%=list_zhidai%>
														</select> <label>织带</label>
													</div>
													<div class="input-field col s12 m8 l9">
														<select multiple="multiple" name="">
															<%=list_weizhi_zhidai%>
														</select> <label>织带位置</label>
													</div>
												</div>
											</div>
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
											<div class="col s12 m6 l4 teal-text">
												<div class="card-panel">
													<div class="row">
														<div class="col s12 m12 l12 teal-text">
															<p>纽扣</p>
														</div>
														<div class="input-field col s12 m12 l12">
															<select name="kouzi">
																<%=list_kouzi%>
															</select> <label>纽扣类型</label>
														</div>
													</div>
												</div>
											</div>
											<div class="col s12 m6 l4 teal-text">
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

											<div style="display: none;">
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
												<input type="text"><label>补位</label>
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
													<div class="col s6 m6 l6">
														<div class="input-field">
															<input type="text" class="validate" name="peise_bn" value="">
															<label>配色面料</label>
														</div>
													</div>
													<div class="input-field col s6 m6 l6">
														<select name="peise_weizhi" multiple="multiple">
															<%=list_weizhi_peise%>
														</select> <label>配色位置</label>
													</div>
												</div>
												<div style="display: none;">
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
													<input type="text"><label>补位</label>
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
													<div class="input-field col s12 m4 l4">
														<select name="LZX_11_FOR_CHAR_SWITCH">
															<option value="0">不使用文字刺绣</option>
															<option value="1">使用文字刺绣</option>
														</select> <label>是否使用文字刺绣</label>
													</div>
													<div class="input-field col s12 m4 l4">
														<select name="LZX_13_FOR_CHAR">
															<%=list_LZX_13%>
														</select> <label>刺绣文字位置</label>
													</div>
													<div class="input-field col s12 m4 l4">
														<select name="LZX_11_CHAR_COLOR">
															<%=list_color%>
														</select> <label>刺绣文字颜色</label>
													</div>
													<div class="input-field col s6 m4 l4">
														<select name="LZX_11_CHAR_TYPE">
															<option value="LZX-11-01">英文中宋</option>
															<option value="LZX-11-02">舒体</option>
															<option value="LZX-11-04">皇家体</option>
															<option value="LZX-11-05">手写体</option>
															<option value="LZX-11-06">古圆体</option>
															<option value="LZX-11-08">维体</option>
															<option value="LZX-11-09">书写体</option>
															<option value="LZX-11-10">哥特体</option>
															<option value="LZX-11-12">卡曼体</option>
															<option value="LZX-11-13">花体</option>
															<option value="LZX-11-14">书信体</option>
															<option value="LZX-11-15">巴洛克体</option>
															<option value="LZX-11-16">英文行楷</option>
															<option value="LZX-11-17">黑体</option>
															<option value="LZX-11-18">隶属</option>
															<option value="LZX-11-19">毛体</option>
															<option value="LZX-11-20">草书</option>
															<option value="LZX-11-21">中文行楷</option>
															<option value="LZX-11-22">中文中宋</option>
														</select> <label>刺绣文字字体</label>
													</div>
													<div class="col s6 m4 l4">
														<div class="input-field">
															<input type="number" class="validate" name="LZX_11_CHAR_SIZE" value="">
															<label>刺绣文字大小</label>
														</div>
													</div>
													<div class="col s6 m6 l4">
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
													<div class="input-field col s12 m4 l4">
														<select name="LZX_11_FOR_PIC_SWITCH">
															<option value="0">不使用图案刺绣</option>
															<option value="1">使用图案刺绣</option>
														</select> <label>是否使用图案刺绣</label>
													</div>
													<div class="input-field col s12 m4 l4">
														<select name="LZX_13_FOR_PIC">
															<%=list_LZX_13%>
														</select> <label>刺绣图案位置</label>
													</div>
													<div class="input-field col s12 m4 l4">
														<select name="LZX_11_PIC_COLOR">
															<%=list_color%>
														</select> <label>刺绣图案颜色</label>
													</div>
													<div class="input-field col s6 m4 l4">
														<select name="LZX_11_PIC_TYPE">
															<option value="LZX-11-23">十二生肖</option>
															<option value="LZX-11-24">十二星座</option>
															<option value="LZX-11-25">宽贴门襟</option>
															<option value="LZX-11-26">宽贴门襟</option>
															<option value="LZX-11-27">宽贴门襟</option>
														</select> <label>刺绣图案系列</label>
													</div>
													<div class="col s6 m4 l4">
														<div class="input-field">
															<input type="number" class="validate" name="LZX_11_PIC_SIZE" value="">
															<label>刺绣图案大小</label>
														</div>
													</div>
													<div class="input-field col s6 m6 l4">
														<select name="LZX_11_PIC_NUM">
															<option value="01">01</option>
															<option value="02">02</option>
															<option value="04">04</option>
															<option value="05">05</option>
															<option value="06">06</option>
															<option value="08">08</option>
															<option value="09">09</option>
															<option value="10">10</option>
															<option value="12">12</option>
															<option value="13">13</option>
															<option value="14">14</option>
															<option value="15">15</option>
															<option value="16">16</option>
															<option value="17">17</option>
															<option value="18">18</option>
															<option value="19">19</option>
															<option value="20">20</option>
															<option value="21">21</option>
															<option value="22">22</option>
															<option value="23">23</option>
															<option value="24">24</option>
														</select> <label>刺绣图案编号</label>
													</div>
												</div>
											</div>
										</div>
									</div>
								</li>
								<li>
									<div class="collapsible-header">
										<i class="material-icons">payment</i>报价
									</div>
									<div class="collapsible-body">
										<div class="row">
											<div class="card-panel">
												<div class="row">
													<div class="col s12 m12 l12 teal-text">
														<p>自主报价</p>
													</div>
													<div class="col s12 m12 l12">
														<div class="input-field">
															<input type="text" class="validate" name="prices" value="">
															<label>报价</label>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</li>
							</ul>

						</div>
						<div class="col s12 m12 l12">
							<div class="card-panel hoverable">
								<div class="card-content grey-text">
									<div class="teal-text">订单信息预览</div>
									<div id="preview_1"></div>
									<div class="teal-text">用户信息预览</div>
									<div id="preview_2"></div>
									<div class="teal-text">面料、领标、包装</div>
									<div id="preview_3"></div>
									<div class="teal-text">成衣尺寸预览</div>
									<div id="preview_4"></div>
									<div class="teal-text">工艺预览</div>
									<div id="preview_5"></div>
									<div class="teal-text">辅料预览</div>
									<div id="preview_6"></div>
									<div class="teal-text">配色预览</div>
									<div id="preview_7"></div>
									<div class="teal-text">刺绣预览</div>
									<div id="preview_8"></div>
								</div>
							</div>
						</div>

						<div class="col s12 m12 l12">
							<div class="card-panel hoverable">
								<div class="card-content grey-text">
									<div class="row">
										<div class="input-field col s6 m4 l3">
											<select name="tailor_type">
												<option value="26">长袖</option>
												<option value="27">短袖</option>
											</select> <label>长袖/短袖</label>
										</div>
										<a class="col s6 m8 l9 waves-effect waves-light btn input-field" onclick="submitclothform()">提交订单数据</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
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
				<a id="btn_download" onclick="downloadcsv(this)" download="downlaod.csv" href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">下载</a>
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