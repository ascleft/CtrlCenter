var url_logout = "/CtrlCenter/LTYX/SCA/Logout.action";

function logout() {
	location.href = url_logout;
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

var DeliveryTimeTable;

function initDeliveryTimeTable() {
	DeliveryTimeTable = {
		'但单独打板，单量单裁': {
			'1': {
				'7': {
					'基础款式': '120',
					'复杂款': '140',
					'客供款式': '170'
				},
				'6': {
					'基础款式': '132',
					'复杂款': '154',
					'客供款式': '187'
				},
				'5': {
					'基础款式': '144',
					'复杂款': '168',
					'客供款式': '204'
				},
				'4': {
					'基础款式': '156',
					'复杂款': '182',
					'客供款式': '221'
				},
				'3': {
					'基础款式': '210',
					'复杂款': '240',
					'客供款式': '280'
				}
			}
		},
		'标准码成衣': {
			'1': {
				'7': {
					'基础款式': '105'
				}
			}
		},
		'团单': {
			'11-30': {
				'12': {
					'基础款式': '84',
					'复杂款': '90',
				}
			},
			'31-100': {
				'17': {
					'基础款式': '70',
					'复杂款': '75',
				}
			},
			'101-300': {
				'18': {
					'基础款式': '65',
					'复杂款': '70',
				}
			},
			'301-500': {
				'20': {
					'基础款式': '65',
					'复杂款': '70',
				}
			},
			'501-999': {
				'25': {
					'基础款式': '55',
					'复杂款': '65',
				}
			},
			'1000-1500': {
				'30': {
					'基础款式': '55',
					'复杂款': '65',
				}
			},
			'1500+': {
				'30-40': {
					'基础款式': '50',
					'复杂款': '60',
				}
			},
		}
	};
}
//订单类型
function set_delivery_time_table_section_1() {
	var list_1 = ""
	for(var prop1 in DeliveryTimeTable) {
		list_1 += "<option>";
		list_1 += prop1;
		list_1 += "</option>";
	}

	$("#delivery_time_table_section_1").html(list_1);
	$('select').material_select();
	set_delivery_time_table_section_2();
}
//件数
function set_delivery_time_table_section_2() {
	selected_1 = "" + $("#delivery_time_table_section_1 option:selected").text();
	var list_2 = ""
	for(var prop1 in DeliveryTimeTable) {
		if(prop1 == selected_1) {
			for(var prop2 in DeliveryTimeTable[prop1]) {
				list_2 += "<option>";
				list_2 += prop2;
				list_2 += "</option>";
			}
		}
	}

	$("#delivery_time_table_section_2").html(list_2);
	$('select').material_select();
	set_delivery_time_table_section_3();
}
//交期
function set_delivery_time_table_section_3() {
	selected_1 = "" + $("#delivery_time_table_section_1 option:selected").text();
	selected_2 = "" + $("#delivery_time_table_section_2 option:selected").text();
	var list_3 = ""
	for(var prop1 in DeliveryTimeTable) {
		if(prop1 == selected_1) {
			for(var prop2 in DeliveryTimeTable[prop1]) {
				if(prop2 == selected_2) {
					for(var prop3 in DeliveryTimeTable[prop1][prop2]) {
						list_3 += "<option>";
						list_3 += prop3;
						list_3 += "</option>";
					}
				}
			}
		}
	}

	$("#delivery_time_table_section_3").html(list_3);
	$('select').material_select();
	set_delivery_time_table_section_4();
}
//款式类型
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
								list_4 += "<option value=\"" + DeliveryTimeTable[prop1][prop2][prop3][prop4] + "\">";
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

function initDeliveryTime() {
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

var LZX_11_Table;

function initLZX11Table() {

	LZX_11_Table = {
		'LZX-11-23': {
			'01': '',
			'02': '',
			'03': '',
			'04': '',
			'05': '',
			'06': '',
			'07': '',
			'08': '',
			'09': '',
			'10': '',
			'11': '',
			'12': '',
			'13': '',
			'14': '',
			'15': '',
			'16': '',
			'17': '',
			'18': '',
			'19': '',
			'20': '',
			'21': '',
			'22': '',
			'23': '',
			'24': '',
		},
		'LZX-11-24': {
			'01': '',
			'02': '',
			'03': '',
			'04': '',
			'05': '',
			'06': '',
			'07': '',
			'08': '',
			'09': '',
			'10': '',
			'11': '',
			'12': '',
			'13': '',
			'14': '',
			'15': '',
			'16': '',
			'17': '',
			'18': '',
			'19': '',
			'20': '',
			'21': '',
			'22': '',
			'23': '',
			'24': '',
		},
		'LZX-11-25': {
			'01': '',
			'02': '',
			'03': '',
			'04': '',
			'05': '',
			'06': '',
			'07': '',
			'08': '',
			'09': '',
			'10': '',
			'11': '',
			'12': ''
		},
		'LZX-11-26': {
			'01': '',
			'02': '',
			'03': '',
			'04': '',
			'05': ''
		},
		'LZX-11-27': {
			'01': '',
			'02': '',
			'03': '',
			'04': '',
			'05': '',
			'06': '',
			'07': '',
			'08': '',
			'09': '',
			'10': ''
		}
	};
}
//订单类型
function set_1() {
	var list_1 = ""
	for(var prop1 in LZX_11_Table) {
		list_1 += "<option>";
		list_1 += prop1;
		list_1 += "</option>";
	}
	console.log("dddd");

	$("select[name$='LZX_11_PIC_TYPE']").html(list_1);
	$('select').material_select();
	set_2();
}
//件数
function set_2() {
	selected_1 = "" + $("select[name$='LZX_11_PIC_TYPE'] option:selected").text();
	var list_2 = ""
	for(var prop1 in LZX_11_Table) {
		if(prop1 == selected_1) {
			for(var prop2 in LZX_11_Table[prop1]) {
				list_2 += "<option>";
				list_2 += prop2;
				list_2 += "</option>";
			}
		}
	}

	$("select[name$='LZX_11_PIC_NUM']").html(list_2);
	$('select').material_select();
}
//初始化刺绣模块
function initLZX11() {
	initLZX11Table();
	set_1();
	$("select[name$='LZX_11_PIC_TYPE']").bind("change", function() {
		set_2();
	});
}

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
}

//启用客供主唛
function use_pbc_YX_08() {
	$("#YX_08_div").hide();
	$("#YX_08").bind("change", function() {
		var selected_name = "" + $("#YX_08 option:selected ").text();
		if(selected_name == "客供") {
			$("#YX_08_div").show();
			$("#YX_08_pbc").val("");
		} else {
			$("#YX_08_div").hide();
			$("#YX_08_pbc").val("");
		}
		Materialize.updateTextFields();
	});
}

//启用尺寸选择
function use_size() {
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
}