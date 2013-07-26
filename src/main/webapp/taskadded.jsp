<!DOCTYPE html>
<html lang="en">
<head>
	<title>BWA INDEXING </title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript">	
		

		$(document).ready(function() {
			$.ajax({
				url :"rest/command/getjobid",
				dataType :"text",
				data :"",
				textField :"jobid",
				valueField :"jobid",
				traditional :true,
				success :function(data) {
					$("#jobid").html(data);
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
					
					<label for="jobid">Your jobId is:</label>
					<div id="jobid"></div>
					<label for="reminder">. Please keep it and use it to check your job progress.</label>					
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