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
		<title>平台商品</title>

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
			var url_submit_key =             "/CtrlCenter/LTYX/Test/UpdatePlatformTailorPage.action";
			var url_get_realtime_inventory = "/CtrlCenter/LTYX/Test/GetPlatformTailorRTInventory.action";
			var url_submit_tailor =          "/CtrlCenter/LTYX/Test/SubmitTailorForm.action";

			var shangpin_list;

			var modal_state_title;
			var modal_state_progress_bar;
			var btn_finish;
			var btn_download;

			var temporary_state_progress_bar;

			var tinybug;

			var csv_string;

			function submitkey() {
				$("#temporary_state_progress_bar").show();
				$.ajax({
					cache: true,
					type: "POST",
					url: url_submit_key,
					data: $('#listform').serialize(),
					async: true,
					error: function(request) {
						Materialize.toast('网路错误，无法更新列表 采用离线数据', 1000);
						var datajia =
							"{\"data\":[{\"name\":\"离线云集!\",\"list\":[{\"name\":\"时尚商务印花衬衫\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS2A-YH38Q\"},{\"name\":\"39\",\"code\":\"P01C17-NS2A-YH38Q\"},{\"name\":\"40\",\"code\":\"P01C17-NS2A-YH38Q\"}]},{\"name\":\"天然纯亚麻时尚四色衬衫\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS2A-YH38Q\"},{\"name\":\"39\",\"code\":\"P01C17-NS2A-YH38Q\"},{\"name\":\"40\",\"code\":\"P01C17-NS2A-YH38Q\"}]}]}]}";
						var resp = JSON.parse(datajia);
						shangpin_list = resp.data;
						update_store();
						$("#temporary_state_progress_bar").hide();
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE) {
							if("succ" == resp.ERRDESC) {
								shangpin_list = resp.data;
								update_store();
								Materialize.toast('更细商品列表成功succ', 1000);
							}
						}
						$("#temporary_state_progress_bar").hide();
						tinybug.message = data;
					}
				});
			}

			function update_list() {
				submitkey();
				Materialize.toast('更新关键字', 1000);
			};

			function update_store() {
				var items = "";
				for(i = 0; i < shangpin_list.length; i++) {
					items += "<option>";
					items += shangpin_list[i].name;
					items += "</option>";
				}
				$("#store").html(items);
				$("#design").html("");
				$("#size").html("");
				$("#inventory").val("等待查询");
				Materialize.updateTextFields();
				$('select').material_select();
				Materialize.toast('更新平台', 1000);
			};

			function update_design(list) {
				var storename = "" + $("#store option:selected").text();
				var items = "";
				for(i = 0; i < shangpin_list.length; i++) {
					if(shangpin_list[i].name == storename) {
						for(j = 0; j < shangpin_list[i].list.length; j++) {
							items += "<option>";
							items += shangpin_list[i].list[j].name;
							items += "</option>";
						}
					}
				}
				$("#design").html(items);
				$("#size").html("");
				$("#inventory").val("等待查询");
				Materialize.updateTextFields();
				$('select').material_select();
				Materialize.toast('更新款式', 1000);
			};

			function update_size(list) {
				var storename = "" + $("#store option:selected").text();
				var designname = "" + $("#design option:selected").text();
				var items = "";
				for(i = 0; i < shangpin_list.length; i++) {
					if(shangpin_list[i].name == storename) {
						for(j = 0; j < shangpin_list[i].list.length; j++) {
							if(shangpin_list[i].list[j].name == designname) {
								for(k = 0; k < shangpin_list[i].list[j].list.length; k++) {
									items += "<option>";
									items += shangpin_list[i].list[j].list[k].name;
									items += "</option>";
								}
							}
						}
					}
				}
				$("#size").html(items);
				$("#inventory").val("等待查询");
				Materialize.updateTextFields();
				$('select').material_select();
				Materialize.toast('更新尺码', 1000);
			};

			function update_inventory(list) {
				$("#temporary_state_progress_bar").show();
				$("#inventory").val("正在查询");
				Materialize.updateTextFields();
				$.ajax({
					cache: true,
					type: "POST",
					url: url_get_realtime_inventory,
					data: $('#listform').serialize(),
					async: true,
					error: function(request) {
						Materialize.toast('网络异常', 1000);
						$("#inventory").val("网络异常");
						Materialize.updateTextFields();
						$("#temporary_state_progress_bar").hide();
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE) {
							if("succ" == resp.ERRDESC) {
								$("#inventory").val(resp.data);
								Materialize.updateTextFields();
							}
						}
						$("#temporary_state_progress_bar").hide();
						tinybug.message = data;
					}
				});

				Materialize.toast('更新库存', 1000);
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

				$("#key").bind("keyup", function() {
					update_list();
				});
				$("#store").bind("change", function() {
					update_design();
				});
				$("#design").bind("change", function() {
					update_size();
				});
				$("#size").bind("change", function() {
					update_inventory();
				});

				$("#temporary_state_progress_bar").hide();

				update_list();

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
				<a id="logo-container" href="#" class="brand-logo white-text">平台商品</a>
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
				<form method="post" id="listform">
					<div class="row">
						<div class="col s12 m12 l12">
							<div class="card-panel hoverable">
								<div class="card-content grey-text">
									<div class="row">
										<div class="col s12 m12 l12 teal-text">
											<div class="input-field">
												<input id="key" type="text" class="validate" name="key" value="">
												<label>筛选条件（平台/商品名/编号）</label>
											</div>
										</div>
										<div class="col s12 m12 l12 teal-text">
											<div class="progress" id="temporary_state_progress_bar">
												<div class="indeterminate"></div>
											</div>
										</div>
										<div class="input-field col s6 m4 l3">
											<select id="store" name="">
											</select> <label>平台</label>
										</div>
										<div class="input-field col s6 m4 l4">
											<select id="design" name="">
											</select> <label>款式名</label>
										</div>
										<div class="input-field col s6 m4 l3">
											<select id="size" name="">
											</select> <label>尺码</label>
										</div>
										<div class="col s6 m4 l2 teal-text">
											<div class="input-field">
												<input id="inventory" type="text" class="validate" disabled="disabled" value="">
												<label>当前库存</label>
											</div>
										</div>
										<div class="col s6 m4 l3 teal-text">
											<div class="input-field">
												<input type="number" class="validate" name="num" value="">
												<label>购买数量</label>
											</div>
										</div>
										<a class="col s12 waves-effect waves-light btn input-field" onclick="submitclothform()">提交订单</a>
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
				<div class="row">
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