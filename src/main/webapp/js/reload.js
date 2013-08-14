function reload(){
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

}