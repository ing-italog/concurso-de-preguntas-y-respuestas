var respuestaOk = document.getElementById("respuestaOk").textContent;
var numRandon;
var categoria = document.getElementById('categoria').value;

function randon() {
	if (categoria == 1) {
		numRandon = Math.floor(Math.random() * 5) + 6;
	} else if (categoria == 2) {
		numRandon = Math.floor(Math.random() * 5) + 11;
	} else if (categoria == 3) {
		numRandon = Math.floor(Math.random() * 5) + 16;
	} else {
		numRandon = Math.floor(Math.random() * 5) + 21;
	}

}

function sleep(ms) {
	return new Promise(
		resolve => setTimeout(resolve, ms)
	);
}

async function verificarRespuesta() {

	randon();

	for (var i = 0; i < document.form.res.length; i++) {

		if (document.form.res[i].checked) {
			seleccion = document.form.res[i].value;

			//Verificar la respuesta
			if (seleccion == respuestaOk) {

				if (categoria == 5) {
					time_seconds = 15;
					document.getElementById("count-down-timer").style.display = "none";
					document.getElementById("mensaje").innerHTML = "FELICIDADES GANASTE TODAS LAS RONDAS";
					await sleep(2500);
					document.getElementById('endGame').click();

				} else {
					time_seconds = 15;
					categoria++;
					document.getElementById("count-down-timer").style.display = "none";
					document.getElementById("mensaje").innerHTML = "CORRECTO ¡MUY BIEN! &#9996; &#9996;";
					await sleep(2000);
					document.getElementById("mensaje").innerHTML = "VAMOS POR MAS";
					await sleep(800);
					location.href = "http://localhost:8080/gameStart" + categoria + "/" + numRandon;
				}

			} else {
				time_seconds = 15;
				document.getElementById("count-down-timer").style.display = "none";
				document.getElementById("mensaje").innerHTML = "INCORRECTO &#128561; &#128561;";
				await sleep(2000);
				document.getElementById("mensaje").innerHTML = "PERDISTE EL ACUMULADO";
				await sleep(1000);
				location.href = "http://localhost:8080";
			}

		}
	}
}

//Contador
function paddedFormat(num) {
	return num < 10 ? "0" + num : num;
}

function startCountDown(duration, element) {

	let secondsRemaining = duration;
	let min = 0;
	let sec = 0;

	let countInterval = setInterval(function() {

		min = parseInt(secondsRemaining / 60);
		sec = parseInt(secondsRemaining % 60);

		element.textContent = `${paddedFormat(min)}:${paddedFormat(sec)}`;

		secondsRemaining = secondsRemaining - 1;
		if (secondsRemaining < 0) { clearInterval(countInterval) };

		if (sec == 8) {
			document.getElementById("mensaje").innerHTML = "Se te acaba el Tiempo &#9203;";

		}
		if (sec == 3) {
			document.getElementById("mensaje").innerHTML = "Perdiste";
		}
		if (sec == 1) {
			document.getElementById("mensaje").innerHTML = "ADIOS";
		}
		if (sec == 0) {
			location.href = "http://localhost:8080/";
		}

	}, 1000);
}

window.onload = function() {
	let time_minutes = 0; // Value in minutes
	let time_seconds = 15; // Value in seconds

	let duration = time_minutes * 60 + time_seconds;

	element = document.querySelector('#count-down-timer');
	element.textContent = `${paddedFormat(time_minutes)}:${paddedFormat(time_seconds)}`;

	startCountDown(--duration, element);
};

