var modal = document.querySelector("#modal");
		var modalOverlay = document.querySelector("#modal-overlay");
		var closeButton = document.querySelector("#close-button");
		var openButton = document.querySelector("#open-button");

		var messageInput = document.querySelector("#message");
		var timeInput = document.querySelector("#time");

		var messageError = document.querySelector("#messageError");
		var timeError = document.querySelector("#timeError");

		closeButton.addEventListener("click", function() {
			modal.classList.toggle("show-modal");
			modalOverlay.style.display = "none";
		});

		openButton.addEventListener("click", function() {
			messageInput.value = "";
			timeInput.value = "";
			messageError.style.display = "none";
			timeError.style.display = "none";
			modal.classList.toggle("show-modal");
			modalOverlay.style.display = "block";
		});

		function addJob() {
			var message = messageInput.value;
			var time = timeInput.value;
			if (message === null || message === "") {
				messageError.style.display = "block";
				return;
			} else {
				messageError.style.display = "none";
			}
			if (time === null || time === "" || new Date(time) < Date.now()) {
				timeError.style.display = "block";
				return;
			} else {
				timeError.style.display = "none";
			}
			var data = {
				"messageText" : message,
				"time" : time
			};
			$.ajax({
				type : "POST",
				contentType : 'application/json; charset=utf-8',
				url : "http://localhost:8080/add_job",
				data : JSON.stringify(data),
				success : function(result) {
					console.log("SUCCESS: ", data);
					location.reload(true);
				},
				error : function(e) {
					console.log("ERROR: ", e);
					closeButton.click();
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		}