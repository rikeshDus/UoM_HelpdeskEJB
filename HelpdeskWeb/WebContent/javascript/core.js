
$(document).ready(function() {
	  // Handler for .ready() called.
	$('#loading')
	.hide(10)  // hide it initially
	.ajaxStart(function() {
	    $(this).show(100);
	})
	.ajaxStop(function() {
	    $(this).hide(100);
	})
	// Handler for .ready() called.
			$("#displaySchedule").hide();
			$("#innerSportCreateEventForm").hide();
			  $(document).mousemove(function(e){
		            // $('#status').html(e.pageX +', '+ e.pageY);
		            x = e.pageX;
		            y = e.pageY;
		        	 
		        	 
		          });
			  $('#divDisplay').hide();
			  
				$('#loading')
				.hide(10)  // hide it initially
				.ajaxStart(function() {
				    $(this).show(100);
				})
				.ajaxStop(function() {
				    $(this).hide(100);
				})
	
	;
	  
	  
	$("#advanceForm").hide();
	});
	
	
	function displayTechnicalForm(){
		//display material
	}
	
	function forwardQuery(user_id){
	alert(user_id);
		$.post("ajax/queryAjax.jsp",
		{
			'query':$("#query").val(),
			'user': user_id,
			'type':'foward'
		},
		function(data,status){	
			alert("");
			$("#queryResult").html(data);
			
		});

	}//end of function forwardQuery(){
	
	function advanceQuery(){
		$("#advanceForm").show(500);
	}//end function advanceQuery()
	
	
	function getQuestion(type, user_id,answer){
		if(!validateBlank('query')){
			
		}
		else{
			document.getElementById("queryResult").innerHTML = "searching ...";
			$.post("ajax/knowledgeAjax.jsp",
			{
				'question':$("#query").val(),
				'user':user_id ,
				'type':type,
				'answer':answer
			},
			function(data,status){
					$("#queryResult").html(data);
					$("#queryResult").show();
					$("#innercontent").show();
								
			});
		}
		
		
	}//end of function getQuestion(){
	
	var currentContent="";
	var currentContentValid = false;
	function getSolution(divName,user_id){
		var div = "#"+divName;
	//	document.getElementById("queryResult").innerHTML = "searching ..";
			$.post("ajax/queryAjax.jsp",
				{
					'query':$(div).val(),
					'user':user_id ,
					'type':'normal'
				},
				function(data,status){
					//if(div.indexOf("search") == -1){
						//$("#queryResult").html(data);
					//	$("#div_content").show();
					//}else{
						
						//$("#div_content").hide();
					//	$("#queryResult").hide();
					if(!currentContentValid){
						currentContent=$("#div_content").html();
						currentContentValid = true;
					}
						$("#div_content").html("<div id=\"newcontent\"><a onclick=\"searchShow();\">close</a>"+data+"</div>");	
					//}					
				});
	}
	
	function searchShow(){
		$('#newcontent').hide();	
		$("#div_content").html(	currentContent);
		currentContentValid = false;
	}
	function getAdvanceSolution(user_id){
		
		if(!validateBlank("advanceSearch")){
			
		}//end of if(validateBlank("advanceSearch")){
		else{
		
			document.getElementById("queryResult").innerHTML = "searching ..";
			$.post("ajax/queryAjax.jsp",
				{
					'query':$("#query").val(),
					'user':user_id,
					
					'txt_wildCard':$("#txt_wildCard").val(),
					'txt_fuzzy':$("#txt_fuzzy").val(),
					'txt_proximity':$("#txt_proximity").val(),
					'txt_firstRange':$("#txt_firstRange").val(),
					'txt_secondRange':$("#txt_secondRange").val(),
					'txt_boosting':$("#txt_boosting").val(),
					'txt_excludeWords':$("#txt_excludeWords").val(),
					'txt_boolean':$("#txt_boolean").val(),
					
					'type':'advance'
				},
				function(data,status){
										
					$("#queryResult").html(data);
					
				});
		}//end of else (validateBlank("advanceSearch"))
	}
	
	/***********************/
	var x;
	var y;
