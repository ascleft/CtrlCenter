var url_logout = "/CtrlCenter/LTYX/SCA/Logout.action";
var url_checklogin = "/CtrlCenter/LTYX/SCA/CheckLogin.action";

function logout() {
	location.href = url_logout;
}

function checkLoginState() {
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
				window.open("/CtrlCenter/LTYX/SCA/ReLoginPage.action");
			}
		}
	});
}

function testVar() {
	var map = {};
	map['单独打板，单量单裁'] = 1;
	map['key2@'] = 2;

	console.log(map['单独打板，单量单裁']); //结果是1.
	console.log(map['key2@']); //结果是2.

	//如果遍历map
	for(var prop in map) {
		if(map.hasOwnProperty(prop)) {
			console.log('key is ' + prop + ' and value is' + map[prop]);
		}
	}
}

//-------------------------------------------------------------------------交期报价模块↓
//定义交期系统字典
var DeliveryTimeTable;
//初始化交期系统字典
function initDeliveryTimeTable() {
	DeliveryTimeTable = {
		"单独打板，单量单裁": {
			"1": {
				"6": {
					"基础款": "120",
					"复杂款": "140",
					"客供款": "170"
				},
				"5": {
					"基础款": "144",
					"复杂款": "168",
					"客供款": "204"
				},
				"4": {
					"基础款": "156",
					"复杂款": "182",
					"客供款": "221"
				},
				"3": {
					"基础款": "240",
					"复杂款": "280",
					"客供款": "340"
				}
			}
		},
		"标准码成衣": {
			"1": {
				"7": {
					"基础款": "105"
				}
			}
		},
		"团单": {
			"11-30": {
				"10-15": {
					"基础款": "84",
					"复杂款": "90",
					"客供款": "9999999"
				}
			},
			"31-100": {
				"10-15": {
					"基础款": "70",
					"复杂款": "75",
					"客供款": "9999999"
				}
			},
			"101-500": {
				"10-15": {
					"基础款": "65",
					"复杂款": "70",
					"客供款": "9999999"
				}
			},
			"501-1500": {
				"15-25": {
					"基础款": "55",
					"复杂款": "65",
					"客供款": "9999999"
				}
			}
		}
	};
}
//填充订单类型
function set_delivery_time_table_section_1() {
	var list_1 = ""
	for(var prop1 in DeliveryTimeTable) {
		list_1 += "<option value=\"" + prop1 + "\">";
		list_1 += prop1;
		list_1 += "</option>";
	}

	$("#delivery_time_table_section_1").html(list_1);
	$('select').material_select();
	set_delivery_time_table_section_2();
}
//填充可选件数区间
function set_delivery_time_table_section_2() {
	selected_1 = "" + $("#delivery_time_table_section_1 option:selected").text();
	var list_2 = ""
	for(var prop1 in DeliveryTimeTable) {
		if(prop1 == selected_1) {
			for(var prop2 in DeliveryTimeTable[prop1]) {
				list_2 += "<option value=\"" + prop2 + "\">";
				list_2 += prop2;
				list_2 += "</option>";
			}
		}
	}

	$("#delivery_time_table_section_2").html(list_2);
	$('select').material_select();
	set_delivery_time_table_section_3();
}
//填充可选交期区间
function set_delivery_time_table_section_3() {
	selected_1 = "" + $("#delivery_time_table_section_1 option:selected").text();
	selected_2 = "" + $("#delivery_time_table_section_2 option:selected").text();
	var list_3 = ""
	for(var prop1 in DeliveryTimeTable) {
		if(prop1 == selected_1) {
			for(var prop2 in DeliveryTimeTable[prop1]) {
				if(prop2 == selected_2) {
					for(var prop3 in DeliveryTimeTable[prop1][prop2]) {
						list_3_item = "";
						list_3_item += "<option value=\"" + prop3 + "\">";
						list_3_item += prop3;
						list_3_item += "</option>";
						list_3 = list_3_item + list_3;
					}
				}
			}
		}
	}
	$("#delivery_time_table_section_3").html(list_3);
	$('select').material_select();
	set_delivery_time_table_section_4();
}
//填充可选款式类型
function set_delivery_time_table_section_4() {
	selected_1 = "" + $("#delivery_time_table_section_1 option:selected").text();
	selected_2 = "" + $("#delivery_time_table_section_2 option:selected").text();
	selected_3 = "" + $("#delivery_time_table_section_3 option:selected").text();
	var list_4 = ""
	for(var prop1 in DeliveryTimeTable) {
		if(prop1 == selected_1) {
			for(var prop2 in DeliveryTimeTable[prop1]) {
				if(prop2 == selected_2) {
					for(var prop3 in DeliveryTimeTable[prop1][prop2]) {
						if(prop3 == selected_3) {
							for(var prop4 in DeliveryTimeTable[prop1][prop2][prop3]) {
								list_4 += "<option value=\"" + prop4 + "\">";
								list_4 += prop4;
								list_4 += "</option>";
							}
						}
					}
				}
			}
		}
	}
	$("#delivery_time_table_section_4").html(list_4);
	$('select').material_select();
}
//初始化交期系统
function use_DeliveryTime() {
	initDeliveryTimeTable();
	set_delivery_time_table_section_1();
	$("#delivery_time_table_section_1").bind("change", function() {
		set_delivery_time_table_section_2();
	});
	$("#delivery_time_table_section_2").bind("change", function() {
		set_delivery_time_table_section_3();
	});
	$("#delivery_time_table_section_3").bind("change", function() {
		set_delivery_time_table_section_4();
	});
}
//-------------------------------------------------------------------------交期报价模块↑

