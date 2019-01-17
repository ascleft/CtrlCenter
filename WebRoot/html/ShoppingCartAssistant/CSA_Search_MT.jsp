<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
	
	String ec_user_id=(String) session.getAttribute("ec_user_id");
	String ec_user_name=(String) session.getAttribute("ec_user_name");
	String ec_user_rank=(String) session.getAttribute("ec_user_rank");
	
	String su=(String) session.getAttribute("su");
	
	String QRurl=(String) session.getAttribute("QRurl");

	String menulist=(String) session.getAttribute("menulist");
%>
<html>
	<!--
		
		作者:鸿安Adrian
		邮箱:ascleft@163.com
		时间:2019-01-16
		描述:购物车添加工具 SCA 3.0
		
		客户经理 即时库存联合查询
		
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

			var url_search = "/CtrlCenter/LTYX/OpenAPI/UnityInventory.action";
			var ec_user_rank = parseInt("<%=ec_user_rank%>");

			$().ready(function() {
				state_defult();
			})

			var ajax_search_K3;
			var ajax_search_ERP600;

			var search_task;
			var board_content;
			var board_table_extra_content;
			var board_table_header;
			var board_table_lines;
			var board_table_footer;

			function searchStart(sortKeyWord) {

				state_loading();

				search_task = null;
				board_content = null;
				board_table_extra_content = null;
				board_table_header = null;
				board_table_lines = null;
				board_table_footer = null;
				if($("[name='Department']").val() == "K3") {
					search_task = [
						["K3", "查询中", 0, "K3库存查询中 "],
						["ERP600", "未启动", 0, ""]
					];
					state_loading(search_task[0][3] + search_task[1][3]);
					searchK3(sortKeyWord);
				} else if($("[name='Department']").val() == "ERP600") {
					search_task = [
						["K3", "未启动", 0, ""],
						["ERP600", "查询中", 0, "ERP600库存查询中 "]
					];
					state_loading(search_task[0][3] + search_task[1][3]);
					searchERP600(sortKeyWord)
				} else {
					search_task = [
						["K3", "查询中", 0, "K3库存查询中 "],
						["ERP600", "查询中", 0, "ERP600库存查询中 "]
					];
					state_loading(search_task[0][3] + search_task[1][3]);
					searchK3(sortKeyWord);
					searchERP600(sortKeyWord);
				}

			}

			function searchK3(sortKeyWord) {

				var datas = new FormData();
				datas.append("Code", $("[name='Code']").val());
				datas.append("CodeType", $("[name='CodeType']").val());
				datas.append("Department", "K3");
				datas.append("Fuzzy", $("[name='Fuzzy']").val());
				datas.append("Warehouse", $("[name='Warehouse']").val());

				ajax_search_K3 =
					$.ajax({
						cache: true,
						type: "POST",
						url: url_search,
						//data: $('#filterForm').serialize(),
						data: datas, //指定data数据
						datatype: 'text', //指定data数据
						processData: false, //指定data数据
						contentType: false, //指定data数据
						async: true,
						error: function(request) {
							state_error("无法连接服务器");
						},
						success: function(data) {
							var resp = JSON.parse(data);
							if("0" == resp.ERRCODE && "succ" == resp.ERRDESC) {
								if(resp.data[0].list.length == 0) {
									if(resp.data[0].desc != "库存信息正常，无库存") {
										search_task[0] = ["K3", "异常", 0, ""];
										board_set_extra_content("鲁泰服务器异常，无法查询" + resp.data[0].name + "库存");
									} else {
										search_task[0] = ["K3", "已完成", 0, ""];
									}
								} else {
									search_task[0] = ["K3", "已完成", resp.data[0].list.length, ""];
									board_add_lines(resp.data[0], sortKeyWord);
								}
								board_update();
							} else {
								state_error(resp.data);
							}
						}
					});
			}

			function searchERP600(sortKeyWord) {

				var datas = new FormData();
				datas.append("Code", $("[name='Code']").val());
				datas.append("CodeType", $("[name='CodeType']").val());
				datas.append("Department", "ERP600");
				datas.append("Fuzzy", $("[name='Fuzzy']").val());
				datas.append("Warehouse", $("[name='Warehouse']").val());

				ajax_search_ERP600 =
					$.ajax({
						cache: true,
						type: "POST",
						url: url_search,
						//data: $('#filterForm').serialize(),
						data: datas, //指定data数据
						datatype: 'text', //指定data数据
						processData: false, //指定data数据
						contentType: false, //指定data数据
						async: true,
						error: function(request) {
							state_error("无法连接服务器");
						},
						success: function(data) {
							var resp = JSON.parse(data);
							if("0" == resp.ERRCODE && "succ" == resp.ERRDESC) {
								if(resp.data[0].list.length == 0) {
									if(resp.data[0].desc != "库存信息正常，无库存") {
										search_task[1] = ["ERP600", "异常", 0, ""];
										board_set_extra_content("鲁泰服务器异常，无法查询" + resp.data[0].name + "库存");
									} else {
										search_task[1] = ["ERP600", "已完成", 0, ""];
									}
								} else {
									search_task[1] = ["ERP600", "已完成", resp.data[0].list.length, ""];
									board_add_lines(resp.data[0], sortKeyWord);
								}
								board_update();
							} else {
								state_error(resp.data);
							}
						}
					});
			}

			function searchStop() {
				if(ajax_search_K3 != null) {
					ajax_search_K3.abort();
				}
				if(ajax_search_ERP600 != null) {
					ajax_search_ERP600.abort();
				}
				state_defult();
			}

			function board_set_extra_content(msg) {
				board_table_extra_content = "";
				board_table_extra_content += "<div class=\"col s12 m12 l12 red-text\">";
				if(msg != null) {
					board_table_extra_content += "<h5>";
					board_table_extra_content += msg;
					board_table_extra_content += "</h5>";
				}
				board_table_extra_content += "</div>";
			}

			function board_get_extra_content() {
				return board_table_extra_content;
			}

			function board_get_header() {
				if(board_table_header == null) {
					board_table_header = "";
					board_table_header += "<div class=\"col s12 m12 l12 red-text\">";
					board_table_header += "<table class=\"striped\">";
					board_table_header += "<thead>";
					board_table_header += "<tr>";
					board_table_header += "<th onclick=\"submitfilterform(\'UskinCode\')\">USKIN编码 </th>";
					if(0 <= ec_user_rank && ec_user_rank < 10) {
						board_table_header += "<th class=\"hide-on-small-only\">部门 </th>";
						board_table_header += "<th class=\"hide-on-small-only\" onclick=\"submitfilterform(\'LuthaiCode\')\">物料编码</th>";
					} else if(10 <= ec_user_rank && ec_user_rank < 20) {
						board_table_header += "<th class=\"hide-on-small-only\">部门 </th>";
						board_table_header += "<th class=\"hide-on-small-only\">物料编码(当前无权查看)</th>";
					} else {
						board_table_header += "<th class=\"hide-on-small-only\">部门 (当前无权查看)</th>";
						board_table_header += "<th class=\"hide-on-small-only\">物料编码(当前无权查看)</th>";
					}
					board_table_header += "<th onclick=\"submitfilterform(\'All\')\">库存 </th>";
					board_table_header += "<th onclick=\"submitfilterform(\'Available\')\">可用库存 </th>";
					board_table_header += "</tr>";
					board_table_header += "</thead>";
					board_table_header += "<tbody>";
				}
				return board_table_header;
			}

			function board_clr_lines() {
				board_table_lines == null;
			}

			function board_add_lines(targetJson, sortKeyWord) {

				if(board_table_lines == null) {
					board_table_lines = "";
				}

				if(targetJson != null) {
					for(var j = 0; j < targetJson.list.length; j++) {

						targetJson.list = jsonSort(targetJson.list, sortKeyWord, false);

						board_table_lines += "<tr>";
						board_table_lines += "<td>";
						if("MLCK040" == targetJson.list[j].Warehouse) {
							board_table_lines += "<a>";
							if("" != targetJson.list[j].UskinCode) {
								if("客供" == targetJson.list[j].UskinCode) {
									board_table_lines += "该面料无USKIN编码";
								} else {
									board_table_lines += targetJson.list[j].UskinCode;
									board_table_lines += "(客供仓库)";
								}
							} else {
								board_table_lines += "该面料无USKIN编码";
							}
							board_table_lines += "</a>";

							board_table_lines += "<br/>";

							board_table_lines += "<a ";
							board_table_lines += "style=\"color: lightskyblue;\" ";
							board_table_lines += "href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBCMan.action?code=";
							board_table_lines += targetJson.list[j].LuthaiCode;
							board_table_lines += "\">";
							board_table_lines += "男装客供";
							board_table_lines += "</a>";

							board_table_lines += "<a>";
							board_table_lines += " ";
							board_table_lines += "</a>";

							board_table_lines += "<a ";
							board_table_lines += "style=\"color: lightpink;\" ";
							board_table_lines += "href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBCWoman.action?code=";
							board_table_lines += targetJson.list[j].LuthaiCode;
							board_table_lines += "\">";
							board_table_lines += "女装客供";
							board_table_lines += "</a>";

						} else if("MLCK037" == targetJson.list[j].Warehouse) {
							board_table_lines += "<a href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYXMain.action?code=";
							board_table_lines += targetJson.list[j].UskinCode;
							board_table_lines += "\">";
							board_table_lines += targetJson.list[j].UskinCode;
							board_table_lines += "(报喜鸟仓库)";
							board_table_lines += "</a>";

						} else {
							board_table_lines += "<a href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYXMan.action?code=";
							board_table_lines += targetJson.list[j].UskinCode;
							board_table_lines += "\">";
							board_table_lines += targetJson.list[j].UskinCode;
							board_table_lines += "</a>";
						}
						board_table_lines += "</td>";
						if(0 <= ec_user_rank && ec_user_rank < 10) {
							board_table_lines += "<td class=\"hide-on-small-only\">";
							board_table_lines += targetJson.list[j].Department;
							board_table_lines += "</td>";
							board_table_lines += "<td class=\"hide-on-small-only\">";
							board_table_lines += targetJson.list[j].LuthaiCode;
							board_table_lines += "</td>";
						} else if(10 <= ec_user_rank && ec_user_rank < 20) {
							board_table_lines += "<td class=\"hide-on-small-only\">";
							board_table_lines += targetJson.list[j].Department;
							board_table_lines += "</td>";
							board_table_lines += "<td class=\"hide-on-small-only\">";
							board_table_lines += "*";
							board_table_lines += "</td>";
						} else {
							if("<%=su%>" == "true") {
								board_table_lines += "<td class=\"hide-on-small-only\">";
								board_table_lines += targetJson.list[j].Department;
								board_table_lines += "</td>";
								board_table_lines += "<td class=\"hide-on-small-only\">";
								board_table_lines += targetJson.list[j].LuthaiCode;
								board_table_lines += "</td>";
							} else {
								board_table_lines += "<td class=\"hide-on-small-only\">";
								board_table_lines += "*";
								board_table_lines += "</td>";
								board_table_lines += "<td class=\"hide-on-small-only\">";
								board_table_lines += "*";
								board_table_lines += "</td>";
							}
						}
						board_table_lines += "<td>";
						board_table_lines += targetJson.list[j].All;
						board_table_lines += "</td>";
						board_table_lines += "<td>";
						board_table_lines += targetJson.list[j].Available;
						board_table_lines += "</td>";
						board_table_lines += "</tr>";
					}
				}

			}

			function board_get_lines() {
				return board_table_lines;
			}

			function board_get_footer() {
				if(board_table_footer == null) {
					board_table_footer = "";
					board_table_footer += "</tbody>";
					board_table_footer += "</table>";
					board_table_footer += "</div>";
				}
				return board_table_footer;
			}

			function board_set_content(msg) {
				board_content = "";
				board_content += "<div class=\"col s12 m12 l12 red-text\">";
				if(msg != null) {
					board_content += "<h5>";
					board_content += msg;
					board_content += "</h5>";
				}
				board_content += "</div>";
			}

			function board_com_content() {
				board_content = "";
				board_content += board_get_extra_content();
				if(board_get_lines() != "" && board_get_lines() != null) {
					board_content += board_get_header();
					board_content += board_get_lines();
					board_content += board_get_footer();
				}
			}

			function board_update() {

				var line_total = 0;
				var task_total = 0;
				var err_total = 0;

				for(var i = 0; i < search_task.length; i++) {
					if(search_task[i][1] == "未启动") {
						line_total += search_task[i][2];
						task_total += 1;
						search_task[i][3] = "";
					}
					if(search_task[i][1] == "查询中") {
						line_total += search_task[i][2];
						task_total += 0;
						search_task[i][3] = "正在查询" + search_task[i][0] + "库存 ";
					}
					if(search_task[i][1] == "已完成") {
						line_total += search_task[i][2];
						task_total += 1;
						search_task[i][3] = "";
					}
					if(search_task[i][1] == "异常") {
						line_total += search_task[i][2];
						task_total += 1;
						search_task[i][3] = "鲁泰" + search_task[i][0] + "服务器异常,暂无数据 ";
						err_total += 1;
					}
				}

				if(task_total == 2) {
					state_answer();
				} else {
					state_loading_incomplete(search_task[0][3] + search_task[1][3]);
				}

				if(line_total == 0) {
					if(err_total == 0) {
						board_set_extra_content("鲁泰仓库显示该面料无库存");
					} else {
						board_set_extra_content("鲁泰仓库显示该面料无库存" + "(" + search_task[0][3] + search_task[1][3] + ")");
					}
				} else {
					board_set_extra_content("");
				}

				board_com_content();

				$("#ucs_table").html(board_content);
			}

			function state_defult() {
				$("#ucs_tip_card").hide();
				$("#ucs_tip").html("");
				$("#ucs_progress_bar").hide();

				$("#ucs_table_card").hide();
				$("#ucs_table").html("");

				$("#btn_search_start").show();
				$("#btn_search_stop").hide();
			}

			function state_loading(msg) {
				$("#ucs_tip_card").show();
				if(msg == null) {
					$("#ucs_tip").html("查询中，请稍候...");
				} else {
					$("#ucs_tip").html("" + msg);
				}
				$("#ucs_progress_bar").show();

				$("#ucs_table_card").hide();
				$("#ucs_table").html("");

				$("#btn_search_start").hide();
				$("#btn_search_stop").show();
			}

			function state_loading_incomplete(msg) {
				$("#ucs_tip_card").show();
				if(msg == null) {
					$("#ucs_tip").html("查询中，请稍候...");
				} else {
					$("#ucs_tip").html("" + msg);
				}
				$("#ucs_progress_bar").show();

				$("#ucs_table_card").show();
				$("#ucs_table").html("");

				$("#btn_search_start").hide();
				$("#btn_search_stop").show();
			}

			function state_answer() {
				$("#ucs_tip_card").hide();
				$("#ucs_tip").html("");
				$("#ucs_progress_bar").hide();

				$("#ucs_table_card").show();
				$("#ucs_table").html("");

				$("#btn_search_start").show();
				$("#btn_search_stop").hide();
			}

			function state_error(string_desc) {
				$("#ucs_tip_card").show();
				$("#ucs_tip").html(string_desc);
				$("#ucs_progress_bar").hide();

				$("#ucs_table_card").hide();
				$("#ucs_table").html("");

				$("#btn_search_start").show();
				$("#btn_search_stop").hide();
			}
		</script>

	</head>

	<body>

		<header>
			<nav class="teal" role="navigation">
				<div class="nav-wrapper container">
					<!-- 页面标题  -->
					<a id="logo-container " href="#" class="brand-logo white-text ">即时库存查询(多线程加速)</a>
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
								<a class=" white-text" onclick="logout()">注销 </a>
							</li>
						</div>
						<%=menulist%>
					</ul>
				</div>
			</nav>
		</header>

		<main>
			<div class="container">
				<div class="section">
					<!--控制器-->
					<div class="col s12 m12 l12">
						<div class="card-panel">
							<form method="post" id="filterForm" onkeydown="if(event.keyCode==13)return false;">
								<div class="row">
									<div style="display: none;">
										<input name="rank">
										<%=ec_user_rank%>
										</input>
									</div>

									<div class="col s12 m8 l8 red-text">
										<div class="input-field">
											<input type="text" class="validate" name="Code" value="">
											<label>请输入USKIN编码 或 客供面料快递单号</label>
										</div>
									</div>

									<div class="col s6 m4 l4 teal-text input-field">
										<select name="CodeType">
											<option value="YX">USKIN编码</option>
											<option value="LT">鲁泰物料编码</option>
										</select><label>编码类型指定（仅作用于现货科）</label>
									</div>
									<div class="col s6 m4 l3 teal-text input-field">
										<select name="Department">
											<option value="All">联合查询</option>
											<option value="ERP600">现货科/零裁组</option>
											<option value="K3">制衣ERP</option>
										</select><label>数据源</label>
									</div>
									<div class="col s6 m4 l3 teal-text input-field ">
										<select name="Fuzzy">
											<option value="Y">模糊查询</option>
											<option value="N">精确查询</option>
										</select><label>查询模式（仅支持制衣ERP）</label>
									</div>
									<div class="col s6 m4 l3 teal-text input-field">
										<select name="Warehouse">
											<option value="LT">鲁泰仓库</option>
											<option value="ZN">智能制造</option>
											<option value="KG">客供仓库</option>
										</select><label>仓库（仅支持制衣ERP）</label>
									</div>

									<div class="col s12 m12 l3 white-text center ">
										<div class="input-field ">
											<a class="btn" onclick="searchStart('UskinCode')" id="btn_search_start">确定</a>
											<a class="btn" onclick="searchStop()" id="btn_search_stop">取消</a>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!--进度板-->
					<div class="card-panel" id="ucs_tip_card">
						<div class="card-content grey-text">
							<div class="row">
								<p class="col s12 m12 l12" id="ucs_tip">tips</p>
								<div class="progress" id="ucs_progress_bar">
									<div class="indeterminate"></div>
								</div>
							</div>
						</div>
					</div>
					<!--结果板-->
					<div class="col s12 m12 l12">
						<div class="card-panel" id="ucs_table_card">
							<div class="card-content grey-text">
								<div class="row">
									<div id="ucs_table">
									</div>
								</div>
							</div>
						</div>
					</div>
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

</html>