/*		$(document).ready(function() {
		  // Handler for .ready() called.
			$("#displaySchedule").hide();
			$("#innerSportCreateEventForm").hide();
			  $(document).mousemove(function(e){
		            // $('#status').html(e.pageX +', '+ e.pageY);
		            x = e.pageX;
		            y = e.pageY;
		        	 
		        	 
		          });
			  $('#divDisplay').hide();
			  
				$('#loading')
				.hide(10)  // hide it initially
				.ajaxStart(function() {
				    $(this).show(100);
				})
				.ajaxStop(function() {
				    $(this).hide(100);
				})
		});*/
		
		function sportHideShow(){
			var event = $("#eventType").val();
			if(event == "sport")
			{
				$("#innerSportCreateEventForm").show().fadeIn(500);
				$("#innerSportOtherEventForm").show().fadeOut(500);
			}
			else
			{
				$("#innerSportCreateEventForm").show().fadeOut(500);
				$("#innerSportOtherEventForm").show().fadeIn(500);
			}
		
						
		}
		
		var courseArray = [];
		var courseNumberAdded = 0;
		var teamArray ="";
		//var teamParticipant = "";
		var teamParticipantCount = 0;
		
		function sportTeamsDisplay(comboUser){
			//get number of teams
			var numTeams = $("#txt_numOfTeam").val();
			var round = numTeams;
			while(round>2){
				round = round/2;
					if((round%2) == 1){
						alert("Number of team should be 2,4,8,16,32: table of 2");
						break;
					}
				
								
			}
			//initialize array
			teamArray = new Array(numTeams);
			fillTeamArray(numTeams);
			
			
			var display = "<table>";
			
			for(var i=0; i<numTeams;i++){
				display += "<tr><td> Team "+(i+1)+"</td><td><select id= 'selectTeam" +(i+1)+"'>"+comboUser+"</select></td>";
				display += "<td><button onclick=\"addParticipant('Team" +(i+1)+"')\">add</button><td></tr><tr><td colspan='3'><textarea id= 'textareaTeam" +(i+1)+"'></textarea></td></tr>";
			}//end for(var i=0; i<numTeams;i++){
			
			display += "</table>";
			
			//add disply to div
			$("#innerSportTeamsDisplay").html(display);
			
		}//end function sportTeamsDisplay(){
			
 			
		function addParticipant(team){
			var combo = "#select"+team;
			var textarea = "#textarea"+team;
			
			var participant = $(combo).val();
			var texareaValue = $(textarea).val();
			var ParticipantCount = team.charAt( team.length-1 );
			teamParticipantCount = parseInt(ParticipantCount)-1;
			$(textarea).val(texareaValue+participant );
			var teamParticipant = teamArray[teamParticipantCount];
			teamParticipant.push(participant);
			teamArray[teamParticipantCount] = teamParticipant;
			teamParticipantCount++;
		}//end function addParticipant(team){
			
		
		
		function fillTeamArray(numTeams){
			for(var i = 0;i<numTeams;i++){
				teamArray[i] = new Array();
				/* var textarea = "#textareaTeam"+(i+1);
				var text = " "+$(textarea).val();
				teamParticipant = text.split("   ");
				teamParticipant.splice(0, 1);
				alert(teamParticipant.length +" : "+teamParticipant);
				teamArray[i] = new Array(teamParticipant.length);
				for(var j=0;j<teamParticipant.length;j++){
					teamArray[i][j] = teamParticipant[j];
					alert(i + " "+ j);
				}
				teamArray[i] = teamParticipant; */
				
			}
			
			
		}
		var roundArr = [];
		
		function submitcreateEventFormDiv(){
			
			//validation
			if(!validateBlank('event')){
				alert("event");
			}//end of if(!validateBlank('event')){
			else{
				$("#createEventFormDiv").hide(1000);
				//get checkbox value
				var faculties = [];
				$("input[name='faculty']:checked").each(function(){faculties.push($(this).val());});
				$.post("ajax/eventAjax.jsp",
				{
					'type':$("#eventType").val(),
					'title':$("#title").val(), 
					'description':$("#description").val(),
					'faculty[]':  faculties,
					'course[]': courseArray,
					'startDate':$("#startDate").val(),
					'endDate':$("#endDate").val(),
					'teams':$("#txt_numOfTeam").val(),
					'teamsParticipant[]':teamArray
				},
				function(data,status){
					  
					 //extract data
					var allData = data.split("-break-");
					var form = allData[1];
					var round = allData[0]; 
					
					// display all values
					for (var i = 1; i < (round.split(" ").length-1); i++) {
						roundArr.push(round.split(" ")[i]);
					};
					
					
					//$('#confirm').hide();
					var temp =$("#displaySchedule").html();
					//if($("#eventType").val()!= "Other")
						//$("#displaySchedule").html( round+"<br>confirm Schedule</br>"+temp); 
						$("#displaySchedule").html(data).show().fadeIn(1000);
					
						$('#confirmScheduleDiv').hide();
				});
			}//end else
			
			
			
		}//end function SubmitcreateEventFormDiv(){
		
			
			
		function addCourse(){	
			courseArray[courseNumberAdded] = $("#comboCourse").val();
			
			var te = $('#courseAddedTextArea').val();
			te = te +courseArray[courseNumberAdded];
			$('#courseAddedTextArea').val(te)
			
			courseNumberAdded++;
			//$("#courseAddedTextArea").append($("#comboCourse").text());
			$("#courseAddedConfirmMessge").html("<span style='color: #123456;'> Course Successfully Added </span>").show().fadeIn(500).fadeOut(2000);
			
		}//end function addCourse(){
			
			
		function submitSchedule(user_id){		
			$.post("ajax/eventSchedule.jsp",
			{
				'type':$("#eventType").val(),
				'title':$("#title").val(), 
				'description':$("#description").val(),
			 	'date':$("#scheduleDate").val(),
				'time':$("#scheduleDaytime").val(),
				'duration':$("#scheduleDuration").val(),
				'event[]':roundArr,
				'user':user_id
			},
			function(data,status){
				$("#displaySchedule").empty();
				$("#calenderMgs").html( data+"in saving operation");
				$("#calendar").empty();
				
			});
			
		}//end function submitSchedule()
		
		/*
		*load all event whic user has created and 
		*format the event in calender format
		*give option update and delete when click on event
		*/
		function loadEvent(option){
			$("#divDisplay").hide();
			$("#createEventFormDiv").hide();
			$("#calendar").show();
			$.post("ajax/loadEventAjax.jsp",
			{
				'option':option,
				'eventId':$("#txt_hid_event_id").val(),
				'time':$('#txt_tem_time').val(),
				'date':$('#txt_tem_date').val(),
				'title':$('#txt_tem_title').val()
			},
			function(data,status){				
				$("#displaySchedule").html(data).show().fadeIn(1000);
				
			});
		
		}//end of function loadEvent(option){
	
		
