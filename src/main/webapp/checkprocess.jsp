<!DOCTYPE html>
<html lang="en">
<head>
	<title>BWA CHECK PROCESS</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript">	

		
			$(document).ready(function() {
			jQuery('form#add-new-task').bind('submit', function(event){
				event.preventDefault();
				
				var tbody = jQuery('#to-do-list > tbody');

				$.ajax({
					type: "POST",
					url: "rest/command/checkprocess",
					contentType: 'text/plain',
					data:  $("#add-new-task").serialize()
					dataType :"json",
				}).done(function() { 
					//\alert("Succeeded to add to-do"); 
					window.location = "/api/process.jsp" 
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
					<label for="jobid">Jobid:</label>
					<input id="jobid" name="jobid" type="text" required>					
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