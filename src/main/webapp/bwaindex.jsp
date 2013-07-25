<!DOCTYPE html>
<html lang="en">
<head>
	<title>BWA INDEXING </title>
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
					type:"POST",
					url:"rest/command/index",
					contentType:'application/json',
					data:json
			
				}).done(function() { 
					alert("Succeeded to add to-do"); 
				}).fail(function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
					console.log(errorThrown);
					});

				return true;
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
					<label for="refpath">RefPath:</label>
					<input id="refpath" name="refpath" type="text" required>
					<label for="refname">RefName:</label>
					<input id="refname" name="refname" type="text" required>					
					<br/>
					<input type="submit" value="Add new">
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