//-------------------------------------------------------------------------刺绣模块↓
//定义刺绣文字字典
var LZX_11_Char_Table;
//定义刺绣图片字典
var LZX_11_Pic_Table;
//初始化刺绣图片和文字字典
function initLZX11Table() {
	LZX_11_Char_Table = {
		'map': {
			'LZX-11-01': {
				'name': '英文中宋',
				'size': '1'
			},
			'LZX-11-02': {
				'name': '舒体',
				'size': '1'
			},
			'LZX-11-04': {
				'name': '皇家体',
				'size': '1'
			},
			'LZX-11-05': {
				'name': '手写体',
				'size': '1'
			},
			'LZX-11-06': {
				'name': '古圆体',
				'size': '1'
			},
			'LZX-11-08': {
				'name': '维体',
				'size': '1'
			},
			'LZX-11-09': {
				'name': '书写体',
				'size': '1'
			},
			'LZX-11-10': {
				'name': '哥特体',
				'size': '1'
			},
			'LZX-11-12': {
				'name': '卡曼体',
				'size': '1'
			},
			'LZX-11-13': {
				'name': '花体',
				'size': '1'
			},
			'LZX-11-14': {
				'name': '书信体',
				'size': '1'
			},
			'LZX-11-15': {
				'name': '巴洛克体',
				'size': '1'
			},
			'LZX-11-16': {
				'name': '英文行楷',
				'size': '1'
			},
			'LZX-11-17': {
				'name': '黑体',
				'size': '1'
			},
			'LZX-11-18': {
				'name': '隶属',
				'size': '1'
			},
			'LZX-11-19': {
				'name': '毛体',
				'size': '1'
			},
			'LZX-11-20': {
				'name': '草书',
				'size': '1'
			},
			'LZX-11-21': {
				'name': '中文行楷',
				'size': '1'
			},
			'LZX-11-22': {
				'name': '中文中宋',
				'size': '1'
			},
		},
		'size': {
			'1': {
				'YX-12-07': '0.7cm',
				'YX-12-08': '0.8cm',
				'YX-12-09': '0.9cm',
				'YX-12-10': '1.0cm',
				'YX-12-11': '1.1cm',
				'YX-12-12': '1.2cm',
				'YX-12-13': '1.3cm',
				'YX-12-14': '1.4cm',
				'YX-12-15': '1.5cm',
				'YX-12-16': '1.6cm',
				'YX-12-17': '1.7cm',
				'YX-12-18': '1.8cm',
				'YX-12-19': '1.9cm',
				'YX-12-20': '2.0cm',
				'YX-12-21': '2.1cm',
				'YX-12-22': '2.2cm',
				'YX-12-23': '2.3cm',
				'YX-12-24': '2.4cm',
				'YX-12-25': '2.5cm',
				'YX-12-26': '2.6cm',
				'YX-12-27': '2.7cm',
				'YX-12-28': '2.8cm',
				'YX-12-29': '2.9cm',
				'YX-12-30': '3.0cm',
			}
		}
	};
	LZX_11_Pic_Table = {
		'LZX-11-23': {
			'map': {
				'01': '2',
				'02': '2',
				'03': '3',
				'04': '2',
				'05': '3',
				'06': '2',
				'07': '3',
				'08': '2',
				'09': '3',
				'10': '3',
				'11': '2',
				'12': '2',
				'13': '1',
				'14': '1',
				'15': '1',
				'16': '1',
				'17': '1',
				'18': '1',
				'19': '1',
				'20': '1',
				'21': '1',
				'22': '1',
				'23': '1',
				'24': '1',
			},
			'size': {
				'1': {
					'YX-12-10': '1.0cm',
					'YX-12-11': '1.1cm',
					'YX-12-12': '1.2cm',
					'YX-12-13': '1.3cm',
					'YX-12-14': '1.4cm',
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
				'2': {
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
				'3': {
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
			}

		},
		'LZX-11-24': {
			'map': {
				'01': '1',
				'02': '1',
				'03': '1',
				'04': '1',
				'05': '1',
				'06': '1',
				'07': '1',
				'08': '1',
				'09': '1',
				'10': '1',
				'11': '1',
				'12': '1',
				'13': '1',
				'14': '1',
				'15': '1',
				'16': '1',
				'17': '1',
				'18': '1',
				'19': '1',
				'20': '1',
				'21': '1',
				'22': '1',
				'23': '1',
				'24': '1',
			},
			'size': {
				'1': {
					'YX-12-10': '1.0cm',
					'YX-12-11': '1.1cm',
					'YX-12-12': '1.2cm',
					'YX-12-13': '1.3cm',
					'YX-12-14': '1.4cm',
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
			},
		},
		'LZX-11-25': {
			'map': {
				'01': '1',
				'02': '1',
				'03': '1',
				'04': '1',
				'05': '1',
				'06': '1',
				'07': '1',
				'08': '1',
				'09': '1',
				'10': '1',
				'11': '1',
				'12': '1'
			},
			'size': {
				'1': {
					'YX-12-10': '1.0cm',
					'YX-12-11': '1.1cm',
					'YX-12-12': '1.2cm',
					'YX-12-13': '1.3cm',
					'YX-12-14': '1.4cm',
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
			},
		},
		'LZX-11-26': {
			'map': {
				'01': '1',
				'02': '1',
				'03': '1',
				'04': '1',
				'05': '1'
			},
			'size': {
				'1': {
					'YX-12-10': '1.0cm',
					'YX-12-11': '1.1cm',
					'YX-12-12': '1.2cm',
					'YX-12-13': '1.3cm',
					'YX-12-14': '1.4cm',
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
			},
		},
		'LZX-11-27': {
			'map': {
				'01': '1',
				'02': '1',
				'03': '1',
				'04': '1',
				'05': '1',
				'06': '1',
				'07': '1',
				'08': '1',
				'09': '1',
				'10': '1'
			},
			'size': {
				'1': {
					'YX-12-10': '1.0cm',
					'YX-12-11': '1.1cm',
					'YX-12-12': '1.2cm',
					'YX-12-13': '1.3cm',
					'YX-12-14': '1.4cm',
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
			},
		}
	};
}
//填充文字刺绣第一部分
function set_lzx11_char_section_1() {
	var list_1 = ""
	for(var prop1 in LZX_11_Char_Table['map']) {
		list_1 += "<option value=\"" + prop1 + "\">";
		list_1 += prop1 + " " + LZX_11_Char_Table['map'][prop1]['name'];
		list_1 += "</option>";
	}
	$("select[name$='LZX_11_CHAR_TYPE']").html(list_1);
	$('select').material_select();
	set_lzx11_char_section_2();
}
//填充文字刺绣第二部分
function set_lzx11_char_section_2() {
	selected_1 = "" + $("select[name$='LZX_11_CHAR_TYPE'] option:selected").val();
	selected_1_size = "" + LZX_11_Char_Table['map'][selected_1]['size'];
	var list_2 = ""
	for(var prop2 in LZX_11_Char_Table['size'][selected_1_size]) {
		list_2 += "<option value=\"" + prop2 + "\">";
		list_2 += prop2 + " " + LZX_11_Char_Table['size'][selected_1_size][prop2];
		list_2 += "</option>";
	}
	$("select[name$='LZX_11_CHAR_SIZE']").html(list_2);
	$('select').material_select();
}
//填充图片刺绣第一部分
function set_lzx11_pic_section_1() {
	var list_1 = ""
	for(var prop1 in LZX_11_Pic_Table) {
		list_1 += "<option value=\"" + prop1 + "\">";
		list_1 += prop1;
		list_1 += "</option>";
	}
	$("select[name$='LZX_11_PIC_TYPE']").html(list_1);
	$('select').material_select();
	set_lzx11_pic_section_2();
}
//填充图片刺绣第二部分
function set_lzx11_pic_section_2() {
	selected_1 = "" + $("select[name$='LZX_11_PIC_TYPE'] option:selected").text();
	var list_2 = ""
	for(var prop2 in LZX_11_Pic_Table[selected_1]['map']) {
		list_2 += "<option value=\"" + prop2 + "\">";
		list_2 += selected_1 + " " + prop2;
		list_2 += "</option>";
	}
	$("select[name$='LZX_11_PIC_NUM']").html(list_2);
	$('select').material_select();
	set_lzx11_pic_section_3();
}
//填充图片刺绣第三部分
function set_lzx11_pic_section_3() {
	selected_1 = "" + $("select[name$='LZX_11_PIC_TYPE'] option:selected").text();
	selected_2 = "" + $("select[name$='LZX_11_PIC_NUM'] option:selected").val();
	selected_2_size = "" + LZX_11_Pic_Table[selected_1]['map'][selected_2];
	var list_3 = ""
	for(var prop3 in LZX_11_Pic_Table[selected_1]['size'][selected_2_size]) {
		list_3 += "<option value=\"" + prop3 + "\">";
		list_3 += prop3 + " " + LZX_11_Pic_Table[selected_1]['size'][selected_2_size][prop3];
		list_3 += "</option>";
	}
	$("select[name$='LZX_11_PIC_SIZE']").html(list_3);
	$('select').material_select();
}
//初始化刺绣模块
function use_lzx11() {
	initLZX11Table();
	set_lzx11_char_section_1();
	set_lzx11_pic_section_1();
	$("select[name$='LZX_11_CHAR_TYPE']").bind("change", function() {
		set_lzx11_char_section_2();
	});
	$("select[name$='LZX_11_PIC_TYPE']").bind("change", function() {
		set_lzx11_pic_section_2();
	});
	$("select[name$='LZX_11_PIC_NUM']").bind("change", function() {
		set_lzx11_pic_section_3();
	});
	console.log("已启用刺绣字典");
}
//-------------------------------------------------------------------------刺绣模块↑

//-------------------------------------------------------------------------客供专项↓
//客供扣子
function use_pbc_kouzi() {
	$("#kouzi_div").hide();
	$("#kouzi").bind("change", function() {
		var selected_name = "" + $("#kouzi option:selected ").text();
		if(selected_name == "客供") {
			$("#kouzi_div").show();
			$("#kouzi_pbc").val("");
		} else {
			$("#kouzi_div").hide();
			$("#kouzi_pbc").val("");
		}
		Materialize.updateTextFields();
	});
	console.log("已启用客供扣子");
}
//启用客供主唛
function use_pbc_YX_08() {
	$("#YX_08_div").hide();
	$("#YX_08").bind("change", function() {
		var selected_name = "" + $("#YX_08 option:selected").text();
		if(selected_name == "客供") {
			$("#YX_08_div").show();
			$("#YX_08_pbc").val("");
		} else {
			$("#YX_08_div").hide();
			$("#YX_08_pbc").val("");
		}
		Materialize.updateTextFields();
	});
	console.log("已启用客供主唛");
}
//-------------------------------------------------------------------------客供专项↑

//-------------------------------------------------------------------------手动专项↓
//自定义配色部位
function use_custom_weizhi_peise() {
	$("#weizhi_peise_div").hide();
	$("#weizhi_peise").bind("change", function() {
		var selected_name = "" + $("#weizhi_peise option:selected").text();
		if(selected_name == "手动填写") {
			$("#weizhi_peise_div").show();
			$("#weizhi_peise_custom").val("");
		} else {
			$("#weizhi_peise_div").hide();
			$("#weizhi_peise_custom").val("");
		}
		Materialize.updateTextFields();
	});
	console.log("已启用手动填写配色部位");
}
//-------------------------------------------------------------------------手动专项↑

//-------------------------------------------------------------------------尺寸专项↓
//启用尺寸选择
function use_size() {
	$("#measure_list").show();
	$("#size_list").hide();
	$("#measure_type").bind("change", function() {
		var selected_name = $("#measure_type option:selected").text();
		if(selected_name == "号衣尺码") {
			$("#measure_list").hide();
			$("#size_list ").show();
		} else if(selected_name == "不新增尺寸信息") {
			$("#measure_list").hide();
			$("#size_list").hide();
		} else {
			$("#measure_list").show();
			$("#size_list").hide();
		}
		Materialize.updateTextFields();
	});
}
//启用尺寸调整
function use_size_change() {
	$("#measure_list").show();
	$("#size_list").hide();
	$("#measure_type").bind("change", function() {
		var selected_name = $("#measure_type option:selected").text();
		if(selected_name == "号衣尺码") {
			$("#measure_list").hide();
			$("#size_list ").show();
		} else {
			$("#measure_list").show();
			$("#size_list").hide();
		}
		Materialize.updateTextFields();
	});
	console.log("已启用尺寸调整");
}
//-------------------------------------------------------------------------尺寸专项↑

//-------------------------------------------------------------------------款式工艺校验↓
//启用特殊款校验
function use_stylebase_check() {
	stylebase_check();
	$("#delivery_time_table_section_4").bind("change", function() {
		stylebase_check();
	});
	console.log("已启用特殊工艺校验");
}
//特殊款校验
function stylebase_check() {
	var selected_name = $("#delivery_time_table_section_4 option:selected").text();
	if(selected_name == "基础款") {
		$("option[stylebase='true']").removeAttr("disabled");
		//$("option[stylebase='true']").attr("disabled", true);
		$("option[stylebase='false']").removeAttr("disabled");
		$("option[stylebase='false']").attr("disabled", true);
		$("option[stylebase='pbc']").removeAttr("disabled");
		$("option[stylebase='pbc']").attr("disabled", true);
	} else if(selected_name == "复杂款") {
		$("option[stylebase='true']").removeAttr("disabled");
		//$("option[stylebase='true']").attr("disabled", true);
		$("option[stylebase='false']").removeAttr("disabled");
		//$("option[stylebase='false']").attr("disabled", true);
		$("option[stylebase='pbc']").removeAttr("disabled");
		$("option[stylebase='pbc']").attr("disabled", true);
	} else if(selected_name == "客供款") {
		$("option[stylebase='true']").removeAttr("disabled");
		//$("option[stylebase='true']").attr("disabled", true);
		$("option[stylebase='false']").removeAttr("disabled");
		//$("option[stylebase='false']").attr("disabled", true);
		$("option[stylebase='pbc']").removeAttr("disabled");
		//$("option[stylebase='pbc']").attr("disabled", true);
	} else {
		$("option[stylebase='true']").removeAttr("disabled");
		$("option[stylebase='true']").attr("disabled", true);
		$("option[stylebase='false']").removeAttr("disabled");
		$("option[stylebase='false']").attr("disabled", true);
		$("option[stylebase='pbc']").removeAttr("disabled");
		$("option[stylebase='pbc']").attr("disabled", true);
	}
	$('select').material_select();
}
//-------------------------------------------------------------------------款式工艺校验↑

//-------------------------------------------------------------------------委外商品报价模块↓
//定义交期系统字典
var SubcontractTable;
//初始化交期系统字典
function initSubcontractTable() {
	SubcontractTable = {
		'西服套装（高价位）': {
			'code': 'SUIT5000',
			'type': {
				'计算价格': {
					'fabric_length': '3',
					'ratio': '1.0',
					'fabric_unit_cost': {
						'2500': '',
						'2000': '',
						'1200': '',
						'900': '',
						'700': '',
						'600': '',
						'500': '',
						'体验店价格': ''
					},
					'process_cost': {
						'西装机器粘合衬': '1500',
						'西装机器半毛衬': '2000',
						'西装机器全毛衬': '2500',
						'西装半手工全毛衬': '3500',
						'西装手工全毛衬': '6500'
					}
				}
			}
		},
		'西服套装': {
			'code': 'SUIT2980',
			'type': {
				'一口价': {
					'USKIN西服套装': '2980'
				}
			}
		},
		'西装上衣': {
			'code': 'BLAZER',
			'type': {
				'一口价': {
					'USKIN西装上衣': '1788'
				},
				'计算价格': {
					'fabric_length': '3',
					'ratio': '0.6',
					'fabric_unit_cost': {
						'2500': '',
						'2000': '',
						'1200': '',
						'900': '',
						'700': '',
						'600': '',
						'500': '',
						'体验店价格': ''
					},
					'process_cost': {
						'西装机器粘合衬': '1500',
						'西装机器半毛衬': '2000',
						'西装机器全毛衬': '2500',
						'西装半手工全毛衬': '3500',
						'西装手工全毛衬': '6500'
					}
				}
			}
		},
		'西装西裤': {
			'code': 'PANTS',
			'type': {
				'一口价': {
					'USKIN西装西裤': '1192'
				},
				'计算价格': {
					'fabric_length': '3',
					'ratio': '0.4',
					'fabric_unit_cost': {
						'2500': '',
						'2000': '',
						'1200': '',
						'900': '',
						'700': '',
						'600': '',
						'500': '',
						'体验店价格': ''
					},
					'process_cost': {
						'西装机器粘合衬': '1500',
						'西装机器半毛衬': '2000',
						'西装机器全毛衬': '2500',
						'西装半手工全毛衬': '3500',
						'西装手工全毛衬': '6500'
					}
				}
			}
		},
		'马甲': {
			'code': 'VEST',
			'type': {
				'一口价': {
					'USKIN西装马甲': '890'
				},
				'计算价格': {
					'fabric_length': '3',
					'ratio': '0.3',
					'fabric_unit_cost': {
						'2500': '',
						'2000': '',
						'1200': '',
						'900': '',
						'700': '',
						'600': '',
						'500': '',
						'体验店价格': ''
					},
					'process_cost': {
						'西装机器粘合衬': '1500',
						'西装机器半毛衬': '2000',
						'西装机器全毛衬': '2500',
						'西装半手工全毛衬': '3500',
						'西装手工全毛衬': '6500'
					}
				}
			}
		},
		'牛仔裤（国产）': {
			'code': 'JEANS_H',
			'type': {
				'一口价': {
					'牛仔裤': '998'
				}
			}
		},
		'牛仔裤（进口）': {
			'code': 'JEANS_I',
			'type': {
				'一口价': {
					'牛仔裤': '1298'
				}
			}
		},
		'大衣': {
			'code': 'OVERCOAT',
			'type': {
				'一口价': {
					'USKIN大衣': '2680'
				},
				'计算价格': {
					'fabric_length': '2.4',
					'ratio': '1.0',
					'fabric_unit_cost': {
						'3500': '',
						'2500': '',
						'2000': '',
						'1200': '',
						'800': ''
					},
					'process_cost': {
						'大衣/粘合衬': '1500',
						'大衣/半毛衬': '2000',
						'大衣/全毛衬': '2500'
					}
				}
			}
		},
		'风衣': {
			'code': 'DUSTCOAT',
			'type': {
				'计算价格': {
					'fabric_length': '2.4',
					'ratio': '1.0',
					'fabric_unit_cost': {
						'800': ''
					},
					'process_cost': {
						'大衣/粘合衬': '1500',
						'大衣/半毛衬': '2000',
						'大衣/全毛衬': '2500'
					}
				}
			}
		},
		'休闲裤': {
			'code': 'SLACKS',
			'type': {
				'一口价': {
					'USKIN休闲裤': '998'
				},
				'计算价格': {
					'fabric_length': '3.0',
					'ratio': '0.4',
					'fabric_unit_cost': {
						'800': ''
					},
					'process_cost': {
						'粘合衬': '1500'
					}
				}
			}
		},
		'夹克': {
			'code': 'JACKET',
			'type': {
				'一口价': {
					'USKIN夹克': '1980'
				},
				'计算价格': {
					'fabric_length': '1.8',
					'ratio': '1.0',
					'fabric_unit_cost': {
						'2500': '',
						'2000': '',
						'1200': '',
						'900': '',
						'700': '',
						'600': '',
						'500': ''
					},
					'process_cost': {
						'粘合衬': '1500'
					}
				}
			}
		},
		'羊绒衫': {
			'code': 'CASHMERE',
			'type': {
				'一口价': {
					'USKIN羊绒衫（低价位）': '998',
					'USKIN羊绒衫（高价位）': '1298'
				}
			}
		}
	};
}
//填充产品列表
function set_subcontract_design_code_list() {
	var list_1 = "";
	for(var prop1 in SubcontractTable) {
		list_1 += "<option value=\"" + SubcontractTable[prop1].code + "\">";
		list_1 += prop1;
		list_1 += "</option>";
	}
	$("#subcontract_design_code").html(list_1);
	$('select').material_select();
	set_subcontract_price_type_list();
}
//填充定价模式列表
function set_subcontract_price_type_list() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	var list_2 = "";
	for(var prop1 in SubcontractTable) {
		if(prop1 == selected_1) {
			for(var prop2 in SubcontractTable[prop1].type) {
				list_2 += "<option>";
				list_2 += prop2;
				list_2 += "</option>";
			}
		}
	}
	$("#subcontract_price_type").html(list_2);
	$('select').material_select();
	dispose_subcontract_price_type();
}
//处理报价类型
function dispose_subcontract_price_type() {
	selected_1 = "" + $("#subcontract_price_type  option:selected").text();
	if("一口价" == selected_1) {
		set_subcontract_price_type_1();
		$("#subcontract_peice_final_div").show();
		$("#subcontract_fabric_unit_cost_div").hide();
		$("#subcontract_process_cost_div").hide();
		$("#subcontract_fabric_length_div").hide();
		$("#subcontract_ratio_div").hide();
	} else if("计算价格" == selected_1) {
		set_subcontract_price_type_2();
		$("#subcontract_peice_final_div").hide();
		$("#subcontract_fabric_unit_cost_div").show();
		$("#subcontract_process_cost_div").show();
		$("#subcontract_fabric_length_div").show();
		$("#subcontract_ratio_div").show();
	}
}
//填充一口价
function set_subcontract_price_type_1() {
	set_subcontract_peice_final_list();
}
//填充计算价格
function set_subcontract_price_type_2() {
	set_subcontract_process_cost_list();
	set_subcontract_fabric_unit_cost_list();
	set_subcontract_fabric_length();
	set_subcontract_ratio();
}
//填充一口价列表
function set_subcontract_peice_final_list() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	selected_2 = "" + $("#subcontract_price_type  option:selected").text();
	var list_1 = "";
	for(var prop1 in SubcontractTable[selected_1].type[selected_2]) {
		list_1 += "<option value=\"" + SubcontractTable[selected_1].type[selected_2][prop1] + "\">";
		list_1 += prop1 + " " + SubcontractTable[selected_1].type[selected_2][prop1];
		list_1 += "</option>";
	}
	$("#subcontract_peice_final").html(list_1);
	$('select').material_select();
}
//填充面料单价列表
function set_subcontract_fabric_unit_cost_list() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	selected_2 = "" + $("#subcontract_price_type  option:selected").text();
	var list_1 = "";
	for(var prop1 in SubcontractTable[selected_1].type[selected_2].fabric_unit_cost) {
		list_1 += "<option value=\"" + prop1 + "\">";
		list_1 += prop1;
		list_1 += "</option>";
	}
	$("#subcontract_fabric_unit_cost").html(list_1);
	$('select').material_select();
}
//填充加工费列表
function set_subcontract_process_cost_list() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	selected_2 = "" + $("#subcontract_price_type  option:selected").text();
	var list_1 = "";
	for(var prop1 in SubcontractTable[selected_1].type[selected_2].process_cost) {
		list_1 += "<option value=\"" + SubcontractTable[selected_1].type[selected_2].process_cost[prop1] + "\">";
		list_1 += prop1 + " " + SubcontractTable[selected_1].type[selected_2].process_cost[prop1];
		list_1 += "</option>";
	}
	$("#subcontract_process_cost").html(list_1);
	$('select').material_select();
}
//填充默认面料用量
function set_subcontract_fabric_length() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	selected_2 = "" + $("#subcontract_price_type  option:selected").text();
	$("#subcontract_fabric_length").val(SubcontractTable[selected_1].type[selected_2].fabric_length);
}
//填充辅助参数
function set_subcontract_ratio() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	selected_2 = "" + $("#subcontract_price_type  option:selected").text();
	$("#subcontract_ratio").val(SubcontractTable[selected_1].type[selected_2].ratio);
}
//启用其他商品报价
function use_SubcontractTable() {
	initSubcontractTable();
	set_subcontract_design_code_list();
	$("#subcontract_design_code").bind("change", function() {
		set_subcontract_price_type_list(); //
	});
	$("#subcontract_price_type").bind("change", function() {
		dispose_subcontract_price_type(); //
	});
	console.log("已启用商品报价");
}
//-------------------------------------------------------------------------委外商品报价模块↑

//-------------------------------------------------------------------------辅助JS↓
/*
 * @description    根据某个字段实现对json数组的排序
 * @param   array  要排序的json数组对象
 * @param   field  排序字段（此参数必须为字符串）
 * @param   reverse 是否倒序（默认为false）
 * @return  array  返回排序后的json数组
 */
function jsonSort(array, field, reverse) {
	//数组长度小于2 或 没有指定排序字段 或 不是json格式数据
	if(array.length < 2 || !field || typeof array[0] !== "object") return array;
	//数字类型排序
	if(typeof array[0][field] === "number") {
		array.sort(function(x, y) {
			return x[field] - y[field]
		});
	}
	//字符串类型排序
	if(typeof array[0][field] === "string") {
		array.sort(function(x, y) {
			return x[field].localeCompare(y[field])
		});
	}
	//倒序
	if(reverse) {
		array.reverse();
	}
	return array;
}
//-------------------------------------------------------------------------辅助JS↑