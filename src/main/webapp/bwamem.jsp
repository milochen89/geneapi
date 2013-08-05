<!DOCTYPE html>
<html lang="en">
<head>
	<title>BWA MEM</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	 <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script type="text/javascript">	
		function ConvertFormToJSON(form){
			var array = jQuery(form).serializeArray();
			var json = {};
			
			jQuery.each(array, function() {
				json[this.name] = this.value || '';
			});
			console.log(json);
			return json;
		}
		
		$(document).ready(function(){
			jQuery('form#add-new-task').bind('submit', function(event){
				event.preventDefault();
				
				var form = this;
				var json = JSON.stringify(ConvertFormToJSON(form));
				var tbody = jQuery('#to-do-list > tbody');
				var checkfile = $("#refname").val()+"_"+$("#inputpath").val()+"_"+$("#algorithm").val();
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
						if (isfileexist)
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
									
										}).done(function() { 
											//alert("Succeeded to add to-do"); 
											window.location = "/api/taskadded.jsp" 
										}).fail(function(jqXHR, textStatus, errorThrown) {
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
					
						}).done(function() { 
							//alert("Succeeded to add to-do"); 
							window.location = "/api/taskadded.jsp" 
						}).fail(function(jqXHR, textStatus, errorThrown) {
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
		 });


		$(document).ready(function() {
			$.ajax({
				url :"rest/command/getreflist",
				dataType :"json",
				data :"{}",
				textField :"refname",
				valueField :"refname",
				contentType :"application/json;charset=utf-8",
				traditional :true,
				success :function(data) {
					var option = null;
					$.each(data.reflist, function(i, item) {
						option+="<option value=" + item + " >"+ item + "</option>";
						console.log(item)
					});
					$("#refname").html(option);
				},
				error :function(msg) {
					alert("error");
				}
			 });

				$.ajax({
					url :"rest/command/getinputlist",
					dataType :"json",
					data :"{}",
					textField :"inputpath",
					valueField :"inputpath",
					contentType :"application/json;charset=utf-8",
					traditional :true,
					success :function(data) {
						var option = null;
						$.each(data.inputlist, function(i, item) {
							option+="<option value=" + item + " >"+ item + "</option>";
							console.log(item)
						});
						$("#inputpath").html(option);
					},
					error :function(msg) {
						alert("error");
					}
				 });
		});




			
		
	</script>	
</head>
<body>

	<header id="banner">	
	</header>
	<!-- [content] -->
	<section id="content">
		<!-- [to-do] -->
		<article id="to-do">			
			<section class="new-to-do">			
				<form id="add-new-task">
					<label for="inputpath">InputPath:</label>
					 <select id="inputpath" name="inputpath"></select>
					<label for="refname">RefName:</label>
					 <select id="refname" name="refname"></select>	
					 <label for="algorithm">Algorithm:</label>
					 <select id="algorithm" name="algorithm">
					   <option value="mem">mem</option>
 						 <option value="backtract">backtrack</option></select>					
					<br/>
					<input type="submit" value="Add new task">
					<div id="Overwrite-confirm" title="Do you want to overwrite the file?">
					 <p><span id="msg" style="float: left; margin: 0 7px 20px 0;visibility:hidden;">The output file already exists on the server, are you sure you want to overwrite?</p>
					</div>
				</form>
			</section>
			<footer>
			</footer>
		</article>
		<!-- [/to-do] -->		
	</section>
	<!-- [/content] -->
</body>
</html>