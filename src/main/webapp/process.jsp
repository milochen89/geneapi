<!DOCTYPE html>
<html lang="en">
<head>
	<title>BWA PROCESS</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript">	
		
		$(document).ready(function() {
			$.ajax({
				url :"rest/command/getprocess",
				dataType :"json",
				data :"{}",
				contentType :"application/json;charset=utf-8",
				traditional :true,
				success :function(data) {
					$("#mapcompletion").html(data.mapcompletion);
					$("#reducecompletion").html(data.reducecompletion);
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
					<label for="mapcompletion">map completion is:</label>
					<div id="mapcompletion"></div>
					<label for="reducecompletion">reduce completion is:</label>
					<div id="reducecompletion"></div>				
					<br/>
					<input type="button"><a href="/api/index.jsp">GO BACK</a>
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