$(document).ready(function(){
	var loader = $("#loader");

	$("form#new_task").on("reset",function(){
		$("#job_created").fadeOut("fast");
	});

	/*BWA-MEM CODE START*/
	/********************/
	
	reload();
	new_task();


	function ConvertFormToJSON(form){
		var array = jQuery(form).serializeArray();
		var json = {};

		jQuery.each(array, function() {
			json[this.name] = this.value || '';
		});
		console.log(json);
		return json;
	}

	function new_task(){

		jQuery('form#new_task').on('submit', function(event){
			event.preventDefault();
			$(".submit").fadeOut("fast",function(){
				$("#sending").fadeIn("fast");
			})
			var form = this;
			var json = JSON.stringify(ConvertFormToJSON(form));
			var tbody = jQuery('#to-do-list > tbody');
			var checkfile = $(".input").eq(0).val()+"_"+$(".input").eq(1).val()+"_"+$(".select").val();
			var isfileexist = 0;
			console.log(checkfile);

			$.ajax({
				type: "POST",
				url: "rest/command/checkoutputlist",
				contentType: 'text/plain',
				data: checkfile,
				success :function(data) {
					isfileexist = data;
					console.log(data);
					if (isfileexist == 1)
					{
						console.log(isfileexist);
						$("#msg").css("visibility","visible");
						$( "#Overwrite-confirm" ).dialog({
							resizable: false,
							height:240,
							modal: true,
							buttons: {
								"Overwrite": function() {

									$.ajax({
										type: "POST",
										url: "rest/command/mem",
										contentType: 'application/json',
										data: json

									}).done(function(data) { 
										//alert("Succeeded to add to-do"); 
										console.log(data);
									}).fail(function(jqXHR, textStatus, errorThrown) {
										alert(jqXHR.responseText);
										console.log(jqXHR);
										console.log(textStatus);
										console.log(errorThrown);
									});
								},
								Cancel: function() {
									$( this ).dialog( "close" );
								}
							}
						});
					}else{

						$.ajax({
							type: "POST",
							url: "rest/command/mem",
							contentType: 'application/json',
							data: json

						}).done(function(data) { 
							//alert("Succeeded to add to-do"); 
							$("#job_created p").html("Job created with id: "+data).parent().fadeIn("fast",function(){
								$("#sending").fadeOut("fast",function(){
									$(".submit").fadeIn("fast");
								})
							});
							//alert(data);
						}).fail(function(jqXHR, textStatus, errorThrown) {
							alert(jqXHR.responseText);
							console.log(jqXHR);
							console.log(textStatus);
							console.log(errorThrown);
						});
					}


				},
				error :function(msg) {
					alert("error");
				}
			});


		});
	}
	/********************/
	/*BWA-MEM CODE END*/

	/*SIDEMENU START*/
	/****************/
	$("nav ul li a").click(function(){
		var link = $(this);

		if(link.attr("class") != "selected" ){
			loader.fadeIn("fast",function(){
				var link_text = link.text();
				if(link_text == "Jobs"){
					$("#container").load("ajax/progress.html",function(){
						
						$(".refresh").on("click",function(event){
							event.preventDefault();
							$("#get_progress").trigger('submit');
						});
						
						$('#get_progress').on('submit', function(event){
							event.preventDefault();

							var tbody = jQuery('#to-do-list > tbody');
							var jobid = $("#get_progress").serialize();
							$("legend").text(jobid.split("=")[1]);
							console.log(jobid.split("=")[1]);

							$.ajax({
								type: "POST",
								url: "rest/command/checkprocess",
								contentType: 'text/plain',
								data:  jobid,
								dataType :"json",
								success :function(data) {
									mc = data.mapcompletion*100;
									rc = data.reducecompletion*100;
									if (data.mapcompletion != -1)
									{
										$( "#mapcompletion" ).progressbar({
											value: mc
										});
										$( "#reducecompletion" ).progressbar({
											value: rc
										});
										$("#completion").fadeIn("fast");
									}else{

										alert("The Job Id is wrong, please go back and check");  

									}
								},
								error :function(msg) {
									alert("error");
								}
							});

							return true;
						});
					});



				}
				else if(link_text == "Create"){
					
					$("#container").load("index.jsp .content",function(){
						reload();
						new_task();
					});
				}
				
				else if(link_text == "Maintenance"){
					$("#container").load("ajax/cleanup.html",function(){
						$(".cleanup").on("click",function(){
							
							var text = $(this).text();
							if(text == "Clean All"){
								$.ajax({
									url :"rest/command/deleteall",
									
									success :function(data) {
										alert ("Deletion completed");
									},
									error :function(msg) {
										alert("error");
									}
								});

							}

							else if(text == "Clean Reference"){
								$.ajax({
									url :"rest/command/deletereference",
									
									success :function(data) {
										alert ("Deletion completed");
									},
									error :function(msg) {
										alert("error");
									}
								});

							}

							else if(text == "Clean BWA"){
								$.ajax({
									url :"rest/command/deletebwa",
									
									success :function(data) {
										alert ("Deletion completed");
									},
									error :function(msg) {
										alert("error");
									}
								});

							}

							else if(text == "Clean Cache"){
								$.ajax({
									url :"rest/command/deletecache",
									
									success :function(data) {
										alert ("Deletion completed");
									},
									error :function(msg) {
										alert("error");
									}
								});

							}

						});
					});
					
				}
				$(".selected").removeClass("selected");
				link.addClass("selected");

				console.log("success");
			}).fadeOut("fast");

		}

	});
	/*SIDEMENU END*/
	/****************/

});