/***************queryReceponce.jsp*********/
		function getMyTracking(userId,option){
			$.post("ajax/trackingNotification.jsp",
			{
				'option':option,
				'userId':userId
			},
			function(data,status){				
				$("#divAllTracking").html(data).show().fadeIn(1000);
				$("#replyQuery").hide();
			});
	
		}//end of function getMyTracking(userId){
		
/*****validation**********/
		function validateBlank(form){
			if(form == "query"){
				if($("#query").val().replace(/^\s+|\s+$/g, "").length == 0)
				{
					alert("plz fill in textbox before querying!");
					$("#query").css({"background-color": "red"});
					return false;
				}//end of if($("#query").val() == "")
				else{
					return true;
				}
			}//end of if(form == "query"){
			else if(form == "event"){
				
				//check for blank
				var blank = true;
				var event = $("#eventType").val();
				
				if(event == "sport")
				{
					//check for blank
					var teamNo = $("#txt_numOfTeam").val();
					if($("#txt_numOfTeam").val().replace(/^\s+|\s+$/g, "").length == 0){
						
						$("#txt_numOfTeam").css({"background-color": "red"});
						
						blank = false;		
					}//end of  if($("#txt_numOfTeam").val().replace(/^\s+|\s+$/g, "").length == 0){
					else if(!(teamNo == 2 || teamNo == 4 ||teamNo == 8 ||teamNo == 16 ||teamNo == 32)){
						$("#txt_numOfTeam").css({"background-color": "red"});
						blank = false; 
					}//end of else if(teamNo == 2 || teamNo == 4 ||teamNo == 8 ||teamNo == 16 ||teamNo == 32){
					for (var i=0;i<teamNo;i++)
					{ 
						var teamInput = "#textareaTeam"+(i+1);
						if($(teamInput).val().replace(/^\s+|\s+$/g, "").length == 0){
							$("#textareaTeam").css({"background-color": "red"});
							blank = false;
						}//end of if($(teamInput).val().replace(/^\s+|\s+$/g, "").length == 0){
					}//end of for (var i=0;i<teamNo;i++) 
				}//end of if(event == "sport")
				else{					
					var checkboxes = [];
					$("input[name='faculty']:checked").each(function(){checkboxes.push($(this).val());});
					if(checkboxes.length<1){
						
						blank = false;
					}
					if($("#courseAddedTextArea").val().replace(/^\s+|\s+$/g, "").length == 0)
					{
						$("#courseAddedTextArea").css({"background-color": "red"});
						blank =  false;
					}
					if($("#courseAddedTextArea").val().replace(/^\s+|\s+$/g, "").length == 0)
					{
						$("#courseAddedTextArea").css({"background-color": "red"});
						blank =  false;
					}
				}//end else (event == "sport")
				
				//check title
				if($("#title").val().replace(/^\s+|\s+$/g, "").length == 0)
				{
					$("#title").css({"background-color": "red"});
					blank =  false;
				}
				
				//check for date
				if($("#startDate").val().replace(/^\s+|\s+$/g, "").length == 0)
				{
					$("#startDate").css({"background-color": "red"});
					blank =  false;
					
				}
				else{
					//start dat should be after 2day
					var start = new Date($("#startDate").val());
					var now = new Date();
					var diff = ((start - now)/1000/60/60/24);
					if(diff < -1){
						$("#startDate").css({"background-color": "red"});
						blank =  false;
					}//end if(diff < 0){
					else{
						//check for end date
						if($("#endDate").val().replace(/^\s+|\s+$/g, "").length == 0)
						{
							$("#endDate").css({"background-color": "red"});
							blank =  false; 
						}//end of if($("#endDate").val().replace(/^\s+|\s+$/g, "").length == 0)
						else{
							//end date should be after start date
							start = new Date($("#startDate").val());
							end = new Date($("#endDate").val());
							diff = ((end - start)/1000/60/60/24);
							if(diff < -1){
								$("#endDate").css({"background-color": "red"});
								blank =  false; 
							}//end if(diff < 0){						
							
							
						}//end for else ($("#endDate").val().replace(/^\s+|\s+$/g, "").length == 0)
					}//end else
				}//end else
				
				return blank;
			}//end else if(form = "event"){
			else if(form == "advanceSearch"){
				var blank = true; 
				//check for date
				if($("#txt_wildCard").val().replace(/^\s+|\s+$/g, "").length == 0)
				{
					blank =  false;
					
				}
				
				return blank;
				
			}//end of else if(form == "advanceSearch"){
			
			
		}//end of function validateBlank(field){
		
		function validate(obj)
		{
		  if(!obj.checkValidity())
		  {
			$(obj).css({"background-color": "red"});
		    obj.focus();
		    return false;
		  }
		  else{
			$(obj).css({"background-color": "white"});
			return true;
		  }
		}
		
		function sendEmail(){
			if(validate(your_email) && validate(your_message)){
				$.post("ajax/sendMailAjax.jsp",
						{
							'name':$("#your_name").val(),
							'message':$("#your_message").val(),
							'email':$('#your_email').val()
						},
						function(data,status){				
							$("#mailConfirm").html(data).show().fadeIn(1000);
						});
				
			}//end of if(validate(your_email) && validate(your_message)){
			
		}//end of function sendEmail(){
		
		
		
		function replyShow(tracking_id,query_id,descrition,user_id){
			data = "<table><tr><td>To :</td><td> "+user_id+"</td></tr>"+
					"<tr><td>Query : </td><td>"+descrition+"</td></tr>"+
					"<tr><td>Reply :</td><td> <textarea id='replyReply'></textarea></td></tr>"+
					"<tr><td colspan=2><div style=\"float:right;\"><input type='button' value='reply' "+
					"onclick = \"replyQuery('"+user_id+"','"+query_id+"','"+tracking_id+"');\" /></div></td></tr>"+
					"</table>";
			$("#replyQuery").html(data).show().fadeIn(1000);
			$("#divAllTracking").hide(500);
		}//end function replyShow(tracking_id,query_id,descrition,user_id){
		
		
		
		
		function replyQuery(user_id,query_id,tracking_id){	
			$.post("ajax/replyQuery.jsp",
					{
						'queryId':query_id,
						'userId':user_id,
						'trackingId':tracking_id,
						'answer':$("#replyReply").val()
					},
					function(data,status){
						$("#divAllTracking").html(data).show().fadeIn(1000);
						$("#replyQuery").hide();
					});
		}// end of function replyQuery(){ 
