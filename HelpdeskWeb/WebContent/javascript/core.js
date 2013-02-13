
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
	
	
	function getQuestion(type, user_id){
		document.getElementById("queryResult").innerHTML = "searching ...";
		$.post("ajax/knowledgeAjax.jsp",
				{
					'question':$("#query").val(),
					'user':user_id ,
					'type':type
				},
				function(data,status){
						$("#queryResult").html(data);
						$("#queryResult").show();
						$("#innercontent").show();
									
				});
		
		
		
	}//end of function getQuestion(){
	
	
	function getSolution(divName,user_id){
		var div = "#"+divName;
		document.getElementById("queryResult").innerHTML = "searching ..";
			$.post("ajax/queryAjax.jsp",
				{
					'query':$(div).val(),
					'user':user_id ,
					'type':'normal'
				},
				function(data,status){
					if(div.indexOf("search") == -1){
						$("#queryResult").html(data);
						$("#innercontent").show();
					}else{
						
						$("#innercontent").hide();
						$("#queryResult").hide();
						$("#content").html($("#content").html()+"<div id=\"newcontent\" style=\"position: absolute;top:320px;\"><a onclick=\"searchShow();\">close</a>"+data+"</div>");	
					}
					
					
				});
	}
	
	function searchShow(){
		$('#newcontent').hide();	
		$('#innercontent').show();	
	}
	function getAdvanceSolution(user_id){
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
				$("#calenderMgs").html( data+"in saving operation <a href='homepage.jsp'>back</a>");
				
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
		
