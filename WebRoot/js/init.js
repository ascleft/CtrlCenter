(function($) {
	$(function() {

		$('.button-collapse').sideNav();
		$('.parallax').parallax();

		//初始化侧栏导航
		$(".button-collapse").sideNav();

		//初始化折叠界面		
		$(document).ready(function() {
			$('.collapsible').collapsible();
		});

		//初始化下拉列表	
		$(document).ready(function() {
			$('select').material_select();
		});

		//日期选择器
		$('.datepicker').pickadate({
			selectMonths: true, // Creates a dropdown to control month
			selectYears: 15 // Creates a dropdown of 15 years to control year
		});

		//初始化模态交互框
		$(document).ready(function() {
			// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
			$('.modal').modal();
			$('.modal').modal({
				dismissible: false, // Modal can be dismissed by clicking outside of the modal
				opacity: .5, // Opacity of modal background
				in_duration: 300, // Transition in duration
				out_duration: 200, // Transition out duration
				starting_top: '4%', // Starting top style attribute
				ending_top: '10%', // Ending top style attribute
				ready: function(modal, trigger) { // Callback for Modal open. Modal and trigger parameters available.
					//				alert("Ready");
					//				console.log(modal, trigger);
				},
				complete: function() { // Callback for Modal close
					//				alert('Closed');
				}
			});
		});

	}); // end of document ready
})(jQuery); // end of jQuery name space