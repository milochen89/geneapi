<!DOCTYPE html>
<html lang="en">
<head>
	<title>BWA MEM</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
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
		
		jQuery(document).on('ready', function() {
			jQuery('form#add-new-task').bind('submit', function(event){
				event.preventDefault();
				
				var form = this;
				var json = JSON.stringify(ConvertFormToJSON(form));
				var tbody = jQuery('#to-do-list > tbody');

				$.ajax({
					type: "POST",
					url: "rest/command/mem",
					contentType: 'application/json',
					data: json
			
				}).done(function() { 
					alert("Succeeded to add to-do"); 
				}).fail(function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
					console.log(errorThrown);
					});

				return true;
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
		});


		$(document).ready(function() {
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
					<label for="outputpath">OutputPath:</label>
					<input id="outputpath" name="outputpath" type="text" required>					
					<br/>
					<input type="submit" value="Add new task">
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